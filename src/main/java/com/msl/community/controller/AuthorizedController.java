package com.msl.community.controller;

import com.msl.community.dto.AccessTokenDTO;
import com.msl.community.dto.GithubUser;
import com.msl.community.mapper.UserMapper;
import com.msl.community.model.User;
import com.msl.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;


/**
 * @Author:msl
 * @Description: Date:Created in 11:30 2019/12/14
 *
 * 授权github登录后，要跳转的页面
 */
@Controller
public class AuthorizedController {

    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private UserMapper userMapper;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code")String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request, HttpServletResponse response){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();

        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);

        String accessToken = githubProvider.getAccessToken(accessTokenDTO);//拿到当前用户从github那里返回的token
        GithubUser githubUser = githubProvider.getUser(accessToken);
//      System.out.println(user.getName());

        if(githubUser !=null && githubUser.getId() != null){
            //将user的信息放到数据库中
            User user = new User();
            user.setName(githubUser.getName());
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            user.setAvatarUrl(githubUser.getAvatarUrl());
            userMapper.insert(user);

            //设置cookie

            response.addCookie(new Cookie("token",token));

            return "redirect:/";
        }else{
            //登录失败
            return "redirect:/";
        }
    }
}
