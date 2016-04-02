/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hossam.questions.delegate.impl;

import com.hossam.common.dto.businessResponse.ServiceDataWrapperDTO;
import com.hossam.common.dto.businessResponse.ServiceStatusDTO;
import com.hossam.questions.business.QuestionController;
import com.hossam.questions.delegate.QuestionDelegate;
import com.hossam.questions.dto.QuestionAddingDTO;
import com.hossam.questions.dto.QuestionDeletingDTO;
import com.hossam.questions.dto.QuestionRatingDTO;
import com.hossam.questions.dto.QuestionRetrievalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Hossam ElDeen
 */
@Component
public class QuestionDelegateImpl implements QuestionDelegate{
    @Autowired
    QuestionController questionController;
    
    @Override
    public ServiceStatusDTO addQuestion(QuestionAddingDTO questionAddingDTO) {
        try{
            ServiceStatusDTO serviceStatusDTO = questionController.addQuestion(questionAddingDTO);
            return serviceStatusDTO;
        }catch(Exception ex){
            ex.printStackTrace();
            return new ServiceStatusDTO("FAILED", "an internal error has been happened");
        }
        
    }

    @Override
    public ServiceStatusDTO rateQuestion(QuestionRatingDTO questionRatingDTO) {
        try{
            ServiceStatusDTO serviceStatusDTO = questionController.rateQuestion(questionRatingDTO);
            return serviceStatusDTO;
        }catch(Exception ex){
            ex.printStackTrace();
            return new ServiceStatusDTO("FAILED", "an internal error has been happened");
        }
    }

    @Override
    public ServiceStatusDTO deleteQuestion(QuestionDeletingDTO questionDeletingDTO) {
        try{
            ServiceStatusDTO serviceStatusDTO = questionController.deleteQuestion(questionDeletingDTO);
            return serviceStatusDTO;
        }catch(Exception ex){
            ex.printStackTrace();
            return new ServiceStatusDTO("FAILED", "an internal error has been happened");
        }
    }

    @Override
    public ServiceDataWrapperDTO search(QuestionRetrievalDTO questionRetrievalDTO) {
        try{
            ServiceDataWrapperDTO serviceDataWrapperDTO = questionController.search(questionRetrievalDTO);
            return serviceDataWrapperDTO;
        }catch(Exception ex){
            ex.printStackTrace();
            return new ServiceDataWrapperDTO("", "FAILED", "an internal error has been happened");
        }
    }
    
}
