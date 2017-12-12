package com.combanc.service.mobileplatform;

import com.combanc.entity.common.BaseResultDto;
import com.combanc.entity.mobileplatform.CampusScenery;
import com.combanc.entity.mobileplatform.CampusSceneryType;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface PlatformCampusSceneryService {
     BaseResultDto campusSceneryType(CampusSceneryType campusSceneryType,CommonsMultipartFile[] images);
     BaseResultDto campusSceneryTypeList(CampusSceneryType campusSceneryType);

     BaseResultDto addCampusScenery(CampusScenery campusScenery,CommonsMultipartFile[] images);
     BaseResultDto  campusSceneryList(CampusScenery campusScenery,HttpServletRequest request);
     BaseResultDto approve(CampusScenery campusScenery);
     BaseResultDto delete(String ids);
}
