package com.combanc.service.system;

import com.combanc.entity.common.BaseResultDto;
import com.combanc.entity.system.PlatFormExceptionLog;
import com.combanc.entity.system.PlatFormNormalLog;

public interface PlatFormLogService {
    BaseResultDto queryexceptionLog(PlatFormExceptionLog exceptionLog);

    BaseResultDto querynormalLog(PlatFormNormalLog normalLog);

    BaseResultDto insertNormalLog(PlatFormNormalLog normalLog);

    BaseResultDto insertExceptionLog(PlatFormExceptionLog exceptionLog);

    BaseResultDto queryIdByAccount(String account);
}
