/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DeliveryCompany.app.gui;

import DeliveryCompany.app.enumerate.DeliveryStatus;
import DeliveryCompany.app.enumerate.SessionType;
import DeliveryCompany.app.functionality.ClientFunc;
import DeliveryCompany.app.functionality.CourierFunc;
import DeliveryCompany.database.init.DatabaseInit;
import DeliveryCompany.database.structure.Address;
import DeliveryCompany.database.structure.Client;
import DeliveryCompany.database.structure.ClientHistory;
import DeliveryCompany.database.structure.Courier;
import DeliveryCompany.database.structure.CourierData;
import DeliveryCompany.database.structure.Data;
import DeliveryCompany.database.structure.Dimensions;
import DeliveryCompany.database.structure.Package;
import com.sun.javafx.scene.control.skin.ComboBoxPopupControl;
import java.awt.Dimension;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.LongStream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventType;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Paweł
 */
public class ClientGUI {
    
    private Client client;
    private ClientFunc clientFunc;
    private Stage window;
    
    //Display
    private TabPane tabPane;
    private Tab tabNewPackage;
    private Tab tabFollow;
    private Tab tabSendingHistory;
    private Tab tabMyAccount;
    //Scene scene;
       
    
    //newPackageContent
    private GridPane gridNewPackageContent;
    
    private Label labelSenderData;
    private Label labelSenderFirstName;
    private Label labelSenderLastName;
    private Label labelSenderCity;
    private Label labelSenderPostCode;
    private Label labelSenderStreet;
    private Label labelSenderHouseNumber;
    private Label labelSenderApartmentNumber;
    private Label labelReceiverData;
    private Label labelReceiverFirstName;
    private Label labelReceiverLastName;
    private Label labelReceiverCity;
    private Label labelReceiverPostCode;
    private Label labelReceiverStreet;
    private Label labelReceiverHouseNumber;
    private Label labelReceiverApartmentNumber;
    private Label labelTelephone;
    private Label labelDimensionsWidth;
    private Label labelDimensionsHeight;
    private Label labelDimensionsDepht;
    private Label labelFormError;
    
    private TextField textSenderFirstName;
    private TextField textSenderLastName;
    private TextField textSenderCity;
    private TextField textSenderPostCode;
    private TextField textSenderStreet;
    private TextField textSenderHouseNumber;
    private TextField textSenderApartmentNumber;
    private TextField textReceiverFirstName;
    private TextField textReceiverLastName;
    private TextField textReceiverCity;
    private TextField textReceiverPostCode;
    private TextField textReceiverStreet;
    private TextField textReceiverHouseNumber;
    private TextField textReceiverApartmentNumber;
    private TextField textTelephone;
    private TextField textDimensionsWidth;
    private TextField textDimensionsHeight;
    private TextField textDimensionsDepht;
    
    private Button buttonSubmitNewPackage;
    
    private CheckBox checkBoxChangeSenderData;
    
    //follow
     private GridPane gridFollowContent;
     
     private Label labelPackageNumber;
     private Label labelPackageLocation;
     
     private TextField textPackageNumber;
     private TextField textPackageLocation;
     
     private Button buttonPackageCheck;
     
    //sending history
     private GridPane gridHistoryContent;
     ObservableList<ClientHistory> listHistory;
     
    // my account
     private GridPane gridMyAccountContent;
    private Label labelFirstName;
    private Label labelLastName;
    private Label labelCity;
    private Label labelPostCode;
    private Label labelStreet;
    private Label labelHouseNumber;
    private Label labelApartmentNumber;
    
    private TextField textFirstName;
    private TextField textLastName;
    private TextField textCity;
    private TextField textPostCode;
    private TextField textStreet;
    private TextField textHouseNumber;
    private TextField textApartmentNumber;
    
    Button buttonsubmitMyAccount;
    
    
    
    Group groupDetails;
    Label labelSelected;
    
    //////////////////////////////
    //TableView table;
      
    TextField inputFirstName ;
    TextField inputLastName;
    TextField inputCity;
    TextField inputPostCode;
    TextField inputStreet;
    TextField inputHouseNumber;
    TextField inputApartmentNumber;
    TextField inputTelephone;
    ComboBox<DeliveryStatus> ComboBoxDelivered;
    Label LabelComboBoxDeliveredError;
    ObservableList<CourierData> listUndelivered;
    
    
    
    

    

