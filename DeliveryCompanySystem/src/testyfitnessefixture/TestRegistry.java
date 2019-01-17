/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testyfitnessefixture;

import DeliveryCompany.app.enumerate.RegisterStatus;
import DeliveryCompany.app.enumerate.UserType;
import fit.ColumnFixture;
import static testyfitnessefixture.SetUp.userFunc;

/**
 *
 * @author Paweł
 */
public class TestRegistry extends ColumnFixture{
    
    String username;
    String password;
    String email;
    
    
    public String Registry()
    {
        
        RegisterStatus status =   SetUp.userFunc.Registry(username, password, email, UserType.Client);
        
        return status.toString();
    }
}
