package com.msl.community.dto;

/**
 * @Author:msl
 * @Description: Date:Created in 11:44 2019/12/14
 *
 * 步骤1是请求用户的GitHub身份：
 * https://github.com/login/oauth/authorize 加上指定的参数
 *
 * https://github.com/login/oauth/authorize?client_id=f786a53908a42504fb30&&redirect_uri=http://localhost:8887/callback&&scope=user&&state=1
 */
public class AccessTokenDTO {

    private String client_id; //从GitHub收到的GitHub App的客户端ID
    private String client_secret; //从GitHub收到的GitHub App的客户密码
    private String code; //收到的作为对步骤1的响应的代码
    private String redirect_uri; //授权后将用户发送到应用程序中的URL
    private String state; //步骤1中提供的无法猜测的随机字符串

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRedirect_uri() {
        return redirect_uri;
    }

    public void setRedirect_uri(String redirect_uri) {
        this.redirect_uri = redirect_uri;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
