package com.combanc.service.helper;

import com.combanc.entity.common.BaseResultDto;

import java.io.InputStream;

public interface SambaRpcService {

    BaseResultDto uploadFileRpc(String filePath, String sourcePath);

    InputStream getSambaFileRpc(String filePath, String targetPath);

    InputStream getSambaFileOutRpc(String filePath, String filename);

    InputStream getSambaFileOutOpenRpc(String filePath);

    BaseResultDto showeImageRpc(String remoteUrl);

    InputStream showAppImageRpc(byte[] webapppicsmall);

}
