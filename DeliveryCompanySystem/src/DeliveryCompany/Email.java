/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DeliveryCompany;

import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;

/**
 *
 * @author Paweł
 */
public class Email {
    
    private int id;
    private String email;
    private boolean confirmation;
    
    public int getId()
    {
        return id;
    }
    
    public void setId(int id)
    {
        this.id = id;
    }
    
    public String getEmail()
    {
        return email;
    }
    
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    public boolean getConfirmation()
    {
        return confirmation;
    }
    
    public void setConfirmation(boolean confirmation)
    {
        this.confirmation = confirmation;
    }
     
}

