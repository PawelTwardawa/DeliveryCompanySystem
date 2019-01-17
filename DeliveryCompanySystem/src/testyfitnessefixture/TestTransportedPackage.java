/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testyfitnessefixture;

import DeliveryCompany.app.enumerate.DeliveryStatus;
import DeliveryCompany.database.structure.CourierData;
import DeliveryCompany.database.structure.StoremanData;
import fit.ColumnFixture;
import java.util.List;

/**
 *
 * @author Paweł
 */
public class TestTransportedPackage extends ColumnFixture {
    
    String status;
    
    public int getTransportedPackage()
    {
        List<CourierData> data;
        if(status == null)
            data  = SetUp.courierFunc.getTransportedPackage(null);
        else
            data  = SetUp.courierFunc.getTransportedPackage(DeliveryStatus.valueOf(status));
       
       return data.size();
    }
    
}
