/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hossam.replies.delegate.impl;

import com.hossam.common.dto.businessResponse.ServiceDataWrapperDTO;
import com.hossam.common.dto.businessResponse.ServiceStatusDTO;
import com.hossam.replies.business.RepliesController;
import com.hossam.replies.delegate.RepliesDelegate;
import com.hossam.replies.dto.ReplyAddingDTO;
import com.hossam.replies.dto.ReplySearchingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Hossam ElDeen
 */
@Component
public class RepliesDelegateImpl implements RepliesDelegate{
    @Autowired
    RepliesController repliesController;
    
    @Override
    public ServiceDataWrapperDTO addReply(ReplyAddingDTO replyAddingDTO) {
        try{
            ServiceDataWrapperDTO serviceDataWrapperDTO = repliesController.addReply(replyAddingDTO);
            return serviceDataWrapperDTO;
        }catch(Exception ex){
            ex.printStackTrace();
            return new ServiceDataWrapperDTO(null,"FAILED", "an internal error has been happened");
        }
    }

    @Override
    public ServiceStatusDTO deleteReply(ReplySearchingDTO replyDeletingDTO) {
        try{
            ServiceStatusDTO serviceStatusDTO = repliesController.deleteReply(replyDeletingDTO);
            return serviceStatusDTO;
        }catch(Exception ex){
            ex.printStackTrace();
            return new ServiceDataWrapperDTO(null,"FAILED", "an internal error has been happened");
        }
    }

    @Override
    public ServiceDataWrapperDTO getChildren(ReplySearchingDTO replyDeletingDTO) {
        try{
            ServiceDataWrapperDTO serviceDataWrapperDTO = repliesController.getChildren(replyDeletingDTO);
            return serviceDataWrapperDTO;
        }catch(Exception ex){
            ex.printStackTrace();
            return new ServiceDataWrapperDTO(null,"FAILED", "an internal error has been happened");
        }
    }
    
}
