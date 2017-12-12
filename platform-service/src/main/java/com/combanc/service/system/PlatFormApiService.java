package com.combanc.service.system;

import com.combanc.entity.api.PlatFormApiType;
import com.combanc.entity.common.BaseResultDto;

public interface PlatFormApiService {
    BaseResultDto addApi(PlatFormApiType apiType);
    BaseResultDto editApi(PlatFormApiType apiType);
    BaseResultDto deleteApi(String id);
    BaseResultDto queryListApi(PlatFormApiType apiType);
}
