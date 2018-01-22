package com.ltsh.im.common.entity;

import java.io.Serializable;

public class BaseReq implements Serializable {
    /**
     * 签名信息
     */
    private String signInfo;
    /**
     * 时间戳
     */
    private String timestamp;
    /**
     * 请求流水号
     */
    private String keep;

    public String getKeep() {
        return keep;
    }

    public void setKeep(String keep) {
        this.keep = keep;
    }

    public String getSignInfo() {
        return signInfo;
    }

    public void setSignInfo(String signInfo) {
        this.signInfo = signInfo;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