    public ClientGUI(Client client) 
    {
        this.client = client;
        clientFunc = new ClientFunc(client);
    }
    
    
    public void Display()
    {
        window = new Stage();
        window.setMinWidth(600);
        //window.setMinHeight(600);

        window.setOnCloseRequest(e -> {
            DatabaseInit.getInstance().getSession(SessionType.Client).close();
            System.exit(0);
        });
        
        window.setTitle( client.getUser().getUserType() + ": " + client.getUser().getUsername());
        
        
        //TabPane
        tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        GridPane.setConstraints(tabPane, 0, 0);  

        
        
        
        
        //Tab new package
        tabNewPackage = new Tab("New package");
        tabNewPackage.setContent(newPackageContent());
        
        tabNewPackage.setOnSelectionChanged(e -> {
           EventType eve =   e.getEventType();
            System.out.println("DeliveryCompany.app.gui.ClientGUI.Display()");
            setSenderData();
        });
        tabPane.getTabs().add(tabNewPackage);
        
        //Tab follow
        tabFollow = new Tab("Follow");
        tabFollow.setContent(followContent());
        //tabNew.setContent(newPackageContent());
        tabFollow.setOnSelectionChanged(e -> {
            System.out.println("DeliveryCompany.app.gui.ClientGUI.Display()");
            if(tabFollow.isSelected())
            {
                System.out.println("DeliveryCompany.app.gui.ClientGUI.Display()");
            }
        });
        tabPane.getTabs().add(tabFollow);
        
        
        //Tab history
        tabSendingHistory = new Tab("Sending history");
        tabSendingHistory.setContent(sendingHistoryContent());
        //tabNew.setContent(newPackageContent());
        tabSendingHistory.setOnSelectionChanged(e -> {
            System.out.println("DeliveryCompany.app.gui.ClientGUI.Display()");
        });
        tabPane.getTabs().add(tabSendingHistory);
        
        //Tab my account
        tabMyAccount = new Tab("My account");
        tabMyAccount.setContent(myAccountContent());
        //tabNew.setContent(newPackageContent());
        tabMyAccount.setOnSelectionChanged(e -> {
            System.out.println("DeliveryCompany.app.gui.ClientGUI.Display()");
        });
        tabPane.getTabs().add(tabMyAccount);
        
        
        Tab tabLogOut = new Tab("Log out");
        tabLogOut.setOnSelectionChanged( e -> {
            if(tabLogOut.isSelected())
            {
                this.window.close();
                LoginRegistry login = new LoginRegistry();
                login.Display();
            }
        });
        tabPane.getTabs().add(tabLogOut);
        
        
        //Set Scene
        window.setScene(new Scene(tabPane, 1300, 800)); 
        window.show();
    }
    
