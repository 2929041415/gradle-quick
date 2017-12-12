package com.combanc.service.mobileplatform.impl;

import com.combanc.dao.mobileplatform.PlatFormCommonDao;
import com.combanc.entity.common.CommonEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service(value = "commonService")
public class CommonServiceImpl {
    @Resource
    private PlatFormCommonDao platFormCommonDao;
    @Value("${checked.image.path}")
    private String imageCheckPath;

    /**
     * 判断用户角色
     *
     * @param accountId
     * @return
     */
    public String checkUserRole(String accountId, String requestSource) {
        String userRole = platFormCommonDao.queryUserRole(accountId, requestSource);
        //校管理角色
        if (null == userRole || "".equals(userRole)) {
            return CommonEnum.ROLE_CODE.学生.getValue();
        } else if (userRole.contains(CommonEnum.ROLE_CODE.超级管理员.getValue())) {
            return CommonEnum.ROLE_CODE.超级管理员.getValue();
        } else if (userRole.contains(CommonEnum.ROLE_CODE.区管理员.getValue())) {
            return CommonEnum.ROLE_CODE.区管理员.getValue();
        } else if (userRole.contains(CommonEnum.ROLE_CODE.校管理员.getValue())) {
            return CommonEnum.ROLE_CODE.校管理员.getValue();
        } else {
            return CommonEnum.ROLE_CODE.学生.getValue();
        }
    }

    /**
     * 用户学校标
     *
     * @param accountId
     * @return
     */
    public String getSchoolId(String accountId) {
        return platFormCommonDao.userSchoolId(accountId);
    }

    public String checkImage() {
        return imageCheckPath;
    }
}
