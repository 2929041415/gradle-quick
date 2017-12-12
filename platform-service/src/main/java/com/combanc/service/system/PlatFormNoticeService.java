package com.combanc.service.system;

import com.combanc.entity.api.PlatFormNotice;
import com.combanc.entity.common.BaseResultDto;

public interface PlatFormNoticeService {
    BaseResultDto addNotice(PlatFormNotice platFormNotice);

    BaseResultDto deleteNotice(String id);

    BaseResultDto queryNotice(PlatFormNotice platFormNotice);

    BaseResultDto updateNotice(PlatFormNotice platFormNotice);

    BaseResultDto noticeDetail(PlatFormNotice platFormNotice);
}
