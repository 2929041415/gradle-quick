package com.combanc.service.school.impl;

import com.combanc.dao.school.PlatFormSchoolDao;
import com.combanc.entity.common.BaseResultDto;
import com.combanc.entity.common.ResultMessage;
import com.combanc.entity.school.PlatFormSchool;
import com.combanc.service.school.PlatFormSchoolService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service(value = "platFormSchoolService")
public class PlatFormSchoolServiceImpl implements PlatFormSchoolService {

    @Resource
    private PlatFormSchoolDao platFormSchoolDao;

    /**
     * 查询学校列表
     *
     * @param platFormSchool
     * @return
     */
    @Override
    public BaseResultDto schoolList(PlatFormSchool platFormSchool) {
        BaseResultDto baseResultDto = new BaseResultDto();
        baseResultDto.setCode(ResultMessage.SUCCESS_CODE);
        baseResultDto.setData(platFormSchoolDao.schoolList(platFormSchool));
        return baseResultDto;
    }
}
