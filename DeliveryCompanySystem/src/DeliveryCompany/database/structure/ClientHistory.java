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
public class ClientHistory {
    
    int ID_client;
    int ID;
    int TelephoneNumber;
    String Location;
    String senderFirstName;
    String senderLastName;
    String senderCity;
    String SenderPostCode;
    String SenderStreet;
    String SenderHouseNumber;
    String SenderApartmentNumber;
    String ReceiverFirstName;
    String ReceiverLastName;
    String ReceiverCity;
    String ReceiverPostCode;
    String ReceiverStreet;
    String ReceiverHouseNumber;
    String ReceiverApartmentNumber;

    public ClientHistory()
    {
        
    }

    public ClientHistory(int ID, int TelephoneNumber, String Location, String senderFirstName, String senderLastName, String senderCity, String SenderPostCode, String SenderStreet, String SenderHouseNumber, String SenderApartmentNumber, String ReceiverFirstName, String ReceiverLastName, String ReceiverCity, String ReceiverPostCode, String ReceiverStreet, String ReceiverHouseNumber, String ReceiverApartmentNumber) {
        this.ID = ID;
        this.TelephoneNumber = TelephoneNumber;
        this.Location = Location;
        this.senderFirstName = senderFirstName;
        this.senderLastName = senderLastName;
        this.senderCity = senderCity;
        this.SenderPostCode = SenderPostCode;
        this.SenderStreet = SenderStreet;
        this.SenderHouseNumber = SenderHouseNumber;
        this.SenderApartmentNumber = SenderApartmentNumber;
        this.ReceiverFirstName = ReceiverFirstName;
        this.ReceiverLastName = ReceiverLastName;
        this.ReceiverCity = ReceiverCity;
        this.ReceiverPostCode = ReceiverPostCode;
        this.ReceiverStreet = ReceiverStreet;
        this.ReceiverHouseNumber = ReceiverHouseNumber;
        this.ReceiverApartmentNumber = ReceiverApartmentNumber;
    }
    
  

    public String getLocation() {
        return Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    public int getID_client() {
        return ID_client;
    }

    public void setID_client(int ID_client) {
        this.ID_client = ID_client;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getTelephoneNumber() {
        return TelephoneNumber;
    }

    public void setTelephoneNumber(int TelephoneNumber) {
        this.TelephoneNumber = TelephoneNumber;
    }

    public String getSenderFirstName() {
        return senderFirstName;
    }

    public void setSenderFirstName(String senderFirstName) {
        this.senderFirstName = senderFirstName;
    }

    public String getSenderLastName() {
        return senderLastName;
    }

    public void setSenderLastName(String senderLastName) {
        this.senderLastName = senderLastName;
    }

    public String getSenderCity() {
        return senderCity;
    }

    public void setSenderCity(String senderCity) {
        this.senderCity = senderCity;
    }

    public String getSenderPostCode() {
        return SenderPostCode;
    }

    public void setSenderPostCode(String SenderPostCode) {
        this.SenderPostCode = SenderPostCode;
    }

    public String getSenderStreet() {
        return SenderStreet;
    }

    public void setSenderStreet(String SenderStreet) {
        this.SenderStreet = SenderStreet;
    }

    public String getSenderHouseNumber() {
        return SenderHouseNumber;
    }

    public void setSenderHouseNumber(String SenderHouseNumber) {
        this.SenderHouseNumber = SenderHouseNumber;
    }

    public String getSenderApartmentNumber() {
        return SenderApartmentNumber;
    }

    public void setSenderApartmentNumber(String SenderApartmentNumber) {
        this.SenderApartmentNumber = SenderApartmentNumber;
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
    
    
    
    
}
