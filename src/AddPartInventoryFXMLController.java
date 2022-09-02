/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import Classes.InHouse;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import static java.lang.Character.isAlphabetic;
import javafx.scene.control.ToggleGroup;
/**
 * FXML Controller class
 *
 * @author Redfi
 */
public class AddPartInventoryFXMLController implements Initializable {
    @FXML
    private RadioButton inHouseButton;
    @FXML
    private RadioButton outSourceButton;
    @FXML
    private Label addPartLabel;
    @FXML
    private AnchorPane addIdLabel;
    @FXML
    private Label partIdLabel;
    @FXML
    private Label partNameLabel;
    @FXML
    private Label partInvLabel;
    @FXML
    private Label partCostLabel;
    @FXML
    private Label partMaxLabel;
    @FXML
    private Label partMinLabel;
    @FXML
    private Button partSaveButton;
    @FXML
    private Button partCancelButton;
    @FXML
    private TextField partIdField;
    @FXML
    private TextField partNameField;
    @FXML
    private TextField partInvField;
    @FXML
    private TextField partCostField;
    @FXML
    private TextField partMaxField;
    private TextField partMachIdField;
    @FXML
    private TextField partMinField;
    
    private String partName;
    private int partMin;
    private int partMax;
    private double partCost;
    private int partInv;
    private int partMachineId;
    @FXML
    private TextField differField;
    @FXML
    private Label differLabel;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ToggleGroup group = new ToggleGroup();
        inHouseButton.setToggleGroup(group);
        outSourceButton.setToggleGroup(group);
        inHouseButton.setSelected(true);
        if(inHouseButton.isSelected()){
            differLabel.setText("Machine ID");
        }
    }    
    
    public boolean validation(){
        System.out.print("Entered valid method!");
        Alert invalid = new Alert(Alert.AlertType.WARNING);
        System.out.println(partNameField.getText());
        if(partNameField.getText() == ""){
            System.out.println("Entered invalid name method!");
            invalid.setContentText("Must start with a letter!");
            invalid.show();
            return false;
        }
        if(isAlphabetic(partNameField.getText().charAt(0))){
            partName = partNameField.getText();
            System.out.println("valid!" + partName);
        }else{
            invalid.setContentText("Must start with a letter!");
            invalid.show();
            return false;
        }
        
        if(partCostField.getText() == null){
            return false;
        }
        try{
        partCost = Double.parseDouble(partCostField.getText());
        }catch (NumberFormatException nfe) {
            invalid.setContentText("Must be a valid decimal number!");
            invalid.show();
            return false;
        }
        
        if(partInvField.getText() == null){
            return false;
        }
        try{
        partInv = Integer.parseInt(partInvField.getText());
        }catch (NumberFormatException nfe) {
            invalid.setContentText("Must be a valid integer number!");
            invalid.show();
            return false;
        }
        
        if(partMinField.getText() == null){
            return false;
        }
        try{
        partMin = Integer.parseInt(partMinField.getText());
        }catch (NumberFormatException nfe) {
            invalid.setContentText("Must be a valid integer number!");
            invalid.show();
            return false;
        }
        
        if(partMaxField.getText() == null){
            return false;
        }
        try{
        partMax = Integer.parseInt(partMaxField.getText());
        }catch (NumberFormatException nfe) {
            invalid.setContentText("Must be a valid integer number!");
            invalid.show();
            return false;
        }
        
        if(partMin > partMax){
            invalid.setContentText("Max must be greater than min!");
            invalid.show();
            return false;
        }
        
        if(partInv > partMax || partInv < partMin){
            invalid.setContentText("Inventory must be between min/max!");
            invalid.show();
            return false;
        }
        
        if (inHouseButton.isSelected()) {
            try{
                partMachineId = Integer.parseInt(partMachIdField.getText());
                System.out.println("valid!" + partMachineId);
            } catch (NumberFormatException nfe) {
                invalid.setContentText("Must be a valid integer number!");
                invalid.show();
                return false;
            }
        }
        return true;  
    }
    
    @FXML
    public void cancelButton(ActionEvent actionEvent){
        System.out.println("Cancel button has been clicked");
        Stage addPartStage = (Stage) partCancelButton.getScene().getWindow();
        addPartStage.close();
    }
    
    @FXML
    public void saveButton(ActionEvent actionEvent){
        System.out.println("Save button has been clicked");
        boolean inHousePart = inHouseButton.isSelected();
        System.out.println("inHousePart " + inHousePart);
        int id = InventorySystemFXMLController.partId;
        System.out.println("id " + id);
        //System.out.print(validation());
        if(validation()){
            System.out.println("Passed valid");
            if(inHousePart){
                InventorySystemFXMLController.addPart(new InHouse(id,partName,partCost,partInv,partMin,partMax,partMachineId));
            }
            InventorySystemFXMLController.partId++;
            System.out.println(id);
            Stage addPartStage = (Stage) partCancelButton.getScene().getWindow();
            addPartStage.close();
        }else{
            System.out.println("Invalid");
        }
        
    }
    
    @FXML
    public void displayRadio(ActionEvent actionEvent){
        if(inHouseButton.isSelected()){
            differLabel.setText("Machine ID");
        }else{
            differLabel.setText("Company Name");
        }
    }
    
}
