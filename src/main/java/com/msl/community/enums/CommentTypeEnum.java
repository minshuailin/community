package com.msl.community.enums;

public enum CommentTypeEnum {
    QUESTION(1), //问题：1
    COMMENT(2); //评论：2

    private Integer type;

    CommentTypeEnum(Integer type) {
        this.type = type;
    }
    public Integer getType() {
        return type;
    }

    public static boolean isExist(Integer type) {
        for (CommentTypeEnum value : CommentTypeEnum.values()) {
            if(value.getType() == type){
                return true;
            }
        }
        return false;
    }

}
