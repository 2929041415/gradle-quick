package com.combanc.dao.system;

import com.combanc.entity.api.PlatFormApiType;
import com.combanc.entity.common.TreeDto;
import com.combanc.entity.webapp.PlatFormWebApp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 接口类型dao
 *
 * @author qinXK
 */
public interface PlatFormApiDao {
    Integer addApi(PlatFormApiType apiType);

    Integer editApi(PlatFormApiType apiType);

    Integer deleteApi(@Param(value = "id") String id);

    Integer queryListCount(PlatFormApiType apiType);

    List<PlatFormApiType> queryListApi(PlatFormApiType apiType);

    List<PlatFormApiType> queryApiTree(@Param(value = "webappid") String webappid);
}
