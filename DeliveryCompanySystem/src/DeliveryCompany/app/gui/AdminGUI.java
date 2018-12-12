/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DeliveryCompany.app.gui;

import DeliveryCompany.app.enumerate.UserType;
import DeliveryCompany.app.functionality.UserFunc;
import DeliveryCompany.database.init.DatabaseInit;
import javafx.event.EventType;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Paweł
 */
public class AdminGUI {
    
    private Stage window;
    
    GridPane grid;
    
    Label labelUsername;
    Label labelPassword;
    Label labelConfirmPassword;
    Label labelUserType;
    
    TextField textUsername;
    TextField textPassword;
    TextField textConfirmPassword;
    
    ComboBox comboBoxUserType;
    
    Button buttonAdd;
    
    public void Display()
    {
        window = new Stage();
        window.setMinWidth(600);
        //window.setMinHeight(600);

        window.setOnCloseRequest(e -> {
            DatabaseInit.getInstance().getSession().close();
            System.exit(0);
        });
        
        window.setTitle( "ADMIN");
        
        
        //Set Scene
        window.setScene(new Scene(adminContent(), 1300, 800)); 
        window.show();
    }
    
    private GridPane adminContent()
    {
        grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);
        
        
        //label username
        labelUsername = new Label("Username: ");
        GridPane.setConstraints(labelUsername, 0, 0);
        grid.getChildren().add(labelUsername);
        
        //text username
        textUsername = new TextField();
        GridPane.setConstraints(textUsername, 1, 0);
        grid.getChildren().add(textUsername);
        
        //label password
        labelPassword = new Label("Password: ");
        GridPane.setConstraints(labelPassword, 0, 1);
        grid.getChildren().add(labelPassword);
        
        //text password
        textPassword = new PasswordField();
        GridPane.setConstraints(textPassword, 1, 1);
        grid.getChildren().add(textPassword);
        
        //label confirm password
        labelConfirmPassword = new Label("Confirm password: ");
        GridPane.setConstraints(labelConfirmPassword, 0, 2);
        grid.getChildren().add(labelConfirmPassword);
        
        //text confirm password
        textConfirmPassword = new PasswordField();
        GridPane.setConstraints(textConfirmPassword, 1, 2);
        grid.getChildren().add(textConfirmPassword);
        
        //label usertype
        labelPassword = new Label("User Type: ");
        GridPane.setConstraints(labelPassword, 0, 3);
        grid.getChildren().add(labelPassword);
        
        comboBoxUserType = new ComboBox();
        comboBoxUserType.getItems().addAll(UserType.Courier, UserType.Storeman);
        GridPane.setConstraints(comboBoxUserType, 1, 3);
        grid.getChildren().add(comboBoxUserType);
        
        buttonAdd = new Button("Add user");
        GridPane.setConstraints(buttonAdd, 1, 4);
        GridPane.setHalignment(buttonAdd, HPos.RIGHT);
        grid.getChildren().add(buttonAdd);
        buttonAdd.setOnAction(e -> {
            if(textUsername.getText().equals(""))
                return;
            if(textPassword.getText().equals(""))
                return;
            if(textConfirmPassword.getText().equals(""))
                return;
            
            if(comboBoxUserType.getValue() == null)
                return;
            
            if(textPassword.getText().equals(textConfirmPassword.getText()))
            {
                UserFunc userFunc = new UserFunc();
                
                userFunc.Registry(textUsername.getText(), textPassword.getText(), "", UserType.valueOf(comboBoxUserType.getValue().toString()));
            
            }

        });
        
        return grid;
    }
    
}
