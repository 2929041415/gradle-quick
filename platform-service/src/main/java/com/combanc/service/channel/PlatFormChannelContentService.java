package com.combanc.service.channel;

import com.combanc.entity.channel.PlatFormChannelContent;
import com.combanc.entity.common.BaseResultDto;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface PlatFormChannelContentService {

    BaseResultDto addContent(PlatFormChannelContent platFormChannelContent, CommonsMultipartFile[] channelFileList);

    BaseResultDto editContent(PlatFormChannelContent platFormChannelContent, CommonsMultipartFile[] channelFileList, String[] deleteIdList);

    BaseResultDto delContent(PlatFormChannelContent platFormChannelContent);

    BaseResultDto contentList(PlatFormChannelContent platFormChannelContent);

    BaseResultDto getContentFileList(String id);

    void getFileList(String id, HttpServletResponse response);
}