    private GridPane myAccountContent()
    {
        gridMyAccountContent = new GridPane();
        gridMyAccountContent.setPadding(new Insets(10, 10, 10, 10));
        gridMyAccountContent.setVgap(8);
        gridMyAccountContent.setHgap(10);
        
        
        
        labelFirstName = new Label("First name");
        GridPane.setConstraints(labelFirstName, 0, 1);
        gridMyAccountContent.getChildren().add(labelFirstName);
        
        labelLastName = new Label("Last name");
        GridPane.setConstraints(labelLastName, 0, 2);
        gridMyAccountContent.getChildren().add(labelLastName);
        
        labelCity = new Label("City");
        GridPane.setConstraints(labelCity, 0, 3);
        gridMyAccountContent.getChildren().add(labelCity);
        
        labelPostCode = new Label("Post code");
        GridPane.setConstraints(labelPostCode, 0, 4);
        gridMyAccountContent.getChildren().add(labelPostCode);
        
        labelStreet = new Label("Street");
        GridPane.setConstraints(labelStreet, 0, 5);
        gridMyAccountContent.getChildren().add(labelStreet);
        
        labelHouseNumber = new Label("House number");
        GridPane.setConstraints(labelHouseNumber, 0, 6);
        gridMyAccountContent.getChildren().add(labelHouseNumber);
        
        labelApartmentNumber = new Label("Apartment number");
        GridPane.setConstraints(labelApartmentNumber, 0, 7);
        gridMyAccountContent.getChildren().add(labelApartmentNumber);
        
        
        textFirstName = new TextField();
        GridPane.setConstraints(textFirstName, 1, 1);
        //textSenderFirstName.setEditable(false);
        gridMyAccountContent.getChildren().add(textFirstName);
        
        
        textLastName = new TextField();
        GridPane.setConstraints(textLastName, 1, 2);
        //textSenderLastName.setEditable(false);
        gridMyAccountContent.getChildren().add(textLastName);
        
        textCity = new TextField();
        GridPane.setConstraints(textCity, 1, 3);
        //textSenderCity.setEditable(false);
        gridMyAccountContent.getChildren().add(textCity);
        
        textPostCode = new TextField();
        GridPane.setConstraints(textPostCode, 1, 4);
        //textSenderPostCode.setEditable(false);
        gridMyAccountContent.getChildren().add(textPostCode);
        
        textStreet = new TextField();
        GridPane.setConstraints(textStreet, 1, 5);
        //textSenderStreet.setEditable(false);
        gridMyAccountContent.getChildren().add(textStreet);
        
        textHouseNumber = new TextField();
        GridPane.setConstraints(textHouseNumber, 1, 6);
        //textSenderHouseNumber.setEditable(false);
        gridMyAccountContent.getChildren().add(textHouseNumber);
        
        textApartmentNumber = new TextField();
        GridPane.setConstraints(textApartmentNumber, 1, 7);
        //textSenderApartmentNumber.setEditable(false);
        gridMyAccountContent.getChildren().add(textApartmentNumber);
        
        buttonsubmitMyAccount = new Button("Save");
        GridPane.setConstraints(buttonsubmitMyAccount, 1, 8);
        gridMyAccountContent.getChildren().add(buttonsubmitMyAccount);
        buttonsubmitMyAccount.setOnAction( e -> {
            Data data =  clientFunc.changeData(new Data(textFirstName.getText(), textLastName.getText(), new Address(textHouseNumber.getText(), textApartmentNumber.getText(),textStreet.getText() , textPostCode.getText(), textCity.getText())));
            
            if(data != null)
                this.client.setData(data);
        
        });
        
        setMyAccountData();
        
        return gridMyAccountContent;
    }
    
    private boolean setMyAccountData()
    {
        Data data = client.getData();
        
        if(data != null)
        {
        
            textFirstName.setText(data.getFirstName());
            textLastName.setText(data.getLastName());
            textCity.setText(data.getAddress().getCity());
            textPostCode.setText(data.getAddress().getPostCode());
            textStreet.setText(data.getAddress().getStreet());
            textHouseNumber.setText(data.getAddress().getHouseNumber());
            textApartmentNumber.setText(data.getAddress().getApartmentNumber());
            
            return true;
        }
        return false;
    }
    
    private GridPane sendingHistoryContent()
    {
        gridHistoryContent = new GridPane();
        gridHistoryContent.setPadding(new Insets(10, 10, 10, 10));
        gridHistoryContent.setVgap(8);
        gridHistoryContent.setHgap(10);
        
        listHistory = FXCollections.observableArrayList();
        listHistory.addAll(clientFunc.getAllSentPackage());
        
        TableView sendingHistory = createTable(listHistory);
        
        gridHistoryContent.getChildren().add(sendingHistory);
        
        return gridHistoryContent;
    }
    
