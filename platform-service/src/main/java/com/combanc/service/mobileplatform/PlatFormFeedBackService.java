package com.combanc.service.mobileplatform;

import com.combanc.entity.common.BaseResultDto;
import com.combanc.entity.mobileplatform.FeedBackType;
import com.combanc.entity.mobileplatform.Feedback;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface PlatFormFeedBackService {
    //意见类型
    BaseResultDto feedBackType(FeedBackType feedBackType);
    BaseResultDto feedBackTypeList(FeedBackType feedBackType);
    //意见反馈
    BaseResultDto addFeedBack(Feedback feedBack, CommonsMultipartFile[] images);
    BaseResultDto feedBackList(Feedback feedBack, HttpServletRequest request);
    BaseResultDto deleteFeedBack(String ids);
    BaseResultDto updateStatus(Feedback feedBack);
}
