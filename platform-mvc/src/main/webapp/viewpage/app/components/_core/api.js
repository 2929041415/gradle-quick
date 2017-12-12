/**
 * Created by Admin on 2016/6/13.
 */
(function(angular){
    var test = false;
    var rootUrl = "http://58.131.210.23/manage/";
    // var rootUrl = "http://192.168.130.94:8080/openplatform/";
    if(test){
        angular.module('starter')
            .constant('PUBLIC_API', {
                // curSchool: "data/curSchool.json",
            })
            .constant('LIST_API', {
                appTypeList: "data/curSchool.json",
            })
    }else{
        angular.module('starter')
            .constant('PUBLIC_API', {
                // curSchool: rootUrl + "data/curSchool.json",
            })
            .constant('LIST_API', {
                appTypeList: rootUrl + "platfromMobileThirdApp/webAppTypeList",
                appTypeAll: rootUrl + "platfromMobileThirdApp/webAppTypeList",
                appList: rootUrl + "platfromMobileThirdApp/listAppByKind",
                addMyApp: rootUrl + "platfromMobileThirdApp/appUseCollect",
                deleMyApp: rootUrl + "platfromMobileThirdApp/deleteAppCollect",
            })
        
    }
})(window.angular);
