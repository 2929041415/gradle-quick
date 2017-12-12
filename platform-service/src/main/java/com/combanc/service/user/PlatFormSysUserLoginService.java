package com.combanc.service.user;

import com.combanc.entity.common.BaseResultDto;

import javax.servlet.http.HttpServletRequest;

public interface PlatFormSysUserLoginService {

    BaseResultDto sysUserLogin(String account, String password, String handleip);

    BaseResultDto verifyUserToken(String token, String account);

    BaseResultDto mobileLogin(String account, String password, String handleip);

    BaseResultDto mobieUserByWeiXin(String weixin, String handleip);

    BaseResultDto mobieUserByQQ(String qq, String handleip);

    BaseResultDto mobieUserByWeiBo(String weibo, String handleip);

    BaseResultDto telphoneLogin(String telphone, String captcha);

    BaseResultDto sendSms(String telphone);

    BaseResultDto userLogout(String token);

}
