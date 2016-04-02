/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hossam.exception.types;

import com.hossam.exception.AbstractException;

/**
 *
 * @author Hossam ElDeen
 */
public class DelegateException extends AbstractException {
    public DelegateException(String message) {
        super(message);
    }
}
