package com.combanc.service.user.impl;

import com.combanc.dao.user.PlatFormSysUserLoginDao;
import com.combanc.entity.common.BaseResultDto;
import com.combanc.entity.common.CommonEnum;
import com.combanc.entity.common.ResultMessage;
import com.combanc.entity.mobileplatform.MobileUser;
import com.combanc.entity.system.PlatFormNormalLog;
import com.combanc.entity.user.PlatFormUser;
import com.combanc.service.common.RedisClusterHelper;
import com.combanc.service.system.PlatFormLogService;
import com.combanc.service.system.PlatFormSendSmsService;
import com.combanc.service.user.PlatFormSysUserLoginService;
import com.combanc.utils.common.CommonUtils;
import com.combanc.utils.common.SHA1Util;
import com.combanc.utils.common.UidUtils;
import com.combanc.utils.jwtauth.JwtUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service(value = "platFormSysUserLoginService")
public class PlatFormSysUserLoginServiceImpl implements PlatFormSysUserLoginService {

    @Resource
    private PlatFormSysUserLoginDao platFormSysUserLoginDao;

    @Resource
    private RedisClusterHelper redisClusterHelper;

    @Resource
    private PlatFormLogService platFormLogService;

    @Resource
    private PlatFormSendSmsService platFormSendSmsService;

    @Value("${user.token.expireTime}")
    private String loginExpireTime;


    /**
     * 用户登录逻辑
     *
     * @param account
     * @param password
     * @return
     */
    @Override
    public BaseResultDto sysUserLogin(String account, String password,String handleip) {
        BaseResultDto baseResultDto = new BaseResultDto();
        if (StringUtils.isNotBlank(account) && StringUtils.isNotBlank(password)) {
            PlatFormUser platFormUser = platFormSysUserLoginDao.getUserByAccount(account, CommonEnum.USER_TYPE.系统用户.getValue());
            if (platFormUser != null) {
                String accountid = platFormUser.getAccount();
                if (StringUtils.isNotBlank(accountid)) {
                    String passdatabase = platFormUser.getPassword();
                    String passsalt = platFormUser.getPasssalt();
                    String getpassword = SHA1Util.hex_sha1(password + passsalt);
                    if (passdatabase.equals(getpassword)) {
                        String token = JwtUtils.createToken(accountid);
                        Integer expireTime = Integer.parseInt(loginExpireTime);
                        boolean setkeyresult = redisClusterHelper.setKey(token, accountid, expireTime);
                        if (setkeyresult) {
                            Map<String, Object> resultMap = new HashMap<>();
                            resultMap.put("token", token);
                            resultMap.put("accountid", platFormUser.getId());
                            baseResultDto.setData(resultMap);
                            baseResultDto.setCode(ResultMessage.SUCCESS_CODE);
                            baseResultDto.setMsg(ResultMessage.SUCCESS_MESSAGE);
                        } else {
                            baseResultDto.setCode(ResultMessage.FAILED_CODE);
                            baseResultDto.setMsg(ResultMessage.FAILED_LOGIN_TOKENERROR_MESSAGE);
                        }
                    } else {
                        baseResultDto.setCode(ResultMessage.FAILED_CODE);
                        baseResultDto.setMsg(ResultMessage.FAILED_LOGIN_PWDUSERERROR_MESSAGE);
                    }
                } else {
                    baseResultDto.setCode(ResultMessage.FAILED_CODE);
                    baseResultDto.setMsg(ResultMessage.FAILED_LOGIN_PWDUSERERROR_MESSAGE);
                }
                String handleresult = "";
                if ("1".equals(baseResultDto.getCode())) {
                    handleresult = CommonEnum.HANDLE_REUSLT.success.getValue();
                } else {
                    handleresult = CommonEnum.HANDLE_REUSLT.failed.getValue();
                }

                PlatFormNormalLog platFormNormalLog = new PlatFormNormalLog(UidUtils.getId(), accountid, "", "用户登录", handleip, "用户登录", handleresult, new Date(), new Date());
                platFormLogService.insertNormalLog(platFormNormalLog);
            } else {
                baseResultDto.setCode(ResultMessage.FAILED_CODE);
                baseResultDto.setMsg(ResultMessage.FAILED_LOGIN_PWDUSERERROR_MESSAGE);
            }
        } else {
            baseResultDto.setCode(ResultMessage.FAILED_CODE);
            baseResultDto.setMsg(ResultMessage.FAILED_LOGIN_NULL_MESSAGE);
        }
        return baseResultDto;
    }

