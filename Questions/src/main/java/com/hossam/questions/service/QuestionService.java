/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hossam.questions.service;

import com.hossam.common.dto.businessResponse.ServiceDataWrapperDTO;
import com.hossam.common.dto.businessResponse.ServiceStatusDTO;
import com.hossam.common.util.ValidationUtil;
import com.hossam.questions.delegate.QuestionDelegate;
import com.hossam.questions.dto.QuestionAddingDTO;
import com.hossam.questions.dto.QuestionDeletingDTO;
import com.hossam.questions.dto.QuestionRatingDTO;
import com.hossam.questions.dto.QuestionRetrievalDTO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
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
@Path("/question")
public class QuestionService {

    @Context
    private HttpServletRequest request;

    @Autowired(required = true)
    private QuestionDelegate questionDelegate;

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    public ServiceStatusDTO addQuestion(@FormParam("header")String header,
            @FormParam("body")String body,
            @FormParam("owner_id")Integer ownerId,
            @FormParam("pictures_url")String picturesUrl) {
        Map argumentsMap = new HashMap<String, Object>();
        argumentsMap.put("header", header);
        argumentsMap.put("body", body);
        argumentsMap.put("owner_id", ownerId);
        String argumentsErrorMessage = ValidationUtil.checkExistenceOfArguments(argumentsMap);
        if (argumentsErrorMessage != null) {
            return new ServiceStatusDTO("FAILED", argumentsErrorMessage);
        }

        QuestionAddingDTO questionAddingDTO = new QuestionAddingDTO(header, body, picturesUrl, ownerId);
        ServiceStatusDTO serviceStatusDTO = questionDelegate.addQuestion(questionAddingDTO);
        return serviceStatusDTO;
    }

    @POST
    @Path("/delete")
    @Produces(MediaType.APPLICATION_JSON)
    public ServiceStatusDTO deleteQuestion(
            @FormParam("user_id") Integer userId,
            @FormParam("question_id") Integer questionId) {

        Map argumentsMap = new HashMap<String, Object>();
        argumentsMap.put("user_id", userId);
        argumentsMap.put("question_id", questionId);
        String argumentsErrorMessage = ValidationUtil.checkExistenceOfArguments(argumentsMap);
        if (argumentsErrorMessage != null) {
            return new ServiceStatusDTO("FAILED", argumentsErrorMessage);
        }

        QuestionDeletingDTO questionDeletingDTO = new QuestionDeletingDTO(questionId, userId);
        ServiceStatusDTO serviceStatusDTO = questionDelegate.deleteQuestion(questionDeletingDTO);
        return serviceStatusDTO;
    }

    @POST
    @Path("/searchWithId")
    @Produces(MediaType.APPLICATION_JSON)
    public ServiceDataWrapperDTO searchWithId(
            @FormParam("question_id") Integer questionId) {
        return search("question_id", questionId, QuestionRetrievalDTO.RetrievalType.ID);
    }

    @POST
    @Path("/searchWithHeader")
    @Produces(MediaType.APPLICATION_JSON)
    public ServiceDataWrapperDTO searchWithHeader(
            @FormParam("header") String header) {
        return search("header", header, QuestionRetrievalDTO.RetrievalType.HEADER);
    }

    @POST
    @Path("/searchWithBody")
    @Produces(MediaType.APPLICATION_JSON)
    public ServiceDataWrapperDTO searchWithBody(
            @FormParam("body") String body) {
        return search("body", body, QuestionRetrievalDTO.RetrievalType.BODY);
    }

    @POST
    @Path("/searchWithHeaderOrBody")
    @Produces(MediaType.APPLICATION_JSON)
    public ServiceDataWrapperDTO searchWithHeaderOrBody(
            @FormParam("key") String key) {
        return search("key", key, QuestionRetrievalDTO.RetrievalType.HEADER_OR_BODY);
    }

    @POST
    @Path("/searchWithTags")
    @Produces(MediaType.APPLICATION_JSON)
    public ServiceDataWrapperDTO searchWithTags(
            @FormParam("tags") List<String> tags) {
        return search("tags", tags, QuestionRetrievalDTO.RetrievalType.TAG);
    }

    private ServiceDataWrapperDTO search(String name, Object key, QuestionRetrievalDTO.RetrievalType retrievalType) {
        Map argumentsMap = new HashMap<String, Object>();
        argumentsMap.put(name, key);
        String argumentsErrorMessage = ValidationUtil.checkExistenceOfArguments(argumentsMap);
        if (argumentsErrorMessage != null) {
            return new ServiceDataWrapperDTO(null, "FAILED", argumentsErrorMessage);
        }

        QuestionRetrievalDTO questionRetrievalDTO = new QuestionRetrievalDTO(key, retrievalType);
        ServiceDataWrapperDTO serviceDataWrapperDTO = questionDelegate.search(questionRetrievalDTO);
        return serviceDataWrapperDTO;
    }
}
