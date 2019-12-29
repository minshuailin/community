package com.msl.community.service;

import com.msl.community.dto.PaginationDTO;
import com.msl.community.dto.QuesetionDTO;
import com.msl.community.mapper.QuestionMapper;
import com.msl.community.mapper.UserMapper;
import com.msl.community.model.Question;
import com.msl.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    public PaginationDTO list(Integer page, Integer size) {
        //分页
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = questionMapper.count(); //问题的总数
        paginationDTO.setPagination(totalCount,page,size);
        if(page<1){
            page = 1;
        }
        if(page>paginationDTO.getTotalPage()){
            page = paginationDTO.getTotalPage();
        }

        //size*(page -1)
        Integer offset = size*(page -1);
        List<Question> questionList = questionMapper.list(offset,size);
        List<QuesetionDTO> questionDTOList = new ArrayList<>();

        for(Question question :questionList){
            User user = userMapper.findById(question.getCreator());
            QuesetionDTO quesetionDTO = new QuesetionDTO();
            //把question上的属性copy到quesetionDTO上
            BeanUtils.copyProperties(question,quesetionDTO);
            quesetionDTO.setUser(user);
            questionDTOList.add(quesetionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }
}
