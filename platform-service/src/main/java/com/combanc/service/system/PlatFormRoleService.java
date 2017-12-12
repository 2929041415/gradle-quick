package com.combanc.service.system;

import com.combanc.entity.common.BaseResultDto;
import com.combanc.entity.system.PlatFormRole;

public interface PlatFormRoleService {

    BaseResultDto addRole(PlatFormRole platFormRole, String menuids);

    BaseResultDto editRole(PlatFormRole platFormRole, String menuids);

    BaseResultDto delRole(String id);

    BaseResultDto listRole(PlatFormRole platFormRole);

    Integer listRoleCount(PlatFormRole platFormRole);

    BaseResultDto addResourceToRole(String roleid, String menuids);

    BaseResultDto getResourceByRole(String roleid);
}
