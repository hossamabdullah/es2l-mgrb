/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hossam.questions.dao;

import com.hossam.persistence.dao.AbstractDao;
import com.hossam.questions.dto.QuestionDTO;
import com.hossam.questions.dto.QuestionRetrievalDTO;
import java.util.List;

/**
 *
 * @author Hossam ElDeen
 */
public abstract class QuestionDao extends AbstractDao{

    public abstract List<QuestionDTO> search(QuestionRetrievalDTO questionRetrievalDTO);
    
}
