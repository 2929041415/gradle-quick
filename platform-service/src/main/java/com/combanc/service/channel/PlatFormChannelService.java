package com.combanc.service.channel;

import com.combanc.entity.channel.PlatFormChannel;
import com.combanc.entity.common.BaseResultDto;

public interface PlatFormChannelService {

    BaseResultDto addChannel(PlatFormChannel platFormChannel);

    BaseResultDto editChannel(PlatFormChannel platFormChannel);

    BaseResultDto delChannel(PlatFormChannel platFormChannel);

    BaseResultDto channelList(PlatFormChannel platFormChannel);

    BaseResultDto channelRootList(PlatFormChannel platFormChannel);

    BaseResultDto channelTree(PlatFormChannel platFormChannel);

}
