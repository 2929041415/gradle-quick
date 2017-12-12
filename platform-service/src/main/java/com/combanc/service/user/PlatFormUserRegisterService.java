package com.combanc.service.user;

import com.combanc.entity.common.BaseResultDto;
import com.combanc.entity.user.PlatFormSendMail;
import com.combanc.entity.user.PlatFormUser;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface PlatFormUserRegisterService {
    BaseResultDto addUser(PlatFormUser platFormUser, CommonsMultipartFile idcardfile, CommonsMultipartFile leaderidcardfile, CommonsMultipartFile companycardfile);

    BaseResultDto editUser(PlatFormUser platFormUser, CommonsMultipartFile idcardfile, CommonsMultipartFile leaderidcardfile, CommonsMultipartFile companycardfile);

    BaseResultDto listUser(PlatFormUser platFormUser);

    BaseResultDto detailUser(PlatFormUser platFormUser);

    BaseResultDto updatePassWord(String userid, String passwordOld, String passwordNew);

    BaseResultDto addSendMail(PlatFormSendMail sendMail);

    BaseResultDto registerStepOne(PlatFormUser platFormUser, HttpServletRequest request, String validateCode);

    BaseResultDto registerStepTwo(HttpServletRequest request);

    BaseResultDto registerSendMail(PlatFormUser platFormUser, HttpServletRequest request);

    BaseResultDto isRegisterSendEmail(PlatFormUser platFormUser);

    BaseResultDto isVerifyUser(String email);

    BaseResultDto isRegisterUser(String account, String email);
}
