/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DeliveryCompany.app.gui;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Paweł
 */
public class MessageBox {
 
    
    public static void Display(String messege)
    {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(10);
        
        Label labelMessege = new Label(messege);
        GridPane.setConstraints(labelMessege, 0, 0);
        GridPane.setHalignment(labelMessege, HPos.CENTER);
        grid.getChildren().add(labelMessege);
        
        Button buttonConfirm = new Button("OK");
        GridPane.setConstraints(buttonConfirm, 0, 1);
        GridPane.setHalignment(buttonConfirm, HPos.CENTER);
        grid.getChildren().add(buttonConfirm);
        buttonConfirm.setOnAction(e -> {
            window.close();
        });
        
        
        window.setScene(new Scene(grid)); 
        window.show();
    }
}
