package com.ltsh.im.common.entity;

public class Result {
    private String code;
    private String message;
    private boolean isBack = false;

    public Result() {
        this.code = "000000";
        this.message = "成功";
    }

    public Result(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public boolean isBack() {
        return isBack;
    }

    public void setBack(boolean back) {
        isBack = back;
    }

    public boolean successful() {
        if(code != null && code.equals("000000")) {
            return true;
        }
        return false;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

