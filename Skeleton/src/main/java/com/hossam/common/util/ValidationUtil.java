/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hossam.common.util;

import java.util.Map;
import javax.ws.rs.GET;

/**
 *	example of usage is
 *  public ServiceStatusDTO sendMessageToUser(
 *	            @FormParam("from_user_id") Integer fromUserId,
 *	            @FormParam("to_user_id") Integer toUserId,
 *	            @FormParam("date_time") Long dateTime,
 *	            @FormParam("content") String content
 *	    ) {
 *	        Map argumentsMap = new HashMap<String, Object>();
 *	        argumentsMap.put("from_user_id", fromUserId);
 *	        argumentsMap.put("to_user_id", toUserId);
 *	        argumentsMap.put("date_time", dateTime);
 *	        argumentsMap.put("content", content);
 *	        String argumentsErrorMessage = ValidationUtil.checkExistenceOfArguments(argumentsMap);
 *	        if (argumentsErrorMessage != null) {
 *	            return ServiceUtil.transformToServiceDataWrapper(null, "FAILED", argumentsErrorMessage);
 *	        }
 * @author Hossam ElDeen
 */
public class ValidationUtil {

    /**
     *
     * accepts a varargs of ArgumentWrapper which will be checked to determine
     * if every one of them is existed or no and return a string indicating
     * which one was missing or null if no one was missing
     *
     * @param argumentWrappers
     * @return
     */
    public static String checkExistenceOfArguments(Map arguments) {

        ExistenceOfArguments eoa = new ExistenceOfArguments();
        for (Object entryObject : arguments.entrySet()) {
            Map.Entry<String, Object> entry = (Map.Entry<String, Object>) entryObject;
            eoa.accept(entry.getKey(), entry.getValue());
        }

        if (eoa.isNoErrorFound()) {
            return null;
        }
        return eoa.getErrorString();
    }

    /**
     *
     * accepts a varargs of arguments and returns false if there is any argument
     * have a value of null or returns true if all arguments are not null
     *
     * @param objects
     * @return
     */
    public static boolean checkExistenceOfArguments(Object... objects) {
        for (Object object : objects) {
            if (object == null) {
                return false;
            }
        }
        return true;
    }
}

class ExistenceOfArguments {

    private boolean noErrorFound = true;
    private String errorString = "";

    public ExistenceOfArguments() {
    }

    public void setErrorString(String errorString) {
        this.errorString = errorString;
    }

    public String getErrorString() {
        if (!errorString.isEmpty()) {
            errorString += "can not be null or empty";
        }
        return errorString;
    }

    public void setNoErrorFound(boolean noErrorFound) {
        this.noErrorFound = noErrorFound;
    }

    public boolean isNoErrorFound() {
        return noErrorFound;
    }

    public void accept(Object key, Object value) {
        if (value == null || (value instanceof String && ((String) value).isEmpty())) {
            if (noErrorFound) {
                errorString += ((String) key) + " ";
                noErrorFound = false;
            } else {
                errorString += "and " + ((String) key) + " ";
            }
        }
    }

}
