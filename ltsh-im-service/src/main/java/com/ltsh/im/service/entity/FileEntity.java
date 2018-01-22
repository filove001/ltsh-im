package com.ltsh.im.service.entity;

import java.io.File;
import java.io.Serializable;

public class FileEntity implements Serializable {
    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 文件
     */
    private File file;

    public void delFile() {
        file.delete();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
