/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hossam.replies.business.impl;

import com.hossam.abyb.persistence.entities.Question;
import com.hossam.abyb.persistence.entities.Reply;
import com.hossam.abyb.persistence.entities.User;
import com.hossam.common.dto.businessResponse.ServiceDataWrapperDTO;
import com.hossam.common.dto.businessResponse.ServiceStatusDTO;
import com.hossam.replies.business.RepliesController;
import com.hossam.replies.dao.RepliesDao;
import com.hossam.replies.dto.ReplyAddingDTO;
import com.hossam.replies.dto.ReplyDTO;
import com.hossam.replies.dto.ReplySearchingDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Hossam ElDeen
 */
@Controller
public class RepliesControllerImpl implements RepliesController {

    @Autowired
    RepliesDao repliesDao;

    @Override
    public ServiceDataWrapperDTO addReply(ReplyAddingDTO replyAddingDTO) {
        User user = (User) repliesDao.load(User.class, replyAddingDTO.getOwnerId());
        Reply reply = null;
        if (replyAddingDTO.getParentReplyId() != null) {
            reply = (Reply) repliesDao.load(Reply.class, replyAddingDTO.getParentReplyId());
        }
        Question question = null;
        if (replyAddingDTO.getQuestionId() != null) {
            question = (Question) repliesDao.load(Question.class, replyAddingDTO.getQuestionId());
        }
        if (reply == null && question == null) {
            return new ServiceDataWrapperDTO(null, "FAILED", "reply has to be added either to parent reply or question");
        }

        Reply entity = (Reply) replyAddingDTO.getEntity(user, reply, question);
        repliesDao.save(entity);

        return new ServiceDataWrapperDTO(entity.getId(), "SUCCESS", "a reply has been added successfully");
    }

    @Override
    public ServiceStatusDTO deleteReply(ReplySearchingDTO replySearchingDTO) throws IllegalAccessException {
        repliesDao.delete(replySearchingDTO.getEntity());
        return new ServiceStatusDTO("SUCCESS", "a reply has been deleted successfully");
    }

    @Override
    public ServiceDataWrapperDTO getChildren(ReplySearchingDTO replySearchingDTO) throws IllegalAccessException {
        List<ReplyDTO> replies = null;
        if (replySearchingDTO.getRetrievalType() == ReplySearchingDTO.RetrievalType.PARENT_REPLY) {
            Reply reply = (Reply) repliesDao.load(Reply.class, replySearchingDTO.getKey());
            replies = repliesDao.getChildren(replySearchingDTO, "parentReply", reply);
        } else if (replySearchingDTO.getRetrievalType() == ReplySearchingDTO.RetrievalType.QUESTION) {
            Question question = (Question) repliesDao.load(Question.class, replySearchingDTO.getKey());
            replies = repliesDao.getChildren(replySearchingDTO, "question", question);
        }else if(replySearchingDTO.getRetrievalType() == ReplySearchingDTO.RetrievalType.OWNER_ID){
            User user = (User) repliesDao.load(User.class, replySearchingDTO.getKey());
            replies = repliesDao.getChildren(replySearchingDTO, "ownerId", user);
        }else{
            throw new IllegalAccessException();
        }
        
        if (replies == null || replies.isEmpty()) {
            return new ServiceDataWrapperDTO(null, "FAILED", "no replies found");
        } else {
            return new ServiceDataWrapperDTO(replies, "SUCCESS", "replies found");
        }
    }

}
