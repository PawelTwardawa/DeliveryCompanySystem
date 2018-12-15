/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DeliveryCompany.app.enumerate;

/**
 *
 * @author Paweł
 */
public enum UserType {
    Client("Client"),
    Courier("Courier"),
    Storeman("Storeman"),
    Admin("Admin");
    
    private final String type;
    
    UserType(final String type)
    {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
    
    
}
