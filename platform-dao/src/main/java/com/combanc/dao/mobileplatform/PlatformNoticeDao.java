package com.combanc.dao.mobileplatform;


import com.combanc.entity.mobileplatform.NoticeModel;

import java.util.List;

public interface PlatformNoticeDao {

    int addNotice(NoticeModel noticeModel);

    NoticeModel noticeDetail(NoticeModel noticeModel);

    int release(NoticeModel noticeModel);

    int noticeUpdate(NoticeModel noticeModel);

    List<NoticeModel> noticeList(NoticeModel noticeModel);

    int noticeListCount(NoticeModel noticeModel);

}
