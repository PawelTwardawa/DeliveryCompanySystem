/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DeliveryCompany.database.structure;

/**
 *
 * @author Paweł
 */
public class StoremanData {
    int ID;
    int ID_courier;
    String DeliveredStatus;
    String City;
    String PostCode;
    String Street;
    String HouseNumber;
    String ApartmentNumber;

    public StoremanData() {
    }

    public StoremanData(int ID, int ID_courier, String City, String PostCode, String Street, String HouseNumber, String ApartmentNumber) {
        this.ID = ID;
        this.ID_courier = ID_courier;
        this.City = City;
        this.PostCode = PostCode;
        this.Street = Street;
        this.HouseNumber = HouseNumber;
        this.ApartmentNumber = ApartmentNumber;
    }

    public String getDeliveredStatus() {
        return DeliveredStatus;
    }

    public void setDeliveredStatus(String DeliveredStatus) {
        this.DeliveredStatus = DeliveredStatus;
    }

    
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID_courier() {
        return ID_courier;
    }

    public void setID_courier(int ID_courier) {
        this.ID_courier = ID_courier;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public String getPostCode() {
        return PostCode;
    }

    public void setPostCode(String PostCode) {
        this.PostCode = PostCode;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String Street) {
        this.Street = Street;
    }

    public String getHouseNumber() {
        return HouseNumber;
    }

    public void setHouseNumber(String HouseNumber) {
        this.HouseNumber = HouseNumber;
    }

    public String getApartmentNumber() {
        return ApartmentNumber;
    }

    public void setApartmentNumber(String ApartmentNumber) {
        this.ApartmentNumber = ApartmentNumber;
    }

    
    
    
}
