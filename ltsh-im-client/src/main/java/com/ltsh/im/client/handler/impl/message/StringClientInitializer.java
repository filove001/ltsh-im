package com.ltsh.im.client.handler.impl.message;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

public class StringClientInitializer extends ChannelInitializer<Channel> {
    private String req;
    public StringClientInitializer(String req) {
        super();
        this.req = req;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ch.pipeline().addLast(new ObjectEncoder());
        ch.pipeline().addLast(new ObjectDecoder(ClassResolvers.weakCachingConcurrentResolver(null)));
        ch.pipeline().addLast(new StringClientHandler(req));
    }
}
