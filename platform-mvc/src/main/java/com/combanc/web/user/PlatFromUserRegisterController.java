package com.combanc.web.user;

import com.combanc.entity.common.BaseResultDto;
import com.combanc.entity.user.PlatFormUser;
import com.combanc.service.user.PlatFormUserRegisterService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/platformUserRegister")
public class PlatFromUserRegisterController {
    @Resource
    private PlatFormUserRegisterService platFormUserRegisterService;


    /**
     * 系统注册开发者
     *
     * @param platFormUser
     * @param idcardfile
     * @param leaderidcardfile
     * @param companycardfile
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/addRegisterUser", produces = "application/json;charset=utf-8")
    public BaseResultDto addRegisterUser(PlatFormUser platFormUser,
                                         @RequestParam(value = "idcardfile", required = false) CommonsMultipartFile idcardfile,
                                         @RequestParam(value = "leaderidcardfile", required = false) CommonsMultipartFile leaderidcardfile,
                                         @RequestParam(value = "companycardfile", required = false) CommonsMultipartFile companycardfile) {

        return platFormUserRegisterService.addUser(platFormUser, idcardfile, leaderidcardfile, companycardfile);
    }

    /**
     * 判断该账号是否已经注册过
     *
     * @param account
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/isRegisterUser", produces = "application/json;charset=utf-8")
    public BaseResultDto isRegisterUser(@RequestParam(value = "account", required = false) String account, @RequestParam(value = "email", required = false) String email) {
        return platFormUserRegisterService.isRegisterUser(account, email);
    }

    /**
     * 查询开发者列表
     *
     * @param platFormUser
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/registerUserList", produces = "application/json;charset=utf-8")
    public BaseResultDto registerUserList(PlatFormUser platFormUser) {
        return platFormUserRegisterService.listUser(platFormUser);

    }

    /**
     * 查询开发者详情
     *
     * @param platFormUser
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/registerDetailUser", produces = "application/json;charset=utf-8")
    public BaseResultDto registerDetailUser(PlatFormUser platFormUser) {
        return platFormUserRegisterService.detailUser(platFormUser);

    }

    /**
     * 修改开发者详情
     *
     * @param platFormUser
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/editRegisterUser", produces = "application/json;charset=utf-8")
    public BaseResultDto editRegisterUser(PlatFormUser platFormUser,
                                          @RequestParam(value = "idcardfile", required = false) CommonsMultipartFile idcardfile,
                                          @RequestParam(value = "leaderidcardfile", required = false) CommonsMultipartFile leaderidcardfile,
                                          @RequestParam(value = "companycardfile", required = false) CommonsMultipartFile companycardfile) {
        return platFormUserRegisterService.editUser(platFormUser, idcardfile, leaderidcardfile, companycardfile);
    }

}