    private GridPane followContent()
    {
        gridFollowContent = new GridPane();
        gridFollowContent.setPadding(new Insets(10, 10, 10, 10));
        gridFollowContent.setVgap(8);
        gridFollowContent.setHgap(10);
        
        labelPackageNumber = new Label("Package number: ");
        GridPane.setConstraints(labelPackageNumber, 0, 0);
        gridFollowContent.getChildren().add(labelPackageNumber);
        
        textPackageNumber = new TextField("enter number");
        GridPane.setConstraints(textPackageNumber, 1, 0);
        gridFollowContent.getChildren().add(textPackageNumber);
        
        buttonPackageCheck = new Button("Find");
        GridPane.setConstraints(buttonPackageCheck, 2, 0);
        gridFollowContent.getChildren().add(buttonPackageCheck);
        buttonPackageCheck.setOnAction(e -> {
            long id;
            
            if(textPackageNumber.equals(""))
                return;
            //try(long id = Long.parseLong(textPackageNumber.getText()))
            try
            {
               id =  Long.parseLong(textPackageNumber.getText());
            }
            catch(NumberFormatException ex)
            {
                System.err.println(ex.getMessage());
                return;
            }
                
            
            String packageLocation = clientFunc.getPackageLocation(id);
            if(packageLocation != null)
            {
                labelPackageLocation.setVisible(true);
                textPackageLocation.setVisible(true);
                textPackageLocation.setText(packageLocation);
            }
            else
            {
                textPackageLocation.setText("package not exists");
                return;
            }
        });
        
        labelPackageLocation = new Label("Package location: ");
        labelPackageLocation.setVisible(false);
        GridPane.setConstraints(labelPackageLocation, 0, 1);
        gridFollowContent.getChildren().add(labelPackageLocation);
        
        textPackageLocation = new TextField();
        textPackageLocation.setVisible(false);
        textPackageLocation.setEditable(false);
        textPackageLocation.setPrefWidth(300);
        GridPane.setConstraints(textPackageLocation, 1, 1);
        gridFollowContent.getChildren().add(textPackageLocation);
        
        
        
        return gridFollowContent;
    }
    
