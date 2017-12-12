package com.combanc.service.webapp;

import com.combanc.entity.common.BaseResultDto;
import com.combanc.entity.webapp.PlatFormAppAccessCount;

public interface PlatFormAppAccessCountService {

    BaseResultDto updateAccessCount(PlatFormAppAccessCount platFormAppAccessCount,String appid);

    BaseResultDto updateCollectCount(String appid);

    void updateCollectCountCut(String appid);

    Integer updateUseCount(String appid, Long usecount);
}
