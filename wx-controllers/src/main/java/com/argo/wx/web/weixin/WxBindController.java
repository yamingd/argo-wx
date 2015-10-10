package com.argo.wx.web.weixin;

import com.argo.security.UserIdentity;
import com.argo.security.exception.PasswordInvalidException;
import com.argo.security.service.AuthorizationService;
import com.argo.service.ServiceException;
import com.argo.web.JsonResponse;
import com.argo.wx.service.WxApiClient;
import com.argo.wx.service.WxMenuService;
import com.argo.wx.service.WxUserService;
import com.argo.wx.token.WxAccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * http://jingyan.baidu.com/article/48206aeae7aa24216ad6b3f3.html
 * Created by $User on 2014-12-05 09:32.
 */

@Controller
@RequestMapping("/wx")
public class WxBindController extends WxBaseController {

    @Override
    public boolean needLogin() {
        return false;
    }

    @Autowired
    private WxMenuService wxMenuService;

    @Autowired
    private WxApiClient wxApiClient;

    @Autowired
    private WxUserService wxUserService;

    @Autowired
    private AuthorizationService authorizationService;

    @RequestMapping(value="users", method= RequestMethod.POST)
    @ResponseBody
    public JsonResponse bind(ModelAndView modelAndView,
                             @Valid WxBindForm form, BindingResult result,
                             HttpServletRequest request, HttpServletResponse response,
                             JsonResponse actResponse){

        if (result.hasErrors()){
            this.wrapError(result, actResponse);
            return actResponse;
        }

        UserIdentity item = null;
        try {
            item = authorizationService.verifyUserPassword(form.getName(), form.getPasswd());
        } catch (PasswordInvalidException e) {
            logger.error(e.getMessage(), e);
            actResponse.setCode(e.getStatusCode());
            return actResponse;
        }

        WxAccessToken wxAccessToken = new WxAccessToken();
        wxAccessToken.setOpenId(form.getOpenid());
        wxAccessToken.setToken(form.getOpentoken());

        //写登录Cookie
        this.rememberUser(request, response, item.getIdentityId());
        request.setAttribute("currentUser", item);

        try {
            postBind(item, wxAccessToken);
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
            actResponse.setCode(e.getErrorCode());
        }

        return actResponse;
    }

    private void postBind(UserIdentity item, WxAccessToken wxAccessToken) throws ServiceException {
        boolean flag = this.wxUserService.bindUser(item, wxAccessToken);
        logger.info("WxBind Result={}, openid={}, token={}", flag, wxAccessToken.getOpenId(), wxAccessToken.getToken());
    }

}
