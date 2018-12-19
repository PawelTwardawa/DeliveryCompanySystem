/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DeliveryCompany.app.gui;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

/**
 *
 * @author Paweł
 */
public class Loader{
    
    private static Stage window;
    
    public static void Display()
    {
        /*
        
            Platform.runLater(() ->{
                
                window = new Stage();
                
                
                window.setScene(new Scene(new TabPane(), 100, 100)); 
                window.show();
            });
            */
        
    }
    
    public static void Hide()
    {
        window.close();
    }
    
}
