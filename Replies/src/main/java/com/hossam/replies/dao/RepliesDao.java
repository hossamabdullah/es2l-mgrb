/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hossam.replies.dao;

import com.hossam.persistence.dao.AbstractDao;
import com.hossam.replies.dto.ReplyDTO;
import com.hossam.replies.dto.ReplySearchingDTO;
import java.util.List;

/**
 *
 * @author Hossam ElDeen
 */
public abstract class RepliesDao extends AbstractDao{
    public abstract List<ReplyDTO> getChildren(ReplySearchingDTO replySearchingDTO, String keyField, Object key);
}
