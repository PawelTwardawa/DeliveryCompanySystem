/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testyfitnessefixture;

import DeliveryCompany.app.enumerate.SessionType;
import DeliveryCompany.app.functionality.ClientFunc;
import DeliveryCompany.app.functionality.CourierFunc;
import DeliveryCompany.app.functionality.StoremanFunc;
import DeliveryCompany.app.functionality.UserFunc;
import DeliveryCompany.database.init.DatabaseInit;
import DeliveryCompany.database.structure.Client;
import DeliveryCompany.database.structure.Courier;
import DeliveryCompany.database.structure.Storeman;
import fit.Fixture;

/**
 *
 * @author Paweł
 */
public class SetUp extends Fixture{
    static UserFunc userFunc;
    static ClientFunc clientFunc;
    static CourierFunc courierFunc;
    
    public SetUp()
    {
        //DatabaseInit.getInstance().getSession(SessionType.Login);
        userFunc = new UserFunc();
        clientFunc = new ClientFunc(new Client());
        courierFunc = new CourierFunc(new Courier());
    }
    
}