    /**
     * 校验用户登录token
     *
     * @param token
     * @param accountid
     * @return
     */
    @Override
    public BaseResultDto verifyUserToken(String token, String accountid) {
        BaseResultDto baseResultDto = new BaseResultDto();
        if (StringUtils.isNotBlank(token)) {
            boolean existToken = redisClusterHelper.exists(token);
            if (existToken) {
                baseResultDto.setCode(ResultMessage.SUCCESS_CODE);
                baseResultDto.setMsg(ResultMessage.SUCCESS_TOKEN_VERIFY_MESSAGE);
            } else {
                baseResultDto.setCode(ResultMessage.FAILED_CODE);
                baseResultDto.setMsg(ResultMessage.FAILED_TOKEN_NOTMATCH_MESSAGE);
            }
        } else {
            baseResultDto.setCode(ResultMessage.FAILED_CODE);
            baseResultDto.setMsg(ResultMessage.FAILED_TOKEN_VERIFY_MESSAGE);
        }
        return baseResultDto;
    }

    /**
     * 移动端登录
     *
     * @param account
     * @param password
     * @return
     */
    @Override
    public BaseResultDto mobileLogin(String account, String password,String handleip) {
        BaseResultDto baseResultDto = new BaseResultDto();
        if (StringUtils.isNotBlank(account) && StringUtils.isNotBlank(password)) {
            MobileUser mobileUser = platFormSysUserLoginDao.getMobieUserByAccount(account);
            if (null != mobileUser) {
                String accountId = mobileUser.getAccountId();
                String passdatabase = mobileUser.getPassword();
                String getpassword = SHA1Util.hex_sha1(password);
                if (passdatabase.equals(getpassword) || passdatabase.equals(password)) {
                    String token = JwtUtils.createToken(accountId);
                    Integer expireTime = Integer.parseInt(loginExpireTime);
                    boolean setkeyresult = redisClusterHelper.setKey(token, accountId, expireTime);
                    if (setkeyresult) {
                        baseResultDto = tokenResult(token, accountId);
                    } else {
                        baseResultDto.setCode(ResultMessage.FAILED_CODE);
                        baseResultDto.setMsg(ResultMessage.FAILED_LOGIN_TOKENERROR_MESSAGE);
                    }
                } else {
                    baseResultDto.setCode(ResultMessage.FAILED_CODE);
                    baseResultDto.setMsg(ResultMessage.FAILED_LOGIN_PWDERROR_MESSAGE);
                }
                String handleresult = "";
                if ("1".equals(baseResultDto.getCode())) {
                    handleresult = CommonEnum.HANDLE_REUSLT.success.getValue();
                } else {
                    handleresult = CommonEnum.HANDLE_REUSLT.failed.getValue();
                }
                PlatFormNormalLog platFormNormalLog = new PlatFormNormalLog(UidUtils.getId(), accountId, "", "用户登录", handleip, "手机用户登录", handleresult, new Date(), new Date());
                platFormLogService.insertNormalLog(platFormNormalLog);
            } else {
                baseResultDto.setCode(ResultMessage.FAILED_CODE);
                baseResultDto.setMsg(ResultMessage.FAILED_LOGIN_NOTUSER_MESSAGE);
            }
        } else {
            baseResultDto.setCode(ResultMessage.FAILED_CODE);
            baseResultDto.setMsg(ResultMessage.FAILED_LOGIN_NULL_MESSAGE);
        }
        return baseResultDto;
    }

    /**
     * 微信登录
     *
     * @param weixin
     * @return
     */
    @Override
    public BaseResultDto mobieUserByWeiXin(String weixin, String handleip) {
        BaseResultDto baseResultDto = new BaseResultDto();
        if (StringUtils.isNotBlank(weixin)) {
            MobileUser mobileUser = platFormSysUserLoginDao.getMobieUserByWeiXin(weixin);
            if (null != mobileUser) {
                String accountId = mobileUser.getAccountId();
                Integer expireTime = Integer.parseInt(loginExpireTime);
                String token = JwtUtils.createToken(accountId);
                boolean setkeyresult = redisClusterHelper.setKey(token, accountId, expireTime);
                if (setkeyresult) {
                    baseResultDto = tokenResult(token, accountId);
                } else {
                    baseResultDto.setCode(ResultMessage.FAILED_CODE);
                    baseResultDto.setMsg(ResultMessage.FAILED_LOGIN_TOKENERROR_MESSAGE);
                }
                String handleresult = "";
                if ("1".equals(baseResultDto.getCode())) {
                    handleresult = CommonEnum.HANDLE_REUSLT.success.getValue();
                } else {
                    handleresult = CommonEnum.HANDLE_REUSLT.failed.getValue();
                }
                PlatFormNormalLog platFormNormalLog = new PlatFormNormalLog(UidUtils.getId(), mobileUser.getId(), mobileUser.getUserName(), "用户登录", handleip, "手机用户登录", handleresult, new Date(), new Date());
                platFormLogService.insertNormalLog(platFormNormalLog);
            } else {
                baseResultDto.setCode(ResultMessage.FAILED_CODE);
                baseResultDto.setMsg(ResultMessage.FAILED_THIRD_LOGIN_ACCOUNTNOTEXIST_MESSAGE);
            }
        } else {
            baseResultDto.setCode(ResultMessage.FAILED_CODE);
            baseResultDto.setMsg(ResultMessage.FAILED_THIRD_LOGIN_WEIXIN_NOTEXIST_MESSAGE);
        }
        return baseResultDto;
    }

