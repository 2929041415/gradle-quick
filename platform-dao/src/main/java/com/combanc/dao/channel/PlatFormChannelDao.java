package com.combanc.dao.channel;


import com.combanc.entity.channel.PlatFormChannel;

import java.util.List;

public interface PlatFormChannelDao {

    Integer addChannel(PlatFormChannel platFormChannel);

    Integer editChannel(PlatFormChannel platFormChannel);

    Integer delChannel(PlatFormChannel platFormChannel);

    List<PlatFormChannel> channelList(PlatFormChannel platFormChannel);

    Integer channelListCount(PlatFormChannel platFormChannel);

    List<PlatFormChannel> channelRootList(PlatFormChannel platFormChannel);

    Integer channelRootListCount(PlatFormChannel platFormChannel);

}
