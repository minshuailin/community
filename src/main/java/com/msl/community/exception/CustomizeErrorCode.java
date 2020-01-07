package com.msl.community.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND(2001,"你找的问题不存在，请不要再试了！"),
    TARGET_PARAM_NOT_FOUND(2002,"未选中任何问题或评论进行回复"),
    NO_LOGIN(2003,"当前操作需要登陆!!!"),
    SYS_ERROR(2004,"服务器出错了！！！"),
    TYPE_PARAM_WRONG(2005,"评论类型错误或不存在！！！"),
    COMMENT_NOT_FOUND(2006,"你找的评论不存在，请不要再试了！"),
    COMMENT_IS_EMPTY(2007,"输入内容不能为空"),

    ;
    private Integer code;
    private String message;

    CustomizeErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }
    @Override
    public Integer getCode() {
        return code;
    }
}
