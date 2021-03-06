package com.combanc.web.user;

import com.combanc.entity.common.BaseResultDto;
import com.combanc.service.user.PlatFormSysUserLoginService;
import com.combanc.utils.common.CommonUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/platformSysUserLogin")
public class PlatFormSysUserLoginController {

    @Resource
    private PlatFormSysUserLoginService platFormSysUserLoginService;


    @RequestMapping(method = RequestMethod.POST,value = "/sysUserLogin", produces = "application/json;charset=utf-8")
    public BaseResultDto sysUserLogin(String account, String password, HttpServletRequest request) {
        String handleip = CommonUtils.getRemoteAddress(request);
        return platFormSysUserLoginService.sysUserLogin(account, password,handleip);
    }


    @RequestMapping(method = RequestMethod.POST,value = "/verifyUserToken", produces = "application/json;charset=utf-8")
    public BaseResultDto verifyUserToken(String token, String accountid, HttpServletRequest request) {
        return platFormSysUserLoginService.verifyUserToken(token, accountid);
    }

}
