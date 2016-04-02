/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hossam.exception;

/**
 *
 * @author Hossam ElDeen
 */
public abstract class AbstractException extends RuntimeException{
    public AbstractException(String message) {
        super(message);
    }
}
