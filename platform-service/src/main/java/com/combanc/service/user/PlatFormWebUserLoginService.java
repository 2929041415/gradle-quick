package com.combanc.service.user;

import com.combanc.entity.common.BaseResultDto;

import javax.servlet.http.HttpServletRequest;

public interface PlatFormWebUserLoginService {

    BaseResultDto webUserLogin(String account, String password, String handleip);

    BaseResultDto verifyUserToken(String token, String account);

    BaseResultDto webUserLogout(HttpServletRequest request);

    BaseResultDto isWebUser(String account);

    BaseResultDto webSendEmail(String account,String validateCode, HttpServletRequest request);

    BaseResultDto webUpdatePassword(String account,String passwordOld, String passwordNew, String ticket);
}
