package com.combanc.service.system;

import com.combanc.entity.api.PlatFormApi;
import com.combanc.entity.common.BaseResultDto;

public interface PlatFormApiManagerService {

    BaseResultDto addApi(PlatFormApi platFormApi);

    BaseResultDto editApi(PlatFormApi platFormApi);

    BaseResultDto deleteApi(String id);

    BaseResultDto queryListApi(PlatFormApi platFormApi);

    BaseResultDto statisticsApi();

    BaseResultDto apiTree(PlatFormApi platFormApi);
}
