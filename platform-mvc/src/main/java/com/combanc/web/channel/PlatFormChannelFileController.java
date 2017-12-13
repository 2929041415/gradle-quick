package com.combanc.web.channel;

import com.combanc.entity.channel.PlatFormChannelFile;
import com.combanc.service.channel.PlatFormChannelContentService;
import com.combanc.service.helper.SambaRpcService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

@Controller
@RequestMapping(value = "/platformChannelFile")
public class PlatFormChannelFileController {
    @Resource
    private PlatFormChannelContentService platFormChannelContentService;

    @Resource
    private SambaRpcService sambaRpcService;

    @RequestMapping(method = RequestMethod.GET, value = "/getFileList", produces = "application/json;charset=utf-8")
    public void getFileList(HttpServletResponse response, String id) {
        List<PlatFormChannelFile> fileList = platFormChannelContentService.getFileList(id);
        if (fileList != null && fileList.size() > 0) {
            for (PlatFormChannelFile file : fileList) {
                String filename = file.getFileoldname();
                InputStream inputStream = sambaRpcService.getSambaFileOutRpc(file.getFilepath(), filename);
                OutputStream out = null;
                try {
                    out = new BufferedOutputStream(response.getOutputStream());
                    // 缓冲内存
                    byte[] buffer = new byte[1024];
                    while (inputStream.read(buffer) != -1) {
                        out.write(buffer);
                        buffer = new byte[1024];
                    }
                    // 最后关闭流
                    out.close();
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (null != out) {
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (null != inputStream) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
