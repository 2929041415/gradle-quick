package com.combanc.service.system;

import com.combanc.entity.common.BaseResultDto;
import com.combanc.entity.system.PlatFormTechSupport;

public interface PlatFormTechSupportService {

    BaseResultDto addQuestion(PlatFormTechSupport platFormTechSupport);

    BaseResultDto editQuestion(PlatFormTechSupport platFormTechSupport);

    BaseResultDto delQuestion(String id);

    BaseResultDto questionList(PlatFormTechSupport platFormTechSupport);
}
