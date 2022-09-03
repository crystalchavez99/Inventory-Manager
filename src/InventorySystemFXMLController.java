/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import Classes.InHouse;
import Classes.Part;
import Classes.Product;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Redfi
 */
public class InventorySystemFXMLController implements Initializable {

    public static int partId = 4;

    @FXML
    private AnchorPane partInventory;
    @FXML
    private ButtonBar partButtonBar;
    @FXML
    private AnchorPane productInventory;
    @FXML
    private ButtonBar productButtonBar;
    @FXML
    private Button exitSystem;
    @FXML
    private Button addPart;
    @FXML
    private Button editPart;
    @FXML
    private Button deletePart;
    @FXML
    private TableView partTable;
    @FXML
    private TextField searchPart;
    @FXML
    private Button addProduct;
    @FXML
    private Button editProduct;
    @FXML
    private Button deleteProduct;
    @FXML
    private TableView productTable;
    @FXML
    private TextField searchProduct;
    
    private static ObservableList <Part> allParts = FXCollections.observableArrayList();
    private static ObservableList <Product> allProducts = FXCollections.observableArrayList();
    @FXML
    private TableColumn partIdCol;
    @FXML
    private TableColumn partNameCol;
    @FXML
    private TableColumn partCostCol;
    @FXML
    private TableColumn productIdCol;
    @FXML
    private TableColumn productNameCol;
    @FXML
    private TableColumn productCostCol;
    @FXML
    private TableColumn partInvLeveCol;
    @FXML
    private TableColumn productInvLeveColl;
    
    private static Part selectedPart = null;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        partTable.setItems(allParts);
        productTable.setItems(allProducts);
        
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvLeveCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partCostCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        productIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        
        
        allParts.add(new InHouse(1, "Brakes",15.00,10,1,20,111));
        allParts.add(new InHouse(2, "Wheels",11.00,16,1,20,112));
        allParts.add(new InHouse(3, "Seat",15.00,10,1,20,113));
        //allProducts.add(new OutSourced(1000, "Giant Bike",299.99,5,1,20,"Super Bikes"));
        
    }  
    
    /**
     *
     * @param newPart
     */
    public static void addPart(Part newPart){
        allParts.add(newPart);
    }
    
    
    
    
    @FXML
    public void addPartButton(ActionEvent actionEvent){
        try {
        System.out.println("Add part button has been clicked");
        FXMLLoader loadPartMenu = new FXMLLoader(getClass().getClassLoader().getResource("AddPartInventoryFXML.fxml"));
       System.out.println(loadPartMenu);
        Parent addP = loadPartMenu.load();
        System.out.println(addP);
        Scene addPartScene = new Scene(addP);
        System.out.println(addPartScene);
        Stage addPartStage = new Stage();
        addPartStage.setScene(addPartScene);
        addPartStage.show();
        }catch (IOException ex) {
            Logger.getLogger(InventorySystemFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void updatePartButton(ActionEvent actionEvent){
        try {
        System.out.println("Add part button has been clicked");
        FXMLLoader loadPartMenu = new FXMLLoader(getClass().getClassLoader().getResource("UpdatePartInventoryFXML.fxml"));
       System.out.println(loadPartMenu);
        Parent updateP = loadPartMenu.load();
        System.out.println(updateP);
        Scene updatePartScene = new Scene(updateP);
        System.out.println(updatePartScene);
        Stage updatePartStage = new Stage();
        updatePartStage.setScene(updatePartScene);
        updatePartStage.show();
        }catch (IOException ex) {
            Logger.getLogger(InventorySystemFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
   
    
    @FXML
    public void exitButton(ActionEvent actionEvent){
         System.out.println("Exit button has been clicked");
        System.exit(0);
    }   
    
}
