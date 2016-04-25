/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hossam.users.delegate.impl;

import com.hossam.common.dto.businessResponse.ServiceDataWrapperDTO;
import com.hossam.common.dto.businessResponse.ServiceStatusDTO;
import com.hossam.users.business.UserController;
import com.hossam.users.delegate.UserDelegate;
import com.hossam.users.dto.UserAddingDTO;
import com.hossam.users.dto.UserLoginDTO;
import com.hossam.users.dto.UserSearchingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Hossam ElDeen
 */
@Component
public class UserDelegateImpl implements UserDelegate {

    @Autowired
    UserController UserController;

    @Override
    public ServiceDataWrapperDTO addUser(UserAddingDTO userAddingDTO) {
        try {
            ServiceDataWrapperDTO serviceDataWrapperDTO = UserController.addUser(userAddingDTO);
            return serviceDataWrapperDTO;
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ServiceDataWrapperDTO(null, "FAILED", "an internal error has been happened");
        }
    }

    @Override
    public ServiceDataWrapperDTO logInUser(UserLoginDTO userLoginDTO) {
        try {
            ServiceDataWrapperDTO serviceDataWrapperDTO = UserController.logInUser(userLoginDTO);
            return serviceDataWrapperDTO;
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ServiceDataWrapperDTO(null, "FAILED", "an internal error has been happened");
        }
    }

    @Override
    public ServiceStatusDTO deactivateUser(UserLoginDTO userLoginDTO) {
        try {
            ServiceStatusDTO serviceStatusDTO = UserController.deactivateUser(userLoginDTO);
            return serviceStatusDTO;
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ServiceDataWrapperDTO(null, "FAILED", "an internal error has been happened");
        }
    }

    @Override
    public ServiceDataWrapperDTO searchUser(UserSearchingDTO userSearchingDTO) {
        try {
            ServiceDataWrapperDTO serviceDataWrapperDTO = UserController.searchUser(userSearchingDTO);
            return serviceDataWrapperDTO;
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ServiceDataWrapperDTO(null, "FAILED", "an internal error has been happened");
        }
    }

}
