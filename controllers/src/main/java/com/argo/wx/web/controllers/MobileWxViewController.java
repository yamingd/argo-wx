package com.argo.wx.web.controllers;

import com.argo.core.base.BaseUser;
import com.argo.core.configuration.SiteConfig;
import com.argo.core.exception.UserNotAuthorizationException;
import com.argo.core.security.AuthorizationService;
import com.argo.wx.WxAccessToken;
import com.argo.wx.WxApiClient;
import com.argo.wx.service.WxMenuService;
import com.argo.wx.service.WxUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * http://jingyan.baidu.com/article/48206aeae7aa24216ad6b3f3.html
 * Created by $User on 2014-12-05 09:32.
 */

@Controller
@RequestMapping("/m/wx")
public class MobileWxViewController extends MobileWxBaseController {

    @Autowired
    private AuthorizationService<BaseUser> authorizationService;

    @Override
    public boolean needLogin() {
        return false;
    }

    @Autowired
    private WxMenuService wxmenuService;

    @Autowired
    private WxApiClient wxApiClient;

    @Autowired
    private SiteConfig siteConfig;

    @Autowired
    private WxUserService wxUserService;


    @RequestMapping(value="view")
    public ModelAndView view(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response){
        //https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxcd983566d32442bc&redirect_uri=http://192.168.1.1/weixin/weixin.do?action=viewtest&response_type=code&scope=snsapi_base&state=1#wechat_redirect
        String code = request.getParameter("code");
        String to = request.getParameter("_to_");
        if (!StringUtils.isBlank(code)){
            WxAccessToken wxAccessToken = wxApiClient.getOAuth2AccessToken(code);
            logger.info("code={}, to={}, accessToken={}", code, to, wxAccessToken);
            if (wxAccessToken != null){
                BaseUser user = wxUserService.findByWxId(wxAccessToken.getOpenId());
                if (user != null){
                    this.rememberUser(request, response, user.getUid());
                    modelAndView.addObject("currentUser", user);
                    if (to != null) {
                        modelAndView.setView(new RedirectView(to));
                    }else{
                        modelAndView.setViewName("/wx/index");
                    }
                }else{
                    try {
                        BaseUser user0 = this.getCurrentUser();
                        if (!user0.isAnonymous()){
                            wxUserService.bindUser(user0, wxAccessToken);
                            if (to != null) {
                                modelAndView.setView(new RedirectView(to));
                            }else{
                                modelAndView.setViewName("/wx/index");
                            }
                        }
                    } catch (UserNotAuthorizationException e) {

                    }
                    modelAndView.addObject("wxAccessToken", wxAccessToken);
                    modelAndView.addObject("to", to);
                    modelAndView.setViewName("/wx/bind");
                }
            }else {
                modelAndView.setViewName("/wx/index");
            }
        }else{
            if (to != null) {
                modelAndView.setView(new RedirectView(to));
            }else{
                modelAndView.setViewName("/wx/index");
            }
        }
        return modelAndView;
    }

}