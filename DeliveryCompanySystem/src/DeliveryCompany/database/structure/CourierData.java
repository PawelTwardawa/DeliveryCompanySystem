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
public class CourierData {
    private int ID;
    private int ID_courier;
    private int ID_client;
    private int TelephoneNumber;
    private String DeliveryStatus;
    private String ReceiverFirstName;
    private String ReceiverLastName;
    private String ReceiverCity;
    private String ReceiverPostCode;
    private String ReceiverStreet;
    private String ReceiverHouseNumber;
    private String ReceiverApartmentNumber;

    public CourierData()
    {
        
    }

    public CourierData(int ID, int ID_courier, int ID_client, int TelephoneNumber, String DeliveryStatus, String ReceiverFirstName, String ReceiverLastName, String ReceiverCity, String ReceiverPostCode, String ReceiverStreet, String ReceiverHouseNumber, String ReceiverApartmentNumber) {
        this.ID = ID;
        this.ID_courier = ID_courier;
        this.ID_client = ID_client;
        this.TelephoneNumber = TelephoneNumber;
        this.DeliveryStatus = DeliveryStatus;
        this.ReceiverFirstName = ReceiverFirstName;
        this.ReceiverLastName = ReceiverLastName;
        this.ReceiverCity = ReceiverCity;
        this.ReceiverPostCode = ReceiverPostCode;
        this.ReceiverStreet = ReceiverStreet;
        this.ReceiverHouseNumber = ReceiverHouseNumber;
        this.ReceiverApartmentNumber = ReceiverApartmentNumber;
    }
     
    
    
    @Override
    public String toString()
    {
        return ID + " " + ReceiverFirstName + " " + ReceiverLastName + " " + ReceiverCity + " " + ReceiverPostCode + " " + ReceiverStreet+ " " + ReceiverHouseNumber + " " + ReceiverApartmentNumber + " " + TelephoneNumber;
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

    public int getID_client() {
        return ID_client;
    }

    public void setID_client(int ID_client) {
        this.ID_client = ID_client;
    }

    public int getTelephoneNumber() {
        return TelephoneNumber;
    }

    public void setTelephoneNumber(int TelephoneNumber) {
        this.TelephoneNumber = TelephoneNumber;
    }

    public String getReceiverFirstName() {
        return ReceiverFirstName;
    }

    public void setReceiverFirstName(String ReceiverFirstName) {
        this.ReceiverFirstName = ReceiverFirstName;
    }

    public String getReceiverLastName() {
        return ReceiverLastName;
    }

    public void setReceiverLastName(String ReceiverLastName) {
        this.ReceiverLastName = ReceiverLastName;
    }

    public String getReceiverCity() {
        return ReceiverCity;
    }

    public void setReceiverCity(String ReceiverCity) {
        this.ReceiverCity = ReceiverCity;
    }

    public String getReceiverPostCode() {
        return ReceiverPostCode;
    }

    public void setReceiverPostCode(String ReceiverPostCode) {
        this.ReceiverPostCode = ReceiverPostCode;
    }

    public String getReceiverStreet() {
        return ReceiverStreet;
    }

    public void setReceiverStreet(String ReceiverStreet) {
        this.ReceiverStreet = ReceiverStreet;
    }

    public String getReceiverHouseNumber() {
        return ReceiverHouseNumber;
    }

    public void setReceiverHouseNumber(String ReceiverHouseNumber) {
        this.ReceiverHouseNumber = ReceiverHouseNumber;
    }

    public String getReceiverApartmentNumber() {
        return ReceiverApartmentNumber;
    }

    public void setReceiverApartmentNumber(String ReceiverApartmentNumber) {
        this.ReceiverApartmentNumber = ReceiverApartmentNumber;
    }
    
    public String getDeliveryStatus() {
        return DeliveryStatus;
    }

    public void setDeliveryStatus(String DeliveryStatus) {
        this.DeliveryStatus = DeliveryStatus;
    }
    
}
