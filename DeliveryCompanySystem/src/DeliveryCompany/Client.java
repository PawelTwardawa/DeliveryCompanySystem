/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DeliveryCompany;

/**
 *
 * @author Paweł
 */
public class Client {
    private int id;
    //private String username;
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
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    } 
*/
}
