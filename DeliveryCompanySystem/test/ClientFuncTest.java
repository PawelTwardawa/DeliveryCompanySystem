/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import DeliveryCompany.app.enumerate.RegisterStatus;
import DeliveryCompany.app.enumerate.UserType;
import DeliveryCompany.app.functionality.ClientFunc;
import DeliveryCompany.app.functionality.UserFunc;
import DeliveryCompany.database.init.DatabaseInit;
import DeliveryCompany.database.structure.Address;
import DeliveryCompany.database.structure.Client;
import DeliveryCompany.database.structure.Data;
import DeliveryCompany.database.structure.Dimensions;
import DeliveryCompany.database.structure.Email;
import DeliveryCompany.database.structure.Package;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import static org.mockito.Mockito.doReturn;

/**
 *
 * @author Paweł
 */
public class ClientFuncTest{
    
    
    //@Mock
    Session session;
    //@Mock
    DatabaseInit database;
    
    
    @Before
    public void setUp()
    {

        session = Mockito.mock(Session.class);
        
        database = Mockito.spy(new DatabaseInit());
        database.setSession(session);
        
        Mockito.when(session.beginTransaction()).thenReturn(null);
        Mockito.when(session.getTransaction()).thenReturn(Mockito.mock(Transaction.class));
        
    }
    
    @Test
    public void sendPackageTestSuccessSend()
    {
        Client client = new Client();
        
        Address senderAddress = new Address("8", "8", "st", "88-888", "city");
        senderAddress.setId(0);
        Data sender = new Data("pawel", "twa", senderAddress);
        sender.setId(0);
        
        Address receiverAddress = new Address("5", "5", "str", "88-668", "cityCity");
        senderAddress.setId(1);
        Data receiver = new Data("dawid", "jakistam", receiverAddress);
        sender.setId(1);
        
        Dimensions dim = new Dimensions(4, 4, 4);
        

        Query findDataQuery = Mockito.mock(Query.class);
        
        Mockito.when(session.createQuery("FROM Data WHERE firstName = :fn AND lastName = :ln")).thenReturn(findDataQuery);
        Mockito.when(findDataQuery.uniqueResult()).thenReturn(sender);
        
        ArgumentCaptor<Data> setDataArgs = ArgumentCaptor.forClass(Data.class);
        
        ClientFunc clientFunc = Mockito.mock(ClientFunc.class);
        
        //clientFunc.SendPackage(sender, senderAddress, receiver, senderAddress, dim, 0);
        
        clientFunc.SendPackage(sender, senderAddress, receiver, senderAddress, dim, 0);
        
        Package pack = Mockito.spy(new Package());
        
        Mockito.verify(pack).setSender(setDataArgs.capture());
        
        Assert.assertEquals(sender, setDataArgs);
        
        
    }
    
}
