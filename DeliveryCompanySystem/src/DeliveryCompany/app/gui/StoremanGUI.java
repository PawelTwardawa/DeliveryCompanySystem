/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DeliveryCompany.app.gui;

import DeliveryCompany.app.enumerate.DeliveryStatus;
import DeliveryCompany.app.enumerate.SessionType;
import static DeliveryCompany.app.enumerate.UserType.Storeman;
import DeliveryCompany.app.functionality.CourierFunc;
import DeliveryCompany.app.functionality.StoremanFunc;
import DeliveryCompany.database.init.DatabaseInit;
import DeliveryCompany.database.structure.Courier;
import DeliveryCompany.database.structure.CourierData;
import DeliveryCompany.database.structure.Storeman;
import DeliveryCompany.database.structure.StoremanData;
import java.lang.reflect.Field;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Paweł
 */
public class StoremanGUI {
    
    Storeman storeman;
    StoremanFunc storemanFunc;
    Stage window;
    
    
    Group groupDetails;
    //Label labelSelected;
    
    //////////////////////////////
    //TableView table;
      
    
    
    //ComboBox<DeliveryStatus> ComboBoxDelivered;
    //Label LabelComboBoxDeliveredError;
    
    
    
    //content
    private GridPane gridContent;
    GridPane gridDestils;
    
    ObservableList<StoremanData> listPackage;
    
    // details
    
    Label labelID ;
    Label labelIdCourier;
    Label labelCity;
    Label labelPostCode;
    Label labelStreet;
    Label labelHouseNumber;
    Label labelApartmentNumber;
    Label labelTarget;
    
    TextField textID ;
    TextField testIdCourier;
    TextField textCity;
    TextField textPostCode;
    TextField textStreet;
    TextField textHouseNumber;
    TextField textApartmentNumber;
    TextField textTarget;
    
    Button buttonConfirm;
    


    public StoremanGUI(Storeman storeman)
    {
        this.storeman = storeman;
        this.storemanFunc = new  StoremanFunc(storeman);
    }
    
    public void Display()
    {
        window = new Stage();
        window.setMinWidth(600);
        //window.setMinHeight(600);

        window.setOnCloseRequest(e -> {
            DatabaseInit.getInstance().getSession(SessionType.Storeman).close();
            System.exit(0);
        });
        
        window.setTitle( storeman.getUser().getUserType() + ": " + storeman.getUser().getUsername());
        
        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        
        Tab pack = new Tab("Package");
        pack.setContent(storemanContent());
        tabPane.getTabs().add(pack);
        
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
        
        
        
        Scene scene = new Scene(tabPane, 1300, 800);

        
        //Set Scene
        window.setScene(scene); 
        window.show();
    }
    
    private GridPane storemanContent()
    {
        gridContent = new GridPane();
        gridContent.setPadding(new Insets(10, 10, 10, 10));
        gridContent.setVgap(8);
        gridContent.setHgap(10);
        
        listPackage = FXCollections.observableArrayList();
        listPackage.addAll(storemanFunc.getPackageInWarehouse());
        TableView<StoremanData> tablePackage = createTable(listPackage);
        GridPane.setConstraints(tablePackage, 0, 0);
        tablePackage.setOnMouseClicked(e -> {
            selectedRow(tablePackage);
        });
        
        gridContent.getChildren().add(tablePackage);
        
        gridDestils = createDetails(tablePackage);
        GridPane.setConstraints(gridDestils, 0, 1);
        gridDestils.setVisible(false);
        
        gridContent.getChildren().add(gridDestils);
        
        return gridContent;
    }
    
