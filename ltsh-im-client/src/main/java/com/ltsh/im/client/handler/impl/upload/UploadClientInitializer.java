package com.ltsh.im.client.handler.impl.upload;

import com.ltsh.im.common.entity.BaseReq;
import com.ltsh.im.common.entity.UploadFileReq;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

public class UploadClientInitializer extends ChannelInitializer<Channel> {
//    private BaseReq req;
//    public UploadClientInitializer(BaseReq req) {
//        super();
//        this.req = req;
//    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ch.pipeline().addLast(new ObjectEncoder());
        ch.pipeline().addLast(new ObjectDecoder(ClassResolvers.weakCachingConcurrentResolver(null)));
        ch.pipeline().addLast(new FileUploadClientHandler());
    }

}
