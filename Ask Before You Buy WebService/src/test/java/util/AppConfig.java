//package util;
//
//import com.isk.smartkey.business.batteryLog.BatteryLogController;
//import com.isk.smartkey.business.batteryLog.impl.BatterLogControllerImpl;
//import com.isk.smartkey.business.common.BusinessUtil;
//import com.isk.smartkey.business.common.EnviromentValidation;
//import com.isk.smartkey.business.common.LoadingEntitiesController;
//import com.isk.smartkey.business.common.constants.RegexConstants;
//import com.isk.smartkey.business.common.serializer.CustomDateSerializer;
//import com.isk.smartkey.business.common.util.DataBaseUtil;
//import com.isk.smartkey.business.common.util.ServiceUtil;
//import com.isk.smartkey.business.common.util.ValidationUtil;
//import com.isk.smartkey.business.group.GroupController;
//import com.isk.smartkey.business.group.impl.GroupControllerImpl;
//import com.isk.smartkey.business.login.LoginController;
//import com.isk.smartkey.business.login.impl.LoginControllerImpl;
//import com.isk.smartkey.business.message.MessageController;
//import com.isk.smartkey.business.message.impl.MessageControllerImpl;
//import com.isk.smartkey.business.registeration.RegisterationController;
//import com.isk.smartkey.business.registeration.impl.RegisterationControllerImpl;
//import com.isk.smartkey.business.registeration.util.PasswordHash;
//import com.isk.smartkey.business.userLog.UserLogController;
//import com.isk.smartkey.business.userLog.impl.UserLogControllerImpl;
//import com.isk.smartkey.interceptor.databaseValidation.DataBaseValidation;
//import com.isk.smartkey.persistence.dao.SmartKeyDao;
//import com.isk.smartkey.persistence.dao.batteryLog.BatteryLogDao;
//import com.isk.smartkey.persistence.dao.batteryLog.impl.BatteryLogDaoImpl;
//import com.isk.smartkey.persistence.dao.group.GroupDao;
//import com.isk.smartkey.persistence.dao.group.impl.GroupDaoImpl;
//import com.isk.smartkey.persistence.dao.message.MessageDao;
//import com.isk.smartkey.persistence.dao.message.impl.MessageDaoImpl;
//import com.isk.smartkey.persistence.dao.permission.PermissionDao;
//import com.isk.smartkey.persistence.dao.permission.impl.PermissionDaoImpl;
//import com.isk.smartkey.persistence.dao.user.UserDao;
//import com.isk.smartkey.persistence.dao.user.impl.UserDaoImpl;
//import com.isk.smartkey.persistence.dao.userLog.UserLogDao;
//import com.isk.smartkey.persistence.dao.userLog.impl.UserLogDaoImpl;
//import com.isk.smartkey.persistence.entities.*;
//import com.isk.smartkey.service.PaymentService;
//import com.isk.smartkey.service.batteryLog.BatteryLogService;
//import com.isk.smartkey.service.group.GroupManipulationService;
//import com.isk.smartkey.service.login.LoginService;
//import com.isk.smartkey.service.message.MessageService;
//import com.isk.smartkey.service.registeration.RegisterationService;
//import com.isk.smartkey.service.userLog.UserLogService;
//import java.util.Properties;
//import javax.sql.DataSource;
//import org.apache.commons.dbcp.BasicDataSource;
//import org.hibernate.SessionFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.orm.hibernate4.HibernateTemplate;
//import org.springframework.orm.hibernate4.HibernateTransactionManager;
//import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
///**
// *
// * @author Hossam ElDeen
// */
//@Configuration
//@EnableTransactionManagement
//public class AppConfig {
//
//    //common
//    @Bean public BusinessUtil businessUtil(){
//        return new BusinessUtil();
//    }
//    @Bean public EnviromentValidation enviromentValidation(){
//        return new EnviromentValidation();
//    }
//    @Bean public LoadingEntitiesController loadingEntitiesController(){
//        return new LoadingEntitiesController();
//    }
//    @Bean public RegexConstants regexConstants(){
//        return new RegexConstants();
//    }
//    @Bean public CustomDateSerializer customDateSerializer(){
//        return new CustomDateSerializer();
//    }
//    @Bean public DataBaseUtil dataBaseUtil(){
//        return new DataBaseUtil();
//    }
//    @Bean public ServiceUtil serviceUtil(){
//        return new ServiceUtil();
//    }
//    @Bean public ValidationUtil validationUtil(){
//        return new ValidationUtil();
//    }
//    
//    //business
//    @Bean public BatteryLogController batteryLogController(){
//        return new BatterLogControllerImpl();
//    }
//    @Bean public GroupController groupController(){
//        return new GroupControllerImpl();
//    }
//    @Bean public LoginController loginController(){
//        return new LoginControllerImpl();
//    }
//    @Bean public MessageController messageController(){
//        return new MessageControllerImpl();
//    }
//    @Bean public RegisterationController registerationController(){
//        return new RegisterationControllerImpl();
//    }
//    @Bean public UserLogController userLogController(){
//        return new UserLogControllerImpl();
//    }
//    
//    @Bean public PasswordHash passwordHash(){
//        return new PasswordHash();
//    }
//    @Bean public DataBaseValidationInterceptor dataBaseValidation(){
//        return new DataBaseValidationInterceptor();
//    }
//            
//    //Daos
//    @Bean public SmartKeyDao smartKeyDao(){
//        return new UserDaoImpl(sessionFactory());
//    }
//    @Bean public BatteryLogDao batteryLogDao(){
//        return new BatteryLogDaoImpl(sessionFactory());
//    }
//    @Bean public GroupDao groupDao(){
//        return new GroupDaoImpl(sessionFactory());
//    }
//    @Bean public MessageDao messageDao(){
//        return new MessageDaoImpl(sessionFactory());
//    }
//    @Bean public PermissionDao permissionDao(){
//        return new PermissionDaoImpl(sessionFactory());
//    }
//    @Bean public UserDao userDao(){
//        return new UserDaoImpl(sessionFactory());
//    }
//    @Bean public UserLogDao userLogDao(){
//        return new UserLogDaoImpl(sessionFactory());
//    }
//    
//    //services
//    @Bean public PaymentService paymentService() {
//        return new PaymentService();
//    }
//    @Bean public BatteryLogService batteryLogService() {
//        return new BatteryLogService();
//    }
//    @Bean public GroupManipulationService groupManipulationService() {
//        return new GroupManipulationService();
//    }
//    @Bean public LoginService loginService() {
//        return new LoginService();
//    }
//    @Bean public MessageService messageService() {
//        return new MessageService();
//    }
//    @Bean public RegisterationService registerationService() {
//        return new RegisterationService();
//    }
//    @Bean public UserLogService userLogService() {
//        return new UserLogService();
//    }
//    
//    //required configuration
//    @Bean public HibernateTemplate hibernateTemplate() {
//        return new HibernateTemplate(sessionFactory());
//    }
//    @Bean public SessionFactory sessionFactory() {
//        Properties prop = new Properties();
//        prop.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
//        
//        return new LocalSessionFactoryBuilder(getDataSource())
//                .addAnnotatedClasses(BatteryLog.class)
//                .addAnnotatedClasses(Group.class)
//                .addAnnotatedClasses(GroupMessage.class)
//                .addAnnotatedClasses(Permission.class)
//                .addAnnotatedClasses(User.class)
//                .addAnnotatedClasses(UserGroupLink.class)
//                .addAnnotatedClasses(UserLog.class)
//                .addAnnotatedClasses(UserMessage.class)
//                .addProperties(prop)
//                .buildSessionFactory();
//    }
//    @Bean public DataSource getDataSource() {
//        BasicDataSource dataSource = new BasicDataSource();
//        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://localhost:3306/isk_db?zeroDateTimeBehavior=convertToNull");
//        dataSource.setUsername("root");
//        dataSource.setPassword("");
//
//        return dataSource;
//    }
//    @Bean public HibernateTransactionManager hibTransMan() {
//        return new HibernateTransactionManager(sessionFactory());
//    }
//}