    /**
     * QQ登录
     *
     * @param qq
     * @return
     */
    @Override
    public BaseResultDto mobieUserByQQ(String qq, String handleip) {
        BaseResultDto baseResultDto = new BaseResultDto();
        if (StringUtils.isNotBlank(qq)) {
            MobileUser mobileUser = platFormSysUserLoginDao.getMobieUserByQQ(qq);
            if (null != mobileUser) {
                String accountId = mobileUser.getAccountId();
                Integer expireTime = Integer.parseInt(loginExpireTime);
                String token = JwtUtils.createToken(accountId);
                boolean setkeyresult = redisClusterHelper.setKey(token, accountId, expireTime);
                if (setkeyresult) {
                    baseResultDto = tokenResult(token, accountId);
                } else {
                    baseResultDto.setCode(ResultMessage.FAILED_CODE);
                    baseResultDto.setMsg(ResultMessage.FAILED_LOGIN_TOKENERROR_MESSAGE);
                }
                String handleresult = "";
                if ("1".equals(baseResultDto.getCode())) {
                    handleresult = CommonEnum.HANDLE_REUSLT.success.getValue();
                } else {
                    handleresult = CommonEnum.HANDLE_REUSLT.failed.getValue();
                }
                PlatFormNormalLog platFormNormalLog = new PlatFormNormalLog(UidUtils.getId(), mobileUser.getId(), mobileUser.getUserName(), "用户登录", handleip, "手机用户QQ登录", handleresult, new Date(), new Date());
                platFormLogService.insertNormalLog(platFormNormalLog);
            } else {
                baseResultDto.setCode(ResultMessage.FAILED_CODE);
                baseResultDto.setMsg(ResultMessage.FAILED_THIRD_LOGIN_ACCOUNTNOTEXIST_MESSAGE);
            }
        } else {
            baseResultDto.setCode(ResultMessage.FAILED_CODE);
            baseResultDto.setMsg(ResultMessage.FAILED_THIRD_LOGIN_QQ_NOTEXIST_MESSAGE);
        }
        return baseResultDto;
    }

    /**
     * 微博登录
     *
     * @param weibo
     * @return
     */
    @Override
    public BaseResultDto mobieUserByWeiBo(String weibo, String handleip) {
        BaseResultDto baseResultDto = new BaseResultDto();
        if (StringUtils.isNotBlank(weibo)) {
            MobileUser mobileUser = platFormSysUserLoginDao.getMobieUserByWeiBo(weibo);
            if (null != mobileUser) {
                String accountId = mobileUser.getAccountId();
                Integer expireTime = Integer.parseInt(loginExpireTime);
                String token = JwtUtils.createToken(accountId);
                boolean setkeyresult = redisClusterHelper.setKey(token, accountId, expireTime);
                if (setkeyresult) {
                    baseResultDto = tokenResult(token, accountId);
                } else {
                    baseResultDto.setCode(ResultMessage.FAILED_CODE);
                    baseResultDto.setMsg(ResultMessage.FAILED_LOGIN_TOKENERROR_MESSAGE);
                }
                String handleresult = "";
                if ("1".equals(baseResultDto.getCode())) {
                    handleresult = CommonEnum.HANDLE_REUSLT.success.getValue();
                } else {
                    handleresult = CommonEnum.HANDLE_REUSLT.failed.getValue();
                }
                PlatFormNormalLog platFormNormalLog = new PlatFormNormalLog(UidUtils.getId(), mobileUser.getId(), mobileUser.getUserName(), "用户登录", handleip, "手机用户微博登录", handleresult, new Date(), new Date());
                platFormLogService.insertNormalLog(platFormNormalLog);
            } else {
                baseResultDto.setCode(ResultMessage.FAILED_CODE);
                baseResultDto.setMsg(ResultMessage.FAILED_THIRD_LOGIN_ACCOUNTNOTEXIST_MESSAGE);
            }
        } else {
            baseResultDto.setCode(ResultMessage.FAILED_CODE);
            baseResultDto.setMsg(ResultMessage.FAILED_THIRD_LOGIN_WEIBO_NOTEXIST_MESSAGE);
        }
        return baseResultDto;
    }

