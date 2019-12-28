package com.msl.community.dto;

import lombok.Data;

/**
 * @Author:msl
 * @Description: Date:Created in 12:50 2019/12/14
 *
 * 在Github中 我拿到的api中 头像的json格式 "avatar_url": "https://avatars0.githubusercontent.com/u/58620379?v=4",
 * 在fastjson中 可以自动把avatar_url跟驼峰命名法映射
 */
@Data
public class GithubUser {
    //用户名
    private String name;
    //id
    private Long id;
    //个性签名
    private String bio;
    //头像
    private String avatarUrl;

}
