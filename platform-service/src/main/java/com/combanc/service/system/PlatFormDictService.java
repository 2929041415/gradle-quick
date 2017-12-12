package com.combanc.service.system;

import com.combanc.entity.common.BaseResultDto;
import com.combanc.entity.system.PlatFormDict;

/**
 * 系统字典service
 *
 * @author  leijie
 */
public interface PlatFormDictService {


    BaseResultDto addDict(PlatFormDict platFormDict);

    BaseResultDto editDict(PlatFormDict platFormDict);

    BaseResultDto delDict(String id);

    Integer dictListCount(PlatFormDict platFormDict);

    BaseResultDto dictList(PlatFormDict platFormDict);


    BaseResultDto dictValueList(PlatFormDict platFormDict);
}
