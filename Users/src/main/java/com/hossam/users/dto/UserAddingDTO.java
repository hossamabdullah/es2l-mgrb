/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hossam.users.dto;

import com.hossam.abyb.persistence.entities.User;

/**
 *
 * @author Hossam ElDeen
 */
public class UserAddingDTO {
    private String email;
    private String name;
    private String pictureUrl;
    private String password;

    public UserAddingDTO() {
    }

    public UserAddingDTO(String email, String name, String pictureUrl, String password) {
        this.email = email;
        this.name = name;
        this.pictureUrl = pictureUrl;
        this.password = password;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public Object getEntity(){
        User user = new User();
        user.setEmail(this.getEmail());
        user.setName(this.getName());
        user.setPictureUrl(this.getPictureUrl());
        user.setPassword(this.getPassword());
        return user;
    }
}
