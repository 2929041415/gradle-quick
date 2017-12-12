package com.combanc.service.mobileplatform;

import com.combanc.entity.common.BaseResultDto;
import com.combanc.entity.mobileplatform.NoticeModel;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public interface PlatformNoticeService {

     BaseResultDto addNotice(NoticeModel noticeModel,CommonsMultipartFile[] attachments);
     BaseResultDto noticeList(NoticeModel noticeModel);
     BaseResultDto noticeDetail(NoticeModel noticeModel);
     BaseResultDto release(NoticeModel noticeModel);
     BaseResultDto noticeUpdate(NoticeModel noticeModel);



}
