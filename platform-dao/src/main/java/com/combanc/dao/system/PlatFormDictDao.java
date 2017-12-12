package com.combanc.dao.system;

import com.combanc.entity.system.PlatFormDict;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统字典daol
 *
 * @author leijie
 */
public interface PlatFormDictDao {

    Integer addDict(PlatFormDict platFormDict);

    Integer editDict(PlatFormDict platFormDict);

    Integer delDict(@Param(value = "id") String id);

    Integer dictListCount(PlatFormDict platFormDict);

    List<PlatFormDict> dictList(PlatFormDict platFormDict);

    Integer dictValueListCount(PlatFormDict platFormDict);

    List<PlatFormDict> dictValueList(PlatFormDict platFormDict);

    int checkedDictCode(PlatFormDict platFormDict);
}
