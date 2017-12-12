package com.combanc.dao.webapp;

import com.combanc.entity.school.PlatFormSchool;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PlatFormWebSchoolDao {

    Integer addWebToSchool(@Param(value = "id") String id, @Param(value = "appid") String appid, @Param(value = "schoolid") String schoolid);

    Integer delWebInSchool(@Param(value = "appid") String appid);

    List<String> getSchoolByWeb(@Param(value = "appid") String appid);

    List<PlatFormSchool> getSchoolTree(@Param(value = "appid") String appid);
}
