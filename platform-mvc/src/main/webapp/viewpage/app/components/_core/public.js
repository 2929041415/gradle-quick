/**
 * Created by Admin on 2016/9/6.
 */
(function () {
    'use strict';

    angular
        .module('starter')
        .factory('overloadHttp', overloadHttp)
        .factory('globalService', globalService);

    overloadHttp.$inject = ['$http', 'REQUEST_TYPE'];
    globalService.$inject = [];

    /* @ngInject */
    function overloadHttp($http, REQUEST_TYPE) {
        var service = {
            get: get,
            save:save,
            del:del
        };
        return service;

        ////////////////

        function get(url, data, canceller) {
            var config = {};
            config.requestType = REQUEST_TYPE.get;
            if(canceller){
                config.timeout = canceller.promise;
            }
            return $http.post(url ,data, config);
        }

        function save(url, data) {
            return $http.post(url ,data, {requestType: REQUEST_TYPE.save})
        }

        function del(url, data) {
            return $http.post(url ,data, {requestType: REQUEST_TYPE.delete})
        }
    }
    
    function globalService() {
        var data = null;
        return {
            set:set,
            get:get
        }
        
        function set(d) {
            data = d;
        }
        
        function get() {
            return data
        }
    }

})();

