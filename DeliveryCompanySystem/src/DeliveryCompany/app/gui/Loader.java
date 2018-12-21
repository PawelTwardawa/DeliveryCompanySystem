/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DeliveryCompany.app.gui;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Paweł
 */
public class Loader{
    
    private static Stage window;
    
    private VBox createContent()
    {
        String url = getClass().getResource("loading.gif").toExternalForm();
        ImageView progress = new ImageView(url);
        progress.setFitWidth(200);
        progress.setFitHeight(200);
        
        VBox vBox = new VBox();
        vBox.setSpacing(10.0);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll( progress);
        //vBox.setStyle("-fx-background-color: transparent;");
        
        return vBox;
    }
    
    public void Display()
    {
        window = new Stage();
        window.initStyle(StageStyle.TRANSPARENT);
        
        Scene scene = new Scene(createContent());
        scene.setFill(Color.TRANSPARENT);
        
        window.setScene(scene);
        
        window.show();
        
    }
    
    public void Hide()
    {
        window.close();
    }
    
}
