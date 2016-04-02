/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hossam.common;

import javax.annotation.PostConstruct;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.hossam.common.util.DataBaseUtil;

/**
 *
 * @author Hossam ElDeen
 */
@Component
@Scope("singleton")
public class EnviromentValidation {

    /**
     *
     * @throws Exception
     */
    @PostConstruct
    public void init(){
        if(!DataBaseUtil.isDataBaseRunning())
            throw  new RuntimeException("DataBase Server is not running.");
    }
}
