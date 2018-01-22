package com.ltsh.im.client.handler.impl.message;

import com.ltsh.common.util.JsonUtils;
import com.ltsh.im.common.entity.UploadFileReq;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class StringClientHandler extends SimpleChannelInboundHandler<String> {
    private String str;
    public StringClientHandler(String str) {
        this.str = str;
    }
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

        System.out.println("Server say : " + JsonUtils.toJson(msg));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Client active ");
        super.channelActive(ctx);
        System.out.println("发送消息是:" + str);
        ctx.write(str);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Client close ");
        super.channelInactive(ctx);
    }
}