/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DeliveryCompany.app.gui;

import DeliveryCompany.app.enumerate.RegisterStatus;
import DeliveryCompany.app.enumerate.UserType;
import DeliveryCompany.app.functionality.UserFunc;
import DeliveryCompany.database.structure.User;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class LoginRegistry extends Application{

    Stage window;
    
    public static void main(String[] args)
    {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        
        //window.setTitle("Login");
        
             
       
        //window.setScene( Login(""));
        //window.show();
        
        Login(window, "");
        
        
        //////////////////////////////
        
        
   }
    
    private void Login(Stage window,String username)
    {
        window.setTitle("Login");
        //Grid
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(2, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);
        
        //Label username error
        Label labelUserError = new Label("");
        GridPane.setConstraints(labelUserError, 1, 0 , 2, 1);
        
        //Label username
        Label labelUsername = new Label("Username");
        GridPane.setConstraints(labelUsername, 0, 1);
        
        //Input username
        TextField inputUsername = new TextField(username);
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
        buttonLogin.setOnAction(e -> {
            //TODO: logowanie

            if(inputUsername.getText().equals(""))
            {
                labelUserError.setText("Empty username");
                return;
            }
            else
            {
                labelUserError.setText("");
            }
            
            if(inputPassword.getText().equals(""))
            {
                labelUserError.setText("Empty password");
                return;
            }
            else
            {
                labelUserError.setText("");
            }
            
            try 
            {
                UserFunc userFunc = new UserFunc();
                
                User user = userFunc.Login(inputUsername.getText(), inputPassword.getText());
                if(user == null)
                {
                    labelUserError.setText("Incorrect username or password");
                }
                else
                {
                    labelUserError.setText("success");
                }
            } 
            catch (NoSuchAlgorithmException ex) 
            {
                System.err.println(ex.getStackTrace());
            }
        });
        
        //Button registry
        Button buttonRegistry = new Button("Registry");
        GridPane.setConstraints(buttonRegistry, 2, 3);
        buttonRegistry.setOnAction(e -> {
            Registry(window, inputUsername.getText());
        });
        
        
        grid.getChildren().addAll(labelUserError, labelUsername, inputUsername, labelPassword, inputPassword, buttonLogin, buttonRegistry);
        
        //Scene login
        //Scene sceneLogin = new Scene(grid, 250, 150); 
        Scene scene = new Scene(grid, 250, 150);
        window.setScene(scene); 
        window.show();
        
    }
    
    private void Registry(Stage window, String username)
    {
        window.setTitle("Login");
        
        //Grid
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(2, 10, 10, 10));
        grid.setVgap(1);
        grid.setHgap(10);
        
        //Label username error
        Label labelUserError = new Label("");
        GridPane.setConstraints(labelUserError, 1, 0);
        
        //Label username
        Label labelUsername = new Label("Username");
        GridPane.setConstraints(labelUsername, 0, 1);
        
        //Input username
        TextField inputUsername = new TextField(username);
        GridPane.setConstraints(inputUsername, 1,1, 2, 1);
        
        //Label password error
        Label labelPasswordError = new Label("");
        GridPane.setConstraints(labelPasswordError, 1, 2);
        
        //Label password
        Label labelPassword = new Label("Password");
        GridPane.setConstraints(labelPassword, 0, 3);
        
        //Input password
        TextField inputPassword = new PasswordField();
        GridPane.setConstraints(inputPassword, 1,3, 2, 1);
        
        //Label confirm password
        Label labelConfirmPassword = new Label("Confirm Password");
        GridPane.setConstraints(labelConfirmPassword, 0, 4);
        
        //Input confirm password
        TextField inputConfirmPassword = new PasswordField();
        GridPane.setConstraints(inputConfirmPassword, 1,4, 2, 1);
        
        //Label mail error
        Label labelEmailError = new Label("");
        GridPane.setConstraints(labelEmailError, 1, 5);
        
        //Label email
        Label labelmail = new Label("Email");
        GridPane.setConstraints(labelmail, 0, 6);
        
        //Input email
        TextField inputEmail = new TextField();
        GridPane.setConstraints(inputEmail, 1,6, 2, 1);
    
        //Button registry
        Button buttonRegistry = new Button("Registry");
        GridPane.setConstraints(buttonRegistry, 1, 8);
        buttonRegistry.setOnAction(e -> {
            //TODO: dodawanie do bazy danych, sprawdzanie poprawnosci danych
            
            UserFunc userFunc = new UserFunc();
            
            if(inputUsername.getText().equals(""))
            {
                labelUserError.setText("Empty username");
                return;
            }
            else
            {
                labelUserError.setText("");
            }
            
            if(inputPassword.getText().equals(""))
            {
                labelPasswordError.setText("Empty password");
                return;
            }
            else
            {
                labelPasswordError.setText("");
            }
                
            if(inputEmail.getText().equals(""))
            {
                labelEmailError.setText("Empty email");
                return;
            }
            else 
            {
                labelEmailError.setText("");
            }
            if(!inputEmail.getText().contains("@") || !inputEmail.getText().contains("."))
            {
                labelEmailError.setText("Incorrect email");
                return;
            }
            else
            {
                labelEmailError.setText("");
            }
            if(!inputPassword.getText().equals(inputConfirmPassword.getText()))
            {
                labelPasswordError.setText("Incorrect confirm password");
                return;
            }
            else
            {
                labelPasswordError.setText("");
            }
            
            try
            {
                RegisterStatus stat =  userFunc.Registry(inputUsername.getText(), inputPassword.getText(), inputEmail.getText(), UserType.Client);
                
                switch(stat)
                {
                    case UsernameExists:
                    {
                        labelUserError.setText("Username Exists");
                        return;
                    }
                    case EmailExists:
                    {
                        labelEmailError.setText("Email exists");
                        return;
                    }
                    case Success:
                    {
                        labelUserError.setText("User register successful");
                        break;
                    }
                }
            }
            catch(Exception ex)
            {
                System.err.println(ex.getStackTrace());
            }
            
            Login(window, inputUsername.getText());           
        });
        
        grid.getChildren().addAll(labelUserError, labelUsername, inputUsername, labelPasswordError, labelConfirmPassword, inputConfirmPassword, labelPassword, inputPassword, labelEmailError, labelmail, inputEmail, buttonRegistry);
        
        //Scene registry
        Scene scene = new Scene(grid, 300, 200); 
        window.setScene(scene); 
        window.show();
    }  
}
