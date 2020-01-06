package com.msl.community.dto;

import lombok.Data;

@Data
public class CommentDTO {
    private Long parentId;//当前问题的Id
    private String content;//评论内容
    private Integer type;
}
