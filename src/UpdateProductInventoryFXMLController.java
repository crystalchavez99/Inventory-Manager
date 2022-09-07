/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import Classes.Part;
import Classes.Product;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Redfi
 */
public class UpdateProductInventoryFXMLController implements Initializable {

    @FXML
    private AnchorPane addIdLabel;
    @FXML
    private Label addProductLabel;
    @FXML
    private Label productIdLabel;
    @FXML
    private Label productNameLabel;
    @FXML
    private Label productInvLabel;
    @FXML
    private Label productCostLabel;
    @FXML
    private Label productMaxLabel;
    @FXML
    private TextField productIdFIeld;
    @FXML
    private TextField productNameField;
    @FXML
    private TextField productInvField;
    @FXML
    private TextField productPriceField;
    @FXML
    private TextField productMaxField;
    @FXML
    private Label productMinLabel;
    @FXML
    private TextField productMinField;
    @FXML
    private TextField productSearchField;
    @FXML
    private TableView productTable;
    /**
     * table for associated products
     */
    public TableView productAscTable;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Button deleteProductButton;
    @FXML
    private Button addProductButton;
    
    private int modIndex;

    private Product temp;
    
    private Part chosenPart = null;
   private Part chosenAscPart = null;
   
   private String productName;
    private int productMin;
    private int productMax;
    private double productCost;
    private int productInv;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        modIndex = InventorySystemFXMLController.getProductIndex(InventorySystemFXMLController.chosenProduct);
        temp = InventorySystemFXMLController.chosenProduct;
        productMod();
    }    
    
    /**
     * displays info of the product that we want to modify
     */
    public void productMod(){
        if(temp != null){
            productIdFIeld.setText(String.valueOf(temp.getId()));
            productNameField.setText(String.valueOf(temp.getName()));
            productInvField.setText(String.valueOf(temp.getStock()));
            productPriceField.setText(String.valueOf(temp.getPrice()));
            productMaxField.setText(String.valueOf(temp.getMax()));
            productMinField.setText(String.valueOf(temp.getMin()));
        }
    }
    
    /**
     * click on the value of the product table
     */
    public void selectedPart(){
        if(productTable != null){
            chosenPart = (Part) productTable.getSelectionModel().getSelectedItem();
        }
    }
    
   /**
    * click on the value of the product associated table
    */
    public void selectedAscPart(){
        if(productAscTable != null){
            chosenAscPart =  (Part) productAscTable.getSelectionModel().getSelectedItem();
        }
    }
    
    /**
     * 
     * @return true or false if inputs are valid
     */
    public boolean validation(){
        Alert invalid = new Alert(Alert.AlertType.WARNING);
        if(InventorySystemFXMLController.stringValid(productNameField.getText())){
            productName = productNameField.getText();
        }else{
            invalid.setContentText("Name must start with a letter!");
            invalid.show();
            return false;
        }
        if(InventorySystemFXMLController.doubleValid(productPriceField.getText())){
            productCost = Double.parseDouble(productPriceField.getText());
        }else{
            invalid.setContentText("Must be a valid decimal number!");
            invalid.show();
            return false;
        }
        if(InventorySystemFXMLController.intValid(productInvField.getText())){
            productInv = Integer.parseInt(productInvField.getText());
        }else{
            invalid.setContentText("Must be a valid integer number!");
            invalid.show();
            return false;
        }
        if(InventorySystemFXMLController.intValid(productMaxField.getText())){
            productMax = Integer.parseInt(productMaxField.getText());
        }else{
            invalid.setContentText("Must be a valid integer number!");
            invalid.show();
            return false;
        }
        if(InventorySystemFXMLController.intValid(productMinField.getText())){
            productMin = Integer.parseInt(productMinField.getText());
        }else{
            invalid.setContentText("Must be a valid integer number!");
            invalid.show();
            return false;
        }
        if(productMin > productMax){
            invalid.setContentText("Max must be greater than min!");
            invalid.show();
            return false;
        }
         if(productInv > productMax || productInv < productMin){
            invalid.setContentText("Inventory must be between min/max!");
            invalid.show();
            return false;
        }
        return true;
    }
    
    /**
     * 
     * @param actionEvent when we click on adding associated parts to that part
     */
    @FXML
     public void addAscPartButton(ActionEvent actionEvent){
        if(chosenPart != null){
            temp.addAssociatedPart(chosenPart);
            productAscTable.setItems(temp.getAllAssociatedParts());
        }else{
            Alert warning = new Alert(Alert.AlertType.WARNING);
            warning.setContentText("Please choose part!");
            warning.show();
        }
    }
     
     /**
      * 
      * @param actionEvent when we click on removing that associated part
      */
    @FXML
     public void removeAscButton(ActionEvent actionEvent){
    if(chosenAscPart!=null){
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setContentText("Please confirm!");
        Optional<ButtonType> result = confirm.showAndWait();
        if(!result.isPresent()){
            chosenAscPart = null;
        }else if(result.get() == ButtonType.OK){
            temp.deleteAssociatedPart(chosenAscPart);
            productAscTable.setItems(temp.getAllAssociatedParts());
            chosenAscPart = null;
        }else if(result.get() == ButtonType.CANCEL){
            chosenAscPart = null;
        }
    }else{
        Alert deleteAscPart = new Alert(Alert.AlertType.WARNING);
        deleteAscPart.setContentText("Please select to remove");
        deleteAscPart.show();
    }
    }
    

    /**
     * 
     * @param event click on cancel button
     */
    @FXML
    public void cancelButton(ActionEvent event) {
        System.out.println("Cancel button has been clicked");
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    
    /**
     * 
     * @param actionEvent when we click on save button
     */
    @FXML
    public void saveProductButton(ActionEvent actionEvent){
        if(validation()){
            temp.setId(InventorySystemFXMLController.productId);
            temp.setName(productNameField.getText());
            temp.setPrice(Double.parseDouble(productPriceField.getText()));
            temp.setStock(Integer.parseInt(productInvField.getText()));
            temp.setMax(Integer.parseInt(productMaxField.getText()));
            temp.setMin(Integer.parseInt(productMinField.getText()));
            InventorySystemFXMLController.updateProduct(modIndex, temp);
            InventorySystemFXMLController.increaseId(1);
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        }
    }
    
    /**
     * 
     * @param keyEvent looks at whats being typed into the text field to be a search field
     */
    @FXML
    public void searchParts(KeyEvent keyEvent){
        if(keyEvent.getCode() == KeyCode.ENTER || productSearchField.getText() == ""){
            int partInteger = Integer.parseInt(productSearchField.getText());
            productTable.setItems(InventorySystemFXMLController.lookupPartId(InventorySystemFXMLController.lookupPart(partInteger)));
        }else{
            productTable.setItems(InventorySystemFXMLController.lookupPart(productSearchField.getText()));
        }
    }
    
    /**
     * 
     * @param mouseEvent when we click on the product table
     */
    @FXML
    public void onProductTableClick(MouseEvent mouseEvent){
        selectedPart();
    }
    /**
     * 
     * @param mouseEvent when we click on the product associated table
     */
    @FXML
    public void onProductAscTableClick(MouseEvent mouseEvent){
        selectedAscPart();
    }
}