    private GridPane newPackageContent() 
    {
        gridNewPackageContent = new GridPane();
        gridNewPackageContent.setPadding(new Insets(10, 10, 10, 10));
        gridNewPackageContent.setVgap(8);
        gridNewPackageContent.setHgap(10);
        
        //labels
        labelSenderData = new Label("Sender");
        GridPane.setConstraints(labelSenderData, 0, 0);
        gridNewPackageContent.getChildren().add(labelSenderData);
        
        labelSenderFirstName = new Label("First name");
        GridPane.setConstraints(labelSenderFirstName, 0, 1);
        gridNewPackageContent.getChildren().add(labelSenderFirstName);
        
        labelSenderLastName = new Label("Last name");
        GridPane.setConstraints(labelSenderLastName, 0, 2);
        gridNewPackageContent.getChildren().add(labelSenderLastName);
        
        labelSenderCity = new Label("City");
        GridPane.setConstraints(labelSenderCity, 0, 3);
        gridNewPackageContent.getChildren().add(labelSenderCity);
        
        labelSenderPostCode = new Label("Post code");
        GridPane.setConstraints(labelSenderPostCode, 0, 4);
        gridNewPackageContent.getChildren().add(labelSenderPostCode);
        
        labelSenderStreet = new Label("Street");
        GridPane.setConstraints(labelSenderStreet, 0, 5);
        gridNewPackageContent.getChildren().add(labelSenderStreet);
        
        labelSenderHouseNumber = new Label("House number");
        GridPane.setConstraints(labelSenderHouseNumber, 0, 6);
        gridNewPackageContent.getChildren().add(labelSenderHouseNumber);
        
        labelSenderApartmentNumber = new Label("Apartment number");
        GridPane.setConstraints(labelSenderApartmentNumber, 0, 7);
        gridNewPackageContent.getChildren().add(labelSenderApartmentNumber);
        
        labelReceiverData = new Label("Receiver");
        GridPane.setConstraints(labelReceiverData, 2, 0);
        gridNewPackageContent.getChildren().add(labelReceiverData);
        
        labelReceiverFirstName = new Label("First name");
        GridPane.setConstraints(labelReceiverFirstName, 2, 1);
        gridNewPackageContent.getChildren().add(labelReceiverFirstName);
        
        labelReceiverLastName = new Label("Last name");
        GridPane.setConstraints(labelReceiverLastName, 2, 2);
        gridNewPackageContent.getChildren().add(labelReceiverLastName);
        
        labelReceiverCity = new Label("City");
        GridPane.setConstraints(labelReceiverCity, 2, 3);
        gridNewPackageContent.getChildren().add(labelReceiverCity);
        
        labelReceiverPostCode = new Label("Post code");
        GridPane.setConstraints(labelReceiverPostCode, 2, 4);
        gridNewPackageContent.getChildren().add(labelReceiverPostCode);
        
        labelReceiverStreet = new Label("Street");
        GridPane.setConstraints(labelReceiverStreet, 2, 5);
        gridNewPackageContent.getChildren().add(labelReceiverStreet);
        
        labelReceiverHouseNumber = new Label("House number");
        GridPane.setConstraints(labelReceiverHouseNumber, 2, 6);
        gridNewPackageContent.getChildren().add(labelReceiverHouseNumber);
        
        labelReceiverApartmentNumber = new Label("Apartment number");
        GridPane.setConstraints(labelReceiverApartmentNumber, 2, 7);
        gridNewPackageContent.getChildren().add(labelReceiverApartmentNumber);
        
        labelTelephone = new Label("Telephone number");
        GridPane.setConstraints(labelTelephone, 4, 1);
        gridNewPackageContent.getChildren().add(labelTelephone);
        
        labelDimensionsWidth = new Label("Width");
        GridPane.setConstraints(labelDimensionsWidth, 4, 3);
        gridNewPackageContent.getChildren().add(labelDimensionsWidth);
        
        labelDimensionsHeight = new Label("Height");
        GridPane.setConstraints(labelDimensionsHeight, 4, 4);
        gridNewPackageContent.getChildren().add(labelDimensionsHeight);
        
        labelDimensionsDepht = new Label("Depth");
        GridPane.setConstraints(labelDimensionsDepht, 4, 5);
        gridNewPackageContent.getChildren().add(labelDimensionsDepht);
        
        labelFormError = new Label("");
        GridPane.setConstraints(labelFormError, 4, 7, 2 ,1);
        GridPane.setHalignment(labelFormError, HPos.RIGHT);
        gridNewPackageContent.getChildren().add(labelFormError);
        
        //TextField
        textSenderFirstName = new TextField();
        GridPane.setConstraints(textSenderFirstName, 1, 1);
        //textSenderFirstName.setEditable(false);
        gridNewPackageContent.getChildren().add(textSenderFirstName);
        
        
        textSenderLastName = new TextField();
        GridPane.setConstraints(textSenderLastName, 1, 2);
        //textSenderLastName.setEditable(false);
        gridNewPackageContent.getChildren().add(textSenderLastName);
        
        textSenderCity = new TextField();
        GridPane.setConstraints(textSenderCity, 1, 3);
        //textSenderCity.setEditable(false);
        gridNewPackageContent.getChildren().add(textSenderCity);
        
        textSenderPostCode = new TextField();
        GridPane.setConstraints(textSenderPostCode, 1, 4);
      /* textSenderPostCode.setOnKeyPressed(e -> {
            
            KeyCode code = e.getCode();
            
            if(textSenderPostCode.getText().length() == 1 && !textSenderPostCode.getText().contains("-") )//!e.getCode().equals("BACK_SPACE"))
            {
                textSenderPostCode.setText(textSenderPostCode.getText() + "-");
            }
            
            
        }); 
*/
       
        //textSenderPostCode.setEditable(false);
        gridNewPackageContent.getChildren().add(textSenderPostCode);
        
        textSenderStreet = new TextField();
        GridPane.setConstraints(textSenderStreet, 1, 5);
        //textSenderStreet.setEditable(false);
        gridNewPackageContent.getChildren().add(textSenderStreet);
        
        textSenderHouseNumber = new TextField();
        GridPane.setConstraints(textSenderHouseNumber, 1, 6);
        //textSenderHouseNumber.setEditable(false);
        gridNewPackageContent.getChildren().add(textSenderHouseNumber);
        
        textSenderApartmentNumber = new TextField();
        GridPane.setConstraints(textSenderApartmentNumber, 1, 7);
        //textSenderApartmentNumber.setEditable(false);
        gridNewPackageContent.getChildren().add(textSenderApartmentNumber);
        
        textReceiverFirstName = new TextField();
        GridPane.setConstraints(textReceiverFirstName, 3, 1);
        gridNewPackageContent.getChildren().add(textReceiverFirstName);
        
        textReceiverLastName = new TextField();
        GridPane.setConstraints(textReceiverLastName, 3, 2);
        gridNewPackageContent.getChildren().add(textReceiverLastName);
        
        textReceiverCity = new TextField();
        GridPane.setConstraints(textReceiverCity, 3, 3);
        gridNewPackageContent.getChildren().add(textReceiverCity);
        
        textReceiverPostCode = new TextField();
        GridPane.setConstraints(textReceiverPostCode, 3, 4);
        gridNewPackageContent.getChildren().add(textReceiverPostCode);
        
        textReceiverStreet = new TextField();
        GridPane.setConstraints(textReceiverStreet, 3, 5);
        gridNewPackageContent.getChildren().add(textReceiverStreet);
        
        textReceiverHouseNumber = new TextField();
        GridPane.setConstraints(textReceiverHouseNumber, 3, 6);
        gridNewPackageContent.getChildren().add(textReceiverHouseNumber);
        
        textReceiverApartmentNumber = new TextField();
        GridPane.setConstraints(textReceiverApartmentNumber, 3, 7);
        gridNewPackageContent.getChildren().add(textReceiverApartmentNumber);
        
        textTelephone = new TextField();
        GridPane.setConstraints(textTelephone, 5, 1);
        gridNewPackageContent.getChildren().add(textTelephone);
        
        textDimensionsWidth = new TextField();
        GridPane.setConstraints(textDimensionsWidth, 5, 3);
        gridNewPackageContent.getChildren().add(textDimensionsWidth);
        
        textDimensionsHeight = new TextField();
        GridPane.setConstraints(textDimensionsHeight, 5, 4);
        gridNewPackageContent.getChildren().add(textDimensionsHeight);
        
        textDimensionsDepht = new TextField();
        GridPane.setConstraints(textDimensionsDepht, 5, 5);
        gridNewPackageContent.getChildren().add(textDimensionsDepht);
        
        //CheckBox
        checkBoxChangeSenderData = new CheckBox("Use different data");
        GridPane.setConstraints(checkBoxChangeSenderData, 0, 8, 2, 1);
        GridPane.setHalignment(checkBoxChangeSenderData, HPos.RIGHT);
        gridNewPackageContent.getChildren().add(checkBoxChangeSenderData);
        checkBoxChangeSenderData.setOnAction( e -> { 
            if(checkBoxChangeSenderData.isSelected())
            {
                setSenderFieldEditable(true);
            }
            else
            {
                if(client.getData() != null)
                {
                    setSenderFieldEditable(false);
                    setSenderData();
                }
                else
                {
                    checkBoxChangeSenderData.setSelected(true);
                }
            }
        });
        
        buttonSubmitNewPackage = new Button("Submit");
        GridPane.setConstraints(buttonSubmitNewPackage, 5, 8);
        GridPane.setHalignment(buttonSubmitNewPackage, HPos.RIGHT);
        gridNewPackageContent.getChildren().add(buttonSubmitNewPackage);
        buttonSubmitNewPackage.setOnAction(e -> {
            
            labelFormError.setText("");
            
            String validation = validateFormNewPackage();
            
            if(validation != null)
            {
                labelFormError.setText(validation);
                return;

            }

            /*
            if(validateFormNewPackage())
            {
                labelFormError.setText("Empty field");
                return;
            }
*/
            else
            {
                labelFormError.setText("");
            }
            
            Address senderAddress = new Address(textSenderHouseNumber.getText(), textSenderApartmentNumber.getText(), textSenderStreet.getText(), textSenderPostCode.getText(), textSenderCity.getText());
            Address receiverAddress = new Address(textReceiverHouseNumber.getText(), textReceiverApartmentNumber.getText(), textReceiverStreet.getText(), textReceiverPostCode.getText(), textReceiverCity.getText());
            Data sender = new Data(textSenderFirstName.getText(), textSenderLastName.getText(), senderAddress);
            Data receiver= new Data(textReceiverFirstName.getText(), textReceiverLastName.getText(), receiverAddress);
            Dimensions dimension;
            int telephone;
            try
            {
                dimension = new Dimensions(Integer.parseInt(textDimensionsWidth.getText()),Integer.parseInt(textDimensionsHeight.getText()), Integer.parseInt(textDimensionsDepht.getText()));
            
                telephone = Integer.parseInt(textTelephone.getText());
            }
            catch(NumberFormatException ex)
            {
                labelFormError.setText("Dimensions Or Telephone can't be a text");
                return;
            }
            
            long id = clientFunc.SendPackage(sender, senderAddress, receiver, receiverAddress, dimension, telephone);
            if(id == -1)
                labelFormError.setText("Error during add package");
            else
                labelFormError.setText("Package number: " + id);
            clearNewPackageField();
            
            listHistory.removeAll(listHistory);
            for(ClientHistory data : clientFunc.getAllSentPackage())
            {
                listHistory.add(data);
            }
        });
        
        if(!setSenderData())
        {
            checkBoxChangeSenderData.setSelected(true);
            setSenderFieldEditable(true);
        }
        else 
        {
            setSenderFieldEditable(false);
        }
        
        return gridNewPackageContent;
    }
    
