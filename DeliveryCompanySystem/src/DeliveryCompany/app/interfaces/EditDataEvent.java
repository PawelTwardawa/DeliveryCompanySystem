/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DeliveryCompany.app.interfaces;

import DeliveryCompany.database.structure.ClientHistory;

/**
 *
 * @author Paweł
 */
public interface EditDataEvent {
    
    void EditDataCallback(ClientHistory data);
    
}
