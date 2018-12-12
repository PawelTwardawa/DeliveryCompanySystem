/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DeliveryCompany.app.functionality;

import DeliveryCompany.app.enumerate.LocationStatus;
import DeliveryCompany.database.init.DatabaseInit;
import DeliveryCompany.database.structure.Address;
import DeliveryCompany.database.structure.Client;
import DeliveryCompany.database.structure.ClientHistory;
import DeliveryCompany.database.structure.Courier;
import DeliveryCompany.database.structure.Data;
import DeliveryCompany.database.structure.Dimensions;
import DeliveryCompany.database.structure.User;
import DeliveryCompany.database.structure.Package;
import java.awt.Dimension;
import java.io.Serializable;
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
    
    private Courier chooseCourier()
    {
       session.beginTransaction();
        
        Query q = session.createQuery("FROM Courier");
        
        List<Courier> couriers = q.list();
        session.getTransaction().commit(); 
        
        return couriers.get(0);
    }
    
    private Address findAddress(Address address)
    {
        session.beginTransaction();
        
        Query q = session.createQuery("FROM Address WHERE houseNumber = :hn AND apartmentNumber = :an AND street = :s AND postCode = :pc AND city = :c ");
        q.setParameter("hn", address.getHouseNumber());
        q.setParameter("an", address.getApartmentNumber());
        q.setParameter("s", address.getStreet());
        q.setParameter("pc", address.getPostCode());
        q.setParameter("c", address.getCity());
        
        Address addr =  (Address)q.uniqueResult();
        
        
        session.getTransaction().commit();
        return addr;
    }
    
    private Data findData(Data data, Address address)
    {
        session.beginTransaction();
        
        Query q = session.createQuery("FROM Data WHERE firstName = :fn AND lastName = :ln");
        q.setParameter("fn", data.getFirstName());
        q.setParameter("ln", data.getLastName());
        
        Data dataOut =  (Data)q.uniqueResult();
        session.getTransaction().commit();
        
        if(dataOut != null)
        {
            if (!dataOut.getAddress().equals(address))
            {
                return null;
            }
        }
        return dataOut;
    }
    
    private Data setData(Data data, Address address)
    {
        Data dataSender = findData(data, address);
        if(dataSender == null)
        {
            Address addrSender =  findAddress(address);
            if(addrSender == null)
            {
                data.setAddress(address);
            }
            else
            {
                data.setAddress(addrSender);
            }
            return data;
        }
        else
        {
            return dataSender;
        }
    }
    
    public long SendPackage(Data sender, Address addressSender, Data receiver, Address addressReceiver, Dimensions dimension, int telephone)
    {
        long id;
        Date utilDate = new Date();
        Package pack = new Package();
        
        pack.setSender(setData(sender, addressSender));
        pack.setReceiver(setData(receiver, addressReceiver));
        pack.setClient(client);
        pack.setDimensions(dimension);
        pack.setLocation(LocationStatus.DoOdebraniaOdNadawcy.toString());
        pack.setTelephone(telephone);
        //pack.setDate(new java.sql.Date(utilDate.getYear(), utilDate.getMonth(), utilDate.getDay()));
        pack.setDate(utilDate);
        pack.setCurier(chooseCourier());
        
        try 
        {
            session.beginTransaction();
            id = Long.parseLong(session.save(pack).toString());
            session.getTransaction().commit(); 
            return id;
        }
        catch(Exception ex)
        {
            System.err.println(ex.getMessage());
            return -1;
        }
        
        
    }
    
    public String getPackageLocation(long packageNumber)
    {
        session.beginTransaction();
        
        Query q = session.createQuery("SELECT location FROM Package WHERE ID = :id");
        q.setParameter("id", packageNumber);
        
        String loc =  (String)q.uniqueResult();
        
        
        session.getTransaction().commit();
        return loc;
    }
    
    public List<ClientHistory> getAllSentPackage()
    {
        session.beginTransaction();
        
        Query q = session.createQuery("FROM ClientHistory WHERE ID_client = :c" );
        q.setParameter("c", client.getId());
        
        List<ClientHistory> pack = q.list();

        session.getTransaction().commit();
        return pack;
    } 
    
    public Data changeData(Data data)
    {
        if(data.equals(client.getData()))
            return null;
        
        Data newData = setData(data, data.getAddress());
        
        session.beginTransaction();
        
        long id = Long.parseLong(session.save(newData).toString());
        
        //session.getTransaction().commit(); 
            
        //session.beginTransaction();
        
        Query q  = session.createQuery("UPDATE Client SET data = :d WHERE id = :id");
        q.setParameter("d",newData );
        q.setParameter("id", client.getId());
        
        int result = q.executeUpdate(); //TODO: usunac inta albo zrobic return
        
        session.getTransaction().commit();
        
        //session.beginTransaction();
        
        //Query q2 = session.createQuery("FROM Client WHERE ID = :id");
        //q2.setParameter("id", client.getId());
        
        //Client client =  (Client)q2.uniqueResult();
       
        
        //session.getTransaction().commit();
        
        String name = client.getData().getFirstName();
        
        if(result == 1)
        {
            //this.client = client;
            this.client.setData(newData);
            return newData;
        }
        else
        {
            return null;
        }
        
    }
}
