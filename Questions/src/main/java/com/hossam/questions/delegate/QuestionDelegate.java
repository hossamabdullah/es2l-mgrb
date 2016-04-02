/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hossam.questions.delegate;

import com.hossam.common.dto.businessResponse.ServiceDataWrapperDTO;
import com.hossam.common.dto.businessResponse.ServiceStatusDTO;
import com.hossam.questions.dto.QuestionAddingDTO;
import com.hossam.questions.dto.QuestionDeletingDTO;
import com.hossam.questions.dto.QuestionRatingDTO;
import com.hossam.questions.dto.QuestionRetrievalDTO;

/**
 *
 * @author Hossam ElDeen
 */
public interface QuestionDelegate {
    public ServiceStatusDTO addQuestion(QuestionAddingDTO questionAddingDTO);
    public ServiceStatusDTO rateQuestion(QuestionRatingDTO questionRatingDTO);

    public ServiceStatusDTO deleteQuestion(QuestionDeletingDTO questionDeletingDTO);

    public ServiceDataWrapperDTO search(QuestionRetrievalDTO questionRetrievalDTO);
}
