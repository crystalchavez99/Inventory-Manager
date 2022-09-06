/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import static java.lang.Character.isAlphabetic;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Redfi
 */
public class AddProductInventoryFXMLController implements Initializable {

    @FXML
    private Label addProductLabel;
    private Label productIdLabel;
    private Label productNameLabel;
    private Label productInvLabel;
    private Label productCostLabel;
    private Label productMaxLabel;
    private Label productMinLabel;
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
    private TextField productMinField;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;
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
        int id = InventorySystemFXMLController.partId;
        productIdFIeld.setText(Integer.toString(id));
    }    
    
    public boolean validation(){
        System.out.print("Entered valid method!");
        Alert invalid = new Alert(Alert.AlertType.WARNING);
        System.out.println(productNameField.getText());
        if(productNameField.getText() == ""){
            System.out.println("Entered invalid name method!");
            invalid.setContentText("Name must start with a letter!");
            invalid.show();
            return false;
        }
        if(isAlphabetic(productNameField.getText().charAt(0))){
            productName = productNameField.getText();
            System.out.println("valid!" + productName);
        }
        
        if(productPriceField.getText() == null){
            return false;
        }
        try{
        productCost = Double.parseDouble(productPriceField.getText());
        }catch (NumberFormatException nfe) {
            invalid.setContentText("Must be a valid decimal number!");
            invalid.show();
            return false;
        }
        
        if(productInvField.getText() == null){
            return false;
        }
        try{
        productInv = Integer.parseInt(productInvField.getText());
        }catch (NumberFormatException nfe) {
            invalid.setContentText("Must be a valid integer number!");
            invalid.show();
            return false;
        }
        
        if(productMinField.getText() == null){
            return false;
        }
        try{
        productMin = Integer.parseInt(productMinField.getText());
        }catch (NumberFormatException nfe) {
            invalid.setContentText("Must be a valid integer number!");
            invalid.show();
            return false;
        }
        
        if(productMaxField.getText() == null){
            return false;
        }
        try{
        productMax = Integer.parseInt(productMaxField.getText());
        }catch (NumberFormatException nfe) {
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
    @FXML
    public void cancelButton(ActionEvent actionEvent){
        System.out.println("Cancel button has been clicked");
        Stage addProductStage = (Stage) cancelButton.getScene().getWindow();
        addProductStage.close();
    }
}
