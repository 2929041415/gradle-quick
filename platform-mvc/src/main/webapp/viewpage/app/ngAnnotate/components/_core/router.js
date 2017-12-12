/**
 * Created by Admin on 2016/6/13.
 */
(function(angular){
    angular.module('starter')
        .config(["$stateProvider", "$urlRouterProvider", function($stateProvider, $urlRouterProvider) {
            $stateProvider

            // setup an abstract state for the tabs directive
                .state('root', {
                    url: '/root',
                    abstract: true,
                    resolve: {
                        platform: ["$q", "$rootScope", "$window", "globalService",function ($q , $rootScope, $window, globalService) {
                            var deferred = $q.defer();
                            if($window.ionic.Platform.isAndroid()){
                                // debugger
                                var result = null;
                                try {
                                    result = $window.jsonToHtml.initHtlmRequest();
                                    result = JSON.parse(result);
                                }catch (e){
                                    result = {
                                        userId : 11,
                                        userType : 2,
                                        access_token:'TGT-13-leD3s3O4Wt7DEMuGK6DcMVDvsyMZhAPbZX2rcHRGAgXYJmf2Cu-combanc.com.cn'
                                    }
                                }
                                // alert(result);

                                // var result = {
                                //     userId : 11,
                                //     access_token:'TGT-2-3Sif5hzMFnXnzspohwpgu1oHd1u6wblMgIvQQrZiQPsF69fH4G-combanc.com.cn'
                                // }
                                result.requestSource = 2;
                                globalService.set(result);
                                deferred.resolve();
                            }else if($window.ionic.Platform.isIOS()){
                                function setupWebViewJavascriptBridge(callback) {
                                    if (window.WebViewJavascriptBridge) { return callback(WebViewJavascriptBridge); }
                                    if (window.WVJBCallbacks) { return window.WVJBCallbacks.push(callback); }
                                    window.WVJBCallbacks = [callback];
                                    var WVJBIframe = document.createElement('iframe');
                                    WVJBIframe.style.display = 'none';
                                    WVJBIframe.src = 'wvjbscheme://__BRIDGE_LOADED__';
                                    document.documentElement.appendChild(WVJBIframe);
                                    setTimeout(function() { document.documentElement.removeChild(WVJBIframe) }, 0)
                                }
                                setupWebViewJavascriptBridge(function(bridge) {
                                    bridge.registerHandler('testJavascriptHandler', function(data, responseCallback) {
                                        //log('ObjC called testJavascriptHandler with', data)
                                        //alert(data.foo)
                                        var responseData = { 'Javascript Says':'Right back atcha!' }
                                        //log('JS responding with', responseData)
                                        responseCallback(responseData)
                                    })
                                    bridge.registerHandler('saveVideoProgress', function(data, responseCallback) {
                                        //log('ObjC called testJavascriptHandler with', data)
                                        //alert(data.foo)
                                        $rootScope.$broadcast("video.setProgress")
                                        var responseData = { 'Javascript Says':'Right back atcha!' }
                                        //log('JS responding with', responseData)
                                        responseCallback(responseData)
                                    })
                                    bridge.callHandler('testObjcCallback', {'foo': 'bar'}, function(response) {
                                        // alert(response);
                                        response.requestSource = 3;
                                        globalService.set(response);
                                        deferred.resolve();
                                    })
                                    // $rootScope.$on("datum.download", function(event,data){
                                    //     bridge.callHandler('downloadCallback', {data:data}, function(response) {})
                                    // })
                                    // document.getElementById("button").addEventListener("click", function(){
                                    //     bridge.callHandler('closeCallback', {}, function(response) {})
                                    // })
                                })
                                // deferred.resolve();
                                // var result = {
                                //     userId : 11,
                                //     access_token:'TGT-674-gaTeaNnjD5eRTfyGiRJsRvu6kM2IJiaXc3Q4Yf9fwhu9xbWJcC-combanc.com.cn'
                                // }
                                // // result = JSON.parse(result);
                                // globalService.set(result);
                                // deferred.resolve();
                            }else{
                                var result = {
                                    userId : 11,
                                    // access_token:'TwwwwFA1v-combanc.com.cn111'
                                }
                                // result = JSON.parse(result);
                                globalService.set(result);
                                deferred.resolve();
                            }
                            // var response = {
                            //     userId : 11,
                            //     access_token:11
                            // }
                            // globalService.set(response);
                            // deferred.resolve();
                            return deferred.promise;
                        }]
                    },
                    templateUrl: 'pages/root/root.html'
                })

                // Each tab has its own nav history stack:

                .state('root.list', {
                    url: '/list',
                    views: {
                        'main': {
                            templateUrl: 'pages/list/list.html',
                            controller: 'listController as vm'
                        }
                    }
                })
                // .state('root.list.content', {
                //     url: '/content',
                //     views: {
                //         'content': {
                //             templateUrl: 'pages/list/content/content.html',
                //             // controller: 'listController as vm'
                //         }
                //     }
                // })
                //
                // .state('root.detail', {
                //     url: '/detail',
                //     views: {
                //         'main': {
                //             templateUrl: 'pages/detail/detail.html',
                //             // controller: 'ChatsCtrl'
                //         }
                //     }
                // })
            // .state('tab.chat-detail', {
            //     url: '/chats/:chatId',
            //     views: {
            //         'tab-chats': {
            //             templateUrl: 'templates/chat-detail.html',
            //             controller: 'ChatDetailCtrl'
            //         }
            //     }
            // })
            //
            // .state('tab.account', {
            //     url: '/account',
            //     views: {
            //         'tab-account': {
            //             templateUrl: 'templates/tab-account.html',
            //             controller: 'AccountCtrl'
            //         }
            //     }
            // });

            // if none of the above states are matched, use this as the fallback
            $urlRouterProvider.otherwise('/root/list');

        }]);
})(window.angular);
