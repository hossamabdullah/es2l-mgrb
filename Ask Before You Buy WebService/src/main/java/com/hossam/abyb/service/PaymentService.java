package com.hossam.abyb.service;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;

@Service
@Path("/payment")
public class PaymentService {
        
	@GET
	public Response savePayment() {
//                List<User> allUsers = ((UserDaoImpl)UserDao).getAllUsers();
//		String result = "There are "+allUsers.size()+" Users.";
		return Response.status(200).entity("Helo World").build();
	}
        
        public boolean getTrue(){
            return true;
        }
}