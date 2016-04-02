/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hossam.questions.business.impl;

import com.hossam.abyb.persistence.entities.Question;
import com.hossam.abyb.persistence.entities.User;
import com.hossam.abyb.persistence.entities.UserQuestionRate;
import com.hossam.abyb.persistence.entities.UserQuestionRatePK;
import com.hossam.common.dto.businessResponse.ServiceDataWrapperDTO;
import com.hossam.common.dto.businessResponse.ServiceStatusDTO;
import com.hossam.questions.business.QuestionController;
import com.hossam.questions.dao.QuestionDao;
import com.hossam.questions.dto.QuestionAddingDTO;
import com.hossam.questions.dto.QuestionDTO;
import com.hossam.questions.dto.QuestionDeletingDTO;
import com.hossam.questions.dto.QuestionRatingDTO;
import com.hossam.questions.dto.QuestionRetrievalDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Hossam ElDeen
 */
@Controller
public class QuestionControllerImpl implements QuestionController {

    @Autowired
    QuestionDao questionDao;

    @Override
    public ServiceStatusDTO addQuestion(QuestionAddingDTO questionAddingDTO) {
        User user = (User) questionDao.load(User.class, questionAddingDTO.getOwnerId());
        questionDao.save(questionAddingDTO.getEntity(user));
        return new ServiceStatusDTO("SUCCESS", "a question has been added successfully");
    }

    @Override
    @Transactional
    public ServiceStatusDTO rateQuestion(QuestionRatingDTO questionRatingDTO) {
        UserQuestionRatePK userQuestionRatePK = new UserQuestionRatePK(questionRatingDTO.getUserId(), questionRatingDTO.getQuestionId());
        UserQuestionRate userQuestionRate = (UserQuestionRate) questionDao.load(UserQuestionRate.class, userQuestionRatePK);
        if (userQuestionRate == null) {
            return addRating(userQuestionRatePK, questionRatingDTO.getRate());
        } else if (userQuestionRate.getRate() == questionRatingDTO.getRate()) {
            return new ServiceStatusDTO("FAILED", "this user has rated this question before");
        } else {
            return removeRating(userQuestionRate);
        }
    }

    private ServiceStatusDTO addRating(UserQuestionRatePK userQuestionRatePK, Boolean rate) {
        Question question = (Question) questionDao.load(Question.class, userQuestionRatePK.getQuestionId());
        UserQuestionRate userQuestionRate = new UserQuestionRate(userQuestionRatePK, rate);
        question.getUsersRatings().add(userQuestionRate);
        if (userQuestionRate.getRate()) {
            question.setUp(question.getUp() + 1);
        } else {
            question.setDown(question.getDown() + 1);
        }
        return new ServiceStatusDTO("SUCCESS", "a question has been rated successfully");
    }

    private ServiceStatusDTO removeRating(UserQuestionRate userQuestionRate) {
        questionDao.delete(userQuestionRate);
        Question question = (Question) questionDao.load(Question.class, userQuestionRate.getQuestion().getId());
        if (userQuestionRate.getRate()) {
            question.setUp(question.getUp() - 1);
        } else {
            question.setDown(question.getDown() - 1);
        }
        return new ServiceStatusDTO("SUCCESS", "rating on question has been removed successfully");
    }

    @Override
    @Transactional
    public ServiceStatusDTO deleteQuestion(QuestionDeletingDTO questionDeletingDTO) {
        Question question = (Question) questionDao.load(Question.class, questionDeletingDTO.getQuestionId());
        if(question==null){
            return new ServiceStatusDTO("FAILED", "question does not exist");
        }else if(question.getOwnerId().getId() == questionDeletingDTO.getUserId()){
            questionDao.delete(question);
            return new ServiceStatusDTO("SUCCESS", "question has been deleted successfully");
        }else{
            return new ServiceStatusDTO("FAILED", "user is not owner on that question");
        }
    }

    @Override
    public ServiceDataWrapperDTO search(QuestionRetrievalDTO questionRetrievalDTO) {
        List<QuestionDTO> questionDTOs = questionDao.search(questionRetrievalDTO);
        ServiceDataWrapperDTO serviceDataWrapperDTO;
        if(questionDTOs==null || questionDTOs.isEmpty())
            serviceDataWrapperDTO = new ServiceDataWrapperDTO(null,"FAILED","No Questions have been found for this criteria");
        else
            serviceDataWrapperDTO = new ServiceDataWrapperDTO(questionDTOs,"SUCCESS","Question have been retrieved successfully");
        return serviceDataWrapperDTO;
    }

}
