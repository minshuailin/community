package com.msl.community.dto;

import lombok.Data;

/**
 * @Author:msl
 * @Description: Date:Created in 11:44 2019/12/14
 *
 * 步骤1是请求用户的GitHub身份：
 * https://github.com/login/oauth/authorize 加上指定的参数
 *
 * https://github.com/login/oauth/authorize?client_id=f786a53908a42504fb30&&redirect_uri=http://localhost:8887/callback&&scope=user&&state=1
 */
@Data
public class AccessTokenDTO {

    private String client_id; //从GitHub收到的GitHub App的客户端ID
    private String client_secret; //从GitHub收到的GitHub App的客户密码
    private String code; //收到的作为对步骤1的响应的代码
    private String redirect_uri; //授权后将用户发送到应用程序中的URL
    private String state; //步骤1中提供的无法猜测的随机字符串


}
