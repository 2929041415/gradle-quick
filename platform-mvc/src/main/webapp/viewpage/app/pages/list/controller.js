/**
 * Created by Admin on 2016/8/10.
 */
(function () {
    'use strict';

    angular
        .module('starter')
        .controller('listController', ControllerName)
        .factory('listService', listService);

    ControllerName.$inject = ["listService", "$scope", "$ionicLoading", "globalService", "$rootScope", "$http", "$timeout", "$ionicScrollDelegate"];
    listService.$inject = ["overloadHttp", "LIST_API", "$q"];

    /* @ngInject */
    function ControllerName(listService, $scope, $ionicLoading, globalService, $rootScope, $http, $timeout, $ionicScrollDelegate) {

        var vm = this, curType = null, timeout = null;
        var requestSource = globalService.get().requestSource;
        var userType = globalService.get().userType;
        var page = {
            size: 10,
            num:1
        }
        vm.title = 'ControllerName';
        vm.search = '';
        vm.listRequestLen = 0;
        vm.changType = changType;
        vm.getApps = getApps;
        vm.addMyApp = addMyApp;
        vm.deleMyApp = deleMyApp;
        vm.searchFn = searchFn;
        vm.noData = false;
        vm.hasMore = false;
        vm.apps = [];
        vm.imgs = [];

        // $scope.$watch(function() {
        //     return $http.pendingRequests.length;
        // }, function (v) {
        //     // console.log(v);
        //     // console.log($http.pendingRequests);
        //     vm.listRequestLen = 0;
        //     angular.forEach($http.pendingRequests, function (req) {
        //         if(req.url.indexOf(LIST_API.appList) >= 0){
        //             vm.listRequestLen ++;
        //         }
        //     })
        //     // console.log(vm.listRequestLen);
        // });
        
        activate();

        ////////////////

        $scope.$on('response.error.onData', function (event) {
            $ionicLoading.hide();
            if(vm.apps.length == 0){
                vm.noData = true;
            }
        })

        function activate() {
            vm.imgs[0] = 'pages/list/images/banner_hd.png';
            if(userType == 1){
                vm.imgs[1] = 'pages/list/images/baoxiu1.png';
                vm.imgs[2] = 'pages/list/images/chengji3.png';
            }else{
                vm.imgs[1] = 'pages/list/images/baoxiu2.png';
                vm.imgs[2] = 'pages/list/images/chengji1.png';
            }
            $ionicLoading.show();
            listService.appTypeAll().success(function (res) {
                $ionicLoading.hide();
                if(res.code == 1){
                    vm.types = res.data;
                    curType = vm.types[0];

                    vm.hasMore = true;
                }else{
                    $ionicLoading.show({
                        template:res.msg,
                        animation: 'fade-in',
                        showBackdrop: false,
                        maxWidth: 200,
                        duration: 2000,
                        showDelay: 0,
                    });
                }
            });
        }

        function getApps() {
            getAppsByType(curType).success(function (res) {
            });
        }
        
        function changType(types, type) {
            if(type == curType){
                return;
            }
            listService.cancelAll();
            if(timeout){
                $timeout.cancel(timeout);
            }
            angular.forEach(types, function (t) {
                t.active = false;
            })
            type.active = true;
            curType = type;
            initSearchCondition();
            if(vm.hasMore){
                $ionicScrollDelegate.$getByHandle('mainScroll').resize().then(function () {
                    timeout = $timeout(function () {
                        $scope.$broadcast('scroll.infiniteScrollComplete');
                    },250);
                });
            }else{
                $ionicScrollDelegate.$getByHandle('mainScroll').resize();
                vm.hasMore = true;
            }


        }

        function getAppsByType(type) {
            var data = {};
            data.first = (page.num - 1)* page.size;
            data.last = page.num * page.size;
            data.requestSource = requestSource;
            data.appType = type.id;
            data.description = vm.search;
            type.active = true;
            vm.noData = false;
            return listService.appList(data).success(function (res) {
                page.num++;
                // console.log(res.data.length)
                if(null==res.data){
                    vm.hasMore = false;
                }else{
                    if(res.data.length < page.size){
                        vm.hasMore = false;
                    }
                    Array.prototype.push.apply(vm.apps, res.data);
                }

                if(vm.apps.length == 0){
                    vm.noData = true;
                }
                $scope.$broadcast('scroll.infiniteScrollComplete');
            });
        }
        
        function addMyApp(app) {
            var data = {
                appId: app.id,
                requestSource: requestSource
            }
            listService.addMyApp(data).success(function (res) {
                if(res.code == 1){
                    app.added = "1";
                }else{
                    $ionicLoading.show({
                        template:res.msg,
                        animation: 'fade-in',
                        showBackdrop: false,
                        maxWidth: 200,
                        showDelay: 0,
                        duration: 1000
                    });
                }
            });
        }

        function deleMyApp(app) {
            var data = {
                appId: app.id
            }
            listService.deleMyApp(data).success(function (res) {
                app.added = "0";
            });
        }

        function searchFn(e) {
            listService.cancelAll();
            if(e.type == 'keypress' && e.keyCode == 13){
                initPage();
            }else if(e.type == 'blur'){
                initPage();
            }
            if(vm.hasMore){
                getAppsByType(curType).success(function (res) {
                });
            }else{
                vm.hasMore = true;
            }
        }

        function initSearchCondition(){
            page.num = 1;
            vm.apps.length = 0;
            vm.search = "";
        }
        function initPage() {
            page.num = 1;
            vm.apps.length = 0;
        }
    }
    
    function listService(overloadHttp, LIST_API, $q){
        var pending = [];
        return {
            appTypeList:appTypeList,
            appTypeAll:appTypeAll,
            appList:appList,
            addMyApp:addMyApp,
            deleMyApp:deleMyApp,
            getPendingLen:getPendingLen,
            cancelAll:cancelAll
        }
        
        function appTypeList(){
            return overloadHttp.get(LIST_API.appTypeList);
        }
        function appTypeAll() {
            return overloadHttp.get(LIST_API.appTypeAll);
        }
        function appList(data) {
            var canceller = $q.defer();
            var ret = null;
            pending.push(canceller);
            ret = overloadHttp.get(LIST_API.appList, data, canceller);
            ret.finally(function() {
                var index = pending.indexOf(canceller);
                if(index >= 0){
                    pending.splice(index, 1);
                }
            });
            return ret;
        }
        function addMyApp(data) {
            return overloadHttp.save(LIST_API.addMyApp, data);
        }
        function deleMyApp(data) {
            return overloadHttp.del(LIST_API.deleMyApp, data);
        }
        function getPendingLen() {
            console.log('pending.length = ' + pending.length);
            return pending.length;
        }
        function cancelAll() {
            angular.forEach(pending, function(p) {
                p.resolve();
            });
            pending.length = 0;
        }
    }

})();

