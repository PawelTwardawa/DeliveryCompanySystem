/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DeliveryCompany.database.init;


import java.awt.Dimension;
import java.io.Console;
import java.sql.Date;
import java.util.List;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.*;
import org.hibernate.service.ServiceRegistry;
/**
 *
 * @author Paweł
 */
public class DatabaseInit {
    
    private static DatabaseInit instance;
    
    static Session sessionObj = null;
    static SessionFactory SessionFactoryObj;
    
    //public static SessionFactory buildSession()
    private SessionFactory buildSession()
    {
        Configuration configObj = new Configuration();
        configObj.configure("hibernate.cfg.xml");
        
        ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(configObj.getProperties()).build();
        
        SessionFactoryObj = configObj.buildSessionFactory(serviceRegistryObj);
        return SessionFactoryObj;
    }
    
    public Session getSession()
    {
        if(sessionObj == null)
            sessionObj = buildSession().openSession();
        return sessionObj;
    }
    
    public static DatabaseInit getInstance()
    {
        if(instance == null)
        {
            instance = new DatabaseInit();
        }
        return instance;
    }
}
