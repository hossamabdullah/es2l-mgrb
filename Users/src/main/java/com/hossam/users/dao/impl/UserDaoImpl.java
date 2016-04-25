/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hossam.users.dao.impl;

import com.hossam.abyb.persistence.entities.User;
import com.hossam.users.dao.UserDao;
import com.hossam.users.dto.UserDTO;
import com.hossam.users.dto.UserLoginDTO;
import com.hossam.users.dto.UserSearchingDTO;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Hossam ElDeen
 */
@Repository
public class UserDaoImpl extends UserDao {

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    @Override
    public UserDTO authenticateUser(UserLoginDTO userLoginDTO) {
        Criteria cr = getSessionFactory().getCurrentSession().createCriteria(User.class);
        cr.add(Restrictions.eq("email", userLoginDTO.getEmail()));
        cr.add(Restrictions.eq("password", userLoginDTO.getPassword()));
        User user = (User) cr.uniqueResult();
        if (user == null) {
            return null;
        }
        UserDTO userDTO = UserDTO.fromEntity(user);
        activateUser(userDTO);
        return userDTO;
    }

    @Override
    public Boolean deactivateUser(UserLoginDTO userLoginDTO) {
        UserDTO user = authenticateUser(userLoginDTO);
        if(user==null)
            return false;
        String queryString = "UPDATE User set disabled = :disabled WHERE id = :userId";
        Query query = getSessionFactory().getCurrentSession().createQuery(queryString);
        query.setParameter("disabled", 0);
        query.setParameter("userId", user.getId());
        query.executeUpdate();
        return true;
    }
    
    private void activateUser(UserDTO user) {
        String queryString = "UPDATE User set disabled = :disabled WHERE id = :userId";
        Query query = getSessionFactory().getCurrentSession().createQuery(queryString);
        query.setParameter("disabled", 1);
        query.setParameter("userId", user.getId());
        query.executeUpdate();
    }

    @Override
    public List<UserDTO> searchUser(UserSearchingDTO userSearchingDTO) {
        Criteria cr = getSessionFactory().getCurrentSession().createCriteria(User.class);
        if (userSearchingDTO.getRetrievalType() == UserSearchingDTO.RetrievalType.EMAIL) {
            cr.add(Restrictions.eq("email", userSearchingDTO.getKey()));
        } else if (userSearchingDTO.getRetrievalType() == UserSearchingDTO.RetrievalType.NAME) {
            cr.add(Restrictions.eq("name", userSearchingDTO.getKey()));
        }
        List<User> users = cr.list();
        if (users == null || users.isEmpty()) {
            return null;
        }
        List<UserDTO> userDTOs = UserDTO.fromListOfEntities(users);
        return userDTOs;
    }

}
