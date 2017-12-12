package com.combanc.dao.mobileplatform;

import com.combanc.entity.mobileplatform.CampusScenery;
import com.combanc.entity.mobileplatform.CampusSceneryType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PlatformCampusSceneryDao {

    int addCampusSceneryType(CampusSceneryType campusSceneryType);

    List<CampusSceneryType> typeList(CampusSceneryType campusSceneryType);

    int typeListCount(CampusSceneryType campusSceneryType);

    int addCampusScenery(CampusScenery campusScenery);

    List<CampusScenery> campusSceneryList(CampusScenery campusScenery);

    int campusSceneryListCount(CampusScenery campusScenery);

    int approve(CampusScenery campusScenery);

    int delete(@Param(value = "ids") String[] ids);
}
