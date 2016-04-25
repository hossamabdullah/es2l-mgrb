/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hossam.questions.dto;

import com.hossam.abyb.persistence.entities.Question;
import com.hossam.abyb.persistence.entities.Tag;
import com.hossam.replies.dto.ReplyDTO;
import com.hossam.users.dto.UserDTO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Hossam ElDeen
 */
public class QuestionDTO {

    private Integer id;
    private String header;
    private String body;
    private String picturesUrl;
    private Integer up;
    private Integer down;
    
    private UserDTO userDTO;
    private List<String> tags;
    private List<ReplyDTO> replies;

    public QuestionDTO() {
    }

    public QuestionDTO(Integer id, String header, String body, String picturesUrl, Integer up, Integer down, UserDTO userDTO, List<String> tags, List<ReplyDTO> replies) {
        this.id = id;
        this.header = header;
        this.body = body;
        this.picturesUrl = picturesUrl;
        this.up = up;
        this.down = down;
        this.userDTO = userDTO;
        this.tags = tags;
        this.replies = replies;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String[] getPicturesUrl() {
        if (picturesUrl == null) {
            return null;
        } else {
            return picturesUrl.split(",");
        }
    }

    public void setPicturesUrl(String picturesUrl) {
        this.picturesUrl = picturesUrl;
    }

    public Integer getUp() {
        return up;
    }

    public void setUp(Integer up) {
        this.up = up;
    }

    public Integer getDown() {
        return down;
    }

    public void setDown(Integer down) {
        this.down = down;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<ReplyDTO> getReplies() {
        return replies;
    }

    public void setReplies(List<ReplyDTO> replies) {
        this.replies = replies;
    }
    
    public static QuestionDTO fromEntity(Question question) {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setBody(question.getBody());
        questionDTO.setDown(question.getDown());
        questionDTO.setHeader(question.getHeader());
        questionDTO.setId(question.getId());
        questionDTO.setPicturesUrl(question.getPictureUrl());
        questionDTO.setUp(question.getUp());
        questionDTO.setUserDTO(UserDTO.fromEntity(question.getOwnerId()));
        questionDTO.setReplies(ReplyDTO.fromListOfEntities(question.getReplies()));
        List<String> listOfTags = questionDTO.getTags();
        listOfTags= new ArrayList<>();
        for(Tag tag: question.getTags()){
            listOfTags.add(tag.getTitle());
        }
        questionDTO.setTags(listOfTags);
        return questionDTO;
    }

    public static List<QuestionDTO> fromListOfEntities(Collection<Question> questions) {
        List<QuestionDTO> questionDTOs = new ArrayList<>();
        for (Question question : questions) {
            questionDTOs.add(fromEntity(question));
        }
        return questionDTOs;
    }
}
