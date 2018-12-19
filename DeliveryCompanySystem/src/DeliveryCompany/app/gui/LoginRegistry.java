/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DeliveryCompany.app.gui;

import DeliveryCompany.app.enumerate.RegisterStatus;
import DeliveryCompany.app.enumerate.SessionType;
import DeliveryCompany.app.enumerate.UserType;
import DeliveryCompany.app.enumerate.DeliveryStatus;
import DeliveryCompany.app.enumerate.LocationStatus;
import DeliveryCompany.app.functionality.UserFunc;
import DeliveryCompany.app.functionality.ClientFunc;
import DeliveryCompany.app.functionality.CourierFunc;
import DeliveryCompany.app.functionality.StoremanFunc;
import DeliveryCompany.database.init.DatabaseInit;
import DeliveryCompany.database.structure.User;
import DeliveryCompany.database.structure.Address;
import DeliveryCompany.database.structure.Client;
import DeliveryCompany.database.structure.ClientHistory;
import DeliveryCompany.database.structure.Courier;
import DeliveryCompany.database.structure.CourierData;
import DeliveryCompany.database.structure.Data;
import DeliveryCompany.database.structure.Dimensions;
import DeliveryCompany.database.structure.Email;
import DeliveryCompany.database.structure.Package;
import DeliveryCompany.database.structure.Storeman;
import DeliveryCompany.database.structure.StoremanData;

import DeliveryCompany.app.gui.AdminGUI;
import DeliveryCompany.app.gui.ClientGUI;
import DeliveryCompany.app.gui.CourierGUI;
import DeliveryCompany.app.gui.EditDataGUI;
import DeliveryCompany.app.gui.MessageBox;
import DeliveryCompany.app.gui.StoremanGUI;


import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author Paweł
 */
public class LoginRegistry extends Application{

    static Stage window;
    //Stage window;
    
    
    //login
    Label labelUserError;
    Label labelUsername;
    Label labelPassword;
    
    TextField inputUsername;
    TextField inputPassword;
    
    Button buttonLogin;
    Button buttonRegistry;
    
    public static void main(String[] args)
    {

        DatabaseInit.getInstance().getSession(SessionType.Login);
        
        launch(args);
        
        
        
        
        
        
        
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        
        //Loader.Display();
        //DatabaseInit.getInstance().getSession(SessionType.Login);
        //Loader.Hide();
        
        //window.setResizable(false);
        window.setAlwaysOnTop(true);
        window.setOnCloseRequest(e -> {
            DatabaseInit.getInstance().getSession(SessionType.Login).close();
            System.exit(0);
        });
        
        
        Login(window, "");
        
   }
    
    public void Display()
    {
        Login(window, "");
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
        labelUserError = new Label("");
        GridPane.setConstraints(labelUserError, 1, 0 , 2, 1);
        
        //Label username
        labelUsername = new Label("Username");
        GridPane.setConstraints(labelUsername, 0, 1);
        
        //Input username
        inputUsername = new TextField();//(username);
        GridPane.setConstraints(inputUsername, 1,1, 2, 1);
        inputUsername.setOnKeyPressed(((event) -> {
            pressEnter(event);
        }));
        
        
        //Label password
        labelPassword = new Label("Password");
        GridPane.setConstraints(labelPassword, 0, 2);
        
        //Input password
        inputPassword = new PasswordField();
        GridPane.setConstraints(inputPassword, 1,2, 2, 1);
        inputPassword.setOnKeyPressed(((event) -> {
            pressEnter(event);
        }));
          
        //Button login
        buttonLogin = new Button("Login");
        GridPane.setConstraints(buttonLogin, 1, 3);
        buttonLogin.setOnAction(e -> {
            //TODO: logowanie

            loginSubmit();
                
        });
        
        //Button registry
        buttonRegistry = new Button("Registry");
        GridPane.setConstraints(buttonRegistry, 2, 3);
        buttonRegistry.setOnAction(e -> {
            Registry(window, inputUsername.getText());
        });
        
        
        grid.getChildren().addAll(labelUserError, labelUsername, inputUsername, labelPassword, inputPassword, buttonLogin, buttonRegistry);
        
        
        //Scene login
        //Scene sceneLogin = new Scene(grid, 250, 150); 
        Scene scene = new Scene(grid, 250, 150);
        //Scene scene = new Scene(grid, Screen.getPrimary().getBounds().getWidth(), grid.getHeight());
        window.setScene(scene); 
        window.show();
        
    }
    
    private void pressEnter(KeyEvent e)
    {
        if(e.getCode().equals(KeyCode.ENTER))
        {
            loginSubmit();
        }
    }
    
    private void loginSubmit()
    {
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
                    
                    switch(UserType.valueOf(user.getUserType()))
                    {
                        case Client:
                        {
                            ClientGUI clientGUI = new ClientGUI((Client)userFunc.getMembership(user));
                            window.close();;
                            clientGUI.Display();
                            break;
                        }
                        case Courier:
                        {
                            CourierGUI courierGUI = new CourierGUI((Courier)userFunc.getMembership(user));
                            window.close();
                            courierGUI.Display();
                            break;
                        }
                        case Storeman:
                        {
                            StoremanGUI storemanGUI = new StoremanGUI((Storeman)userFunc.getMembership(user));
                            window.close();
                            storemanGUI.Display();
                            break;
                        }
                        case Admin:
                        {
                            AdminGUI adminGUI = new AdminGUI();
                            window.close();
                            adminGUI.Display();
                            break;
                        }
                    }
                }
                
            } 
            catch (NoSuchAlgorithmException ex) 
            {
                System.err.println(ex.getStackTrace());
            }
    }
    
    private void Registry(Stage window, String username)
    {
        window.setTitle("Registry");
        
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
            
            //try
            //{
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
                /*
            }
            catch(Exception ex)
            {
                System.err.println(ex.getCause() + " " + ex.getMessage() + " " + ex.getStackTrace());
                return;
            }
            */
            Login(window, inputUsername.getText());           
        });
        
        grid.getChildren().addAll(labelUserError, labelUsername, inputUsername, labelPasswordError, labelPassword, inputPassword, labelConfirmPassword, inputConfirmPassword, labelEmailError, labelmail, inputEmail, buttonRegistry);
        
        //Scene registry
        Scene scene = new Scene(grid, 300, 200); 
        window.setScene(scene); 
        window.show();
    }  
}
