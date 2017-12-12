package com.combanc.service.user;

import com.combanc.entity.common.BaseResultDto;

import javax.servlet.http.HttpServletRequest;

public interface PlatFormSysUserLoginService {

    BaseResultDto sysUserLogin(String account, String password, String handleip);

    BaseResultDto verifyUserToken(String token, String account);

    BaseResultDto mobileLogin(String account, String password, HttpServletRequest request);

    BaseResultDto mobieUserByWeiXin(String weixin, HttpServletRequest request);

    BaseResultDto mobieUserByQQ(String qq, HttpServletRequest request);

    BaseResultDto mobieUserByWeiBo(String weibo, HttpServletRequest request);

    BaseResultDto telphoneLogin(String telphone, String captcha);

    BaseResultDto sendSms(String telphone);

    BaseResultDto userLogout(HttpServletRequest request);

}
