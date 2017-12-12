package com.combanc.web.system;

import com.combanc.entity.common.BaseResultDto;
import com.combanc.entity.system.PlatFormExceptionLog;
import com.combanc.entity.system.PlatFormNormalLog;
import com.combanc.service.system.PlatFormLogService;
import com.combanc.service.tokenfilter.AccessToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/platformlog")
public class PlatFormLogController {
    @Resource
    private PlatFormLogService platFormLogService;

    /**
     * 查询操作日志
     * @param normalLog
     * @return
     */
    @RequestMapping(value = "/querynormalLog",produces = "application/json;charset=utf-8")
    @AccessToken
    public BaseResultDto querynormalLog(PlatFormNormalLog normalLog){
        return platFormLogService.querynormalLog(normalLog);
    }

    /**
     * 查询异常日志
     * @param exceptionLog
     * @return
     */
    @RequestMapping(value = "/queryexceptionLog",produces = "application/json;charset=utf-8")
    public BaseResultDto queryexceptionLog(PlatFormExceptionLog exceptionLog){
        return platFormLogService.queryexceptionLog(exceptionLog);
    }
}
