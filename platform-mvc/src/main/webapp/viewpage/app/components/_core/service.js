/**
 * Created by Admin on 2016/8/10.
 */
(function () {
    'use strict';

    angular
        .module('starter')
        .factory('myInterceptor', myInterceptor);

    myInterceptor.$inject = ["$q", "$rootScope", "REQUEST_TYPE", "globalService"];

    /* @ngInject */
    function myInterceptor($q, $rootScope, REQUEST_TYPE, globalService) {
        var myInterceptor = {
            'request':function(config){
                // console.log(config);
                if(config.method.toLowerCase() == 'post'){
                    var data = globalService.get();
                    if(config.data){
                        // config.data.access_token = data.access_token;
                    }else{
                        config.data = {};
                        // config.data.access_token = data.access_token;
                    }
                    config.data.access_token = data.access_token;
                    //config.data.access_token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjcmVhdGV0aW1lIjoxNTEwMDI4OTA1MDA0LCJhY2NvdW50IjoiOTEyMTA4NjMifQ.m-wYMZLTPgTM4COwP1HSsNQzXSPY8g4k0T_iRRcBHaU";
                    // $httpProvider.defaults.headers.common['Authorization'] = 'code_bunny';
                    // config.headers.access_token = 1111;
                }
                return config;
            },
            'response':function(response){

                if(response.config.method == "POST") {

                    if(response.data.data == '-2'){
                        $rootScope.$broadcast("response.error.onData");
                        return $q.reject();
                    }
                }

                return response;
                
                // if(response.config.method == "POST") {
                //     if(response.data.result != 1){
                //         return $q.reject();
                //     }else{
                //
                //     }
                // }
                // if(response.config.method == "POST") {
                //     console.log(response);
                //     var alert = {};
                //     if (response.config.data) {
                //         if (response.config.data.requestType == REQUEST_TYPE.save || response.config.data.requestType == REQUEST_TYPE.delete) {
                //             alert = {
                //                 msg: response.data.msg,
                //                 type: "success"
                //             };
                //             if (response.data.result == 1) {
                //                 alert.type = "success";
                //                 $rootScope.$broadcast("response.onData", alert);
                //             }
                //
                //         }
                //     }
                //     if (response.config.data && response.data.result == "2") {
                //         if (response.config.data.requestType == REQUEST_TYPE.save || response.config.data.requestType == REQUEST_TYPE.delete
                //             || response.config.data.requestType == REQUEST_TYPE.import) {
                //             alert = {
                //                 msg: response.data.msg,
                //                 type: "danger"
                //             };
                //             $rootScope.$broadcast("response.onData", alert);
                //             //$rootScope.$broadcast("response.error.onData");
                //             return $q.reject()
                //         }
                //     } else {
                //         return response;
                //     }
                // }
            },
            'requestError':function(rejection){
                return $q.reject(rejection);
            },
            'responseError':function(rejection){
                //return rejection

                // if(rejection.config.data){
                //     var alert = {
                //         msg : "",
                //         type : "danger"
                //     };
                //     if(rejection.config.data.requestType == REQUEST_TYPE.save){
                //         alert.msg = "保存失败";
                //         $rootScope.$broadcast("response.onData",alert);
                //     }else if(rejection.config.data.requestType == REQUEST_TYPE.delete){
                //         alert.msg = "删除失败";
                //         $rootScope.$broadcast("response.onData",alert);
                //     }
                // }
                // $rootScope.$broadcast("response.error.onData");
                $rootScope.$broadcast("response.error.onData");

                return $q.reject(rejection);
            }
        };
        return myInterceptor;
    }

})();

