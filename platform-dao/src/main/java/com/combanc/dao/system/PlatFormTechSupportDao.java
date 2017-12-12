package com.combanc.dao.system;

import com.combanc.entity.system.PlatFormTechSupport;

import java.util.List;

public interface PlatFormTechSupportDao {

    Integer addQuestion(PlatFormTechSupport platFormTechSupport);

    Integer editQuestion(PlatFormTechSupport platFormTechSupport);

    Integer delQuestion(String id);

    List<PlatFormTechSupport> questionList(PlatFormTechSupport platFormTechSupport);

    Integer questionListCount(PlatFormTechSupport platFormTechSupport);

}
