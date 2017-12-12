package com.combanc.dao.user;

import com.combanc.entity.user.PlatFormSendMail;
import com.combanc.entity.user.PlatFormUser;
import org.apache.ibatis.annotations.Param;

public interface PlatFormUserRegisterDao {

    PlatFormUser getUserByUserId(@Param(value = "userid") String userid, @Param(value = "usertype")String usertype);

    Integer addSendMail(PlatFormSendMail sendMail);
}
