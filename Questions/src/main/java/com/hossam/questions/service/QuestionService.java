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
import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
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
@Path("/question")
public class QuestionService {

    @Context
    private HttpServletRequest request;

    @Autowired(required = true)
    private QuestionDelegate questionDelegate;

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    public ServiceStatusDTO addQuestion(FormDataMultiPart multiPart) {
        //getting data from form parameters
        FormDataBodyPart headerElement = multiPart.getField("header");
        FormDataBodyPart bodyElement = multiPart.getField("body");
        FormDataBodyPart ownerIdElement = multiPart.getField("owner_id");
        List<FormDataBodyPart> fields = multiPart.getFields("pictures");
        int numOfPictures = fields.size();
        String picturesUrl = "";
        for (FormDataBodyPart field : fields) {
            String fileName = field.getFormDataContentDisposition().getFileName();
            picturesUrl += handleInputStream(field.getValueAs(InputStream.class), "C://uploaded/" + fileName);
            picturesUrl += ";";
        }

        //converting data into regular data types
        String header = null;
        if (headerElement != null) {
            header = headerElement.getValue();
        }
        String body = null;
        if (bodyElement != null) {
            body = bodyElement.getValue();
        }
        Integer ownerId = null;
        if (ownerIdElement != null) {
            ownerId = ownerIdElement.getValueAs(Integer.class);
        }

        //check send parameters
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
        holdWhilePicturesGetUploaded(numOfPictures);
        return serviceStatusDTO;
    }

    private String handleInputStream(final InputStream uploadedInputStream, final String uploadedFileLocation) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OutputStream out = new FileOutputStream(new File(uploadedFileLocation));
                    int read = 0;
                    byte[] bytes = new byte[1024];
                    out = new FileOutputStream(new File(uploadedFileLocation));
                    while ((read = uploadedInputStream.read(bytes)) != -1) {
                        out.write(bytes, 0, read);
                    }
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (request.getAttribute("picturesUploaded") == null) {
                        request.setAttribute("picturesUploaded", 1);
                    } else {
                        Integer numOfUploadedPics = (Integer) request.getAttribute("picturesUploaded");
                        request.setAttribute("picturesUploaded", numOfUploadedPics + 1);
                    }
                }
            }
        }).start();

        return uploadedFileLocation;
    }

    private void holdWhilePicturesGetUploaded(int numOfPictures) {
        Integer numOfPicturesUploaded = (Integer) request.getAttribute("picturesUploaded");
        while (numOfPicturesUploaded == null || numOfPicturesUploaded < numOfPictures) {
            numOfPicturesUploaded = (Integer) request.getAttribute("picturesUploaded");
        }
    }

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
        ServiceStatusDTO serviceStatusDTO = questionDelegate.rateQuestion(questionRatingDTO);
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
        Map argumentsMap = new HashMap<String, Object>();
        argumentsMap.put("question_id", questionId);
        String argumentsErrorMessage = ValidationUtil.checkExistenceOfArguments(argumentsMap);
        if (argumentsErrorMessage != null) {
            return new ServiceDataWrapperDTO(null, "FAILED", argumentsErrorMessage);
        }

        QuestionRetrievalDTO questionRetrievalDTO = new QuestionRetrievalDTO(questionId, QuestionRetrievalDTO.RetrievalType.ID);
        ServiceDataWrapperDTO serviceDataWrapperDTO = questionDelegate.search(questionRetrievalDTO);
        return serviceDataWrapperDTO;
    }

    @POST
    @Path("/searchWithHeader")
    @Produces(MediaType.APPLICATION_JSON)
    public ServiceDataWrapperDTO searchWithHeader(
            @FormParam("header") String header) {
        Map argumentsMap = new HashMap<String, Object>();
        argumentsMap.put("header", header);
        String argumentsErrorMessage = ValidationUtil.checkExistenceOfArguments(argumentsMap);
        if (argumentsErrorMessage != null) {
            return new ServiceDataWrapperDTO(null, "FAILED", argumentsErrorMessage);
        }

        QuestionRetrievalDTO questionRetrievalDTO = new QuestionRetrievalDTO(header, QuestionRetrievalDTO.RetrievalType.HEADER);
        ServiceDataWrapperDTO serviceDataWrapperDTO = questionDelegate.search(questionRetrievalDTO);
        return serviceDataWrapperDTO;
    }

    @POST
    @Path("/searchWithBody")
    @Produces(MediaType.APPLICATION_JSON)
    public ServiceDataWrapperDTO searchWithBody(
            @FormParam("body") String body) {
        Map argumentsMap = new HashMap<String, Object>();
        argumentsMap.put("body", body);
        String argumentsErrorMessage = ValidationUtil.checkExistenceOfArguments(argumentsMap);
        if (argumentsErrorMessage != null) {
            return new ServiceDataWrapperDTO(null, "FAILED", argumentsErrorMessage);
        }

        QuestionRetrievalDTO questionRetrievalDTO = new QuestionRetrievalDTO(body, QuestionRetrievalDTO.RetrievalType.BODY);
        ServiceDataWrapperDTO serviceDataWrapperDTO = questionDelegate.search(questionRetrievalDTO);
        return serviceDataWrapperDTO;
    }

    @POST
    @Path("/searchWithHeaderOrBody")
    @Produces(MediaType.APPLICATION_JSON)
    public ServiceDataWrapperDTO searchWithHeaderOrBody(
            @FormParam("key") String key) {
        Map argumentsMap = new HashMap<String, Object>();
        argumentsMap.put("key", key);
        String argumentsErrorMessage = ValidationUtil.checkExistenceOfArguments(argumentsMap);
        if (argumentsErrorMessage != null) {
            return new ServiceDataWrapperDTO(null, "FAILED", argumentsErrorMessage);
        }

        QuestionRetrievalDTO questionRetrievalDTO = new QuestionRetrievalDTO(key, QuestionRetrievalDTO.RetrievalType.HEADER_OR_BODY);
        ServiceDataWrapperDTO serviceDataWrapperDTO = questionDelegate.search(questionRetrievalDTO);
        return serviceDataWrapperDTO;
    }

    public ServiceDataWrapperDTO searchWithTags(
            @FormParam("tags") List<String> tags) {
        Map argumentsMap = new HashMap<String, Object>();
        argumentsMap.put("tags", tags);
        String argumentsErrorMessage = ValidationUtil.checkExistenceOfArguments(argumentsMap);
        if (argumentsErrorMessage != null) {
            return new ServiceDataWrapperDTO(null, "FAILED", argumentsErrorMessage);
        }
        
        //TODO create task
        return null;
    }
}
