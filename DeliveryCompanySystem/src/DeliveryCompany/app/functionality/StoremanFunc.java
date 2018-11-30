/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DeliveryCompany.app.functionality;

import DeliveryCompany.database.init.DatabaseInit;
import DeliveryCompany.database.structure.Client;
import DeliveryCompany.database.structure.Storeman;
import DeliveryCompany.database.structure.Courier;
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
    
    //TODO: przetestowac ta funkcje
    public void changePackageCourier(int packageNumber, Courier courier)
    {
        session.beginTransaction();
        
        Query q = session.createQuery("UPDATE Package set courier = :c WHERE id = :id");
        q.setParameter("c", courier);
        q.setParameter("id", packageNumber);
        
        int result = q.executeUpdate(); //TODO: usunac inta albo zrobic return
        
        session.getTransaction().commit();
    }
    
}
