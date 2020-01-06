package com.msl.community.mapper;

import com.msl.community.model.Question;
import com.msl.community.model.QuestionExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface QuestionExtMapper {

    int incView(Question record);
    int incCommentCount(Question record);
}