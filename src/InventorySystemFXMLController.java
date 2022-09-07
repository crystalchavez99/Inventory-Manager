/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import Classes.InHouse;
import Classes.OutSourced;
import Classes.Part;
import Classes.Product;
import java.io.IOException;
import static java.lang.Character.isAlphabetic;
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
    /**
     * starting point of partId from the table
     */
    public static int partId = 4;
    
    /**
     * starting point of productId from the table
     */
    public static int productId = 3;

    /**
     * allows to have the window for part menu
     */
    @FXML
    private AnchorPane partInventory;
    
    /**
     * button bar to hold trio of buttons of add ,mod, delete
     */
    @FXML
    private ButtonBar partButtonBar;
    
    /**
     * allows to have the window for product menu
     */
    @FXML
    private AnchorPane productInventory;
    
    /**
     * button bar to hold trio of buttons of add ,mod, delete
     */
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
    
     /** Temporary storage for a selected Part.
     */
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
        
        allProducts.add(new Product(1,"Giant Bike",299.99,5,1,20));
        allProducts.add(new Product(2,"Tricycle",99.99,3,1,20));
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
     * @param partId the part id
     * @return searched up part
     for the search field to lookup
     */
    public static Part lookupPart(int partId){
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
     * @param productId the product id
     * @return searched up product
     */
    public static Product lookupProduct(int productId){
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
    
    /**
     * 
     * @param partName the name of the part
     * @return the part we are searching for in a list
     */
    public static ObservableList<Part> lookupPart(String partName){
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
    
    /**
     * 
     * @param productName the name of the product
     * @return the product we are searching for in a list
     */
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
    
     /**
      * 
      * @param partId id of the part
      * @return the part we are searching for in a list
      */
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
     
     /**
      * 
      * @param productId id of the product
      * @return the product we are searching for in a list
      */
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
     * @param index the index of part from table
     * @param updatePart part we want to update to that value
     */
    public static void updatePart(int index, Part updatePart){
        allParts.set(index, updatePart);
    }
    
    /**
     * 
     * @param index the index of product from table
     * @param updateProduct product we want to update to that value
     */
    public static void updateProduct(int index, Product updateProduct){
        allProducts.set(index, updateProduct);
    }
    
    /**
     * 
     * @param removePart part that we want to remove
     * @return true or false if removal is successful or not
     */
    public static boolean deletePart(Part removePart){
        return allParts.remove(removePart);
    }
    
    /**
     * 
     * @param removeProduct product that we want to remove
     * @return true or false if removal is successful or not
     */
    public static boolean deleteProduct(Product removeProduct){
        return allProducts.remove(removeProduct);
    }
    
    /**
     * 
     * @return list of all parts
     */
     public static ObservableList getAllParts(){
        return allParts;
    }
    
     /**
     * 
     * @return list of all products
     */
    public ObservableList getAllProducts(){
        return allProducts;
    }
    
    /**
     * 
     * @param object representing part or project
     * @return increment value id of part or project
     */
    public static int increaseId(int object){
        if(object == 0){
            return partId++;
        }else{
            return productId++;
        }
    }
    
    /**
     * 
     * @param part the part
     * @return the index of that part
     */
    public static int getPartIndex(Part part){
        return allParts.indexOf(part);
    }
    
    /**
     * 
     * @param product the product
     * @return the index of that product
     */
    public static int getProductIndex(Product product){
        return allProducts.indexOf(product);
    }
    
    /**
     * 
     * @param index of the part
     * @return the part from index of allParts
     */
    public static Part getPart(int index){
        return allParts.get(index);
    }
    
    /**
     * 
     * @param index of the product
     * @return the product from the index of allProducts
     */
    public static Product getProduct(int index){
        return allProducts.get(index);
    }
    
    /**
     * 
     * @param integer from fields of form
     * @return true or false if input is valid
     */
    public static boolean intValid(String integer){
        if(integer == null){
            return false;
        }
        try{
            Integer.parseInt(integer);
        }catch(NumberFormatException NFE){
            return false;
        }
        return true;
    }
    
    /**
     * 
     * @param doub from fields of form
     * @return true or false if input is valid
     */
    public static boolean doubleValid(String doub){
        if(doub == null){
            return false;
        }
        try{
            Double.parseDouble(doub);
        }catch(NumberFormatException NFE){
            return false;
        }
        return true;
    }
    
    /**
     * 
     * @param string from fields of form
     * @return true or false if input is valid
     */
     public static boolean stringValid(String string){
        if(string == null || string == ""){
            return false;
        }
        if(isAlphabetic(string.charAt(0))){
            return true;
        }else{
            return false;
        }
    }
    

    
    
    
    
    /**
     * @param actionEvent when user clicks on add
     * when we click on add, it will open the new scene
     */
    @FXML
    public void addPartButton(ActionEvent actionEvent){
        try {
        //System.out.println("Add part button has been clicked");
        FXMLLoader loadPartMenu = new FXMLLoader(getClass().getClassLoader().getResource("AddPartInventoryFXML.fxml"));
       //System.out.println(loadPartMenu);
        Parent addP = loadPartMenu.load();
       // System.out.println(addP);
        Scene addPartScene = new Scene(addP);
       // System.out.println(addPartScene);
        Stage addPartStage = new Stage();
        addPartStage.setScene(addPartScene);
        addPartStage.show();
        }catch (IOException ex) {
            Logger.getLogger(InventorySystemFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * @param actionEvent when user clicks on add
     * when we click on add, it will open the new  scene
     * @throws java.io.IOException any issues
     */
    @FXML
    public void addProductButton(ActionEvent actionEvent) throws IOException{
        System.out.println("Add product button has been clicked");
        FXMLLoader loadProductMenu = new FXMLLoader(getClass().getClassLoader().getResource("AddProductInventoryFXML.fxml"));
       System.out.println(loadProductMenu);
        Parent root = loadProductMenu.load();
        System.out.println(root);
        Scene addProductScene = new Scene(root);
        System.out.println(addProductScene);
        Stage addProductStage = new Stage();
        addProductStage.setScene(addProductScene);
        addProductStage.show();
    }
    
    /**
     * @param actionEvent when user clicks on modify
     * @throws java.io.IOException
     * when we click on modify, it will open the new scene
     */
    @FXML
    public void updatePartButton(ActionEvent actionEvent) throws IOException{
        //System.out.println("Update part! " + chosenPart);
        if(chosenPart != null){
        //    System.out.println("Add part button has been clicked");
        FXMLLoader loadPartMenu = new FXMLLoader(getClass().getClassLoader().getResource("UpdatePartInventoryFXML.fxml"));
     //  System.out.println(loadPartMenu);
        Parent updateP = loadPartMenu.load();
     //   System.out.println(updateP);
        Scene updatePartScene = new Scene(updateP);
    //    System.out.println(updatePartScene);
        Stage updatePartStage = new Stage();
        updatePartStage.setScene(updatePartScene);
        updatePartStage.show();
        }else{
            Alert warning = new Alert(Alert.AlertType.WARNING);
            warning.setContentText("Please select part!");
            warning.show();
        }
    }
    
    /**
     * @param actionEvent
     * when we click on modify, it will open the new scene
     * @throws java.io.IOException when input output fails
     */
    @FXML
    public void updateProductButton(ActionEvent actionEvent) throws IOException{
        //System.out.println("Update part! " + chosenPart);
        if(chosenProduct != null){
        FXMLLoader loadProductMenu = new FXMLLoader(getClass().getClassLoader().getResource("UpdateProductInventoryFXML.fxml"));
        Parent updateP = loadProductMenu.load();
        Scene updateProductScene = new Scene(updateP);
        Stage updateProductStage = new Stage();
        updateProductStage.setScene(updateProductScene);
        updateProductStage.show();
        }else{
            Alert warning = new Alert(Alert.AlertType.WARNING);
            warning.setContentText("Please select part!");
            warning.show();
        }
    }
    
    
    
    /**
     * setting our selectPart
     */
    public void selectedPart(){
        chosenProduct = null;
        chosenPart = getPart(getPartIndex((Part) partTable.getSelectionModel().getSelectedItem()));
        System.out.println("Chosen Part!" + chosenPart.getMax());
    }
    
    /**
     * setting our selectProduct
     */
    public void selectedProduct(){
        chosenProduct = getProduct(getProductIndex((Product) productTable.getSelectionModel().getSelectedItem()));
        chosenPart = null;
        System.out.println("Chosen Product!" + chosenProduct);
    }
    
    
    
   /**
    * 
    * @param mouseEvent when we click on table
    */
    @FXML
    public void partTableClick(MouseEvent mouseEvent){
        
        if(!partTable.getSelectionModel().isEmpty()){
            System.out.println("Clicked!");
            selectedPart();
        }
    }
    
    /**
     * 
     * @param mouseEvent when we click on table
     */
    @FXML
    public void productTableClick(MouseEvent mouseEvent){
        System.out.println("Enter prod table");
        if(!productTable.getSelectionModel().isEmpty()){
            System.out.println("Clicked!");
            selectedProduct();
        }
    }
    
    /**
     * 
     * @param actionEvent when we click on delete button
     * @throws IOException when input/output fails
     */
    @FXML
    public void deletePartButton(ActionEvent actionEvent) throws IOException{
        //System.out.println("Delete has been clicked!");
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
    
    /**
     * 
     * @param actionEvent when we click on delete button
     * @throws IOException when input/output fails
     */
    @FXML
     public void deleteProductButton(ActionEvent actionEvent) throws IOException{
        //System.out.println("Delete has been clicked!");
        if(chosenProduct != null){
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setContentText("Please confirm deletion!");
            Optional<ButtonType> decide = confirm.showAndWait();
            if(!decide.isPresent()){
                Alert info = new Alert(Alert.AlertType.INFORMATION);
                info.setContentText(chosenPart.getName() + " not deleted yet!");
                info.show();
                chosenPart = null;
            }else if(decide.get() == ButtonType.OK){
                deleteProduct(chosenProduct);
                chosenProduct = null;
            }else if(decide.get() == ButtonType.CANCEL){
                Alert info = new Alert(Alert.AlertType.INFORMATION);
                info.setContentText(chosenProduct.getName() + " not deleted yet!");
                info.show();
                chosenProduct = null;
            }
        }else{
            Alert warning = new Alert(Alert.AlertType.WARNING);
            warning.setContentText("Please select part!");
            warning.show();
        }
    }
    
    /**
     * 
     * @param keyEvent when we type into field
     */
    @FXML
    public void seachParts(KeyEvent keyEvent){
        if(keyEvent.getCode() == KeyCode.ENTER || searchPart.getText() == ""){
            int partInteger = Integer.parseInt(searchPart.getText());
            partTable.setItems(lookupPartId(lookupPart(partInteger)));
        }else{
            partTable.setItems(lookupPart(searchPart.getText()));
        }
    }
    
    /**
     * 
     * @param keyEvent when we type into field
     */
    @FXML
    public void seachProducts(KeyEvent keyEvent){
        if(keyEvent.getCode() == KeyCode.ENTER || searchProduct.getText() == ""){
            int productInteger = Integer.parseInt(searchProduct.getText());
            productTable.setItems(lookupProductId(lookupProduct(productInteger)));
        }else{
            productTable.setItems(lookupProduct(searchProduct.getText()));
        }
    }
    
   
    
    /**
     * 
     * @param actionEvent when we click on exit button
     */
    @FXML
    public void exitButton(ActionEvent actionEvent){
         System.out.println("Exit button has been clicked");
        System.exit(0);
    }   
    
}
