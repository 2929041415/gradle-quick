package com.combanc.service.home.impl;

import com.combanc.dao.system.PlatFormApiManagerDao;
import com.combanc.dao.user.PlatFormUserDao;
import com.combanc.dao.webapp.PlatFormWebAppDao;
import com.combanc.entity.api.PlatFormApi;
import com.combanc.entity.common.BaseResultDto;
import com.combanc.entity.common.CommonEnum;
import com.combanc.entity.common.ResultMessage;
import com.combanc.entity.user.PlatFormUser;
import com.combanc.entity.webapp.PlatFormWebApp;
import com.combanc.service.home.SystemHomeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service(value = "systemHomeService")
public class SystemHomeServiceImpl implements SystemHomeService {

    @Resource
    private PlatFormApiManagerDao platFormApiManagerDao;

    @Resource
    private PlatFormUserDao platFormUserDao;

    @Resource
    private PlatFormWebAppDao platFormWebAppDao;

    /**
     * 应用总数
     *
     * @return
     */
    @Override
    public BaseResultDto appTotal() {
        PlatFormWebApp platFormWebApp = new PlatFormWebApp();
        BaseResultDto baseResultDto = new BaseResultDto();
        baseResultDto.setCode(ResultMessage.SUCCESS_CODE);
        baseResultDto.setData(platFormWebAppDao.webAppListCount(platFormWebApp));
        return baseResultDto;
    }

    /**
     * 注册厂商总数
     *
     * @return
     */
    @Override
    public BaseResultDto userTotal() {
        PlatFormUser platFormUser = new PlatFormUser();
        platFormUser.setUsertype(CommonEnum.USER_TYPE.厂商用户.getValue());
        BaseResultDto baseResultDto = new BaseResultDto();
        baseResultDto.setCode(ResultMessage.SUCCESS_CODE);
        baseResultDto.setData(platFormUserDao.userListCount(platFormUser));
        return baseResultDto;
    }

    /**
     * 接口注册总数
     *
     * @return
     */
    @Override
    public BaseResultDto apiTotal() {
        PlatFormApi platFormApi = new PlatFormApi();
        BaseResultDto baseResultDto = new BaseResultDto();
        baseResultDto.setCode(ResultMessage.SUCCESS_CODE);
        baseResultDto.setData(platFormApiManagerDao.queryListCount(platFormApi));
        return baseResultDto;
    }
}
