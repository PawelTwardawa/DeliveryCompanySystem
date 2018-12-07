/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DeliveryCompany.app.gui;

import DeliveryCompany.app.enumerate.DeliveryStatus;
import DeliveryCompany.app.functionality.ClientFunc;
import DeliveryCompany.app.functionality.CourierFunc;
import DeliveryCompany.database.init.DatabaseInit;
import DeliveryCompany.database.structure.Address;
import DeliveryCompany.database.structure.Client;
import DeliveryCompany.database.structure.Courier;
import DeliveryCompany.database.structure.CourierData;
import DeliveryCompany.database.structure.Data;
import DeliveryCompany.database.structure.Dimensions;
import com.sun.javafx.scene.control.skin.ComboBoxPopupControl;
import java.awt.Dimension;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.List;
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
            DatabaseInit.getInstance().getSession().close();
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
        });
        tabPane.getTabs().add(tabNewPackage);
        
        //Tab new package
        Tab tabNew = new Tab("New");
        //tabNew.setContent(newPackageContent());
        tabNew.setOnSelectionChanged(e -> {
            System.out.println("DeliveryCompany.app.gui.ClientGUI.Display()");
        });
        tabPane.getTabs().add(tabNew);
        
        
        //Set Scene
        window.setScene(new Scene(tabPane, 1300, 800)); 
        window.show();
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
        
        labelDimensionsDepht = new Label("Width");
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
            
            if(!validateFormData())
            {
                labelFormError.setText("Empty field");
                return;
            }
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
            
            clientFunc.SendPackage(sender, senderAddress, receiver, receiverAddress, dimension, telephone);
            labelFormError.setText("Success");
            clearNewPackageField();
        });
        
        if(!setSenderData())
        {
            checkBoxChangeSenderData.setSelected(true);
            setSenderFieldEditable(true);
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
    
    private boolean validateFormData()
    {
        if(textSenderPostCode.getText().length() > 5)
            return false;
        
        if(textReceiverPostCode.getText().length() > 5)
            return false;
        
        if(textDimensionsDepht.getText().equals(""))
            return false;
        
        if(textDimensionsHeight.getText().equals(""))
            return false;
        
        if(textDimensionsWidth.getText().equals(""))
            return false;
        
        if(textReceiverApartmentNumber.getText().equals(""))
            return false;
        
        if(textReceiverCity.getText().equals(""))
            return false;
        
        if(textReceiverFirstName.getText().equals(""))
            return false;
        
        if(textReceiverLastName.getText().equals(""))
            return false;
        
        if(textReceiverPostCode.getText().equals(""))
            return false;
        
        if(textReceiverStreet.getText().equals(""))
            return false;
        
        if(textReceiverHouseNumber.getText().equals(""))
            return false;
        
        if(textSenderApartmentNumber.getText().equals(""))
            return false;
        
        if(textSenderHouseNumber.getText().equals(""))
            return false;
        
        if(textSenderCity.getText().equals(""))
            return false;
        
        if(textSenderFirstName.getText().equals(""))
            return false;

        if(textSenderLastName.getText().equals(""))
            return false;
        
        if(textSenderPostCode.getText().equals(""))
            return false;
        
        if(textSenderStreet.getText().equals(""))
            return false;
        
        if(textTelephone.getText().equals(""))
            return false;
        
        return true;
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
    
    /*
    private GridPane createDetails(TableView tableForDelivery, TableView tableUndelivered)
    {
        //GridPane gridDelivered
        GridPane gridDelivered = new GridPane();
        gridDelivered.setPadding(new Insets(10, 10, 10, 10));
        gridDelivered.setVgap(8);
        gridDelivered.setHgap(10);
        
        //label labelFirstName
        Label labelFirstName = new Label("First Name:");
        GridPane.setConstraints(labelFirstName, 0, 0);
        
        //input name
        inputFirstName = new TextField();
        inputFirstName.setEditable(false);
        GridPane.setConstraints(inputFirstName, 1, 0);
        
        //label labelLastName
        Label labelLastName = new Label("Last Name:");
        GridPane.setConstraints(labelLastName, 0, 1);
        
        //input lastName
        inputLastName = new TextField();
        inputLastName.setEditable(false);
        GridPane.setConstraints(inputLastName, 1, 1);
        
        //label labelCity
        Label labelCity = new Label("City:");
        GridPane.setConstraints(labelCity, 0, 2);
        
        //input name
        inputCity = new TextField();
        inputCity.setEditable(false);
        GridPane.setConstraints(inputCity, 1, 2);
        
        //label labelPostCode
        Label labelPostCode = new Label("Post Code:");
        GridPane.setConstraints(labelPostCode, 0, 3);
        
        //input postCode
        inputPostCode = new TextField();
        inputPostCode.setEditable(false);
        GridPane.setConstraints(inputPostCode, 1, 3);
        
        //label labelStreet
        Label labelStreet = new Label("Street:");
        GridPane.setConstraints(labelStreet, 0, 4);
        
        //input street
        inputStreet = new TextField();
        inputStreet.setEditable(false);
        GridPane.setConstraints(inputStreet, 1, 4);
        
        //label labelHouseNumber
        Label labelHouseNumber = new Label("House number:");
        GridPane.setConstraints(labelHouseNumber, 0, 5);
        
        //input houseNumber
        inputHouseNumber = new TextField();
        inputHouseNumber.setEditable(false);
        GridPane.setConstraints(inputHouseNumber, 1, 5);
        
        //label labelApartmentNumber
        Label labelApartmentNumber = new Label("Apartment number:");
        GridPane.setConstraints(labelApartmentNumber, 0, 6);
        
        //input ApartmentNumber
        inputApartmentNumber = new TextField();
        inputApartmentNumber.setEditable(false);
        GridPane.setConstraints(inputApartmentNumber,1, 6);
        
        //label labelTelephone
        Label labelTelephone = new Label("Telephone:");
        GridPane.setConstraints(labelTelephone, 0, 7);
        
        //input Telephone
        inputTelephone = new TextField();
        inputTelephone.setEditable(false);
        GridPane.setConstraints(inputTelephone, 1, 7);    
        
        //label delivered status
        Label labelDeliveredStatus = new Label("Delivered status:");
        GridPane.setConstraints(labelDeliveredStatus, 2, 0);
        
        //label delivered status error
        LabelComboBoxDeliveredError = new Label("");
        GridPane.setConstraints(LabelComboBoxDeliveredError, 3, 1);
        
        //ComboBox Delivery status
        ComboBoxDelivered = new ComboBox<>();
        //ComboBoxDelivered.getItems().addAll(DeliveryStatus.delivered, DeliveryStatus.undelivered);
        ComboBoxDelivered.setPromptText("Delivered status");
        GridPane.setConstraints(ComboBoxDelivered, 3, 0);
        
        //button confirm
        Button buttonConfirm = new Button("Confirm");
        GridPane.setConstraints(buttonConfirm, 3, 7);
        GridPane.setHalignment(buttonConfirm, HPos.RIGHT);
        buttonConfirm.setOnAction((event) -> {
            buttonConfirmDetails(tableForDelivery, tableUndelivered);
        });
          
        gridDelivered.getChildren().addAll(labelApartmentNumber, labelCity, labelFirstName, labelHouseNumber, labelLastName, labelPostCode, labelStreet, labelTelephone, labelDeliveredStatus, LabelComboBoxDeliveredError);
        gridDelivered.getChildren().addAll(inputFirstName, inputLastName, inputCity, inputPostCode, inputStreet, inputHouseNumber, inputApartmentNumber, inputTelephone, ComboBoxDelivered, buttonConfirm);   
        
        return gridDelivered;
    }
    */
    /*
    private void buttonConfirmDetails(TableView tableForDelivery, TableView tableUndelivered)
    {
        ObservableList<CourierData> allData, selectedData;
        
        selectedData = tableForDelivery.getSelectionModel().getSelectedItems();
        allData = tableForDelivery.getItems();
        
        DeliveryStatus status =  ComboBoxDelivered.getValue();
        
        if(status == null)
        {
            LabelComboBoxDeliveredError.setText("Choose delivery status");
            return;
        }
        else
        {
            LabelComboBoxDeliveredError.setText("");
        }
        
        int val = courierFunc.setDeliveryStatus(status, selectedData.get(0).getID());
        
        listUndelivered.removeAll(listUndelivered);
        for(CourierData data : courierFunc.getTransportedPackage(DeliveryStatus.undelivered))
        {
            listUndelivered.add(data);
        }
        
        if(val == -1)
            System.err.println("ERROR UPDATE DELIVERY STATUS");
        
        if(val > 1)
            System.err.println("MULTIPLY PACKAGE ID DURING UPDATE DELIVERY STATUS");
        
        if(val == 1)
        {
            selectedData.forEach(allData::remove);
            selectedRow(tableForDelivery);
        }  
    }
    */
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
                list.add(new CourierData(-1, -1, -1, -1, "", "", "", "", "", "", "", ""));
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