    private GridPane createDetails(TableView<StoremanData> tablePackage)
    {
        //GridPane gridDelivered
        GridPane gridDelivered = new GridPane();
        gridDelivered.setPadding(new Insets(10, 10, 10, 10));
        gridDelivered.setVgap(8);
        gridDelivered.setHgap(10);
        
        //label labelFirstName
        labelID = new Label("ID:");
        GridPane.setConstraints(labelID, 0, 0);
        gridDelivered.getChildren().add(labelID);
        
        //input name
        textID = new TextField();
        textID.setEditable(false);
        GridPane.setConstraints(textID, 1, 0);
        gridDelivered.getChildren().add(textID);
        
        //label labelLastName
        labelIdCourier = new Label("ID courier:");
        GridPane.setConstraints(labelIdCourier, 0, 1);
        gridDelivered.getChildren().add(labelIdCourier);
        
        //input lastName
        testIdCourier = new TextField();
        testIdCourier.setEditable(false);
        GridPane.setConstraints(testIdCourier, 1, 1);
        gridDelivered.getChildren().add(testIdCourier);
        
        //label labelCity
        labelCity = new Label("City:");
        GridPane.setConstraints(labelCity, 0, 2);
        gridDelivered.getChildren().add(labelCity);
        
        //input name
        textCity = new TextField();
        textCity.setEditable(false);
        GridPane.setConstraints(textCity, 1, 2);
        gridDelivered.getChildren().add(textCity);
        
        //label labelPostCode
        labelPostCode = new Label("Post Code:");
        GridPane.setConstraints(labelPostCode, 0, 3);
        gridDelivered.getChildren().add(labelPostCode);
        
        //input postCode
        textPostCode = new TextField();
        textPostCode.setEditable(false);
        GridPane.setConstraints(textPostCode, 1, 3);
        gridDelivered.getChildren().add(textPostCode);
        
        //label labelStreet
        labelStreet = new Label("Street:");
        GridPane.setConstraints(labelStreet, 0, 4);
        gridDelivered.getChildren().add(labelStreet);
        
        //input street
        textStreet = new TextField();
        textStreet.setEditable(false);
        GridPane.setConstraints(textStreet, 1, 4);
        gridDelivered.getChildren().add(textStreet);
        
        //label labelHouseNumber
        labelHouseNumber = new Label("House number:");
        GridPane.setConstraints(labelHouseNumber, 0, 5);
        gridDelivered.getChildren().add(labelHouseNumber);
        
        //input houseNumber
        textHouseNumber = new TextField();
        textHouseNumber.setEditable(false);
        GridPane.setConstraints(textHouseNumber, 1, 5);
        gridDelivered.getChildren().add(textHouseNumber);
        
        //label labelApartmentNumber
        labelApartmentNumber = new Label("Apartment number:");
        GridPane.setConstraints(labelApartmentNumber, 0, 6);
        gridDelivered.getChildren().add(labelApartmentNumber);
        
        //input ApartmentNumber
        textApartmentNumber = new TextField();
        textApartmentNumber.setEditable(false);
        GridPane.setConstraints(textApartmentNumber,1, 6);
        gridDelivered.getChildren().add(textApartmentNumber);
        
        //label target
        labelTarget = new Label("Target");
        GridPane.setConstraints(labelTarget,2, 1);
        gridDelivered.getChildren().add(labelTarget);
        
        
        //input ApartmentNumber
        textTarget = new TextField();
        textTarget.setEditable(false);
        GridPane.setConstraints(textTarget,3, 1);
        gridDelivered.getChildren().add(textTarget);
        
        //button confirm
        buttonConfirm = new Button("Moved");
        GridPane.setConstraints(buttonConfirm, 3, 6);
        GridPane.setHalignment(buttonConfirm, HPos.RIGHT);
        gridDelivered.getChildren().add(buttonConfirm);
        buttonConfirm.setOnAction((event) -> {
            buttonConfirm(tablePackage);
        });
        

        return gridDelivered;
    }
    
    private void buttonConfirm(TableView<StoremanData> tablePackage)
    {
        ObservableList<StoremanData> allData, selectedData;
        
        selectedData = tablePackage.getSelectionModel().getSelectedItems();
        allData = tablePackage.getItems();
        
        
        
        int val = storemanFunc.changePackageCourier((StoremanData)selectedData.get(0));
        
        if(val == -1)
            System.err.println("ERROR UPDATE");
        
        if(val > 1)
            System.err.println("MULTIPLY PACKAGE ID DURING UPDATE COURIER");
        
        if(val == 1)
        {
            selectedData.forEach(allData::remove);
            selectedRow(tablePackage);
        }  
        
    }
    
    private void selectedRow(TableView table)
    {
        
        
        ObservableList selectedItem = table.getSelectionModel().getSelectedItems();
        
        if(selectedItem.get(0) == null)
        {
            
            gridDestils.setVisible(false);
            return;
        }
        
        gridDestils.setVisible(true);
        
        textID.setText(((StoremanData)selectedItem.get(0)).getID() + "");
        testIdCourier.setText(((StoremanData)selectedItem.get(0)).getID_courier() + "");
        textCity.setText(((StoremanData)selectedItem.get(0)).getCity());
        textPostCode.setText(((StoremanData)selectedItem.get(0)).getPostCode());
        textStreet.setText(((StoremanData)selectedItem.get(0)).getStreet());
        textHouseNumber.setText(((StoremanData)selectedItem.get(0)).getHouseNumber());
        textApartmentNumber.setText(((StoremanData)selectedItem.get(0)).getApartmentNumber());
        textTarget.setText((storemanFunc.targetCourier((StoremanData)selectedItem.get(0))).getId() + "");

    }
    
    private TableView<StoremanData> createTable(ObservableList<StoremanData> list)
    {
        boolean clearList = false;
        
        TableView<StoremanData> table = new TableView<>();
        table.prefWidthProperty().bind(window.widthProperty());
        table.prefHeightProperty().bind(window.heightProperty());
        table.setEditable(false);
        
        table.setItems(list);
        
        if(list != null)// && list.size() != 0)
        {
            if(list.size() == 0)
            {
                list.add(new StoremanData(-1, -1, "", "", "", "", ""));
                clearList = true;
            }
            
            Field[] declaredFields = list.get(0).getClass().getDeclaredFields();
            
            for (Field declaredField : declaredFields) {
                if (!declaredField.getName().contains("DeliveredStatus")) {
                    TableColumn<StoremanData, String> col = new TableColumn<>(declaredField.getName());
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