    private void clearNewPackageField()
    {
        textSenderFirstName.setText("");
        textSenderLastName.setText("");
        textSenderCity.setText("");
        textSenderPostCode.setText("");
        textSenderStreet.setText("");
        textSenderHouseNumber.setText("");
        textSenderApartmentNumber.setText("");
        textReceiverFirstName.setText("");
        textReceiverLastName.setText("");
        textReceiverCity.setText("");
        textReceiverPostCode.setText("");
        textReceiverStreet.setText("");
        textReceiverHouseNumber.setText("");
        textReceiverApartmentNumber.setText("");
        textTelephone.setText("");
        textDimensionsWidth.setText("");
        textDimensionsHeight.setText("");
        textDimensionsDepht.setText("");
        
        if(!checkBoxChangeSenderData.isSelected())
            setSenderData();
    }
    
    private String validateFormNewPackage()
    {
        String[] split = textReceiverPostCode.getText().split("-");
        if(split[0].length() != 2 || split[1].length() != 3)
            return "Incorrect receiver post code format";
        
        split = textSenderPostCode.getText().split("-");
        if(split[0].length() != 2 || split[1].length() != 3)
            return "Incorrect sender post code format";
        
        if(!textSenderPostCode.getText().contains("-"))
            return "Incorrect sender post code";
        
        if(!textReceiverPostCode.getText().contains("-"))
            return "Incorrect receiver post code";
        
        if(textSenderPostCode.getText().length() != 6)
            return "Incorrect sender post code length";
        
        if(textReceiverPostCode.getText().length() != 6)
            return "Incorrect receiver post code length";
        
        if(textDimensionsDepht.getText().equals(""))
            return "Empty dimensions depth";
        
        if(textDimensionsHeight.getText().equals(""))
            return "Empty dimensions height";
        
        if(textDimensionsWidth.getText().equals(""))
            return "Empty dimensions width";
        
        if(textReceiverApartmentNumber.getText().equals(""))
            return "Empty receiver apartment number";
        
        if(textReceiverApartmentNumber.getText().length() > 4)
            return "Too long receiver apartment number";
        
        if(textReceiverCity.getText().equals(""))
            return "Empty receiver city";
        
        if(textReceiverCity.getText().length() > 40)
            return "Too long receiver city";
        
        if(textReceiverFirstName.getText().equals(""))
            return "Empty reeiver first name";
        
        if(textReceiverFirstName.getText().length() > 255)
            return "Too long receiver first name";
        
        if(textReceiverLastName.getText().equals(""))
            return "Empty receiver last name";
        
        if(textReceiverLastName.getText().length() > 255)
            return "Too long receiver last name";
        
        if(textReceiverPostCode.getText().equals(""))
            return "Empty receiver post code";
        
        if(textReceiverStreet.getText().equals(""))
            return "Empty receiver street";
        
        if(textReceiverStreet.getText().length() > 80)
            return "Too long receiver street";
        
        if(textReceiverHouseNumber.getText().equals(""))
            return "Empty receiver house number";
        
        if(textReceiverHouseNumber.getText().length() > 5)
            return "Too long receiver house number";
        
        if(textSenderApartmentNumber.getText().equals(""))
            return "Empty sender apartment number";
        
        if(textSenderApartmentNumber.getText().length() > 4)
            return "Too long sender apartment number";
        
        if(textSenderHouseNumber.getText().equals(""))
            return "Empty sender house number";
        
        if(textSenderHouseNumber.getText().length() > 5)
            return "Too long sender house number";
        
        if(textSenderCity.getText().equals(""))
            return "Empty sender city";
        
        if(textSenderCity.getText().length() > 40)
            return "Too long sender city";
        
        if(textSenderFirstName.getText().equals(""))
            return "Empty sender first name";
        
        if(textSenderFirstName.getText().length() > 255)
            return "Too long sender last name";

        if(textSenderLastName.getText().equals(""))
            return "Empty sender last name";
        
        if(textSenderLastName.getText().length() > 255)
            return "Too long sender last name";
        
        if(textSenderPostCode.getText().equals(""))
            return "Empty post code";
        
        if(textSenderStreet.getText().equals(""))
            return "Empty sender street";
        
        if(textSenderStreet.getText().length() > 80)
            return "Too long sender street";
        
        if(textTelephone.getText().equals(""))
            return "Empty telephone number";
        
        if(textTelephone.getText().length() > 9)
            return "Too long telephone";
        
        return null;
    }
    
