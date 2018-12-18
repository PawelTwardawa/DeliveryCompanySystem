/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import DeliveryCompany.app.enumerate.DeliveryStatus;
import DeliveryCompany.app.enumerate.LocationStatus;
import DeliveryCompany.app.functionality.CourierFunc;
import DeliveryCompany.database.init.DatabaseInit;
import DeliveryCompany.database.structure.Courier;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

/**
 *
 * @author Paweł
 */
public class CourierFuncTest {
    
    //@Mock
    Session session;
    //@Mock
    DatabaseInit database;
    
    public CourierFuncTest() {
    }
    
    @Before
    public void setUp()
    {

        session = Mockito.mock(Session.class);
        
        database = Mockito.spy(new DatabaseInit());
        database.setSession(session);
        
        Mockito.when(session.beginTransaction()).thenReturn(null);
        Mockito.when(session.getTransaction()).thenReturn(Mockito.mock(Transaction.class));
        
    }
    /*
    @Test
    public void setDeliveryStatusTestDelivered()
    {
        ArgumentCaptor<String> statCap = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> statCap1 = ArgumentCaptor.forClass(String.class);
        
        Query q = Mockito.mock(Query.class);
        
        Mockito.when(session.createQuery("UPDATE Package SET DeliveredStatus = :s, location = :l WHERE id = :id")).thenReturn(q);
        Mockito.when(q.executeUpdate()).thenReturn(1);
        
        
        CourierFunc cf = new CourierFunc(new Courier());
        cf.setDeliveryStatus(DeliveryStatus.delivered, 0);
        
        Mockito.verify(q, Mockito.times(3)).setParameter(statCap1.capture(), statCap.capture());
        
        Assert.assertEquals(statCap.getAllValues().get(0), DeliveryStatus.delivered.toString());
        Assert.assertEquals(statCap.getAllValues().get(1), LocationStatus.Doreczono.toString());
        
    }
    
    @Test
    public void setDeliveryStatusTestUndelivered()
    {
        ArgumentCaptor<String> statCap = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> statCap1 = ArgumentCaptor.forClass(String.class);
        
        Query q = Mockito.mock(Query.class);
        
        Mockito.when(session.createQuery("UPDATE Package SET DeliveredStatus = :s, location = :l WHERE id = :id")).thenReturn(q);
        Mockito.when(q.executeUpdate()).thenReturn(1);
        
        
        CourierFunc cf = new CourierFunc(new Courier());
        cf.setDeliveryStatus(DeliveryStatus.undelivered, 0);
        
        Mockito.verify(q, Mockito.times(3)).setParameter(statCap1.capture(), statCap.capture());
        
        Assert.assertEquals(statCap.getAllValues().get(0), DeliveryStatus.undelivered.toString());
        Assert.assertEquals(statCap.getAllValues().get(1), LocationStatus.PowrotDoMagazynu.toString());
        
    }
    
    @Test
    public void setDeliveryStatusTestPickedUp()
    {
        ArgumentCaptor<String> statCap = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> statCap1 = ArgumentCaptor.forClass(String.class);
        
        Query q = Mockito.mock(Query.class);
        
        Mockito.when(session.createQuery("UPDATE Package SET DeliveredStatus = :s, location = :l WHERE id = :id")).thenReturn(q);
        Mockito.when(q.executeUpdate()).thenReturn(1);
        
        
        CourierFunc cf = new CourierFunc(new Courier());
        cf.setDeliveryStatus(DeliveryStatus.pickedUp, 0);
        
        Mockito.verify(q, Mockito.times(3)).setParameter(statCap1.capture(), statCap.capture());
        
        Assert.assertEquals(statCap.getAllValues().get(0), DeliveryStatus.pickedUp.toString());
        Assert.assertEquals(statCap.getAllValues().get(1), LocationStatus.OdebranoOdNadawcy.toString());
        
    }
    
    @Test
    public void setDeliveryStatusTestNotPickedUp()
    {
        ArgumentCaptor<String> statCap = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> statCap1 = ArgumentCaptor.forClass(String.class);
        
        Query q = Mockito.mock(Query.class);
        
        Mockito.when(session.createQuery("UPDATE Package SET DeliveredStatus = :s, location = :l WHERE id = :id")).thenReturn(q);
        Mockito.when(q.executeUpdate()).thenReturn(1);
        
        
        CourierFunc cf = new CourierFunc(new Courier());
        cf.setDeliveryStatus(DeliveryStatus.notPickedUp, 0);
        
        Mockito.verify(q, Mockito.times(3)).setParameter(statCap1.capture(), statCap.capture());
        
        Assert.assertEquals(statCap.getAllValues().get(0), DeliveryStatus.notPickedUp.toString());
        Assert.assertEquals(statCap.getAllValues().get(1), LocationStatus.NieOdebranoOdNadawcy.toString());
        
    }
*/
    @Test
    public void setDeliveryStatusTestUnknowDeliveredStatus()
    {
        ArgumentCaptor<String> statCap = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> statCap1 = ArgumentCaptor.forClass(String.class);
        
        Query q = Mockito.mock(Query.class);
        
        Mockito.when(session.createQuery("UPDATE Package SET DeliveredStatus = :s, location = :l WHERE id = :id")).thenReturn(q);
        Mockito.when(q.executeUpdate()).thenReturn(1);
        
        
        CourierFunc cf = new CourierFunc(new Courier());
        Assert.assertEquals(cf.setDeliveryStatus( null, 0), -1);
        
        
    }
    
}
