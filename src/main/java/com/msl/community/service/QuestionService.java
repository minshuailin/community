package com.msl.community.service;

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

    public List<QuesetionDTO> list() {
        List<Question> questionList = questionMapper.list();
        List<QuesetionDTO> quesetionDTOList = new ArrayList<>();
        for(Question question :questionList){
            User user = userMapper.findById(question.getCreator());
            QuesetionDTO quesetionDTO = new QuesetionDTO();
            //把question上的属性copy到quesetionDTO上
            BeanUtils.copyProperties(question,quesetionDTO);
            quesetionDTO.setUser(user);
            quesetionDTOList.add(quesetionDTO);
        }
        return quesetionDTOList;
    }
}
