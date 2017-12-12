package com.combanc.service.user;

import com.combanc.entity.common.BaseResultDto;
import com.combanc.entity.user.PlatFormUser;

public interface PlatFormUserService {

    BaseResultDto addSysUser(PlatFormUser platFormUser, String roleids);

    BaseResultDto updateSysUser(PlatFormUser platFormUser, String roleids);

    BaseResultDto delSysUser(PlatFormUser platFormUser);

    BaseResultDto userList(PlatFormUser platFormUser);

    Integer userListCount(PlatFormUser platFormUser);

    boolean checkUserInSystem(String account, String email);

    BaseResultDto getRoleByUser(String userid);

    BaseResultDto resetPassWordByAccount(String accountid);

    BaseResultDto getResourcesByUser(String userid);

    BaseResultDto updateUserPassword(String account, String passwordOld, String passwordNew);

    boolean checkUserInSystemStatus(String s, String value);
}
