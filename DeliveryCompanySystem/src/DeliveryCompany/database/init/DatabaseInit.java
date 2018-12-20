/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DeliveryCompany.database.init;


import DeliveryCompany.app.enumerate.SessionType;
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
    static Session sessionObjAdmin = null;
    static Session sessionObjLogin = null;
    static Session sessionObjClient = null;
    static Session sessionObjCourier = null;
    static Session sessionObjStoreman = null;
    static SessionFactory SessionFactoryObj;
    
    private SessionFactory buildSession()
    {
        return buildSession("hibernate_Admin.cfg.xml");
    }
    
    /***
     * configuruje i nawiazuje polaczenia z baza danych o wskazanej konfiguracji
     * @param conf plik z konfiguracja
     * @return zwraca utowrzona sesje
     */
    private SessionFactory buildSession(String conf)
    {
        Configuration configObj = new Configuration();
        configObj.configure(conf);
        
        ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(configObj.getProperties()).build();
        
        SessionFactoryObj = configObj.buildSessionFactory(serviceRegistryObj);
        return SessionFactoryObj;
    }
    
    /**
     * odpowiada za utorzenie odpowiedniej sesji (zaleznie od typu zuytkownika) oraz otwarcie jej
     * @param type okresla uzytkownika ktory ma polaczyc sie z baza
     * @return zwraca sesje 
     */
    public Session getSession(SessionType type)
    {
        switch(type)
        {
            case Admin:
            {
                closeSessionsWithout(SessionType.Admin);
                if(sessionObjAdmin == null || !sessionObjAdmin.isOpen())
                    sessionObjAdmin = buildSession("hibernate_Admin.cfg.xml").openSession();
                return sessionObjAdmin;
            }
            case Client:
            {
                closeSessionsWithout(SessionType.Client);
                if(sessionObjClient == null || !sessionObjClient.isOpen())
                    sessionObjClient = buildSession("hibernate_Client.cfg.xml").openSession();
                return sessionObjClient;
            }
            case Courier:
            {
                closeSessionsWithout(SessionType.Courier);
                if(sessionObjCourier == null || !sessionObjCourier.isOpen())
                    sessionObjCourier = buildSession("hibernate_Courier.cfg.xml").openSession();
                return sessionObjCourier;
            }
            case Login:
            {
                closeSessionsWithout(SessionType.Login);
                if(sessionObjLogin == null || !sessionObjLogin.isOpen())
                    sessionObjLogin = buildSession("hibernate_Login.cfg.xml").openSession();
                return sessionObjLogin;
            }
            case Storeman:
            {
                closeSessionsWithout(SessionType.Storeman);
                if(sessionObjStoreman == null || !sessionObjStoreman.isOpen())
                    sessionObjStoreman = buildSession("hibernate_Storeman.cfg.xml").openSession();
                return sessionObjStoreman;
            }
            default:
            {
                return null;
            }
        }
    }
    
    private void closeSessionsWithout(SessionType type)
    {
        if(sessionObjAdmin != null)
            if(sessionObjAdmin.isOpen() && type != SessionType.Admin)
                sessionObjAdmin.close();
        
        if(sessionObjClient != null)
            if(sessionObjClient.isOpen() && type != SessionType.Client)
                sessionObjClient.close();
        
        if(sessionObjCourier != null)
            if(sessionObjCourier.isOpen() && type != SessionType.Courier)
                sessionObjCourier.close();
        
        if(sessionObjLogin != null)
            if(sessionObjLogin.isOpen() && type != SessionType.Login)
                sessionObjLogin.close();
        
        if(sessionObjStoreman != null)
            if(sessionObjStoreman.isOpen() && type != SessionType.Storeman)
                sessionObjStoreman.close();
    }
    
    /*
    public Session getSession()
    {
        
        //if(sessionObj == null)
        //    sessionObj = buildSession().openSession();
        //return sessionObj;
        
        return getSession(SessionType.Admin);
    }
*/
    
    public static DatabaseInit getInstance()
    {
        if(instance == null)
        {
            instance = new DatabaseInit();
        }
        return instance;
    }
    
    /*
    public void setInstance(DatabaseInit instance)
    {
        this.instance = instance;
    }
*/
    
    public void setSession(Session session)
    {
        sessionObj = session;
        sessionObj = session;
        sessionObjAdmin = session;
        sessionObjLogin = session;
        sessionObjClient = session;
        sessionObjCourier = session;
        sessionObjStoreman = session;
    }
}
