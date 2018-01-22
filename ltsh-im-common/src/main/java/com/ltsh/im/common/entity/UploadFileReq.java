package com.ltsh.im.common.entity;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class UploadFileReq extends BaseReq {
    private File file;
    private String fileName;// 文件名
    private String fileMd5;// 文件md5
    private int starPos;// 开始位置
    private byte[] bytes;// 文件字节数组
    private int endPos;// 结尾位置
    private Map<String, Object> extraParams = new HashMap<String, Object>();

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public int getStarPos() {
        return starPos;
    }

    public void setStarPos(int starPos) {
        this.starPos = starPos;
    }

    public int getEndPos() {
        return endPos;
    }

    public void setEndPos(int endPos) {
        this.endPos = endPos;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public Map<String, Object> getExtraParams() {
        return extraParams;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileMd5() {
        return fileMd5;
    }

    public void setFileMd5(String fileMd5) {
        this.fileMd5 = fileMd5;
    }
}