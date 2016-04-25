/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hossam.users.service;

import com.hossam.common.dto.businessResponse.ServiceDataWrapperDTO;
import com.hossam.common.dto.businessResponse.ServiceStatusDTO;
import com.hossam.common.util.ValidationUtil;
import com.hossam.users.delegate.UserDelegate;
import com.hossam.users.dto.UserAddingDTO;
import com.hossam.users.dto.UserLoginDTO;
import com.hossam.users.dto.UserSearchingDTO;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Hossam ElDeen
 */
@Service
@Path("/user")
public class UserService {
    @Context
    private HttpServletRequest request;
    
    @Autowired(required = true)
    private UserDelegate userDelegate;
    
    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    public ServiceDataWrapperDTO addUser(@FormParam("email")String email,
            @FormParam("name")String name,
            @FormParam("picture")String picture){
        String password = request.getHeader("password");
        
        Map argumentsMap = new HashMap<String, Object>();
        argumentsMap.put("email", email);
        argumentsMap.put("name", name);
        argumentsMap.put("picture", picture);
        argumentsMap.put("password", password);
        String argumentsErrorMessage = ValidationUtil.checkExistenceOfArguments(argumentsMap);
        if (argumentsErrorMessage != null) {
            return new ServiceDataWrapperDTO(null, "FAILED", argumentsErrorMessage);
        }
        
        UserAddingDTO userAddingDTO = new UserAddingDTO(email, name, picture, password);
        ServiceDataWrapperDTO serviceDataWrapperDTO = userDelegate.addUser(userAddingDTO);
        return serviceDataWrapperDTO;
    }
    
    
    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public ServiceDataWrapperDTO logInUser(@FormParam("email")String email){
        String password = request.getHeader("password");
        
        Map argumentsMap = new HashMap<String, Object>();
        argumentsMap.put("email", email);
        argumentsMap.put("password", password);
        String argumentsErrorMessage = ValidationUtil.checkExistenceOfArguments(argumentsMap);
        if (argumentsErrorMessage != null) {
            return new ServiceDataWrapperDTO(null, "FAILED", argumentsErrorMessage);
        }
        
        UserLoginDTO userLoginDTO = new UserLoginDTO(email, password);
        ServiceDataWrapperDTO serviceDataWrapperDTO = userDelegate.logInUser(userLoginDTO);
        return serviceDataWrapperDTO;
    }
    
    public ServiceStatusDTO deactivateUser(@FormParam("email")String email){
        String password = request.getHeader("password");
        
        Map argumentsMap = new HashMap<String, Object>();
        argumentsMap.put("email", email);
        argumentsMap.put("password", password);
        String argumentsErrorMessage = ValidationUtil.checkExistenceOfArguments(argumentsMap);
        if (argumentsErrorMessage != null) {
            return new ServiceStatusDTO("FAILED", argumentsErrorMessage);
        }
        
        UserLoginDTO userLoginDTO = new UserLoginDTO(email, password);
        ServiceStatusDTO serviceStatusDTO = userDelegate.deactivateUser(userLoginDTO);
        return serviceStatusDTO;
    }
    
    public ServiceDataWrapperDTO searchUserWithEmail(@FormParam("email")String email){
        Map argumentsMap = new HashMap<String, Object>();
        argumentsMap.put("email", email);
        String argumentsErrorMessage = ValidationUtil.checkExistenceOfArguments(argumentsMap);
        if (argumentsErrorMessage != null) {
            return new ServiceDataWrapperDTO(null, "FAILED", argumentsErrorMessage);
        }
        
        UserSearchingDTO userSearchingDTO = new UserSearchingDTO(email, UserSearchingDTO.RetrievalType.EMAIL);
        ServiceDataWrapperDTO serviceDataWrapperDTO = userDelegate.searchUser(userSearchingDTO);
        return serviceDataWrapperDTO;
    }
    
    public ServiceDataWrapperDTO searchUserWithName(@FormParam("name")String name){
        Map argumentsMap = new HashMap<String, Object>();
        argumentsMap.put("name", name);
        String argumentsErrorMessage = ValidationUtil.checkExistenceOfArguments(argumentsMap);
        if (argumentsErrorMessage != null) {
            return new ServiceDataWrapperDTO(null, "FAILED", argumentsErrorMessage);
        }
        
        UserSearchingDTO userSearchingDTO = new UserSearchingDTO(name, UserSearchingDTO.RetrievalType.NAME);
        ServiceDataWrapperDTO serviceDataWrapperDTO = userDelegate.searchUser(userSearchingDTO);
        return serviceDataWrapperDTO;
    }
}
