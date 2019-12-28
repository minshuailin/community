package com.msl.community.mapper;

import com.msl.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;


/**
 * @Author:msl
 * @Description: Date:Created in 17:03 2019/12/14
 */
@Component
@Mapper
public interface UserMapper {

    @Insert("insert into user(name,account_id,token,gmt_create,gmt_modified,avatar_url) " +
            "values(#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insert (User user);

    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);
}
