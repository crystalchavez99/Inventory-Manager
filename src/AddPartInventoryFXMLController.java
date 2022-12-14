/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import Classes.InHouse;
import Classes.OutSourced;
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
    private String partCompanyName;
    @FXML
    private TextField differField;
    @FXML
    private Label differLabel;
    /**
     Initializes the controller class.
     set up for fxml controller
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
        int id = InventorySystemFXMLController.partId;
        partIdField.setText(Integer.toString(id));
        partIdField.setEditable(false);
    }    
    
    /**
     *
     * @return true or false if input for each field is valid input
     */
    public boolean validation(){
        Alert invalid = new Alert(Alert.AlertType.WARNING);
        if(InventorySystemFXMLController.stringValid(partNameField.getText())){
            partName = partNameField.getText();
        }else{
            invalid.setContentText("Name must start with a letter!");
            invalid.show();
            return false;
        }
        if(InventorySystemFXMLController.doubleValid(partCostField.getText())){
            partCost = Double.parseDouble(partCostField.getText());
        }else{
            invalid.setContentText("Must be a valid decimal number!");
            invalid.show();
            return false;
        }
        if(InventorySystemFXMLController.intValid(partInvField.getText())){
            partInv = Integer.parseInt(partInvField.getText());
        }else{
            invalid.setContentText("Must be a valid integer number!");
            invalid.show();
            return false;
        }
        if(InventorySystemFXMLController.intValid(partMaxField.getText())){
            partMax = Integer.parseInt(partMaxField.getText());
        }else{
            invalid.setContentText("Must be a valid integer number!");
            invalid.show();
            return false;
        }
        if(InventorySystemFXMLController.intValid(partMinField.getText())){
            partMin = Integer.parseInt(partMinField.getText());
        }else{
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
                partMachineId = Integer.parseInt(differField.getText());
                System.out.println("valid!" + partMachineId);
            } catch (NumberFormatException nfe) {
                invalid.setContentText("Must be a valid integer number!");
                invalid.show();
                return false;
            }
        }else if(outSourceButton.isSelected()){
            partCompanyName = differField.getText();
            if(partCompanyName != ""){
                System.out.println("valid!" + partCompanyName);
            }else{
                invalid.setContentText("Must be a valid string!");
                invalid.show();
                return false; 
            }
//            try{
//                partCompanyName = differField.getText();
//                System.out.println("valid!" + partCompanyName);
//            }catch(Exception e){
//               invalid.setContentText("Must be a valid string!");
//                invalid.show();
//                return false; 
//            }
        }
        return true;  
    }
    
    /**
     *
     @param actionEvent
     closes window when cancel is clicked
     */
    @FXML
    public void cancelButton(ActionEvent actionEvent){
        System.out.println("Cancel button has been clicked");
        Stage addPartStage = (Stage) partCancelButton.getScene().getWindow();
        addPartStage.close();
    }
    
    /**
     *
     @param actionEvent
     add part of allParts, id is incremented and window closed
     */
    @FXML
    public void saveButton(ActionEvent actionEvent){
        System.out.println("Save button has been clicked");
        boolean inHousePart = inHouseButton.isSelected();
        boolean outSourcedPart = outSourceButton.isSelected();
        System.out.println("inHousePart " + inHousePart);
        int id = InventorySystemFXMLController.partId;
        System.out.println("id " + id);
        //System.out.print(validation());
        if(validation()){
            System.out.println("Passed valid");
            if(inHousePart){
                InventorySystemFXMLController.addPart(new InHouse(id,partName,partCost,partInv,partMin,partMax,partMachineId));
            }else if(outSourcedPart){
                InventorySystemFXMLController.addPart(new OutSourced(id,partName,partCost,partInv,partMin,partMax,partCompanyName));
            }
            InventorySystemFXMLController.increaseId(0);
            System.out.println(id);
            Stage addPartStage = (Stage) partCancelButton.getScene().getWindow();
            addPartStage.close();
        }else{
            System.out.println("Invalid");
        }
        
    }
    
    /**
     *
     * @param actionEvent
     * allows user to switch part inhouse or outsourced
     */
    @FXML
    public void displayRadio(ActionEvent actionEvent){
        if(inHouseButton.isSelected()){
            differLabel.setText("Machine ID");
        }else{
            differLabel.setText("Company Name");
        }
    }
    
}