    private void setSenderFieldEditable(boolean value)
    {
        textSenderFirstName.setEditable(value);
        textSenderLastName.setEditable(value);
        textSenderCity.setEditable(value);
        textSenderPostCode.setEditable(value);
        textSenderStreet.setEditable(value);
        textSenderHouseNumber.setEditable(value);
        textSenderApartmentNumber.setEditable(value);
    }
    
    private boolean setSenderData()
    {
        Data data = client.getData();
        
        if(data != null)
        {
        
            textSenderFirstName.setText(data.getFirstName());
            textSenderLastName.setText(data.getLastName());
            textSenderCity.setText(data.getAddress().getCity());
            textSenderPostCode.setText(data.getAddress().getPostCode());
            textSenderStreet.setText(data.getAddress().getStreet());
            textSenderHouseNumber.setText(data.getAddress().getHouseNumber());
            textSenderApartmentNumber.setText(data.getAddress().getApartmentNumber());
            
            return true;
        }
        return false;
    }
    
    
    private void selectedRow(TableView table)
    {
        ComboBoxDelivered.setValue(null);
        
        ObservableList selectedItem = table.getSelectionModel().getSelectedItems();
        
        if(selectedItem.get(0) == null)
        {
            
            groupDetails.setVisible(false);
            return;
        }
        if(((CourierData)selectedItem.get(0)).getDeliveryStatus().equals(DeliveryStatus.toDelivery.toString()))
        {
            ComboBoxDelivered.getItems().clear();
            ComboBoxDelivered.getItems().addAll(DeliveryStatus.delivered, DeliveryStatus.undelivered);
        }
        else if(((CourierData)selectedItem.get(0)).getDeliveryStatus().equals(DeliveryStatus.toPickUp.toString()))
        {
            ComboBoxDelivered.getItems().clear();
            ComboBoxDelivered.getItems().addAll(DeliveryStatus.pickedUp, DeliveryStatus.notPickedUp);
        }
        
        groupDetails.setVisible(true);
        
        inputFirstName.setText(((CourierData)selectedItem.get(0)).getReceiverFirstName());
        inputLastName.setText(((CourierData)selectedItem.get(0)).getReceiverLastName());
        inputCity.setText(((CourierData)selectedItem.get(0)).getReceiverCity());
        inputPostCode.setText(((CourierData)selectedItem.get(0)).getReceiverPostCode());
        inputStreet.setText(((CourierData)selectedItem.get(0)).getReceiverStreet());
        inputHouseNumber.setText(((CourierData)selectedItem.get(0)).getReceiverHouseNumber());
        inputTelephone.setText(((CourierData)selectedItem.get(0)).getTelephoneNumber() + "");
        inputApartmentNumber.setText(((CourierData)selectedItem.get(0)).getReceiverApartmentNumber());
    }
    
    private TableView createTable(ObservableList list)
    {
        boolean clearList = false;
        
        TableView table = new TableView();
        table.prefWidthProperty().bind(window.widthProperty());
        table.prefHeightProperty().bind(window.heightProperty());
        table.setEditable(false);
        
        table.setItems(list);
        
        if(list != null)// && list.size() != 0)
        {
            if(list.size() == 0)
            {
                list.add(new ClientHistory(-1, -1,"", "", "", "", "", "", "", "", "", "", "", "", "", "", ""));
                clearList = true;
            }
            
            Field[] declaredFields = list.get(0).getClass().getDeclaredFields();
            
            for (Field declaredField : declaredFields) {
                if (!declaredField.getName().contains("ID_")) {
                    TableColumn col = new TableColumn(declaredField.getName());
                    col.setMinWidth(Double.MIN_NORMAL);
                    col.setCellValueFactory(new PropertyValueFactory<>(declaredField.getName()));
                    table.getColumns().add(col);
                }
            }
            
            if(clearList)
            {
                table.getItems().clear();
            }
        }
        return table;
    }  

    
}
