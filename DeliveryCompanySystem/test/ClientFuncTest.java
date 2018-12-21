/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import DeliveryCompany.app.enumerate.DeliveryStatus;
import DeliveryCompany.app.enumerate.LocationStatus;
import DeliveryCompany.app.functionality.ClientFunc;
import DeliveryCompany.app.functionality.CourierFunc;
import DeliveryCompany.database.init.DatabaseInit;
import DeliveryCompany.database.structure.Address;
import DeliveryCompany.database.structure.Client;
import DeliveryCompany.database.structure.Courier;
import DeliveryCompany.database.structure.Data;
import DeliveryCompany.database.structure.Dimensions;
import DeliveryCompany.database.structure.User;
import DeliveryCompany.database.structure.Package;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import org.junit.Before;
import org.mockito.ArgumentCaptor;
import org.mockito.Matchers;
import org.mockito.Mockito;
import simulateDatabase.simQuery;

/**
 *
 * @author Paweł
 */
public class ClientFuncTest {

    private Session session;
    private DatabaseInit database;
    
    public ClientFuncTest() {
    }
    
    
    
    @Before
    public void setUp()
    {
        session = Mockito.mock(Session.class);
        
        database = Mockito.spy(new DatabaseInit());
        
        database.setSession(session);
        
        Mockito.when(session.isOpen()).thenReturn(Boolean.TRUE);
        
        Mockito.when(session.beginTransaction()).thenReturn(null);
        Mockito.when(session.getTransaction()).thenReturn(Mockito.mock(Transaction.class));
        
    }
    
    @Test
    public void sendPackageTestCreateNewDataAndAddress()
    {
        ArgumentCaptor<Package> userCap = ArgumentCaptor.forClass(Package.class);
        
        Query q = Mockito.mock(Query.class);
        
        Mockito.when(session.createQuery("FROM Address WHERE houseNumber = :hn AND apartmentNumber = :an AND street = :s AND postCode = :pc AND city = :c ")).thenReturn(new simQuery("Address"));
        //Mockito.when(q.executeUpdate()).thenReturn(1);
        
        Mockito.when(session.createQuery("FROM Data WHERE firstName = :fn AND lastName = :ln")).thenReturn(new simQuery("Data"));
        Mockito.when(session.createQuery("FROM Courier")).thenReturn(new simQuery("Courier"));
        Mockito.when(session.save(any())).thenReturn(1);
        
        /**/Address senderAddress = new Address("100", "10", "ulica", "00-000", "miasto");
        senderAddress.setId(0);
        /**/ Data senderData = new Data("pawel", "kowalski", senderAddress);
        senderData.setId(0);
        
        Address receiverAddress = new Address("10", "10", "ulica", "00-000", "miasto");
        receiverAddress.setId(0);
        Data receiverData = new Data("pawel", "kowalski", senderAddress);
        receiverAddress.setId(0);
        
        ClientFunc cf = new ClientFunc(new Client());
        cf.SendPackage(senderData, senderAddress, receiverData, receiverAddress, new Dimensions(1, 1, 1), 346457123);
        
        
        
        Mockito.verify(session, Mockito.times(1)).save(userCap.capture());
        
        Assert.assertEquals(userCap.getAllValues().get(0).getSender().getId(), senderData.getId());
        Assert.assertEquals(userCap.getAllValues().get(0).getSender().getAddress().getId(), senderAddress.getId());
    }
    
    @Test
    public void sendPackageTestUseExistingDataAndAddress()
    {
        ArgumentCaptor<Package> userCap = ArgumentCaptor.forClass(Package.class);
        
        Query q = Mockito.mock(Query.class);
        
        Mockito.when(session.createQuery("FROM Address WHERE houseNumber = :hn AND apartmentNumber = :an AND street = :s AND postCode = :pc AND city = :c ")).thenReturn(new simQuery("Address"));
        //Mockito.when(q.executeUpdate()).thenReturn(1);
        
        Mockito.when(session.createQuery("FROM Data WHERE firstName = :fn AND lastName = :ln")).thenReturn(new simQuery("Data"));
        Mockito.when(session.createQuery("FROM Courier")).thenReturn(new simQuery("Courier"));
        Mockito.when(session.save(any())).thenReturn(1);
        
        /**/Address senderAddress = new Address("10", "10", "ulica", "00-000", "miasto");
        senderAddress.setId(0);
        /**/Data senderData = new Data("pawel", "kowalski", senderAddress);
        senderData.setId(0);
        
        Address receiverAddress = new Address("10", "10", "ulica", "00-000", "miasto");
        receiverAddress.setId(0);
        Data receiverData = new Data("pawel", "kowalski", senderAddress);
        receiverAddress.setId(0);
        
        ClientFunc cf = new ClientFunc(new Client());
        cf.SendPackage(senderData, senderAddress, receiverData, receiverAddress, new Dimensions(1, 1, 1), 346457123);
        
        Mockito.verify(session, Mockito.times(1)).save(userCap.capture());
        
        Assert.assertEquals(userCap.getAllValues().get(0).getSender().getId(), 1);
        Assert.assertEquals(userCap.getAllValues().get(0).getSender().getAddress().getId(), 1);
    }
    
    @Test
    public void sendPackageTestCreateNewDataAndUseExistingAddress()
    {
        ArgumentCaptor<Package> userCap = ArgumentCaptor.forClass(Package.class);
        
        Query q = Mockito.mock(Query.class);
        
        Mockito.when(session.createQuery("FROM Address WHERE houseNumber = :hn AND apartmentNumber = :an AND street = :s AND postCode = :pc AND city = :c ")).thenReturn(new simQuery("Address"));
        //Mockito.when(q.executeUpdate()).thenReturn(1);
        
        Mockito.when(session.createQuery("FROM Data WHERE firstName = :fn AND lastName = :ln")).thenReturn(new simQuery("Data"));
        Mockito.when(session.createQuery("FROM Courier")).thenReturn(new simQuery("Courier"));
        Mockito.when(session.save(any())).thenReturn(1);
        
        /**/Address senderAddress = new Address("10", "10", "ulica", "00-000", "miasto");
        senderAddress.setId(0);
        /**/Data senderData = new Data("pawell", "kowalski", senderAddress);
        senderData.setId(0);
        
        Address receiverAddress = new Address("10", "10", "ulica", "00-000", "miasto");
        receiverAddress.setId(0);
        Data receiverData = new Data("pawel", "kowalski", senderAddress);
        receiverAddress.setId(0);
        
        ClientFunc cf = new ClientFunc(new Client());
        cf.SendPackage(senderData, senderAddress, receiverData, receiverAddress, new Dimensions(1, 1, 1), 346457123);
        
        Mockito.verify(session, Mockito.times(1)).save(userCap.capture());
        
        Assert.assertEquals(userCap.getAllValues().get(0).getSender().getId(), senderData.getId());
        Assert.assertEquals(userCap.getAllValues().get(0).getSender().getAddress().getId(), 1);
    }
    
    
    
}
