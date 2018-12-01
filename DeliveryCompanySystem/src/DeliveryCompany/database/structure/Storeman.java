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
public class Storeman {
    private int id;
    //private int username;
    
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
/*
    public int getUsername() {
        return username;
    }

    public void setUsername(int username) {
        this.username = username;
    }
    */
}
