/**
 * Created by Admin on 2016/8/10.
 */
(function () {
    'use strict';

    angular
        .module('starter')
        .directive('tabsSwipe', directiveName);

    directiveName.$inject = ['$ionicGesture'];

    /* @ngInject */
    function directiveName($ionicGesture) {
        var directive = {
            bindToController: true,
            controller: ControllerName,
            controllerAs: 'vm',
            link: link,
            restrict: 'A',
            scope: {}
        };
        return directive;

        function link(scope, element, attrs) {
            console.log(667);
            element.on('swipe', function(){
                console.log(44455);
            })
        }
    }

    ControllerName.$inject = [];

    /* @ngInject */
    function ControllerName() {

    }

})();

