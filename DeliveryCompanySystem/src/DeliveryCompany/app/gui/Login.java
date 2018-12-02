/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DeliveryCompany.app.gui;

import javafx.scene.control.Button;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Paweł
 */
public class Login extends Application{

    Button loginButton;
    Stage window;
    
    public static void main(String[] args)
    {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        
        window.setTitle("Login");
        
        //Grid
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(2, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);
        
        //Label username error
        Label labelUserError = new Label("");
        GridPane.setConstraints(labelUserError, 1, 0);
        
        //Label username
        Label labelUsername = new Label("Username");
        GridPane.setConstraints(labelUsername, 0, 1);
        
        //Input username
        TextField inputUsername = new TextField();
        GridPane.setConstraints(inputUsername, 1,1, 2, 1);
        
        
        //Label password
        Label labelPassword = new Label("Password");
        GridPane.setConstraints(labelPassword, 0, 2);
        
        //Input password
        TextField inputPassword = new PasswordField();
        GridPane.setConstraints(inputPassword, 1,2, 2, 1);
          
        //Button login
        Button buttonLogin = new Button("Login");
        GridPane.setConstraints(buttonLogin, 1, 3);
        
        //Button registry
        Button buttonRegistry = new Button("Registry");
        GridPane.setConstraints(buttonRegistry, 2, 3);
        
        
        grid.getChildren().addAll(labelUserError, labelUsername, inputUsername, labelPassword, inputPassword, buttonLogin, buttonRegistry);
        
        //Scene
        Scene sceneLogin = new Scene(grid, 250, 150);      
       
        window.setScene(sceneLogin);
        window.show();
   }
    
    
    
}
