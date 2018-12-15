/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DeliveryCompany.app.functionality;

import DeliveryCompany.app.enumerate.RegisterStatus;
import DeliveryCompany.app.enumerate.SessionType;
import DeliveryCompany.app.enumerate.UserType;
import static DeliveryCompany.app.enumerate.UserType.Storeman;
import DeliveryCompany.database.init.DatabaseInit;
import DeliveryCompany.database.structure.Client;
import DeliveryCompany.database.structure.Courier;
import DeliveryCompany.database.structure.Email;
import DeliveryCompany.database.structure.Storeman;
import DeliveryCompany.database.structure.User;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.SQLException;
import javassist.bytecode.stackmap.BasicBlock;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author Paweł
 */
public class UserFunc {
    
    Session session;
    //
    DatabaseInit database;
    //

    public UserFunc() //throws NoSuchAlgorithmException {
    {
        this.session = DatabaseInit.getInstance().getSession(SessionType.Login);
        //database = new DatabaseInit();
        //session = database.getSession();
        //session = null;
    }
    
    /*
    private void getSession()
    {
        if(session == null)
            session = DatabaseInit.getInstance().getSession();
    }
    
    public void setSession(Session session)
    {
        this.session = session;
    }
    /*
    public void setDatabaseInit(DatabaseInit databaseInit)
    {
        this.database = databaseInit;
        this.session = databaseInit.getSession();
    }
    */
    
    public User Login(String username, String password) throws NoSuchAlgorithmException
    {
        //getSession();
        
        session.beginTransaction();
        
        Query q = session.createQuery("FROM User WHERE Username = :un AND Password = :pa");
        q.setParameter("un", username);
        q.setParameter("pa", getSecurePassword(password));
        
        User user =  (User)q.uniqueResult();
        

        session.getTransaction().commit();
        
        return user;
    }
    
    public RegisterStatus Registry(String username, String password, String email, UserType type)// throws Exception
    {
        //getSession();

        RegisterStatus status = RegisterStatus.Success;
        
        session.beginTransaction();
        Query q;
        Email emailExist = null;
        if(type == UserType.Client)
        {
            q = session.createQuery("FROM Email WHERE email = :e");
            q.setParameter("e", email);
            emailExist = (Email)q.uniqueResult();

            
        }
        
        q = session.createQuery("FROM User WHERE Username = :u");
        q.setParameter("u", username);
        User usernameExist = (User)q.uniqueResult();
        
        session.getTransaction().commit();
        
        if(emailExist != null && type == UserType.Client)
                return RegisterStatus.EmailExists;

        if(usernameExist != null)
            return RegisterStatus.UsernameExists;
        
        Email emailObj = new Email();
        emailObj.setEmail(email);
        
        User userObj = new User();
        
        if(type == UserType.Client)
            userObj.setID_email(emailObj);
        
        userObj.setUsername(username);
        userObj.setPassword(getSecurePassword(password));
        userObj.setUserType(type.toString());
            
        session.beginTransaction();
        session.save(userObj);
        session.getTransaction().commit(); 
        
        return RegisterStatus.Success;
        
        /*
        RegisterStatus status = RegisterStatus.Success;
        
        Email emailObj = new Email();
        emailObj.setEmail(email);
        
        User userObj = new User();
        userObj.setID_email(emailObj);
        userObj.setUsername(username);
        userObj.setPassword(getSecurePassword(password));
        userObj.setUserType(type.toString());
        try
        {
            
            session.beginTransaction();
            session.save(userObj);
            session.getTransaction().commit(); 
            //session.getTransaction().rollback();
            
        }
        catch(ConstraintViolationException ex)
        {
            //session.getTransaction().rollback();
            
            if(ex.getCause().toString().contains(email))
                //status = RegisterStatus.EmailExists;
                return RegisterStatus.EmailExists;
            else if (ex.getCause().toString().contains(username))
                //status = RegisterStatus.UsernameExists;
                return RegisterStatus.UsernameExists;
            //else 
                //throw new Exception();
            
        }
        finally
        {
            //session.getTransaction().commit();
            //session.getTransaction().rollback();
            //System.err.println("");
        }
        
        return RegisterStatus.Success;
        //return status;
*/
    }
    
    //TODO: zrobic to lepiej
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
    
    public <T> T getMembership(User user)
    {
        if(user == null)
            return null;
        
        //session.beginTransaction();
        try
        {
            switch(UserType.valueOf(user.getUserType()))
            {
                case Client:
                {
                    session = DatabaseInit.getInstance().getSession(SessionType.Client);
                    session.beginTransaction();
                    Query q = session.createQuery("FROM Client WHERE user = :u");
                    q.setParameter("u", user);
                    Client obj = (Client)q.uniqueResult();

                    session.getTransaction().commit();

                    return (T) obj;
                }
                case Courier:
                {
                    session = DatabaseInit.getInstance().getSession(SessionType.Courier);
                    session.beginTransaction();
                    Query q = session.createQuery("FROM Courier WHERE user = :u");
                    q.setParameter("u", user);
                    Courier obj = (Courier)q.uniqueResult();

                    session.getTransaction().commit();

                    return (T) obj;
                }
                case Storeman:
                {
                    session = DatabaseInit.getInstance().getSession(SessionType.Storeman);
                    session.beginTransaction();
                    Query q = session.createQuery("FROM Storeman WHERE user = :u");
                    q.setParameter("u", user);
                    Storeman obj = (Storeman)q.uniqueResult();

                    session.getTransaction().commit();

                    return (T) obj;
                }
                default:
                {
                    //session.getTransaction().commit();
                }
            }
        }
        catch(IllegalArgumentException ex)
        {
            System.err.println(ex.getMessage());
        }
        return null;
    }
    
    public boolean confirmEmail()
    {
        return false;
        
    }
    
}
