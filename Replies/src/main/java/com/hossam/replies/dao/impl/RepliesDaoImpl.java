/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hossam.replies.dao.impl;

import com.hossam.abyb.persistence.entities.Question;
import com.hossam.abyb.persistence.entities.Reply;
import com.hossam.replies.dao.RepliesDao;
import com.hossam.replies.dto.ReplyDTO;
import com.hossam.replies.dto.ReplySearchingDTO;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Hossam ElDeen
 */
@Repository
public class RepliesDaoImpl extends RepliesDao {

    @Autowired
    public RepliesDaoImpl(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    @Override
    public List<ReplyDTO> getChildren(ReplySearchingDTO replySearchingDTO, String keyField, Object key) {
        Criteria cr = getSessionFactory().getCurrentSession().createCriteria(Reply.class);
        cr.add(Restrictions.eq(keyField, key));
        List<Reply> replies = cr.list();
        List<ReplyDTO> replyDTOs = ReplyDTO.fromListOfEntities(replies);
        return replyDTOs;
    }

}
