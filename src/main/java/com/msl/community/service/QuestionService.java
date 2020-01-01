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
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalPage;

        Integer totalCount = questionMapper.count();

        if(totalCount % size == 0){
            totalPage = totalCount /size;
        }else {
            totalPage = totalCount /size +1;
        }
        if(page < 1){
            page = 1;
        }
        if(page > totalPage){
            page = totalPage;
        }
        paginationDTO.setPagination(totalPage,page);

        //size*(page -1)
        Integer offset = size * (page -1);
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
    public PaginationDTO list(Integer userId,Integer page,Integer size){
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalPage;
        Integer totalCount = questionMapper.countByUserId(userId);

        if(totalCount % size == 0){
            totalPage = totalCount /size;
        }else {
            totalPage = totalCount /size +1;
        }
        if(page<1){
            page = 1;
        }
        if(page>totalPage){
            page = totalPage;
        }
        paginationDTO.setPagination(totalPage,page);

        Integer offset = size*(page -1);
        List<Question> questionList = questionMapper.listByUserId(userId,offset,size);
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

    public QuesetionDTO getById(Integer id) {
        Question question =questionMapper.getById(id);
        QuesetionDTO quesetionDTO = new QuesetionDTO();
        //把question上的属性copy到quesetionDTO上
        BeanUtils.copyProperties(question,quesetionDTO);
        User user = userMapper.findById(question.getCreator());
        quesetionDTO.setUser(user);

        return quesetionDTO;
    }
}
