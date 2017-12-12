!function(e){e.module("starter",["ionic","componentImgByDpr","templates"]),e.module("templates",[])}(window.angular),function(){"use strict";function e(e){function n(e,t,n){console.log(667),t.on("swipe",function(){console.log(44455)})}var o={bindToController:!0,controller:t,controllerAs:"vm",link:n,restrict:"A",scope:{}};return o}function t(){}angular.module("starter").directive("tabsSwipe",e),e.$inject=["$ionicGesture"],t.$inject=[]}(),function(){"use strict";function e(e,t,n,o,i,a,r,s){function l(){h.imgs[0]="pages/list/images/banner_hd.png",1==T?(h.imgs[1]="pages/list/images/baoxiu1.png",h.imgs[2]="pages/list/images/chengji3.png"):(h.imgs[1]="pages/list/images/baoxiu2.png",h.imgs[2]="pages/list/images/chengji1.png"),n.show(),e.appTypeAll().success(function(e){n.hide(),1==e.code?(h.types=e.data,y=h.types[0],h.hasMore=!0):n.show({template:e.msg,animation:"fade-in",showBackdrop:!1,maxWidth:200,duration:2e3,showDelay:0})})}function c(){u(y).success(function(e){})}function p(n,o){o!=y&&(e.cancelAll(),b&&r.cancel(b),angular.forEach(n,function(e){e.active=!1}),o.active=!0,y=o,g(),h.hasMore?s.$getByHandle("mainScroll").resize().then(function(){b=r(function(){t.$broadcast("scroll.infiniteScrollComplete")},250)}):(s.$getByHandle("mainScroll").resize(),h.hasMore=!0))}function u(n){var o={};return o.first=(A.num-1)*A.size,o.last=A.num*A.size,o.requestSource=w,o.appType=n.id,o.description=h.search,n.active=!0,h.noData=!1,e.appList(o).success(function(e){A.num++,null==e.data?h.hasMore=!1:(e.data.length<A.size&&(h.hasMore=!1),Array.prototype.push.apply(h.apps,e.data)),0==h.apps.length&&(h.noData=!0),t.$broadcast("scroll.infiniteScrollComplete")})}function d(t){var o={appId:t.id,requestSource:w};e.addMyApp(o).success(function(e){1==e.code?t.added="1":n.show({template:e.msg,animation:"fade-in",showBackdrop:!1,maxWidth:200,showDelay:0,duration:1e3})})}function m(t){var n={appId:t.id};e.deleMyApp(n).success(function(e){t.added="0"})}function f(t){e.cancelAll(),"keypress"==t.type&&13==t.keyCode?v():"blur"==t.type&&v(),h.hasMore?u(y).success(function(e){}):h.hasMore=!0}function g(){A.num=1,h.apps.length=0,h.search=""}function v(){A.num=1,h.apps.length=0}var h=this,y=null,b=null,w=o.get().requestSource,T=o.get().userType,A={size:100,num:1};h.title="ControllerName",h.search="",h.listRequestLen=0,h.changType=p,h.getApps=c,h.addMyApp=d,h.deleMyApp=m,h.searchFn=f,h.noData=!1,h.hasMore=!1,h.apps=[],h.imgs=[],l(),t.$on("response.error.onData",function(e){n.hide(),0==h.apps.length&&(h.noData=!0)})}function t(e,t,n){function o(){return e.get(t.appTypeList)}function i(){return e.get(t.appTypeAll)}function a(o){var i=n.defer(),a=null;return p.push(i),a=e.get(t.appList,o,i),a["finally"](function(){var e=p.indexOf(i);e>=0&&p.splice(e,1)}),a}function r(n){return e.save(t.addMyApp,n)}function s(n){return e.del(t.deleMyApp,n)}function l(){return console.log("pending.length = "+p.length),p.length}function c(){angular.forEach(p,function(e){e.resolve()}),p.length=0}var p=[];return{appTypeList:o,appTypeAll:i,appList:a,addMyApp:r,deleMyApp:s,getPendingLen:l,cancelAll:c}}angular.module("starter").controller("listController",e).factory("listService",t),e.$inject=["listService","$scope","$ionicLoading","globalService","$rootScope","$http","$timeout","$ionicScrollDelegate"],t.$inject=["overloadHttp","LIST_API","$q"]}(),angular.module("templates").run(["$templateCache",function(e){e.put("pages/list/content/content.html","<ion-view view-title=应用中心></ion-view>"),e.put("pages/detail/detail.html","<ion-view view-title=详情><ion-content>sssssssssss</ion-content></ion-view>"),e.put("pages/list/list.html",'<ion-view view-title=应用中心><ion-content delegate-handle=mainScroll><div class="card-reset card"><label class="item item-input list__item-input"><i class="icon ion-search placeholder-icon list__input--search__icon" ng-show="!vm.focus && !vm.search">搜索</i><input type=text class=list__input--search ng-model=vm.search ng-keypress="$event.which == 13 && vm.searchFn($event)" ng-focus="vm.focus = true" ng-blur="vm.focus = false"></label> <img class=full-image component-img-by-dpr></div><div><ion-scroll zooming=false direction=x scrollbar-x=false style="width: 100%;height: 64px;white-space: nowrap" delegate-handle=horizontal horizontal-scroll-fix=mainScroll><div class="tabs-striped tabs-color-calm"><div class="tabs tabs-reset static"><a class="tab-item tab-item-reset" ng-class={active:type.active} ng-repeat="type in vm.types" ng-click="vm.changType(vm.types, type)">{{type.typename}}</a></div></div></ion-scroll></div><div class=list><a class="item item-avatar list__item-avatar item-button-right list__item" ng-repeat="app in vm.apps"><img ng-src={{app.appLogo}}><div class=list__item--name><div class=list__item--name__content><h2>{{app.appName}}</h2><p ng-if=app.appVersion.versionName>v{{app.appVersion.versionName}}</p></div></div><button class="button button-calm list__button" ng-if="app.displayPosition != \'1\' && app.added != \'1\'" ng-click=vm.addMyApp(app)>添加应用</button> <button class="button button-outline button-stable list__button" ng-if="app.displayPosition == \'1\' || app.added == \'1\'" ng-disabled=true>已添加</button></a><div ng-if=vm.noData class=text-center><img src=images/Nodata_img.jpg></div></div><ion-infinite-scroll ng-if=vm.hasMore on-infinite=vm.getApps() distance=1%></ion-infinite-scroll></ion-content></ion-view>'),e.put("pages/root/root.html","<ion-nav-view name=main></ion-nav-view>")}]),function(){"use strict";function e(e,t,n,o){var i={request:function(e){if("post"==e.method.toLowerCase()){var t=o.get();e.data||(e.data={}),e.data.access_token=t.access_token}return e},response:function(n){return"POST"==n.config.method&&"-2"==n.data.data?(t.$broadcast("response.error.onData"),e.reject()):n},requestError:function(t){return e.reject(t)},responseError:function(n){return t.$broadcast("response.error.onData"),e.reject(n)}};return i}angular.module("starter").factory("myInterceptor",e),e.$inject=["$q","$rootScope","REQUEST_TYPE","globalService"]}(),function(e){e.module("starter").config(["$stateProvider","$urlRouterProvider",function(e,t){e.state("root",{url:"/root","abstract":!0,resolve:{platform:["$q","$rootScope","$window","globalService",function(e,t,n,o){function i(e){if(window.WebViewJavascriptBridge)return e(WebViewJavascriptBridge);if(window.WVJBCallbacks)return window.WVJBCallbacks.push(e);window.WVJBCallbacks=[e];var t=document.createElement("iframe");t.style.display="none",t.src="wvjbscheme://__BRIDGE_LOADED__",document.documentElement.appendChild(t),setTimeout(function(){document.documentElement.removeChild(t)},0)}var a=e.defer();if(n.ionic.Platform.isAndroid()){var r=null;try{r=n.jsonToHtml.initHtlmRequest(),r=JSON.parse(r)}catch(s){r={userId:11,userType:2,access_token:"TGT-13-leD3s3O4Wt7DEMuGK6DcMVDvsyMZhAPbZX2rcHRGAgXYJmf2Cu-combanc.com.cn"}}r.requestSource=2,o.set(r),a.resolve()}else if(n.ionic.Platform.isIOS())i(function(e){e.registerHandler("testJavascriptHandler",function(e,t){var n={"Javascript Says":"Right back atcha!"};t(n)}),e.registerHandler("saveVideoProgress",function(e,n){t.$broadcast("video.setProgress");var o={"Javascript Says":"Right back atcha!"};n(o)}),e.callHandler("testObjcCallback",{foo:"bar"},function(e){e.requestSource=3,o.set(e),a.resolve()})});else{var r={userId:11};o.set(r),a.resolve()}return a.promise}]},templateUrl:"pages/root/root.html"}).state("root.list",{url:"/list",views:{main:{templateUrl:"pages/list/list.html",controller:"listController as vm"}}}),t.otherwise("/root/list")}])}(window.angular),function(){"use strict";function e(e,t){function n(n,o,i){var a={};return a.requestType=t.get,i&&(a.timeout=i.promise),e.post(n,o,a)}function o(n,o){return e.post(n,o,{requestType:t.save})}function i(n,o){return e.post(n,o,{requestType:t["delete"]})}var a={get:n,save:o,del:i};return a}function t(){function e(e){n=e}function t(){return n}var n=null;return{set:e,get:t}}angular.module("starter").factory("overloadHttp",e).factory("globalService",t),e.$inject=["$http","REQUEST_TYPE"],t.$inject=[]}(),function(e){e.module("starter").constant("REQUEST_TYPE",{save:1,get:2,"delete":3}).constant("ROLE",{admin:1,firm:2,user:3}).constant("OP_TYPE",{lookUp:1,check:2})}(window.angular),function(e){e.module("starter").config(["$ionicConfigProvider",function(e){e.platform.ios.tabs.style("striped"),e.platform.ios.tabs.position("top"),e.platform.android.tabs.style("striped"),e.platform.android.tabs.position("top"),e.platform.ios.navBar.alignTitle("center"),e.platform.android.navBar.alignTitle("center"),e.platform.ios.backButton.previousTitleText("").icon("ion-ios-arrow-thin-left"),e.platform.android.backButton.previousTitleText("").icon("ion-android-arrow-back"),e.platform.ios.views.transition("ios"),e.platform.android.views.transition("android"),e.views.maxCache(10)}]).config(["$httpProvider",function(t){t.defaults.headers.post["Content-Type"]="application/x-www-form-urlencoded;charset=utf-8";var n=function(e){var t,o,i,a,r,s,l,c="";for(t in e)if(o=e[t],o instanceof Array)for(l=0;l<o.length;++l)r=o[l],i=t+"["+l+"]",s={},s[i]=r,c+=n(s)+"&";else if(o instanceof Object)for(a in o)r=o[a],i=t+"["+a+"]",s={},s[i]=r,c+=n(s)+"&";else void 0!==o&&null!==o&&(c+=encodeURIComponent(t)+"="+encodeURIComponent(o)+"&");return c.length?c.substr(0,c.length-1):c};t.defaults.transformRequest=[function(t){return e.isObject(t)&&"[object File]"!==String(t)?n(t):t}]}]).config(["$httpProvider",function(e){e.interceptors.push("myInterceptor")}]).run(["$rootScope","$state",function(e,t){e.$on("$stateChangeSuccess",function(e,t,n,o,i){})}])}(window.angular),function(e){var t=!1,n="http://58.131.210.23/manage/";t?e.module("starter").constant("PUBLIC_API",{}).constant("LIST_API",{appTypeList:"data/curSchool.json"}):e.module("starter").constant("PUBLIC_API",{}).constant("LIST_API",{appTypeList:n+"platfromMobileThirdApp/webAppTypeList",appTypeAll:n+"platfromMobileThirdApp/webAppTypeList",appList:n+"platfromMobileThirdApp/listAppByKind",addMyApp:n+"platfromMobileThirdApp/appUseCollect",deleMyApp:n+"platfromMobileThirdApp/deleteAppCollect"})}(window.angular),function(){"use strict";function e(e){function n(t,n,o){var i="";i=1==e.devicePixelRatio?"pages/list/images/banner.png":"pages/list/images/banner_hd.png",n.attr("src",i)}var o={bindToController:!0,controller:t,controllerAs:"vm",link:n,restrict:"A",scope:{}};return o}function t(){}angular.module("componentImgByDpr",[]).directive("componentImgByDpr",e),e.$inject=["$window"],t.$inject=[]}();