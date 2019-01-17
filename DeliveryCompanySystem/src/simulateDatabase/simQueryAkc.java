/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulateDatabase;

import DeliveryCompany.app.enumerate.LocationStatus;
import DeliveryCompany.database.structure.Address;
import DeliveryCompany.database.structure.Courier;
import DeliveryCompany.database.structure.Data;
import DeliveryCompany.database.structure.Email;
import DeliveryCompany.database.structure.StoremanData;
import DeliveryCompany.database.structure.User;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Query;

/**
 *
 * @author Paweł
 */
public class simQueryAkc {
private Object obj;
    private Map<String, Object> map;
    private String type;
    
    public simQueryAkc(String type)
    {
        this.type = type;
        map = new HashMap<>();
    }
    
    public Query setParameter(String string, Object o) {
        map.put(string, o);
        return null;
    }
    
    public Object uniqueResult()
    {
        if(type.equals("User"))
        {
            if(!map.get("un").equals("pawel"))
                return null;
            else if(!map.get("pa").equals(getSecurePassword("pass")))
                return null;    
            else return new User();
        }
        
        if(type.equals("UserEmail"))
        {
            if(!map.get("u").equals("pawel"))
                return null;  
            else return new User();
        }
        
        if(type.equals("Email"))
        {
            if(!map.get("e").equals("email@email.com"))
                return null;   
            else return new Email();
        }
        
        if(type.equals("Address"))
        {
            if(!map.get("hn").equals("10"))
                return null;  
            else if(!map.get("an").equals("10"))
                return null; 
            else if(!map.get("s").equals("ulica"))
                return null; 
            else if(!map.get("pc").equals("00-000"))
                return null; 
            else if(!map.get("c").equals("miasto"))
                return null; 
            else
            {
                Address senderAddress = new Address("10", "10", "ulica", "00-000", "miasto");
                senderAddress.setId(1);
                return senderAddress;
            }
           
        }
        
        if(type.equals("Data"))
        {
            if(!map.get("fn").equals("pawel"))
                return null;
            if(!map.get("ln").equals("kowalski"))
                return null;  
            else return new Data();
        }
        
        if(type.equals("Location"))
        {
            if((long)map.get("id") == 1)
                return LocationStatus.Doreczono.toString();
            if((long)map.get("id") == 2)
                return LocationStatus.OdebranoOdNadawcy.toString();
            else return null;
        }
        
        return null;
    }
    
    public List list() {
        if(type.equals("Data"))
        {
            if(!map.get("fn").equals("pawel"))
                return null;
            if(!map.get("ln").equals("kowalski"))
                return null;  
            else
            {
                Address senderAddress = new Address("10", "10", "ulica", "00-000", "miasto");
                senderAddress.setId(1);
                Data data = new Data("pawel", "kowalski", senderAddress);
                data.setId(1);
               ArrayList<Data> list = new ArrayList<>();
                list.add(data);
                return list;
            }
        }
        
        if(type.equals("Courier"))
        {   
            Courier courier = new Courier();
            courier.setId(0);
            courier.setUser(new User());
                
                ArrayList<Courier> list = new ArrayList<>();
                list.add(courier);
                return list;
            
        }
        
        
        if(type.equals("getTransportedPackage"))
        {   
            
            if(!map.containsKey("s"))
            {
                 List<StoremanData> data = new ArrayList();
                 data.add(new StoremanData());
                 data.add(new StoremanData());
                 data.add(new StoremanData());
                 data.add(new StoremanData());
                 return data;
            }
            else
            {
                if(map.get("s").equals("delivered"))
                {
                    List<StoremanData> data = new ArrayList();
                    data.add(new StoremanData());
                    data.add(new StoremanData());
                    return data;
                }
                else
                {
                    List<StoremanData> data = new ArrayList();
                    return data;
                }
            }
        }
        
        return null;
    }

     private static String getSecurePassword(String passwordToHash)
    {
        String generatedPassword = null;
        try 
        {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x101, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return generatedPassword;
    }
}