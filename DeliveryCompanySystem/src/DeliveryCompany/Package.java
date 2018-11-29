/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DeliveryCompany;

import java.util.Date;

/**
 *
 * @author Paweł
 */
public class Package {
    private int id;
    private int id_sender;
    private int id_receiver;
    private String location;
    private int id_courier;
    private int id_dimensions;
    private int telephone;
    private Date date;
    private int id_client;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

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

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }
    
}
