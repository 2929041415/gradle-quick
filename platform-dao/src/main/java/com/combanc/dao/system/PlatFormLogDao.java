package com.combanc.dao.system;

import com.combanc.entity.system.PlatFormExceptionLog;
import com.combanc.entity.system.PlatFormNormalLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PlatFormLogDao {
    Integer insertExceptionLog(PlatFormExceptionLog exceptionLog);

    Integer inserNormalLog(PlatFormNormalLog normalLog);

    Integer querynormalLogCount(PlatFormNormalLog normalLog);

    List<PlatFormNormalLog> querynormalLog(PlatFormNormalLog normalLog);

    Integer queryexceptionCount(PlatFormExceptionLog exceptionLog);

    List<PlatFormExceptionLog> queryexceptionLog(PlatFormExceptionLog exceptionLog);

    PlatFormNormalLog queryIdByAccount(@Param(value = "account") String account);
}
