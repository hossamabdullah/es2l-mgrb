/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hossam.users.dao;

import com.hossam.persistence.dao.AbstractDao;
import com.hossam.users.dto.UserDTO;
import com.hossam.users.dto.UserLoginDTO;
import com.hossam.users.dto.UserSearchingDTO;
import java.util.List;

/**
 *
 * @author Hossam ElDeen
 */
public abstract class UserDao extends AbstractDao{

    public abstract UserDTO authenticateUser(UserLoginDTO userLoginDTO);

    public abstract Boolean deactivateUser(UserLoginDTO userLoginDTO);

    public abstract List<UserDTO> searchUser(UserSearchingDTO userSearchingDTO);
    
}
