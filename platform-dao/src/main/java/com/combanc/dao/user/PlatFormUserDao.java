package com.combanc.dao.user;

import com.combanc.entity.user.PlatFormUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PlatFormUserDao {

    Integer addUser(PlatFormUser platFormUser);

    Integer updateUser(PlatFormUser platFormUser);

    Integer delUser(PlatFormUser platFormUser);

    List<PlatFormUser> userList(PlatFormUser platFormUser);

    Integer userListCount(PlatFormUser platFormUser);

    Integer changePwdByAccount(@Param(value = "password") String password, @Param(value = "passsalt") String passsalt, @Param(value = "accountid") String accountid);
}
