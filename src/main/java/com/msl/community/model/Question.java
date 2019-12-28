package com.msl.community.model;

import lombok.Data;

@Data
public class Question {
    private Integer id;
    //标题
    private String title;
    //描述
    private String description;
    //标签
    private String tag;
    //创建时间
    private Long gmtCreate;
    //修改时间
    private Long gmtModified;
    //发起人
    private Integer creator;
    //浏览量
    private Integer viewCount;
    //评论数
    private Integer commentCount;
    //点赞数
    private Integer likeCount;

}
