/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DeliveryCompany;

/**
 *
 * @author Paweł
 */
public class User {
    private String Username;
    private String Password;
    private String UserType;
    private int ID_email;
    
    
    public String getUsername()
    {
        return Username;
    }
    
    public void setUsername(String name)
    {
        Username = name;
    }
    
    public String getPassword()
    {
        return Password;
    }
    
    public void setPassword(String pass)
    {
        Password = pass;
    }
    
    public String getUserType()
    {
        return UserType;
    }
    
    public void setUserType(String type)
    {
        UserType = type;
    }
    
    public int getID_email()
    {
        return ID_email;
    }
    
    public void setID_email(int email)
    {
        ID_email = email;
    }
}