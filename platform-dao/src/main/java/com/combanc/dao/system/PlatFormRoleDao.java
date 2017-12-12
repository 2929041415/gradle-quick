package com.combanc.dao.system;

import com.combanc.entity.system.PlatFormRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PlatFormRoleDao {

    Integer addRole(PlatFormRole platFormRole);

    Integer editRole(PlatFormRole platFormRole);

    Integer delRole(@Param(value = "id") String id);

    List<PlatFormRole> listRole(PlatFormRole platFormRole);

    Integer listRoleCount(PlatFormRole platFormRole);
}
