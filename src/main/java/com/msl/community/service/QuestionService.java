package com.msl.community.service;

import com.msl.community.dto.PaginationDTO;
import com.msl.community.dto.QuesetionDTO;
import com.msl.community.exception.CustomizeErrorCode;
import com.msl.community.exception.CustomizeException;
import com.msl.community.mapper.QuestionExtMapper;
import com.msl.community.mapper.QuestionMapper;
import com.msl.community.mapper.UserMapper;
import com.msl.community.model.Question;
import com.msl.community.model.QuestionExample;
import com.msl.community.model.User;
import org.apache.ibatis.session.RowBounds;
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
    private QuestionExtMapper questionExtMapper;
    @Autowired
    private UserMapper userMapper;

    public PaginationDTO list(Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalPage;

        Integer totalCount = (int)questionMapper.countByExample(new QuestionExample());

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
//        List<Question> questionList = questionMapper.list(offset,size);
        List<Question>  questionList = questionMapper.selectByExampleWithRowbounds(new QuestionExample(), new RowBounds(offset, size));
        List<QuesetionDTO> questionDTOList = new ArrayList<>();

        for(Question question :questionList){
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuesetionDTO quesetionDTO = new QuesetionDTO();
            //把question上的属性copy到quesetionDTO上
            BeanUtils.copyProperties(question,quesetionDTO);
            quesetionDTO.setUser(user);
            questionDTOList.add(quesetionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }
    public PaginationDTO list(Long userId,Integer page,Integer size){
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalPage;
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria()
                .andCreatorEqualTo(userId);
        Integer totalCount = (int)questionMapper.countByExample(questionExample);


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
//        List<Question> questionList = questionMapper.listByUserId(userId,offset,size);
        QuestionExample example = new QuestionExample();
        example.createCriteria()
                .andCreatorEqualTo(userId);
        List<Question> questionList = questionMapper.selectByExampleWithRowbounds(example,new RowBounds(offset,size));
        List<QuesetionDTO> questionDTOList = new ArrayList<>();

        for(Question question :questionList){
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuesetionDTO quesetionDTO = new QuesetionDTO();
            //把question上的属性copy到quesetionDTO上
            BeanUtils.copyProperties(question,quesetionDTO);
            quesetionDTO.setUser(user);
            questionDTOList.add(quesetionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }

    public QuesetionDTO getById(Long id) {
        Question question =questionMapper.selectByPrimaryKey(id);
        if(question == null){
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        QuesetionDTO quesetionDTO = new QuesetionDTO();
        //把question上的属性copy到quesetionDTO上
        BeanUtils.copyProperties(question,quesetionDTO);
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        quesetionDTO.setUser(user);

        return quesetionDTO;
    }

    public void createOrUpdate(Question question) {
        if(question.getId() == null){
            //创建
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtModified());
            questionMapper.insert(question);
        }else {
            //更新
//            question.setGmtModified(System.currentTimeMillis());
            Question updataQuestion = new Question();
            updataQuestion.setGmtModified(System.currentTimeMillis());
            updataQuestion.setTitle(question.getTitle());
            updataQuestion.setDescription(question.getDescription());
            updataQuestion.setTag(question.getTag());
            QuestionExample example = new QuestionExample();
            example.createCriteria()
                    .andIdEqualTo(question.getId());
            int updated = questionMapper.updateByExampleSelective(updataQuestion,example);
            if(updated !=1){
                throw  new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }

    public void incView(Long id) {
        Question question = new Question();
        question.setId(id);
        question.setViewCount(1);
        questionExtMapper.incView(question);
    }
}
