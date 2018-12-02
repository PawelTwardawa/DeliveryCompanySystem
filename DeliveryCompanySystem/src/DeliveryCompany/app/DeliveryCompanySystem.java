/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DeliveryCompany.app;


import DeliveryCompany.app.enumerate.DeliveryStatus;
import DeliveryCompany.app.enumerate.UserType;
import DeliveryCompany.app.functionality.ClientFunc;
import DeliveryCompany.app.functionality.CourierFunc;
import DeliveryCompany.app.functionality.StoremanFunc;
import DeliveryCompany.app.functionality.UserFunc;
import DeliveryCompany.app.gui.Login;
import DeliveryCompany.database.structure.Courier;
import DeliveryCompany.database.structure.Dimensions;
import DeliveryCompany.database.structure.Client;
import DeliveryCompany.database.structure.Address;
import DeliveryCompany.database.structure.User;
import DeliveryCompany.database.structure.Email;
import DeliveryCompany.database.structure.Package;
import DeliveryCompany.database.structure.Data;
import DeliveryCompany.database.init.DatabaseInit;
import DeliveryCompany.database.structure.CourierData;
import java.awt.Dimension;
import java.io.Console;
import java.sql.Date;
import java.util.List;
import net.sf.ehcache.search.aggregator.Count;
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
    //static SessionFactory SessionFactoryObj;
    
    public static void main(String[] args) throws Exception {
        
        System.out.print("start");
        
        
        /*
        sessionObj = DatabaseInit.getInstance().getSession();
        //sessionObj.beginTransaction();
        
        UserFunc uf = new UserFunc();
        //System.out.println(uf.Registry("anna", "password1234", "anna@email.com", UserType.Client));
        
        User user = uf.Login("adam", "password1234");
        
        
        
        
        System.out.println(((Client)uf.getMembership(user)).getUser().getUserType());
        
        System.out.println(user.getUsername());
        */
        
        /*
        Query userQuery = sessionObj.createQuery("FROM User WHERE Username = 'kurier1'");
        User user = (User)userQuery.uniqueResult();
        
        Query q = sessionObj.createQuery("FROM Courier WHERE user = :u");
        q.setParameter("u", user);
        Courier courier = (Courier)q.uniqueResult();
        
        CourierFunc courierFunc = new CourierFunc(courier);
        
        List<CourierData> data = courierFunc.getTransportedPackage();
        //courierFunc.setDeliveryStatus(DeliveryStatus.delivered, 1);
        */
        /*
        StoremanFunc storemanFunc = new StoremanFunc(null);
        
        storemanFunc.changePackageCourier(3, courier);
        */
        
        /*
        Query userQuery = sessionObj.createQuery("FROM User WHERE Username = 'pawel'");
        User user = (User)userQuery.uniqueResult();
        
        
        Query q = sessionObj.createQuery("FROM Client WHERE user = :us");
        q.setParameter("us", user);
        Client clients = (Client)q.uniqueResult();
        
        sessionObj.getTransaction().commit();
        ClientFunc clientFunc = new ClientFunc(clients);
        //String loc = clientFunc.getPackageLocation(1);
        //System.err.println(loc);
        
        Address addressSender = new Address();
        addressSender.setCity("Poznan");
        addressSender.setPostCode("27903");
        addressSender.setStreet("Warszawska");
        addressSender.setHouseNumber("4");
        addressSender.setApartmentNumber("15");
        
        Address addressReceiver = new Address();
        addressReceiver.setCity("Krakow");
        addressReceiver.setPostCode("23468");
        addressReceiver.setStreet("Wroclawska");
        addressReceiver.setHouseNumber("34");
        addressReceiver.setApartmentNumber("6");
        
        
        Data sender = new Data();
        sender.setFirstName("Pawel");
        sender.setLastName("Adamczyk");
        sender.setAddress(addressSender);
        
        Data receiver = new Data();
        receiver.setFirstName("Anna");
        receiver.setLastName("Brat");
        receiver.setAddress(addressReceiver);
        
        Dimensions dim = new Dimensions();
        dim.setWidth(6);
        dim.setHeight(12);
        dim.setDepth(8);
        
        clientFunc.SendPackage(sender, addressSender, receiver, addressReceiver, dim, 123456789); 
        
        */
        /*
        List<Package> pack = clientFunc.getAllSentPackage();
        */
        
        
        
        /*
        Query userQuery = sessionObj.createQuery("FROM User WHERE Username = 'pawel'");
        User user = (User)userQuery.uniqueResult();
        
        Client client;
        
        Query q = sessionObj.createQuery("FROM Client");
        List<Client> clients = q.list();
        for(Client obj : clients)
        {
            if(obj.getUser().getUsername() == user.getUsername())
            {
                
            }
        }
        */
        
        
        
        //ClientFunc clientFunc = new ClientFunc(client)
        
        //try
        //{
        //sessionObj = buildSessionFactory().openSession();
           //sessionObj =  DatabaseInit.getSession();
        //sessionObj =   DatabaseInit.getInstance().getSession();
        //sessionObj =  DatabaseInit.buildSession().openSession();
        //sessionObj.beginTransaction();
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
        /*
        Email emailClient = new Email();
        //email.setId(4);
        emailClient.setEmail("pawelNo@email.com");
        emailClient.setConfirmation(false);
        
        
        User userClient = new User();
        userClient.setUsername("robert");
        userClient.setPassword("pass1234");
        userClient.setUserType("Client");
        userClient.setID_email(emailClient);
        
        Client client = new Client();
        client.setUser(userClient);
        
        Email emailCourier = new Email();
        emailCourier.setEmail("KurierAdrian@delivery.com");
        
        User userCourier = new User();
        userCourier.setUsername("kurier4");
        userCourier.setPassword("pass1234");
        userCourier.setUserType("Courier");
        userCourier.setID_email(emailCourier);
        
        Courier courier = new Courier();
        courier.setUser(userCourier);
        
        Address addressSender = new Address();
        addressSender.setCity("Poznan");
        addressSender.setPostCode("27903");
        addressSender.setStreet("Warszawska");
        addressSender.setHouseNumber("4");
        addressSender.setApartmentNumber("15");
        
        Address addressReceiver = new Address();
        addressReceiver.setCity("Krakow");
        addressReceiver.setPostCode("23468");
        addressReceiver.setStreet("Wroclawska");
        addressReceiver.setHouseNumber("34");
        addressReceiver.setApartmentNumber("6");
        
        
        Data sender = new Data();
        sender.setFirstName("Pawel");
        sender.setLastName("Adamczyk");
        sender.setAddress(addressSender);
        
        Data receiver = new Data();
        receiver.setFirstName("Ewa");
        receiver.setLastName("Path");
        receiver.setAddress(addressReceiver);
        
        Dimensions dim = new Dimensions();
        dim.setWidth(6);
        dim.setHeight(12);
        dim.setDepth(8);
        
        Package pack = new Package();
        pack.setSender(sender);
        pack.setReceiver(receiver);
        pack.setLocation("w drodze ");
        pack.setCurier(courier);
        pack.setDimensions(dim);
        pack.setTelephone(565656565);
        pack.setDate(new Date(2018, 11, 29));
        pack.setClient(client);
        
        
        
        sessionObj.save(pack);
           */ 
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
            
        //sessionObj.getTransaction().commit();
        //}
        //catch(Exception ex)
        //{
            //System.err.println(ex.getMessage());
            /*
            if(null != sessionObj.getTransaction()) {
				System.out.println("\n.......Transaction Is Being Rolled Back.......");
				sessionObj.getTransaction().rollback();
			}
            ex.printStackTrace();
            */
        //}
        /*
        finally
        {
            //if(sessionObj != null)
            //{
                sessionObj.close();
            //}
        }
*/
        System.out.print("end");
        System.exit(0);
    }
    
}
