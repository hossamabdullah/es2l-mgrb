/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hossam.questions.dto;

import com.hossam.abyb.persistence.entities.Question;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hossam ElDeen
 */
public class QuestionDTO {
    Integer id;
    String header;
    String body;
    String picturesUrl;
    Integer up;
    Integer down;

    public QuestionDTO() {
    }

    public QuestionDTO(Integer id, String header, String body, String picturesUrl, Integer up, Integer down) {
        this.id = id;
        this.header = header;
        this.body = body;
        this.picturesUrl = picturesUrl;
        this.up = up;
        this.down = down;
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
        return picturesUrl.split(",");
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
    
    public static QuestionDTO fromEntity(Question question){
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setBody(question.getBody());
        questionDTO.setDown(question.getDown());
        questionDTO.setHeader(question.getHeader());
        questionDTO.setId(question.getId());
        questionDTO.setPicturesUrl(question.getPictureUrl());
        questionDTO.setUp(question.getUp());
        return questionDTO;
    }
    
    public static List<QuestionDTO> fromListOfEntities(List<Question> questions){
        List<QuestionDTO> questionDTOs = new ArrayList<>();
        for(Question question : questions){
            questionDTOs.add(fromEntity(question));
        }
        return questionDTOs;
    }
}
