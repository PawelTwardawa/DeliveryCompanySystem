/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DeliveryCompany.app.gui;

import DeliveryCompany.app.functionality.ClientFunc;
import DeliveryCompany.app.interfaces.EditDataEvent;
import DeliveryCompany.database.structure.Address;
import DeliveryCompany.database.structure.ClientHistory;
import DeliveryCompany.database.structure.Data;
import DeliveryCompany.database.structure.Dimensions;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Paweł
 */
public class EditDataGUI {
    
    public EditDataEvent editDataCallback;
    
    private Stage window;
    
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
    
    private Button buttonSubmitNewPackage;
    
    ClientHistory data;
    Stage owner;
    ClientFunc clientFunc;

    public EditDataGUI(ClientHistory data, Stage owner, ClientFunc clientFunc ) {
        this.data = data;
        this.owner = owner;
        this.clientFunc = clientFunc;
    }
    
    
    
    public void Display()
    {
        window = new Stage();
        window.initOwner(owner);
        window.setMinWidth(600);
        window.setTitle("Edit data");
        window.initModality(Modality.APPLICATION_MODAL);
        //window.setMinHeight(600);
        
        
        window.setScene(new Scene(editContent(), 1300, 800)); 
        window.show();
    }
    
    
    private GridPane editContent() 
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
        
        /*
        textDimensionsWidth = new TextField();
        GridPane.setConstraints(textDimensionsWidth, 5, 3);
        gridNewPackageContent.getChildren().add(textDimensionsWidth);
        
        textDimensionsHeight = new TextField();
        GridPane.setConstraints(textDimensionsHeight, 5, 4);
        gridNewPackageContent.getChildren().add(textDimensionsHeight);
        
        textDimensionsDepht = new TextField();
        GridPane.setConstraints(textDimensionsDepht, 5, 5);
        gridNewPackageContent.getChildren().add(textDimensionsDepht);
        */
        
        buttonSubmitNewPackage = new Button("Submit");
        GridPane.setConstraints(buttonSubmitNewPackage, 5, 8);
        GridPane.setHalignment(buttonSubmitNewPackage, HPos.RIGHT);
        gridNewPackageContent.getChildren().add(buttonSubmitNewPackage);
        buttonSubmitNewPackage.setOnAction(e -> {
            
            labelFormError.setText("");
            
            String validation = validateForm();
            
            if(validation != null)
            {
                labelFormError.setText(validation);
                return;

            }
            else
            {
                labelFormError.setText("");
            }
            
            //ClientHistory newData = new ClientHistory(0, 0, 0, validation, validation, validation, validation, validation, validation, validation, validation, validation, validation, validation, validation, validation, validation, validation, validation)
            
            Address senderAddress = new Address(textSenderHouseNumber.getText(), textSenderApartmentNumber.getText(), textSenderStreet.getText(), textSenderPostCode.getText(), textSenderCity.getText());
            Address receiverAddress = new Address(textReceiverHouseNumber.getText(), textReceiverApartmentNumber.getText(), textReceiverStreet.getText(), textReceiverPostCode.getText(), textReceiverCity.getText());
            Data sender = new Data(textSenderFirstName.getText(), textSenderLastName.getText(), senderAddress);
            Data receiver= new Data(textReceiverFirstName.getText(), textReceiverLastName.getText(), receiverAddress);
            int telephone;
            try
            {
                telephone = Integer.parseInt(textTelephone.getText());
            }
            catch(NumberFormatException ex)
            {
                labelFormError.setText("Dimensions Or Telephone can't be a text");
                return;
            }
            
            
            ClientHistory newData = new ClientHistory();
            newData.setDeliveredStatus(data.getDeliveredStatus());
            newData.setID(data.getID());
            newData.setID_client(data.getID_client());
            newData.setLocation(data.getLocation());
            newData.setReceiverApartmentNumber(textReceiverApartmentNumber.getText());
            newData.setReceiverCity(textReceiverCity.getText());
            newData.setReceiverFirstName(textReceiverFirstName.getText());
            newData.setReceiverHouseNumber(textReceiverHouseNumber.getText());
            newData.setReceiverLastName(textReceiverLastName.getText());
            newData.setReceiverPostCode(textReceiverPostCode.getText());
            newData.setReceiverStreet(textReceiverStreet.getText());
            newData.setTelephoneNumber(telephone);
            
            newData.setSenderApartmentNumber(textSenderApartmentNumber.getText());
            newData.setSenderCity(textSenderCity.getText());
            newData.setSenderFirstName(textSenderFirstName.getText());
            newData.setSenderHouseNumber(textSenderHouseNumber.getText());
            newData.setSenderLastName(textSenderLastName.getText());
            newData.setSenderPostCode(textSenderPostCode.getText());
            newData.setSenderStreet(textSenderStreet.getText());
            
            
            
            
            long result = clientFunc.updateData(data.getID(), sender, receiver, telephone);
            if(result == 1)
            {
                window.close();
                editDataCallback.EditDataCallback(newData);
                //labelFormError.setText("Sucess editing data");
            }
            else
            {
                labelFormError.setText("Error during update data");
            }
        });
        
        setData();
        
        return gridNewPackageContent;
    }
    
    private void setData()
    {
            textSenderFirstName.setText(data.getSenderFirstName());
            textSenderLastName.setText(data.getSenderLastName());
            textSenderCity.setText(data.getSenderCity());
            textSenderPostCode.setText(data.getSenderPostCode());
            textSenderStreet.setText(data.getSenderStreet());
            textSenderHouseNumber.setText(data.getSenderHouseNumber());
            textSenderApartmentNumber.setText(data.getSenderApartmentNumber());
            
            textReceiverFirstName.setText(data.getReceiverFirstName());
            textReceiverLastName.setText(data.getReceiverLastName());
            textReceiverCity.setText(data.getReceiverCity());
            textReceiverPostCode.setText(data.getReceiverPostCode());
            textReceiverStreet.setText(data.getReceiverStreet());
            textReceiverHouseNumber.setText(data.getReceiverHouseNumber());
            textReceiverApartmentNumber.setText(data.getReceiverApartmentNumber());
            
            textTelephone.setText(data.getTelephoneNumber() + "");
            
            
    }
    
    private String validateForm()
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
}