    /**
     * 手机登录
     *
     * @param telphone
     * @param captcha
     * @return
     */
    @Override
    public BaseResultDto telphoneLogin(String telphone, String captcha) {
        BaseResultDto baseResultDto = new BaseResultDto();
        BaseResultDto checkResult = platFormSendSmsService.checKSms(telphone, captcha);
        if (ResultMessage.SUCCESS_CODE.equals(checkResult.getCode())) {
            MobileUser mobileUser = platFormSysUserLoginDao.getMobieUserByTelPhone(telphone);
            if (null != mobileUser) {
                String accountId = mobileUser.getAccountId();
                Integer expireTime = Integer.parseInt(loginExpireTime);
                String token = JwtUtils.createToken(accountId);
                boolean setkeyresult = redisClusterHelper.setKey(token, accountId, expireTime);
                if (setkeyresult) {
                    baseResultDto = tokenResult(token, accountId);
                } else {
                    baseResultDto.setCode(ResultMessage.FAILED_CODE);
                    baseResultDto.setMsg(ResultMessage.FAILED_LOGIN_TOKENERROR_MESSAGE);
                }
            } else {
                baseResultDto.setCode(ResultMessage.FAILED_CODE);
                baseResultDto.setMsg(ResultMessage.FAILED_THIRD_LOGIN_ACCOUNTNOTEXIST_MESSAGE);
            }
        } else {
            baseResultDto.setCode(ResultMessage.FAILED_CODE);
            baseResultDto.setMsg(checkResult.getMsg());
        }
        return baseResultDto;
    }

    /**
     * 手机发送验证码
     *
     * @param telphone
     * @return
     */
    @Override
    public BaseResultDto sendSms(String telphone) {
        BaseResultDto baseResultDto = new BaseResultDto();
        MobileUser mobileUser = platFormSysUserLoginDao.getMobieUserByTelPhone(telphone);
        if (null != mobileUser) {
            BaseResultDto sendResult = platFormSendSmsService.sendSms(telphone);
            if (ResultMessage.SUCCESS_CODE.equals(sendResult.getCode())) {
                baseResultDto.setCode(ResultMessage.SUCCESS_CODE);
                baseResultDto.setMsg(ResultMessage.SUCCESS_MESSAGE);
            } else {
                baseResultDto.setCode(ResultMessage.FAILED_CODE);
                baseResultDto.setMsg(sendResult.getMsg());
            }
        } else {
            baseResultDto.setCode(ResultMessage.FAILED_CODE);
            baseResultDto.setMsg(ResultMessage.FAILED_THIRD_LOGIN_ACCOUNTNOTEXIST_MESSAGE);
        }
        return baseResultDto;
    }

    /**
     * 用户登出
     *
     * @param request
     * @return
     */
    @Override
    public BaseResultDto userLogout(String token) {
        BaseResultDto baseResultDto = new BaseResultDto();

        boolean logoutResult = redisClusterHelper.delKey(token);
        if (!logoutResult) {
            baseResultDto.setCode(ResultMessage.SUCCESS_CODE);
            baseResultDto.setMsg(ResultMessage.SUCCESS_MESSAGE);
        } else {
            baseResultDto.setCode(ResultMessage.FAILED_CODE);
            baseResultDto.setMsg(ResultMessage.FAILED_MESSAGE);
        }
        return baseResultDto;
    }

    /**
     * 生成token结果
     *
     * @param token
     * @param accountId
     * @return
     */
    public BaseResultDto tokenResult(String token, String accountId) {
        BaseResultDto baseResultDto = new BaseResultDto();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put(CommonEnum.HEAD_PARAM.TOKEN.getValue(), token);
        resultMap.put("account", accountId);
        baseResultDto.setData(resultMap);
        baseResultDto.setCode(ResultMessage.SUCCESS_CODE);
        baseResultDto.setMsg(ResultMessage.SUCCESS_MESSAGE);
        return baseResultDto;
    }

}
