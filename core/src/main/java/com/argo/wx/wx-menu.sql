CREATE TABLE `wx_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '记录id',
  `title` varchar(255) NOT NULL COMMENT '菜单名称',
  `parentId` int(11) NOT NULL DEFAULT '0' COMMENT '上级菜单名称',
  `orderNo` smallint(6) NOT NULL DEFAULT '100' COMMENT '显示顺序',
  `codeName` varchar(255) DEFAULT NULL COMMENT '英文代号',
  `pageUrl` varchar(255) DEFAULT NULL COMMENT '跳转到页面路径',
  `createTime` datetime DEFAULT NULL,
  `updateTime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `statusId` smallint(2) NOT NULL DEFAULT '0' COMMENT '状态(0:正常,1:已发布,9:删除)',
  `wxSync` tinyint(1) NOT NULL DEFAULT '0' COMMENT '同步到微信(1:已同步,0:未同步)',
  `wxBind` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否需要绑定微信帐号(1/0)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ix_wx_menu_codeName` (`codeName`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='微信菜单表';


CREATE TABLE `wx_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '记录id',
  `openId` varchar(100) DEFAULT NULL COMMENT 'wx用户id',
  `openToken` varchar(255) DEFAULT NULL COMMENT 'wx用户Token',
  `createTime` datetime DEFAULT NULL COMMENT '记录创建日期',
  `bindTime` datetime DEFAULT NULL COMMENT '绑定时间',
  `userId` bigint(20) DEFAULT NULL COMMENT '业务用户id',
  `lastAccessTime` datetime DEFAULT NULL COMMENT '最后访问时间',
  `statusId` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态(0:创建,1:绑定,2:解绑)',
  `rmBindTime` datetime DEFAULT NULL COMMENT '解绑时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信用户记录表';