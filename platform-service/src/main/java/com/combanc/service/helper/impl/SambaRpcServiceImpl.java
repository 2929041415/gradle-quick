package com.combanc.service.helper.impl;

import com.combanc.entity.common.BaseResultDto;
import com.combanc.entity.common.ResultMessage;
import com.combanc.service.helper.SambaRpcService;
import com.combanc.utils.smab.SambaHelper;
import com.combanc.utils.smab.SambaUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service(value = "sambaRpcService")
public class SambaRpcServiceImpl implements SambaRpcService {


    @Value("${samba.username}")
    private String sambausername;

    @Value("${samba.password}")
    private String sambapassword;

    @Value("${samba.host}")
    private String sambahost;

    @Value("${samba.target}")
    private String sambatarget;

    @Override
    public BaseResultDto uploadFileRpc(String filePath, String sourcePath) {

        BaseResultDto baseResultDto = new BaseResultDto();
        if (StringUtils.isNotBlank(filePath) && StringUtils.isNotBlank(sourcePath)) {
            try {
                SambaUtils.uploadFile(sambausername, sambapassword, sambahost, filePath, sambatarget + sourcePath);
                baseResultDto.setMsg(ResultMessage.SUCCESS_CODE);
                baseResultDto.setCode(ResultMessage.SUCCESS_MESSAGE);
            } catch (IOException e) {
                e.printStackTrace();
                baseResultDto.setMsg(ResultMessage.FAILED_MESSAGE);
                baseResultDto.setCode(ResultMessage.FAILED_CODE);
            }
        } else {
            baseResultDto.setMsg(ResultMessage.FAILED_MESSAGE);
            baseResultDto.setCode(ResultMessage.FAILED_CODE);

        }
        return baseResultDto;
    }

    @Override
    public InputStream getSambaFileRpc(String filePath, String targetPath) {
        return null;
    }

    @Override
    public InputStream getSambaFileOutRpc(String filePath, String filename) {
        InputStream inputStream = null;
        try {
            inputStream = SambaHelper.downloadFileOutInputStream(sambausername, sambapassword, sambahost, sambatarget + filePath, filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inputStream;
    }

    @Override
    public InputStream getSambaFileOutOpenRpc(String filePath) {
        InputStream inputStream = null;
        try {
            inputStream = SambaHelper.downloadFileOutOpenInputStream(sambausername, sambapassword, sambahost, sambatarget + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inputStream;
    }

    @Override
    public BaseResultDto showeImageRpc(String remoteUrl) {
        BaseResultDto baseResultDto = new BaseResultDto();
        try {
            SambaUtils.showImage(sambausername, sambapassword, sambahost, sambatarget + remoteUrl);
            baseResultDto.setMsg(ResultMessage.SUCCESS_CODE);
            baseResultDto.setCode(ResultMessage.SUCCESS_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            baseResultDto.setMsg(ResultMessage.FAILED_MESSAGE);
            baseResultDto.setCode(ResultMessage.FAILED_CODE);
        }
        return baseResultDto;
    }

    @Override
    public InputStream showAppImageRpc(byte[] webapppicsmall) {
        InputStream in = new ByteArrayInputStream(webapppicsmall);
        return in;
    }
}
