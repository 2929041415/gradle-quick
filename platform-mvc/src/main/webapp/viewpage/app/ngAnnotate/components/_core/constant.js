/**
 * Created by Admin on 2016/6/13.
 */
(function(angular){
    angular.module('starter')
        .constant('REQUEST_TYPE', {
            save:1,
            get:2,
            delete:3
        })
        .constant('ROLE', {
            admin:1,
            firm:2,
            user:3
        })
        .constant('OP_TYPE', {
            lookUp:1,
            check:2
        })
})(window.angular);
