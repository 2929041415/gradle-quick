package com.combanc.service.user;

import com.combanc.entity.common.BaseResultDto;

public interface PlatFormMobileService {

    BaseResultDto updateUserPassword(String account, String passwordOld, String passwordNew);

    BaseResultDto updateTzjyyUserPassword(String account, String passwordOld, String passwordNew);

    BaseResultDto checkUserPassword(String account, String passwordOld, String passwordNew);

    BaseResultDto checkTokenResult(String token);
}
