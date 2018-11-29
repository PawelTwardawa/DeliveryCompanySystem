/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DeliveryCompany;


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
        
        Email emailClient = new Email();
        //email.setId(4);
        emailClient.setEmail("janKowalski@email.com");
        emailClient.setConfirmation(false);
        
        
        User userClient = new User();
        userClient.setUsername("kowalski");
        userClient.setPassword("pass1234");
        userClient.setUserType("Client");
        userClient.setID_email(emailClient);
        
        Client client = new Client();
        client.setUser(userClient);
        
        Email emailCourier = new Email();
        emailCourier.setEmail("KurierKowalski@delivery.com");
        
        User userCourier = new User();
        userCourier.setUsername("kurier1");
        userCourier.setPassword("pass1234");
        userCourier.setUserType("Courier");
        userCourier.setID_email(emailCourier);
        
        Courier courier = new Courier();
        courier.setUser(userCourier);
        
        Address addressSender = new Address();
        addressSender.setCity("Wroclaw");
        addressSender.setPostCode("34343");
        addressSender.setStreet("Niewiadoma");
        addressSender.setHouseNumber("7");
        addressSender.setApartmentNumber("10");
        
        Address addressReceiver = new Address();
        addressReceiver.setCity("Warszawa");
        addressReceiver.setPostCode("33678");
        addressReceiver.setStreet("Uliczna");
        addressReceiver.setHouseNumber("73");
        addressReceiver.setApartmentNumber("1");
        
        
        Data sender = new Data();
        sender.setFirstName("Jakub");
        sender.setLastName("Nowak");
        sender.setAddress(addressSender);
        
        Data receiver = new Data();
        receiver.setFirstName("Adam");
        receiver.setLastName("Greg");
        receiver.setAddress(addressReceiver);
        
        Dimensions dim = new Dimensions();
        dim.setWidth(10);
        dim.setHeight(10);
        dim.setDepth(10);
        
        Package pack = new Package();
        pack.setSender(sender);
        pack.setReceiver(receiver);
        pack.setLocation("w drodze ");
        pack.setCurier(courier);
        pack.setDimensions(dim);
        pack.setTelephone(787878787);
        pack.setDate(new Date(2018, 11, 29));
        pack.setClient(client);
        
        
        
        sessionObj.save(pack);
            
        /*
        Address addr = new Address();
        addr.setCity("Warszawa");
        addr.setPostCode("33678");
        addr.setStreet("Uliczna");
        addr.setHouseNumber("73");
        addr.setApartmentNumber("1");
        
        Data data = new Data();
        data.setFirstName("Adam");
        data.setLastName("Greg");
        data.setAddress(addr);
        
        sessionObj.save(data);
        */
            
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
