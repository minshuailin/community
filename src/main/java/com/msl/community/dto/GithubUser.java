package com.msl.community.dto;

import lombok.Data;

/**
 * @Author:msl
 * @Description: Date:Created in 12:50 2019/12/14
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
    private String avatar_url;


}
