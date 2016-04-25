/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hossam.users.business;

import com.hossam.common.dto.businessResponse.ServiceDataWrapperDTO;
import com.hossam.common.dto.businessResponse.ServiceStatusDTO;
import com.hossam.users.dto.UserAddingDTO;
import com.hossam.users.dto.UserLoginDTO;
import com.hossam.users.dto.UserSearchingDTO;

/**
 *
 * @author Hossam ElDeen
 */
public interface UserController {

    public ServiceDataWrapperDTO searchUser(UserSearchingDTO userSearchingDTO);

    public ServiceStatusDTO deactivateUser(UserLoginDTO userLoginDTO);

    public ServiceDataWrapperDTO logInUser(UserLoginDTO userLoginDTO);

    public ServiceDataWrapperDTO addUser(UserAddingDTO userAddingDTO);
    
}
