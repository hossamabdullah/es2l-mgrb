/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hossam.replies.service;

import com.hossam.common.dto.businessResponse.ServiceDataWrapperDTO;
import com.hossam.common.dto.businessResponse.ServiceStatusDTO;
import com.hossam.common.util.ValidationUtil;
import com.hossam.questions.dto.QuestionRatingDTO;
import com.hossam.replies.delegate.RepliesDelegate;
import com.hossam.replies.dto.ReplyAddingDTO;
import com.hossam.replies.dto.ReplySearchingDTO;
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
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    public ServiceDataWrapperDTO addReply(@FormParam("owner_id")Integer ownerId,
            @FormParam("body")String body,
            @FormParam("pictures")String pictures,
            @FormParam("parent_reply_id")Integer parentReplyId,
            @FormParam("question_id")Integer questionId) {
        Map argumentsMap = new HashMap<String, Object>();
        argumentsMap.put("owner_id", ownerId);
        argumentsMap.put("body", body);
        argumentsMap.put("pictures", pictures);
        String argumentsErrorMessage = ValidationUtil.checkExistenceOfArguments(argumentsMap);
        if (argumentsErrorMessage != null) {
            return new ServiceDataWrapperDTO(null, "FAILED", argumentsErrorMessage);
        }

        ReplyAddingDTO replyAddingDTO = new ReplyAddingDTO(parentReplyId, ownerId, body, pictures, questionId);
        ServiceDataWrapperDTO serviceDataWrapperDTO = repliesDelegate.addReply(replyAddingDTO);
        return serviceDataWrapperDTO;
    }
    
    @POST
    @Path("/delete")
    @Produces(MediaType.APPLICATION_JSON)
    public ServiceStatusDTO removeReply(@FormParam("reply_id")Integer replyId){
        Map argumentsMap = new HashMap<String, Object>();
        argumentsMap.put("reply_id", replyId);
        String argumentsErrorMessage = ValidationUtil.checkExistenceOfArguments(argumentsMap);
        if (argumentsErrorMessage != null) {
            return new ServiceStatusDTO("FAILED", argumentsErrorMessage);
        }
        
        ReplySearchingDTO replyDeletingDTO =  new ReplySearchingDTO(replyId,ReplySearchingDTO.RetrievalType.REPLY);
        ServiceStatusDTO serviceStatusDTO = repliesDelegate.deleteReply(replyDeletingDTO);
        return serviceStatusDTO;
    }
    
    @POST
    @Path("/getRepliesOfParentReply")
    @Produces(MediaType.APPLICATION_JSON)
    public ServiceDataWrapperDTO getChildren(@FormParam("reply_id")Integer replyId){
        return search("reply_id", replyId, ReplySearchingDTO.RetrievalType.PARENT_REPLY);
    }
    
    @POST
    @Path("/getRepliesOfQuestion")
    @Produces(MediaType.APPLICATION_JSON)
    public ServiceDataWrapperDTO getRepliesOfQuestion(@FormParam("question_id")Integer questionId){
        return search("question_id", questionId, ReplySearchingDTO.RetrievalType.QUESTION);
    }
    
    @POST
    @Path("/getRepliesOfUser")
    @Produces(MediaType.APPLICATION_JSON)
    public ServiceDataWrapperDTO getRepliesOfUser(@FormParam("owner_id")Integer ownerId){
        return search("owner_id", ownerId, ReplySearchingDTO.RetrievalType.OWNER_ID);
    }
    
    private ServiceDataWrapperDTO search(String name, Integer key, ReplySearchingDTO.RetrievalType retrievalType){
        Map argumentsMap = new HashMap<String, Object>();
        argumentsMap.put(name, key);
        String argumentsErrorMessage = ValidationUtil.checkExistenceOfArguments(argumentsMap);
        if (argumentsErrorMessage != null) {
            return new ServiceDataWrapperDTO(null,"FAILED", argumentsErrorMessage);
        }
        
        ReplySearchingDTO replySearchingDTO =  new ReplySearchingDTO(key,retrievalType);
        ServiceDataWrapperDTO serviceDataWrapperDTO = repliesDelegate.getChildren(replySearchingDTO);
        return serviceDataWrapperDTO;
    }
}
