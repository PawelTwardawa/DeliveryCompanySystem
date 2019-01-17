/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testyfitnessefixture;

import DeliveryCompany.app.enumerate.UserType;
import DeliveryCompany.database.structure.Email;
import DeliveryCompany.database.structure.User;
import fit.ColumnFixture;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Paweł
 */
public class TestLogin extends ColumnFixture {
    
    String username;
    String password;
    
    public boolean Login()
    {
        Email email = new Email();
        email.setId(0);
        email.setEmail("email@amail.com");
        email.setConfirmation(false);
        
        User user = new User();
        user.setUsername("pawel");
        user.setPassword("pass");
        user.setUserType(UserType.Client.toString());
        user.setID_email(email);
        
        try
        {
            User userResult = SetUp.userFunc.Login(username, password);
            
            //return userResult.equals(user);
            if(userResult instanceof User)
                return true;
            else if(userResult == null)
                return false;
        }
        catch(NoSuchAlgorithmException e)
        {
            System.out.println(e.getMessage());
        }
        return false;
        
        
    }
    
}
