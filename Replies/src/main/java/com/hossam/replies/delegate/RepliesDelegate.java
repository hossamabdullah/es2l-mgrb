/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hossam.replies.delegate;

import com.hossam.common.dto.businessResponse.ServiceDataWrapperDTO;
import com.hossam.common.dto.businessResponse.ServiceStatusDTO;
import com.hossam.replies.dto.ReplyAddingDTO;
import com.hossam.replies.dto.ReplySearchingDTO;

/**
 *
 * @author Hossam ElDeen
 */
public interface RepliesDelegate {

    public ServiceDataWrapperDTO addReply(ReplyAddingDTO replyAddingDTO);

    public ServiceStatusDTO deleteReply(ReplySearchingDTO replyDeletingDTO);

    public ServiceDataWrapperDTO getChildren(ReplySearchingDTO replyDeletingDTO);
    
}
