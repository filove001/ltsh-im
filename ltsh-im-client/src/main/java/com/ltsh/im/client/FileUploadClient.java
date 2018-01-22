package com.ltsh.im.client;

import com.ltsh.im.client.handler.impl.message.StringClientInitializer;
import com.ltsh.im.client.handler.impl.upload.FileUploadClientHandler;
import com.ltsh.im.client.handler.impl.upload.UploadClientInitializer;
import com.ltsh.im.common.entity.BaseReq;
import com.ltsh.im.common.entity.StringMessageReq;
import com.ltsh.im.common.entity.UploadFileReq;
import com.ltsh.im.common.utils.IoUtils;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;


import java.io.*;

public class FileUploadClient {
//    public void connect(int port, String host, final BaseReq req) throws Exception {
//        EventLoopGroup group = new NioEventLoopGroup();
//        try {
//            Bootstrap b = new Bootstrap();
//            b.group(group).channel(NioSocketChannel.class)
//                    .option(ChannelOption.TCP_NODELAY, true)
//                    .handler(new UploadClientInitializer(req));
//            ChannelFuture f = b.connect(host, port).sync();
//            f.channel().closeFuture().sync();
//        } finally {
//            group.shutdownGracefully();
//        }
//    }
    private EventLoopGroup group = null;
    private ChannelFuture channelFuture;
    private int port = 8080;
    private String host = null;
    public FileUploadClient(int port, String host) {
        this.port = port;
        this.host = host;
    }
    public Channel getChannel() throws Exception {
        if(group == null) {
            group = new NioEventLoopGroup();
        }
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new UploadClientInitializer());
            channelFuture = bootstrap.connect(host, port).sync();
            Channel channel = channelFuture.channel();
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        channel.closeFuture().sync();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }).start();
            return channel;
        } catch(Exception e) {
            group.shutdownGracefully();
            throw e;
        }
    }
    public void stop(){
//        group.shutdownGracefully();
    }
    public static void main(String[] args) {
        int port = 8080;
        String host = "127.0.0.1";
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        try {
            UploadFileReq uploadFile = new UploadFileReq();
            File file = new File("D:\\工作备份\\测试文件\\图片\\aa.jpg");
            String fileName = file.getName();// 文件名
            uploadFile.setFile(file);
            uploadFile.setFileName(fileName);
            uploadFile.setStarPos(0);// 文件开始位置
//            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");

            byte[] bytes = IoUtils.toByteArray(file);
            uploadFile.setBytes(bytes);

            FileUploadClient fileUploadClient = new FileUploadClient(port, host);
            Channel channel = fileUploadClient.getChannel();
            channel.writeAndFlush(uploadFile);
            channel.flush();
            channel.close();
            channel = fileUploadClient.getChannel();
//            fileUploadClient.connect(port, "127.0.0.1", uploadFile);
            StringMessageReq stringMessageReq = new StringMessageReq();
            stringMessageReq.setContent("你好啊");
            ChannelFuture channelFuture = channel.writeAndFlush(stringMessageReq);
            channel.flush();
            channel.close();
//            channelFuture.sync();
//            Thread.sleep(5000L);
//            fileUploadClient.connect(port, "127.0.0.1", stringMessageReq);
            System.out.println("执行完毕");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
