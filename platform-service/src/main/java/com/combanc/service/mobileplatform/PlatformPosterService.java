package com.combanc.service.mobileplatform;

import com.combanc.entity.common.BaseResultDto;
import com.combanc.entity.mobileplatform.Poster;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface PlatformPosterService {

     BaseResultDto addPoster(Poster poster, CommonsMultipartFile[] posterImage);
     BaseResultDto listPoster(Poster poster, HttpServletRequest request);
     BaseResultDto updatePoster(Poster poster, CommonsMultipartFile[] posterImage);
     BaseResultDto deletePoster(Poster poster);
     BaseResultDto updateStatus(Poster poster);


}
