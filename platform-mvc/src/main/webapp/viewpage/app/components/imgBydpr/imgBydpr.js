/**
 * Created by Admin on 2016/10/14.
 */
(function () {
    'use strict';

    angular
        .module('componentImgByDpr', [])
        .directive('componentImgByDpr', directiveName);

    directiveName.$inject = ["$window"];

    /* @ngInject */
    function directiveName($window) {
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
            var src = "";
            if($window.devicePixelRatio == 1){
                src = "pages/list/images/banner.png";
            }else{
                src = "pages/list/images/banner_hd.png";
            }
            element.attr("src", src);
        }
    }

    ControllerName.$inject = [];

    /* @ngInject */
    function ControllerName() {
        
    }

})();

