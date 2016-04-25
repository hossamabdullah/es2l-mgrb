/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hossam.questions.dao.impl;

import com.hossam.abyb.persistence.entities.Question;
import com.hossam.abyb.persistence.entities.Tag;
import com.hossam.abyb.persistence.entities.User;
import com.hossam.questions.dao.QuestionDao;
import com.hossam.questions.dto.QuestionDTO;
import com.hossam.questions.dto.QuestionRetrievalDTO;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Hossam ElDeen
 */
@Repository
public class QuestionDaoImpl extends QuestionDao {

    @Autowired
    public QuestionDaoImpl(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }
    
    //FIXME
    //TAG if have to be moved to TagDaoImpl
    @Override
    @Transactional
    public List<QuestionDTO> search(QuestionRetrievalDTO questionRetrievalDTO) {
        Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(Question.class);
        String searchKey = "";
        if (questionRetrievalDTO.getRetrievalType() == null) {
            throw new IllegalArgumentException("retrieval type can not be null");
        }
        if (questionRetrievalDTO.getRetrievalType() == QuestionRetrievalDTO.RetrievalType.ID) {
            criteria.add(Restrictions.eq("id", questionRetrievalDTO.getKeyword()));
        }
        if (questionRetrievalDTO.getRetrievalType() == QuestionRetrievalDTO.RetrievalType.HEADER) {
            criteria.add(Restrictions.eq("header", questionRetrievalDTO.getKeyword()));
        }
        if (questionRetrievalDTO.getRetrievalType() == QuestionRetrievalDTO.RetrievalType.BODY) {
            criteria.add(Restrictions.eq("body", questionRetrievalDTO.getKeyword()));
        }

        if (questionRetrievalDTO.getRetrievalType() == QuestionRetrievalDTO.RetrievalType.HEADER_OR_BODY) {
            SimpleExpression eq1 = Restrictions.eq("header", questionRetrievalDTO.getKeyword());
            SimpleExpression eq2 = Restrictions.eq("body", questionRetrievalDTO.getKeyword());
            criteria.add(Restrictions.or(eq1, eq2));
        }
        
        if(questionRetrievalDTO.getRetrievalType() == QuestionRetrievalDTO.RetrievalType.USER_ID){
            User user = (User) load(User.class, (Integer)questionRetrievalDTO.getKeyword());
            criteria.add(Restrictions.eq("ownerId", user));
        }
        
        if (questionRetrievalDTO.getRetrievalType() == QuestionRetrievalDTO.RetrievalType.TAG) {
            criteria = getSessionFactory().getCurrentSession().createCriteria(Tag.class);
            List<String> fetchTags = (List<String>) questionRetrievalDTO.getKeyword();
            Criterion[] criterions = new Criterion[fetchTags.size()];
            int index = 0;
            for (String tag : fetchTags) {
                SimpleExpression eq = Restrictions.eq("title", tag);
                criterions[index] = eq;
                index++;
            }
            criteria.add(Restrictions.or(criterions));
            List<Tag> tags = (List<Tag>) criteria.list();
            Set<Question> result = new HashSet<>();
            for (Tag tag : tags) {
                result.addAll(tag.getQuestions());
            }
            List<QuestionDTO> resultDTO = QuestionDTO.fromListOfEntities(result);
            return resultDTO;
        } else {
            List<Question> result = (List<Question>) criteria.list();
            List<QuestionDTO> resultDTO = QuestionDTO.fromListOfEntities(result);
            return resultDTO;
        }
    }
}
