package com.combanc.service.mobileplatform;

import com.combanc.entity.common.BaseResultDto;
import com.combanc.entity.mobileplatform.LostFound;
import com.combanc.entity.mobileplatform.LostFoundReview;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface PlatformLostFoundService {

     BaseResultDto addLostFound(LostFound lostFound, CommonsMultipartFile[] images);
     BaseResultDto lostFoundList(LostFound lostFound,HttpServletRequest request);
     BaseResultDto lostFoundDetail(LostFound lostFound,HttpServletRequest request);

     BaseResultDto review(LostFoundReview lostFoundReview);
     BaseResultDto reviewList(LostFoundReview lostFoundReview);
     BaseResultDto deleteLostFound(String  ids);
     BaseResultDto deleteReview(String  ids);


}
