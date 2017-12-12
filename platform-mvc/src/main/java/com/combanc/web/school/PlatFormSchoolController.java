package com.combanc.web.school;

import com.combanc.entity.common.BaseResultDto;
import com.combanc.entity.school.PlatFormSchool;
import com.combanc.service.school.PlatFormSchoolService;
import com.combanc.service.tokenfilter.AccessToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/platformschool")
public class PlatFormSchoolController {

    @Resource
    private PlatFormSchoolService platFormSchoolService;


    @AccessToken
    @RequestMapping(method = RequestMethod.POST,value = "/schoolList", produces = "application/json;charset=utf-8")
    public BaseResultDto schoolList(PlatFormSchool platFormSchool){
        return platFormSchoolService.schoolList(platFormSchool);
    }

}
