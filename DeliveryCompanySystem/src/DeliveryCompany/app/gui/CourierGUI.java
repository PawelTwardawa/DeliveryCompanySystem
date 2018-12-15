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
import DeliveryCompany.database.structure.Courier;
import DeliveryCompany.database.structure.CourierData;
import com.sun.javafx.scene.control.skin.ComboBoxPopupControl;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
public class CourierGUI {
    
    Courier courier;
    CourierFunc courierFunc;
    Stage window;
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
    

    

    public CourierGUI(Courier courier) 
    {
        this.courier = courier;
        courierFunc = new CourierFunc(courier);
    }
    
    
    public void Display()
    {
        window = new Stage();
        window.setMinWidth(600);
        //window.setMinHeight(600);

        window.setOnCloseRequest(e -> {
            DatabaseInit.getInstance().getSession(SessionType.Courier).close();
            System.exit(0);
        });
        
        window.setTitle( courier.getUser().getUserType() + ": " + courier.getUser().getUsername());
        
        
        //Table delivered View Data
        //ObservableList<CourierData> listUndelivered = FXCollections.observableArrayList();
        listUndelivered = FXCollections.observableArrayList();
        listUndelivered.addAll(courierFunc.getTransportedPackage(DeliveryStatus.undelivered));
        TableView tableUndelivered = createTable(listUndelivered);
        
        //TabPane
        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        GridPane.setConstraints(tabPane, 2, 4);  

        //GridPane
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);
        
        //grid.getChildren().add(tabPane);
        
        //Vbox
        VBox vBoxTableView = new VBox();
        GridPane.setConstraints(vBoxTableView, 0, 0);
        
        //Table for delivery View Data
        ObservableList<CourierData> transportedPackage = FXCollections.observableArrayList();
        transportedPackage.addAll(courierFunc.getTransportedPackage(null));
        
        //Table view
        TableView tableForDelivery = createTable(transportedPackage);
        tableForDelivery.setOnMouseClicked((event) -> {
            selectedRow(tableForDelivery);
        });
        vBoxTableView.getChildren().add(tableForDelivery);
        GridPane.setConstraints(vBoxTableView, 0, 0, 3,1);
        
        //Group group Details
        groupDetails = new Group();
        groupDetails.setVisible(false);
        groupDetails.getChildren().add(createDetails(tableForDelivery, tableUndelivered));
        GridPane.setConstraints(groupDetails, 0, 1);
        
        //Add controls to GridPane
        grid.getChildren().addAll(vBoxTableView, groupDetails);
        
        //Tab for dalivery
        Tab tabForDelivery = new Tab("For Delivery");
        tabForDelivery.setContent(grid);
         
        
        
        //GridPane undelivred
        GridPane gridUndelivered = new GridPane();
        gridUndelivered.setPadding(new Insets(10, 10, 10, 10));
        gridUndelivered.setVgap(8);
        gridUndelivered.setHgap(10);
        gridUndelivered.getChildren().add(tableUndelivered);
        
        //Tab for dalivery
        Tab tabUndelivered = new Tab("Undelivered");
        tabUndelivered.setContent(gridUndelivered);
        
        //Add tabs to TabPane
        tabPane.getTabs().add(tabForDelivery);
        tabPane.getTabs().add(tabUndelivered);
        
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
         
        //Create scene
        //Scene scene = new Scene(grid, 1300, 800);
        Scene scene = new Scene(tabPane, 1300, 800);
        
        //Set Scene
        window.setScene(scene); 
        window.show();
    }
    
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
