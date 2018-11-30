/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DeliveryCompany.app.functionality;

import DeliveryCompany.database.init.DatabaseInit;
import DeliveryCompany.database.structure.Address;
import DeliveryCompany.database.structure.Client;
import DeliveryCompany.database.structure.Data;
import DeliveryCompany.database.structure.Dimensions;
import DeliveryCompany.database.structure.User;
import com.sun.org.apache.bcel.internal.generic.AALOAD;
import DeliveryCompany.database.structure.Package;
import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Paweł
 */
public class ClientFunc {
    //TODO: zrobic dobieranie kuriera do lokalizacji, w bazie zmienic ID_courier na NOT NULL
    // SPRAWDZANIE CZY PODANY ADRES ISTNIEJE
    // -funkcjonalnosci klienta 
    // -nadawanie przesylki
    // -sledzenie przesylki
    // -historia nadania
    // -
    
    Client client;
    Session session;
    
    public ClientFunc(Client client)
    {
        this.client = client;
        this.session = DatabaseInit.getInstance().getSession();
    }
    
    public void setClient(Client client)
    {
        this.client = client;
    }
    
    public void SendPackage(Data sender, Address addressSender, Data receiver, Address addressReceiver, Dimensions dimension, int telephone)
    {
        session.beginTransaction();
        Date utilDate = new Date();
        
        Package pack = new Package();
        
        sender.setAddress(addressSender);
        receiver.setAddress(addressReceiver);
        
        pack.setSender(sender);
        pack.setReceiver(receiver);
        pack.setClient(client);
        pack.setDimensions(dimension);
        pack.setLocation("Do odebrania od nadawcy");
        pack.setTelephone(telephone);
        pack.setDate(new java.sql.Date(utilDate.getYear(), utilDate.getMonth(), utilDate.getDay()));
        
        session.save(pack);
        
        session.getTransaction().commit();   
    }
    
    public String getPackageLocation(int packageNumber)
    {
        session.beginTransaction();
        
        Query q = session.createQuery("SELECT location FROM Package WHERE ID = :id");
        q.setParameter("id", 1);
        
        String loc =  (String)q.uniqueResult();
        
        
        session.getTransaction().commit();
        return loc;
    }
    
    public List<Package> getAllSentPackage()
    {
        session.beginTransaction();
        
        Query q = session.createQuery("FROM Package WHERE client = :c" );
        q.setParameter("c", client);
        
        List<Package> pack = q.list();

        session.getTransaction().commit();
        return pack;
    } 
}
