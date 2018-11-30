/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DeliveryCompany.app.functionality;

import DeliveryCompany.app.enumerate.DeliveryStatus;
import DeliveryCompany.database.init.DatabaseInit;
import DeliveryCompany.database.structure.Courier;
import DeliveryCompany.database.structure.Package;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Paweł
 */
public class CourierFunc {
    
    Courier courier;
    Session session;

    public CourierFunc(Courier courier) {
        this.courier = courier;
        this.session = DatabaseInit.getInstance().getSession();
    }
    
    public List<Package> getTransportedPackage()
    {
        session.beginTransaction();
        
        Query q = session.createQuery("FROM Package WHERE courier = :c" );
        q.setParameter("c", courier);
        
        List<Package> pack = q.list();

        session.getTransaction().commit();
        return pack;
    }
    
    public void setDeliveryStatus(DeliveryStatus status)
    {
        //TODO:
    }
    
}
