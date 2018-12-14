/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DeliveryCompany.app.functionality;

import DeliveryCompany.app.enumerate.DeliveryStatus;
import DeliveryCompany.database.init.DatabaseInit;
import DeliveryCompany.database.structure.Client;
import DeliveryCompany.database.structure.Storeman;
import DeliveryCompany.database.structure.Courier;
import DeliveryCompany.database.structure.StoremanData;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Paweł
 */
//TODO: zrobic to inaczej
public class StoremanFunc extends ClientFunc{
    
    private Storeman storeman;
    Session session;
    
    public StoremanFunc(Storeman storeman) {
        super(null);
        this.storeman = storeman;
        this.session = DatabaseInit.getInstance().getSession();
    }
   
    //public void changePackageCourier(int packageNumber, Courier courier)
    public int changePackageCourier(StoremanData data)
    {
        Courier courier = targetCourier(data);
        session.beginTransaction();
        
        Query q = session.createQuery("UPDATE Package SET curier = :c, DeliveredStatus =:s  WHERE id = :id");
        q.setParameter("c", courier);
        q.setParameter("s", DeliveryStatus.toDelivery.toString());
        q.setParameter("id", data.getID());
        
        int result = q.executeUpdate(); //TODO: usunac inta albo zrobic return
        
        session.getTransaction().commit();
        return result;
    }
    
    public List<StoremanData> getPackageInWarehouse()
    {
        
        session.beginTransaction();
        
        Query q = session.createQuery("FROM StoremanData WHERE DeliveredStatus = :s");
        q.setParameter("s", DeliveryStatus.inWarehouse.toString());
        
        List<StoremanData> data = q.list();
        session.getTransaction().commit(); 
        
        return data;
    }
    
    public Courier targetCourier(StoremanData data)
    {
        session.beginTransaction();
        
        Query q = session.createQuery("FROM Courier");
        
        List<Courier> couriers = q.list();
        session.getTransaction().commit(); 
        
        
        
        return couriers.get(1);
    }
    
}
