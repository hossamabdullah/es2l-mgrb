/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hossam.common.util;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 *
 * @author Hossam ElDeen
 */
public class DataBaseUtil {
    
    public static boolean isDataBaseRunning(){
        try {
            (new Socket("localhost", 3306)).close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public static boolean isPortInUse(String host, int port){
        try {
            (new Socket(host, port)).close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
