/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hossam.replies.dto;

import com.hossam.abyb.persistence.entities.Question;
import com.hossam.abyb.persistence.entities.Reply;
import com.hossam.questions.dto.QuestionDTO;
import com.hossam.users.dto.UserDTO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Hossam ElDeen
 */
public class ReplyDTO {
    private Integer id;
    private String body;
    private String pictureUrl;
    private Integer up;
    private Integer down;
    private UserDTO ownerId;

    public ReplyDTO() {
    }

    public ReplyDTO(Integer id, String body, String pictureUrl, Integer up, Integer down, UserDTO ownerId) {
        this.id = id;
        this.body = body;
        this.pictureUrl = pictureUrl;
        this.up = up;
        this.down = down;
        this.ownerId = ownerId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
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

    public UserDTO getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(UserDTO ownerId) {
        this.ownerId = ownerId;
    }
    
    public static ReplyDTO fromEntity(Reply reply) {
        ReplyDTO replyDTO = new ReplyDTO();
        replyDTO.setId(reply.getId());
        replyDTO.setBody(reply.getBody());
        replyDTO.setDown(reply.getDown());
        replyDTO.setUp(reply.getUp());
        replyDTO.setOwnerId(UserDTO.fromEntity(reply.getOwnerId()));
        replyDTO.setPictureUrl(reply.getPictureUrl());
        return replyDTO;
    }

    public static List<ReplyDTO> fromListOfEntities(Collection<Reply> replies) {
        List<ReplyDTO> replyDTOs = new ArrayList<>();
        for (Reply reply : replies) {
            replyDTOs.add(fromEntity(reply));
        }
        return replyDTOs;
    }
}
