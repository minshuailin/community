package com.msl.community.dto;

import com.msl.community.model.User;
import lombok.Data;

@Data
public class CommentDTO {
    private Long id;
    private Long parentId; //当前问题的id
    private Integer type;//当前评论的类型 1 ：问题的评论 2 ：评论的评论
    private Long commentator;//评论人
    private Long gmtCreate;//评论创建时间
    private Long gmtModified;//评论修改时间
    private Long likeCount;//点赞数
    private String content;//评论内容
    private User user;

}
