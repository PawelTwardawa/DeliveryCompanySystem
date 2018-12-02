/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DeliveryCompany.app.functionality;

import DeliveryCompany.app.enumerate.RegisterStatus;
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

    public UserFunc() //throws NoSuchAlgorithmException {
    {
        this.session = DatabaseInit.getInstance().getSession();
    }
    
    
    
    public User Login(String username, String password) throws NoSuchAlgorithmException
    {
        session.beginTransaction();
        
        Query q = session.createQuery("FROM User WHERE Username = :un AND Password = :pa");
        q.setParameter("un", username);
        q.setParameter("pa", getSecurePassword(password));
        
        User user =  (User)q.uniqueResult();
        

        session.getTransaction().commit();
        
        return user;
    }
    
    public RegisterStatus Registry(String username, String password, String email, UserType type) throws Exception
    {
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
        }
        catch(ConstraintViolationException ex)
        {
            if(ex.getCause().toString().contains(email))
                return RegisterStatus.EmailExists;
            else if (ex.getCause().toString().contains(username))
                return RegisterStatus.UsernameExists;
            else 
                throw new Exception();
        }
        
        return RegisterStatus.Success;
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
    
    public <T> T getMembership( User user)
    {
        if(user == null)
            return null;
        
        session.beginTransaction();
        try
        {
            switch(UserType.valueOf(user.getUserType()))
            {
                case Client:
                {
                    Query q = session.createQuery("FROM Client WHERE user = :u");
                    q.setParameter("u", user);
                    Client obj = (Client)q.uniqueResult();

                    session.getTransaction().commit();

                    return (T) obj;
                }
                case Courier:
                {
                    Query q = session.createQuery("FROM Courier WHERE user = :u");
                    q.setParameter("u", user);
                    Courier obj = (Courier)q.uniqueResult();

                    session.getTransaction().commit();

                    return (T) obj;
                }
                case Storeman:
                {
                    Query q = session.createQuery("FROM Storeman WHERE user = :u");
                    q.setParameter("u", user);
                    Storeman obj = (Storeman)q.uniqueResult();

                    session.getTransaction().commit();

                    return (T) obj;
                }
                default:
                {
                    session.getTransaction().commit();
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
