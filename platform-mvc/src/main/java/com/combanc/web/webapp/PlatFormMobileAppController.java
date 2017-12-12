package com.combanc.web.webapp;

import com.combanc.entity.common.BaseResultDto;
import com.combanc.entity.common.CommonEnum;
import com.combanc.entity.system.PlatFormDict;
import com.combanc.entity.webapp.PlatFormWebApp;
import com.combanc.service.system.PlatFormDictService;
import com.combanc.service.tokenfilter.AccessToken;
import com.combanc.service.webapp.PlatFormWebAppService;
import com.combanc.utils.jwtauth.JwtUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/platfromMobileApp")
public class PlatFormMobileAppController {
    @Resource
    private PlatFormWebAppService platFormWebAppService;
    @Resource
    private PlatFormDictService platFormDictService;

    /**
     * 应用类型列表-移动端
     *
     * @param platFormDict
     * @return
     */
    @AccessToken
    @RequestMapping(method = RequestMethod.POST, value = "/webAppTypeList", produces = "application/json;charset=utf-8")
    public BaseResultDto webAppTypeList(PlatFormDict platFormDict) {
        //设置type为应用
        platFormDict.setType("webappkind");
        return platFormDictService.dictValueList(platFormDict);
    }

    /**
     * 查询应用列表-移动端
     *
     * @param platFormWebApp
     * @return
     */
    @AccessToken
    @RequestMapping(method = RequestMethod.POST, value = "/listAppByKind", produces = "application/json;charset=utf-8")
    public BaseResultDto listAppByKind(PlatFormWebApp platFormWebApp, HttpServletRequest request, @RequestParam(value = "myApp", required = false) String myApp, @RequestParam(value = "ids", required = false) String ids) {
        String user = JwtUtils.veriftToken(request.getHeader(CommonEnum.HEAD_PARAM.TOKEN.getValue()));
        return platFormWebAppService.listMobileAppCollect(user, "collect", myApp, platFormWebApp.getWebappos());
    }

    /**
     * 应用收藏--移动端
     *
     * @param
     * @return
     */
    @AccessToken
    @RequestMapping(method = RequestMethod.POST, value = "/appUseCollect", produces = "application/json;charset=utf-8")
    public BaseResultDto appUseCollect(String appid, String type, String requestSource, HttpServletRequest request) {
        String user = JwtUtils.veriftToken(request.getHeader(CommonEnum.HEAD_PARAM.TOKEN.getValue()));
        return platFormWebAppService.appUseCollect(user, appid, type, requestSource);
    }

    /**
     * 取消收藏应用--移动端
     *
     * @param ids
     * @return
     */
    @AccessToken
    @RequestMapping(method = RequestMethod.POST, value = "/deleteAppCollect", produces = "application/json;charset=utf-8")
    public BaseResultDto deleteAppCollect(String ids, String requestSource, HttpServletRequest request) {
        String user = JwtUtils.veriftToken(request.getHeader(CommonEnum.HEAD_PARAM.TOKEN.getValue()));
        return platFormWebAppService.deleteAppCollect(ids, user, requestSource);
    }

    /**
     * 我的应用排序--移动端
     *
     * @param ids
     * @return
     */
    @AccessToken
    @RequestMapping(method = RequestMethod.POST, value = "/orderAppCollect", produces = "application/json;charset=utf-8")
    public BaseResultDto orderAppCollect(String ids, String type, String requestSource, HttpServletRequest request) {
        String user = JwtUtils.veriftToken(request.getHeader(CommonEnum.HEAD_PARAM.TOKEN.getValue()));
        return platFormWebAppService.orderAppCollect(ids, user, type, requestSource);
    }

}
