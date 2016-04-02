/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hossam.questions.dao.impl;

import com.hossam.abyb.persistence.entities.Question;
import com.hossam.questions.dao.QuestionDao;
import com.hossam.questions.dto.QuestionDTO;
import com.hossam.questions.dto.QuestionRetrievalDTO;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Hossam ElDeen
 */
@Repository
public class QuestionDaoImpl extends QuestionDao{
    
    @Autowired
    public QuestionDaoImpl(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    @Override
    @Transactional
    public List<QuestionDTO> search(QuestionRetrievalDTO questionRetrievalDTO) {
        Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(Question.class);
        String searchKey = "";
        if(questionRetrievalDTO.getRetrievalType() == null)
            throw new IllegalArgumentException("retrieval type can not be null");
        if(questionRetrievalDTO.getRetrievalType() == QuestionRetrievalDTO.RetrievalType.ID){
            criteria.add(Restrictions.eq("id", questionRetrievalDTO.getKeyword()));
        }
        if(questionRetrievalDTO.getRetrievalType() == QuestionRetrievalDTO.RetrievalType.HEADER
                ||questionRetrievalDTO.getRetrievalType() == QuestionRetrievalDTO.RetrievalType.HEADER_OR_BODY){
            criteria.add(Restrictions.eq("header", questionRetrievalDTO.getKeyword()));
        }
        if(questionRetrievalDTO.getRetrievalType() == QuestionRetrievalDTO.RetrievalType.BODY
                ||questionRetrievalDTO.getRetrievalType() == QuestionRetrievalDTO.RetrievalType.HEADER_OR_BODY){
            criteria.add(Restrictions.eq("body", questionRetrievalDTO.getKeyword()));
        }
        
        List<Question> result = (List<Question>)criteria.list();
        List<QuestionDTO> resultDTO = QuestionDTO.fromListOfEntities(result);
        return resultDTO;
    }
    
}
