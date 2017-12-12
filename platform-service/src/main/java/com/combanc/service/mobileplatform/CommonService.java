package com.combanc.service.mobileplatform;

public interface CommonService {

    String checkUserRole(String accountId, String requestSource);

    String getSchoolId(String accountId);

    String checkImage();
}
