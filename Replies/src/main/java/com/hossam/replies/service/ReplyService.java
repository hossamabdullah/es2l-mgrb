/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hossam.replies.service;

import com.hossam.common.dto.businessResponse.ServiceStatusDTO;
import com.hossam.common.util.ValidationUtil;
import com.hossam.questions.dto.QuestionRatingDTO;
import com.hossam.replies.delegate.impl.RepliesDelegate;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Hossam ElDeen
 */
@Service
@Path("/reply")
public class ReplyService {
    @Context
    private HttpServletRequest request;

    @Autowired(required = true)
    private RepliesDelegate repliesDelegate;
    
    @POST
    @Path("/rate")
    @Produces(MediaType.APPLICATION_JSON)
    public ServiceStatusDTO rateQuestion(
            @FormParam("user_id") Integer userId,
            @FormParam("question_id") Integer questionId,
            @FormParam("is_up_vote") Boolean upVote) {

        Map argumentsMap = new HashMap<String, Object>();
        argumentsMap.put("user_id", userId);
        argumentsMap.put("question_id", questionId);
        argumentsMap.put("is_up_vote", upVote);
        String argumentsErrorMessage = ValidationUtil.checkExistenceOfArguments(argumentsMap);
        if (argumentsErrorMessage != null) {
            return new ServiceStatusDTO("FAILED", argumentsErrorMessage);
        }

        QuestionRatingDTO questionRatingDTO = new QuestionRatingDTO(questionId, userId, upVote);
     
        return null;
    }
}
