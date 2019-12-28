package com.msl.community.model;

import lombok.Data;

/**
 * @Author:msl
 * @Description: Date:Created in 17:05 2019/12/14
 */
@Data
public class User {

    //id
    private int id;
    //姓名
    private String name;
    private String accountId;
    //从Github中拿到的tokrn
    private String token;
    //创建时间
    private Long gmtCreate;
    //修改时间
    private Long gmtModified;
    //用户头像
    private String avatarUrl;


}
