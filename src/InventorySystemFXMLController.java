/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import Classes.InHouse;
import Classes.OutSourced;
import Classes.Part;
import Classes.Product;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Crystal Chavez
 */
public class InventorySystemFXMLController implements Initializable {

    public static int partId = 5;

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
    
    public static Part chosenPart = null;
    /** Temporary storage for a selected Product.
     */
    public static Product chosenProduct = null;
    
    static ObservableList <Part> allParts = FXCollections.observableArrayList();
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
        productInvLeveColl.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productCostCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        
        
        allParts.add(new InHouse(1, "Brakes",15.00,10,1,20,111));
        allParts.add(new InHouse(2, "Wheels",11.00,16,1,20,112));
        allParts.add(new InHouse(3, "Seat",15.00,10,1,20,113));
        //allParts.add(new OutSourced(4, "Giant Bike",299.99,5,1,20,"Super Bikes"));
        
        allProducts.add(new Product(5,"Giant Bike",299.99,5,1,20));
        allProducts.add(new Product(6,"Tricycle",99.99,3,1,20));
    }  
    
    /**
     * 
     * @param newPart
     allows us to add a new part to allParts
     */
    public static void addPart(Part newPart){
        allParts.add(newPart);
    }
    
    /**
     * 
     * @param newProduct 
     allows us to add a new product to allProducts
     */
    public static void addProduct(Product newProduct){
        allProducts.add(newProduct);
    }
    
    /**
     * 
     * @param partId
     * @return searched up part
     for the search field to lookup
     */
    public static Part lookPart(int partId){
        ObservableList <Part> foundPart = allParts;
        for (Part soughtPart : foundPart) {
            if(soughtPart.getId() == partId){
                return soughtPart;
            }
        }
        Alert na = new Alert(Alert.AlertType.INFORMATION);
        na.setContentText("Not available");
        na.show();
        return null;
        
    }
    
    /**
     * 
     * @param productId
     * @return searched up product
     */
    public static Product lookProduct(int productId){
        ObservableList <Product> foundProduct = allProducts;
        for (Product soughtProduct : foundProduct) {
            if(soughtProduct.getId() == partId){
                return soughtProduct;
            }
        }
        Alert na = new Alert(Alert.AlertType.INFORMATION);
        na.setContentText("Not available");
        na.show();
        return null;
    }
    
    public ObservableList<Part> lookupPart(String partName){
        ObservableList<Part> searchPartName = FXCollections.observableArrayList();
        for(Part part: allParts){
                if(part.getName().toLowerCase().contains(partName.toLowerCase())){
                    searchPartName.add(part);
                }
        }
        if(searchPartName.size() >0){
            return searchPartName;
        }else{
            Alert na = new Alert(Alert.AlertType.INFORMATION);
            na.setContentText("Not available");
            na.show();
            return allParts;
        }
    }
     public ObservableList<Product> lookupProduct(String productName){
        ObservableList<Product> searchProductName = FXCollections.observableArrayList();
        for(Product product: allProducts){
                if(product.getName().toLowerCase().contains(productName.toLowerCase())){
                    searchProductName.add(product);
                }
        }
        if(searchProductName.size() >0){
            return searchProductName;
        }else{
            Alert na = new Alert(Alert.AlertType.INFORMATION);
            na.setContentText("Not available");
            na.show();
            return allProducts;
        }
    }
    
     public static ObservableList<Part> lookupPartId(Part partId){
         if(partId != null){
             ObservableList<Part> listPartId = FXCollections.observableArrayList();
             for(Part part: allParts){
                 if(part.equals(partId)){
                     listPartId.add(part);
                 }
             }
             if(listPartId.size() > 0){
                 return listPartId;
             }else{
                 return allParts;
             }
         }else{
             return allParts;
         }
     }
     
     public static ObservableList<Product> lookupProductId(Product productId){
         if(productId != null){
             ObservableList<Product> listProductId = FXCollections.observableArrayList();
             for(Product product: allProducts){
                 if(product.equals(productId)){
                     listProductId.add(product);
                 }
             }
             if(listProductId.size() > 0){
                 return listProductId;
             }else{
                 return allProducts;
             }
         }else{
             return allProducts;
         }
     }
     
    /**
     * 
     * @param index
     * @param updatePart 
     */
    public static void updatePart(int index, Part updatePart){
        allParts.set(index, updatePart);
    }
    
    public static void updateProduct(int index, Product updateProduct){
        allProducts.set(index, updateProduct);
    }
    
    public static boolean deletePart(Part removePart){
        return allParts.remove(removePart);
    }
    
    public static boolean deleteProduct(Product removeProduct){
        return allProducts.remove(removeProduct);
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
    public void addProductButton(ActionEvent actionEvent){
        try {
        System.out.println("Add part button has been clicked");
        FXMLLoader loadProductMenu = new FXMLLoader(getClass().getClassLoader().getResource("AddProductInventoryFXML.fxml"));
       System.out.println(loadProductMenu);
        Parent addP = loadProductMenu.load();
        System.out.println(addP);
        Scene addProductScene = new Scene(addP);
        System.out.println(addProductScene);
        Stage addProductStage = new Stage();
        addProductStage.setScene(addProductScene);
        addProductStage.show();
        }catch (IOException ex) {
            Logger.getLogger(InventorySystemFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void updatePartButton(ActionEvent actionEvent) throws IOException{
        System.out.println("Update part! " + chosenPart);
        if(chosenPart != null){
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
        }else{
            Alert warning = new Alert(Alert.AlertType.WARNING);
            warning.setContentText("Please select part!");
            warning.show();
        }
    }
    
    public void selectedPart(){
        chosenProduct = null;
        chosenPart = allParts.get(allParts.indexOf(partTable.getSelectionModel().getSelectedItem()));
        System.out.println("Chosen Part!" + chosenPart);
    }
    
    public void partTableClick(MouseEvent mouseEvent){
        
        if(!partTable.getSelectionModel().isEmpty()){
            System.out.println("Clicked!");
            selectedPart();
        }
    }
    
    public void deletePartButton(ActionEvent actionEvent) throws IOException{
        System.out.println("Delete has been clicked!");
        if(chosenPart != null){
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setContentText("Please confirm deletion!");
            Optional<ButtonType> decide = confirm.showAndWait();
            if(!decide.isPresent()){
                Alert info = new Alert(Alert.AlertType.INFORMATION);
                info.setContentText(chosenPart.getName() + " not deleted yet!");
                info.show();
                chosenPart = null;
            }else if(decide.get() == ButtonType.OK){
                deletePart(chosenPart);
                chosenPart = null;
            }else if(decide.get() == ButtonType.CANCEL){
                Alert info = new Alert(Alert.AlertType.INFORMATION);
                info.setContentText(chosenPart.getName() + " not deleted yet!");
                info.show();
                chosenPart = null;
            }
        }else{
            Alert warning = new Alert(Alert.AlertType.WARNING);
            warning.setContentText("Please select part!");
            warning.show();
        }
    }
    
    public void seachParts(KeyEvent keyEvent){
        if(keyEvent.getCode() == KeyCode.ENTER || searchPart.getText() == ""){
            int partInteger = Integer.parseInt(searchPart.getText());
            partTable.setItems(lookupPartId(lookPart(partInteger)));
        }else{
            partTable.setItems(lookupPart(searchPart.getText()));
        }
    }
    
    public void seachProducts(KeyEvent keyEvent){
        if(keyEvent.getCode() == KeyCode.ENTER || searchProduct.getText() == ""){
            int productInteger = Integer.parseInt(searchProduct.getText());
            productTable.setItems(lookupProductId(lookProduct(productInteger)));
        }else{
            productTable.setItems(lookupProduct(searchProduct.getText()));
        }
    }
    
   
    
    @FXML
    public void exitButton(ActionEvent actionEvent){
         System.out.println("Exit button has been clicked");
        System.exit(0);
    }   
    
}
