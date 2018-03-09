package com.haho.druid.entity;

import java.io.Serializable;

public class DruidResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    /* 成功返回码 */
    public static final String RESULT_CODE_SUCCESS = "0";

    /** 未知错误返回码 */
    public static final String RESULT_CODE_FAIL = "-1";

    /** 返回code */
    private String code = RESULT_CODE_SUCCESS;

    /* 返回消息 */
    private String message;

    /* druid返回结果 ,JSON格式 */
    private String attatch;

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

    public String getAttatch() {
        return attatch;
    }

    public void setAttatch(String attatch) {
        this.attatch = attatch;
    }

    /**
     * 判断返回结果中响应码是否成功
     */
    public boolean isSuccess() {
        return RESULT_CODE_SUCCESS.equals(code);
    }

    /**
     * 返回错误信息，标记响应码为-1
     */
    public DruidResponse error(String message) {
        return error(RESULT_CODE_FAIL, message);
    }


    /**
     * 返回错误信息，标记响应码为具体的错误码
     */
    public DruidResponse error(String code, String message) {
        this.code = code;
        this.message = message;
        return this;
    }

    @Override
    public String toString() {
        return "DruidResponse [code=" + code + ", message=" + message + ", attatch=" + attatch + "]";
    }
}
