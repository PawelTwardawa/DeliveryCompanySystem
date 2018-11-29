/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DeliveryCompany;


import java.io.Console;
import java.util.List;
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
/*
        Email email = new Email();
        email.setId(0);
        email.setEmail("email@email.com");
        email.setConfirmation(false);
        
        sessionObj.save(email);
        */
        /*
        String hql = "FROM User";
        Query query = sessionObj.createQuery(hql);
        List<User> results = query.list();
        
            for (User us : results) {
                System.out.println(us.getUsername());
            }

        Query query2 = sessionObj.createQuery("SELECT Username, Password FROM User");
        List<Object[]> users = (List<Object[]>)query2.list();
        
            for (Object[] user : users) {
                System.out.println(user[0] + " " + user[1]);
            }
        */
        Email email = new Email();
        //email.setId(4);
        email.setEmail("test@test.pwr");
        email.setConfirmation(false);
        
        
        User user = new User();
        user.setUsername("testJoin2");
        user.setPassword("pass1234");
        user.setUserType("Client");
        user.setID_email(email);
        
        sessionObj.save(user);
            
            
        sessionObj.getTransaction().commit();
        }
        catch(Exception ex)
        {
            System.err.println(ex.getMessage());
            /*
            if(null != sessionObj.getTransaction()) {
				System.out.println("\n.......Transaction Is Being Rolled Back.......");
				sessionObj.getTransaction().rollback();
			}
            ex.printStackTrace();
            */
        }
        finally
        {
            //if(sessionObj != null)
            //{
                sessionObj.close();
            //}
        }
        System.out.print("end");
        System.exit(0);
    }
    
}
