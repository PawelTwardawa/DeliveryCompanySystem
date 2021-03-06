/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import DeliveryCompany.app.enumerate.RegisterStatus;
import DeliveryCompany.app.enumerate.SessionType;
import DeliveryCompany.app.enumerate.UserType;
import DeliveryCompany.app.functionality.UserFunc;
import DeliveryCompany.database.init.DatabaseInit;
import DeliveryCompany.database.structure.Client;
import DeliveryCompany.database.structure.Courier;
import DeliveryCompany.database.structure.Email;
import DeliveryCompany.database.structure.Storeman;
import DeliveryCompany.database.structure.User;
import java.security.NoSuchAlgorithmException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.ParentRunner;
import org.mockito.*;
import static org.mockito.Matchers.any;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import simulateDatabase.simQuery;

/**
 *
 * @author Paweł
 */
@Category(UserFunc.class)
@RunWith(MockitoJUnitRunner.class)
public class UserFuncTest {
    
    
    //@Mock
    Session session;
    //@Mock
    DatabaseInit database;
    
    

    public UserFuncTest() {
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
    public void loginTestNotNull() throws NoSuchAlgorithmException
    {       
        
        Query q = Mockito.mock(Query.class);
        
        Mockito.when(session.createQuery(any(String.class))).thenReturn(new simQuery("User"));
        //Mockito.when(q.uniqueResult()).thenReturn(user);

        UserFunc userFunc = new UserFunc();
        
        /**/User resultUser = userFunc.Login("pawel", "pass");
        
        Assert.assertNotNull(resultUser);
        
    }

    
    @Test
    public void loginTestNull() throws NoSuchAlgorithmException
    {
        User user = new User();
        user.setUsername("pawel");
        user.setPassword("pass");
        
        Query q = Mockito.mock(Query.class);
        
        Mockito.when(session.createQuery(any(String.class))).thenReturn(new simQuery("User"));
        //Mockito.when(q.uniqueResult()).thenReturn(user);

        UserFunc userFunc = new UserFunc();
        
        /**/User resultUser = userFunc.Login("pawell", "pass");
        
        Assert.assertNull(resultUser);
        
    }
    
    @Test
    public void registryClientTestEmailExists()
    {


        Query queryEmail = Mockito.mock(Query.class);
        Mockito.when(session.createQuery("FROM Email WHERE email = :e")).thenReturn(new simQuery("Email"));
        //Mockito.when(session.createQuery("FROM Email WHERE email = :e")).thenReturn(queryEmail);
        //Mockito.when(queryEmail.uniqueResult()).thenReturn(email);
        
        Query queryUser = Mockito.mock(Query.class);
        
        Mockito.when(session.createQuery("FROM User WHERE Username = :u")).thenReturn(new simQuery("UserEmail"));
        Mockito.when(queryUser.uniqueResult()).thenReturn(null);
        
        UserFunc userFunc = new UserFunc();
        
        /**/Assert.assertEquals(userFunc.Registry("pawell", "pass", "email@email.com", UserType.Client), RegisterStatus.EmailExists);
        
    }
    
    @Test
    public void registryClientTestUsernameExists()
    {


        Query queryEmail = Mockito.mock(Query.class);
        Mockito.when(session.createQuery("FROM Email WHERE email = :e")).thenReturn(new simQuery("Email"));
        //Mockito.when(session.createQuery("FROM Email WHERE email = :e")).thenReturn(queryEmail);
        //Mockito.when(queryEmail.uniqueResult()).thenReturn(email);
        
        Query queryUser = Mockito.mock(Query.class);
        
        Mockito.when(session.createQuery("FROM User WHERE Username = :u")).thenReturn(new simQuery("UserEmail"));
        Mockito.when(queryUser.uniqueResult()).thenReturn(null);
        
        UserFunc userFunc = new UserFunc();
        
        /**/Assert.assertEquals(userFunc.Registry("pawel", "pass", "emaill@email.com", UserType.Client), RegisterStatus.UsernameExists);
        
    }
    
    @Test
    public void registryClientTestSuccess()
    {

        Query queryEmail = Mockito.mock(Query.class);
        Mockito.when(session.createQuery("FROM Email WHERE email = :e")).thenReturn(new simQuery("Email"));
        //Mockito.when(session.createQuery("FROM Email WHERE email = :e")).thenReturn(queryEmail);
        //Mockito.when(queryEmail.uniqueResult()).thenReturn(email);
        
        Query queryUser = Mockito.mock(Query.class);
        
        Mockito.when(session.createQuery("FROM User WHERE Username = :u")).thenReturn(new simQuery("UserEmail"));
        Mockito.when(queryUser.uniqueResult()).thenReturn(null);
        
        UserFunc userFunc = new UserFunc();
        
        /**/ Assert.assertEquals(userFunc.Registry("pawel", "pass", "emaill@email.com", UserType.Client), RegisterStatus.Success);
        
    }
    
    @Test
    public void getMembershipTestReturnClient()
    {
        Client client = new Client();
        
        Query query = Mockito.mock(Query.class);
        
        Mockito.when(session.createQuery(any(String.class))).thenReturn(query);
        Mockito.when(query.uniqueResult()).thenReturn(client);
        
        User user = new User();
        user.setUsername("pawel");
        user.setPassword("pass");
        /**/user.setUserType(UserType.Client.toString());
        
        UserFunc userFunc = new UserFunc();
        
        //Assert.assertEquals(userFunc.getMembership(user), );
        Assert.assertNotNull(userFunc.getMembership(user));
        Assert.assertTrue(userFunc.getMembership(user) instanceof Client);
    }
    
    @Test
    public void getMembershipTestReturnCourier()
    {
        Courier courier = new Courier();
        
        Query query = Mockito.mock(Query.class);
        
        Mockito.when(session.createQuery(any(String.class))).thenReturn(query);
        Mockito.when(query.uniqueResult()).thenReturn(courier);
        
        User user = new User();
        user.setUsername("pawel");
        user.setPassword("pass");
        /**/user.setUserType(UserType.Courier.toString());
        
        UserFunc userFunc = new UserFunc();
        
        //Assert.assertEquals(userFunc.getMembership(user), );
        Assert.assertNotNull(userFunc.getMembership(user));
        Assert.assertTrue(userFunc.getMembership(user) instanceof Courier);
    }
    
    @Test
    public void getMembershipTestReturnStoreman()
    {
        Storeman storeman = new Storeman();
        
        Query query = Mockito.mock(Query.class);
        
        Mockito.when(session.createQuery(any(String.class))).thenReturn(query);
        Mockito.when(query.uniqueResult()).thenReturn(storeman);
        
        User user = new User();
        user.setUsername("pawel");
        user.setPassword("pass");
        /**/user.setUserType(UserType.Storeman.toString());
        
        UserFunc userFunc = new UserFunc();
        
        //Assert.assertEquals(userFunc.getMembership(user), );
        //Assert.assertNotNull(userFunc.getMembership(user));
        Assert.assertTrue(userFunc.getMembership(user) instanceof Storeman);
    }
    
    @Test(expected = ClassCastException.class)
    public void getMembershipTestException()
    {
        Storeman storeman = new Storeman();
        
        Query query = Mockito.mock(Query.class);
        
        Mockito.when(session.createQuery(any(String.class))).thenReturn(query);
        Mockito.when(query.uniqueResult()).thenReturn(storeman);
        
        User user = new User();
        user.setUsername("pawel");
        user.setPassword("pass");
        /**/user.setUserType(UserType.Storeman.toString()); // storeman
        
        UserFunc userFunc = new UserFunc();
        
        //Assert.assertEquals(userFunc.getMembership(user), );
        //Assert.assertNotNull(userFunc.getMembership(user));
        Assert.assertTrue(userFunc.getMembership(user) instanceof Storeman);
    }

}
