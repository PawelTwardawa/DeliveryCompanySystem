/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DeliveryCompany.app.functionality;

import DeliveryCompany.app.enumerate.DeliveryStatus;
import DeliveryCompany.app.enumerate.LocationStatus;
import DeliveryCompany.database.init.DatabaseInit;
import DeliveryCompany.database.structure.Courier;
import DeliveryCompany.database.structure.CourierData;
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
    
    //public List<Package> getTransportedPackage()
    public List<CourierData> getTransportedPackage()
        {
        session.beginTransaction();
        
        //Query q = session.createQuery("FROM Package WHERE courier = :c" );
        //Query q = session.createQuery("FROM CourierData WHERE ID_courier = :c" );
        Query q = session.createQuery("SELECT TelephoneNumber, ReceiverFirstName, ReceiverLastName, ReceiverCity, ReceiverPostCode, ReceiverStreet, ReceiverHouseNumber, ReceiverApartmentNumber FROM CourierData WHERE ID_courier = :c AND DeliveredStatus = NULL" );
        q.setParameter("c", courier.getId());
        
        //List<Package> pack = q.list();
        List<CourierData> pack = q.list();

        session.getTransaction().commit();
        return pack;
    }
    
    public void setDeliveryStatus(DeliveryStatus status, int packageNumber)
    {
        DeliveryStatus stat;
        LocationStatus loc;
        
        switch(status)
        {
            case delivered:
            {
                stat = DeliveryStatus.delivered;
                loc = LocationStatus.Doreczono;
                break;
            }
            case undelivered:
            {
                stat = DeliveryStatus.undelivered;
                loc = LocationStatus.PowrotDoMagazynu;
                break;
            }
            default:
            {
                return;
            }
        }
        
        session.beginTransaction();
        
        Query q  = session.createQuery("UPDATE Package SET DeliveredStatus = :s, location = :l WHERE id = :id");
        q.setParameter("s", stat.toString());
        q.setParameter("l", loc.toString());
        q.setParameter("id", packageNumber);
        
        int result = q.executeUpdate(); //TODO: usunac inta albo zrobic return
        
        session.getTransaction().commit();
    }
    
}
