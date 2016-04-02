///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.isk.smartkey.service;
//
//import javax.transaction.Transactional;
//import util.AppConfig;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
//import org.junit.runner.RunWith;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.support.AnnotationConfigContextLoader;
//import org.springframework.beans.factory.annotation.Autowired;
///**
// *
// * @author Hossam ElDeen
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = AppConfig.class, loader = AnnotationConfigContextLoader.class)
//@Rollback
//@Transactional
//public class PaymentServiceTest {
//    
//    @Autowired
//    private PaymentService paymentService;
//    
//    public PaymentServiceTest() {
//    }
//    
//    @BeforeClass
//    public static void setUpClass() {
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }
//    
//    @Before
//    public void setUp() {
//    }
//    
//    @After
//    public void tearDown() {
//    }
//    
//    @Test
//    public void test1(){
//        assertFalse(paymentService.getTrue());
//    }
//}
