package com.ltsh.im.service.handler.impl;

import com.ltsh.common.util.JsonUtils;
import com.ltsh.im.common.entity.Result;
import com.ltsh.im.common.entity.StringMessageReq;
import com.ltsh.im.common.entity.UploadFileReq;
import com.ltsh.im.service.entity.FileEntity;
import com.ltsh.im.service.handler.LtshBusinessHandler;
import com.ltsh.im.service.handler.LtshSecurityHandler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;

import java.io.File;
import java.io.RandomAccessFile;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class FileUploadServerHandler extends ChannelInboundHandlerAdapter {
    private int byteRead;
    private volatile int start = 0;
    private String tempFileDir = "E:\\temp\\";
    private LtshSecurityHandler ltshSecurityHandler;
    private LtshBusinessHandler ltshBusinessHandler;
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof UploadFileReq) {
            System.out.println("开始文件上传");
            UploadFileReq req = (UploadFileReq) msg;
            if(ltshSecurityHandler != null) {
                ltshSecurityHandler.verify(req);
            }

            byte[] bytes = req.getBytes();
            byteRead = req.getEndPos();
            String fileName = req.getFileName();//文件名
            File dirFile = new File(tempFileDir);
            if(!dirFile.exists()) {
                dirFile.mkdirs();
            }
            String path = tempFileDir + File.separator + fileName;
            File file = new File(path);

            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            randomAccessFile.seek(start);
            randomAccessFile.write(bytes);
            start = start + byteRead;
            if (byteRead > 0) {
                ctx.writeAndFlush(start);
            } else {
                randomAccessFile.close();
                FileEntity fileEntity  = new FileEntity();
                fileEntity.setFileName(fileName);
                fileEntity.setFile(file);
                if(ltshBusinessHandler != null) {
                    Result result = ltshBusinessHandler.run(fileEntity);
                    if(result.isBack()) {
                        ByteBuf content = ctx.alloc().buffer();
                        content.writeBytes("ok".getBytes("utf-8"));
//                        ByteBufUtil.writeAscii(content, " - via HTTP/2");
                        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, content);
                        ctx.writeAndFlush(response);
                        ctx.flush();
                        ctx.close();
                        return;
                    }
                }
                ctx.writeAndFlush(new Result());
                ctx.flush();
                ctx.close();
            }
        } else if(msg instanceof StringMessageReq) {
            StringMessageReq stringMessageReq = (StringMessageReq) msg;

            System.out.println("client message:" + stringMessageReq.getContent());

            ctx.writeAndFlush(new Result());
            ctx.flush();
            ctx.close();
        } else{
            System.out.println(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
