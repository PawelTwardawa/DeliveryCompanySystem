/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DeliveryCompany;


import java.io.Console;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.*;
import org.hibernate.service.ServiceRegistry;
/**
 *
 * @author Paweł
 */
public class DeliveryCompanySystem {

    /**
     * @param args the command line arguments
     */
    static Session sessionObj;
    static SessionFactory SessionFactoryObj;
    
    private static SessionFactory buildSessionFactory()
    {
        Configuration configObj = new Configuration();
        configObj.configure("hibernate.cfg.xml");
        
        ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(configObj.getProperties()).build();
        
        SessionFactoryObj = configObj.buildSessionFactory(serviceRegistryObj);
        return SessionFactoryObj;
    }
    
    public static void main(String[] args) {
        
        System.out.print("start");
        try
        {
        sessionObj = buildSessionFactory().openSession();
        sessionObj.beginTransaction();
        /*
        User user = new User();
        user.setUsername("pawel");
        user.setPassword("pass1234");
        user.setUserType("Client");
        user.setID_email(3);
        
        sessionObj.save(user);
        */
        
        Email email = new Email();
        email.setId(0);
        email.setEmail("email@email.com");
        email.setConfirmation(false);
        
        sessionObj.save(email);
        sessionObj.getTransaction().commit();
        }
        catch(Exception ex)
        {
            if(null != sessionObj.getTransaction()) {
				System.out.println("\n.......Transaction Is Being Rolled Back.......");
				sessionObj.getTransaction().rollback();
			}
            ex.printStackTrace();
        }
        finally
        {
            //if(sessionObj != null)
            //{
                sessionObj.close();
            //}
        }
        System.out.print("end");
    }
    
}
