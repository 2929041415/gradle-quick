package com.combanc.service.system;

import com.combanc.entity.common.BaseResultDto;

public interface PlatFormSendSmsService {

    BaseResultDto sendSms(String telphone);

    BaseResultDto checKSms(String telphone, String captcha);
}
