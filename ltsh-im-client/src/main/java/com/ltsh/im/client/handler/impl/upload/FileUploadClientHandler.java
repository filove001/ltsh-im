package com.ltsh.im.client.handler.impl.upload;

import com.ltsh.common.util.JsonUtils;
import com.ltsh.im.common.entity.BaseReq;
import com.ltsh.im.common.entity.Result;
import com.ltsh.im.common.entity.StringMessageReq;
import com.ltsh.im.common.entity.UploadFileReq;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileUploadClientHandler extends ChannelInboundHandlerAdapter {
    private int byteRead;
    private volatile int start = 0;
    private volatile int lastLength = 0;
    public RandomAccessFile randomAccessFile;
//    private BaseReq req;

//    public FileUploadClientHandler(BaseReq req) {
//        this.req = req;
//    }

    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println();
//        if(req instanceof UploadFileReq) {
//            UploadFileReq uploadFileReq = (UploadFileReq) this.req;
//            try {
//                randomAccessFile = new RandomAccessFile(uploadFileReq.getFile(), "r");
//                randomAccessFile.seek(uploadFileReq.getStarPos());
//                lastLength = (int) randomAccessFile.length() / 10;
//                byte[] bytes = new byte[lastLength];
//                if ((byteRead = randomAccessFile.read(bytes)) != -1) {
//                    uploadFileReq.setEndPos(byteRead);
//                    uploadFileReq.setBytes(bytes);
//                    ctx.writeAndFlush(uploadFileReq);
//                } else {
//                    System.out.println("文件已经读完");
//                    ctx.close();
//                }
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException i) {
//                i.printStackTrace();
//            }
//        } else if(req instanceof StringMessageReq) {
//            StringMessageReq stringMessageReq = (StringMessageReq) this.req;
//            ctx.write(stringMessageReq);
//            ctx.close();
//        }

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(msg);
        if(msg instanceof Result) {
            System.out.println(JsonUtils.toJson(msg));
        }
//        if(req instanceof UploadFileReq) {
//            UploadFileReq uploadFileReq = (UploadFileReq) this.req;
//            if (msg instanceof Integer) {
//                start = (Integer) msg;
//                if (start != -1) {
//                    randomAccessFile = new RandomAccessFile(uploadFileReq.getFile(), "r");
//                    randomAccessFile.seek(start);
//                    System.out.println("块儿长度：" + (randomAccessFile.length() / 10));
//                    System.out.println("长度：" + (randomAccessFile.length() - start));
//                    int a = (int) (randomAccessFile.length() - start);
//                    int b = (int) (randomAccessFile.length() / 10);
//                    if (a < b) {
//                        lastLength = a;
//                    }
//                    byte[] bytes = new byte[lastLength];
//                    System.out.println("-----------------------------" + bytes.length);
//                    if ((byteRead = randomAccessFile.read(bytes)) != -1 && (randomAccessFile.length() - start) > 0) {
//                        System.out.println("byte 长度：" + bytes.length);
//                        uploadFileReq.setEndPos(byteRead);
//                        uploadFileReq.setBytes(bytes);
//                        try {
//                            ctx.writeAndFlush(uploadFileReq);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    } else {
//                        randomAccessFile.close();
//                        ctx.close();
//                        System.out.println("文件已经读完--------" + byteRead);
//                    }
//                }
//            }
//        } else if(req instanceof StringMessageReq) {
//            if (msg instanceof String) {
//                System.out.println("服务端返回信息:" + msg);
//            }
//        }

    }

    // @Override
    // public void channelRead(ChannelHandlerContext ctx, Object msg) throws
    // Exception {
    // System.out.println("Server is speek ："+msg.toString());
    // FileRegion filer = (FileRegion) msg;
    // String path = "E://Apk//APKMD5.txt";
    // File fl = new File(path);
    // fl.createNewFile();
    // RandomAccessFile rdafile = new RandomAccessFile(path, "rw");
    // FileRegion f = new DefaultFileRegion(rdafile.getChannel(), 0,
    // rdafile.length());
    //
    // System.out.println("This is" + ++counter + "times receive server:["
    // + msg + "]");
    // }

    // @Override
    // public void channelReadComplete(ChannelHandlerContext ctx) throws
    // Exception {
    // ctx.flush();
    // }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
    // @Override
    // protected void channelRead0(ChannelHandlerContext ctx, String msg)
    // throws Exception {
    // String a = msg;
    // System.out.println("This is"+
    // ++counter+"times receive server:["+msg+"]");
    // }
}