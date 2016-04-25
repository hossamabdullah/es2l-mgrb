/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hossam.users.business.impl;

import com.hossam.abyb.persistence.entities.User;
import com.hossam.common.dto.businessResponse.ServiceDataWrapperDTO;
import com.hossam.common.dto.businessResponse.ServiceStatusDTO;
import com.hossam.users.business.UserController;
import com.hossam.users.dao.UserDao;
import com.hossam.users.dto.UserAddingDTO;
import com.hossam.users.dto.UserDTO;
import com.hossam.users.dto.UserLoginDTO;
import com.hossam.users.dto.UserSearchingDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Hossam ElDeen
 */
@Controller
public class UserControllerImpl implements UserController{
    @Autowired
    UserDao userDao;
    
    @Override
    public ServiceDataWrapperDTO searchUser(UserSearchingDTO userSearchingDTO) {
        List<UserDTO> users = userDao.searchUser(userSearchingDTO);
        if(users==null || users.isEmpty())
            return new ServiceDataWrapperDTO(null, "SUCCESS", "no users match the criteria");
        
        return new ServiceDataWrapperDTO(users, "SUCCESS", "users matching the specfied criteria have been found");
    }

    @Override
    public ServiceStatusDTO deactivateUser(UserLoginDTO userLoginDTO) {
        Boolean result = userDao.deactivateUser(userLoginDTO);
        if(result)
            return new ServiceStatusDTO("SUCCESS", "user has been deactivated successfully");
        else
            return new ServiceStatusDTO("FAILED", "email or password is not valid");
    }

    @Override
    public ServiceDataWrapperDTO logInUser(UserLoginDTO userLoginDTO) {
        UserDTO user = userDao.authenticateUser(userLoginDTO);
        if(user == null)
            return new ServiceDataWrapperDTO(null, "FAILED", "email or password is not valid");
        return new ServiceDataWrapperDTO(user, "SUCCESS", "user has logged in successfully");
    }

    @Override
    public ServiceDataWrapperDTO addUser(UserAddingDTO userAddingDTO) {
        User user = (User) userAddingDTO.getEntity();
        userDao.save(user);
        return new ServiceDataWrapperDTO(user.getId(), "SUCCESS", "user has been added successfully");
    }
    
}
