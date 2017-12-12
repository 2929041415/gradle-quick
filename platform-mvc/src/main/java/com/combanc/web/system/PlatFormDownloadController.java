package com.combanc.web.system;

import com.combanc.service.system.PlatFormDownloadService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.io.IOException;

@Controller
@RequestMapping(value = "/platformDownload")
public class PlatFormDownloadController {


    @Resource
    private PlatFormDownloadService platFormDownloadService;

    @RequestMapping("/downloadregister")
    public ResponseEntity<byte[]> download(String filename) throws IOException {
        return platFormDownloadService.downloadRegisterDoc(filename);
    }
}
