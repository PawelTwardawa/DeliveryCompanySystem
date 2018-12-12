/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DeliveryCompany.database.structure;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Paweł
 */
public class Package {
    private int id;
    //private int id_sender;
    private Data sender;
    //private int id_receiver;
    private Data receiver;
    private String location;
    //private int id_courier;
    private Courier curier; // TODO: zmienic na cOurier
    //private int id_dimensions;
    private Dimensions dimensions;
    private int telephone;
    //private java.sql.Date date;
    private Date date;
    //private int id_client;
    private Client client;
    private String DeliveredStatus;

    public Package()
    {
        
    }
    
    public Package(Data sender, Data receiver, String location, Courier curier, Dimensions dimensions, int telephone, Date date, Client client, String DeliveredStatus) {
        this.sender = sender;
        this.receiver = receiver;
        this.location = location;
        this.curier = curier;
        this.dimensions = dimensions;
        this.telephone = telephone;
        this.date = date;
        this.client = client;
        this.DeliveredStatus = DeliveredStatus;
    }

    
    
    public String getDeliveredStatus() {
        return DeliveredStatus;
    }

    public void setDeliveredStatus(String DeliveredStatus) {
        this.DeliveredStatus = DeliveredStatus;
    }

    public Data getSender() {
        return sender;
    }

    public void setSender(Data sender) {
        this.sender = sender;
    }

    public Data getReceiver() {
        return receiver;
    }

    public void setReceiver(Data receiver) {
        this.receiver = receiver;
    }

    public Courier getCurier() {
        return curier;
    }

    public void setCurier(Courier curier) {
        this.curier = curier;
    }

    public Dimensions getDimensions() {
        return dimensions;
    }

    public void setDimensions(Dimensions dimensions) {
        this.dimensions = dimensions;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
/*
    public int getId_sender() {
        return id_sender;
    }

    public void setId_sender(int id_sender) {
        this.id_sender = id_sender;
    }

    public int getId_receiver() {
        return id_receiver;
    }

    public void setId_receiver(int id_receiver) {
        this.id_receiver = id_receiver;
    }
*/
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
/*
    public int getId_courier() {
        return id_courier;
    }

    public void setId_courier(int id_courier) {
        this.id_courier = id_courier;
    }

    public int getId_dimensions() {
        return id_dimensions;
    }

    public void setId_dimensions(int id_dimensions) {
        this.id_dimensions = id_dimensions;
    }
*/
    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }
/*
    public java.sql.Date getDate() {
        return date;
    }

    public void setDate(java.sql.Date date) {
        this.date = date;
    }
    */
/*
    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }
*/

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
}
