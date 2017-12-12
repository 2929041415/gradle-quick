package com.combanc.dao.mobileplatform;

import com.combanc.entity.mobileplatform.LostFound;
import com.combanc.entity.mobileplatform.LostFoundReview;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PlatformLostFoundDao {

    int addLostFound(LostFound lostFound);

    List<LostFound> lostFoundList(LostFound lostFound);

    int lostFoundListCount(LostFound lostFound);

    LostFound lostFoundDetail(LostFound lostFound);

    int lostFoundReview(LostFoundReview lostFoundReview);

    List<LostFoundReview> reviewList(LostFoundReview lostFoundReview);

    int reviewListCount(LostFoundReview lostFoundReview);

    int deleteLostFound(@Param(value = "ids") String ids[]);

    int deleteReview(@Param(value = "ids") String ids[]);

}
