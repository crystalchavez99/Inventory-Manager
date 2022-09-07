/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import Classes.InHouse;
import Classes.OutSourced;
import static java.lang.Character.isAlphabetic;
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
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Redfi
 */
public class UpdatePartInventoryFXMLController implements Initializable {

    @FXML
    private RadioButton inHouseButton;
    @FXML
    private RadioButton outSourceButton;
    @FXML
    private Label modifyPartLabel;
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
    private TextField partIdField;
    @FXML
    private TextField partNameField;
    @FXML
    private TextField partInvField;
    @FXML
    private TextField partCostField;
    @FXML
    private TextField partMaxField;
    @FXML
    private TextField differField;
    @FXML
    private Label partMinLabel;
    @FXML
    private TextField partMinField;
    @FXML
    private Button partSaveButton;
    @FXML
    private Button partCancelButton;
    @FXML
    private Label differLabel;
    
    private String partName;
    private int partMin;
    private int partMax;
    private double partCost;
    private int partInv;
    private int partMachineId;
    private String partCompanyName;
    private InHouse inHousePart = null;
    private OutSourced outSourcedPart = null;
    private int modIndex;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ToggleGroup group = new ToggleGroup();
        inHouseButton.setToggleGroup(group);
        outSourceButton.setToggleGroup(group);
        if(InventorySystemFXMLController.chosenPart.getClass() == InHouse.class){
            inHouseButton.setSelected(true);
            inHousePart = (InHouse) InventorySystemFXMLController.chosenPart;
        }else if(InventorySystemFXMLController.chosenPart.getClass() == OutSourced.class){
            outSourceButton.setSelected(true);
            outSourcedPart = (OutSourced) InventorySystemFXMLController.chosenPart;
        }
        displayRadio();
        modPartInfo();
        
    }    
    
    
    
    public boolean validation(){
        System.out.print("Entered valid method!");
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
            System.out.println("Max is" + partMax);
        }else{
            invalid.setContentText("Must be a valid integer number!");
            invalid.show();
            return false;
        }
        if(InventorySystemFXMLController.intValid(partMinField.getText())){
            partMin = Integer.parseInt(partMinField.getText());
            System.out.println("Min is" + partMin);
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
        }else if (outSourceButton.isSelected()) {
            partCompanyName = differField.getText();
            if(partCompanyName != ""){
                System.out.println("valid!" + partCompanyName);
            }else{
                invalid.setContentText("Must be a valid string!");
                invalid.show();
                return false; 
            }
        }
        return true;  
    }
    /**
     *
     * @param actionEvent
     */
    @FXML
    public void cancelButton(ActionEvent actionEvent){
        System.out.println("Cancel button has been clicked");
        Stage addPartStage = (Stage) partCancelButton.getScene().getWindow();
        addPartStage.close();
    }
    
    
    
    /**
     *
     * @param actionEvent
     */
    @FXML
    public void saveButton(ActionEvent actionEvent){
        if(validation()){
            modIndex = InventorySystemFXMLController.getPartIndex(InventorySystemFXMLController.chosenPart);
            int modPartId = Integer.parseInt(partIdField.getText());
            String modPartName = partNameField.getText();
            double modPartCost = Double.parseDouble(partCostField.getText());
            int modPartInv = Integer.parseInt(partInvField.getText());
            int modPartMax = Integer.parseInt(partMaxField.getText());
            int modPartMin = Integer.parseInt(partMinField.getText());
            if(inHouseButton.isSelected()){
                int modMachineId = Integer.parseInt(differField.getText());
                InHouse modPart = new InHouse(modPartId,modPartName,modPartCost,modPartInv,modPartMax, modPartMin,modMachineId  );
                InventorySystemFXMLController.updatePart(modIndex, modPart);
            }else{
                String modCompanyName = differField.getText();
                OutSourced modPart = new OutSourced(modPartId,modPartName,modPartCost,modPartInv,modPartMax, modPartMin,modCompanyName  );
                InventorySystemFXMLController.updatePart(modIndex, modPart);
            }
            Stage modPartStage = (Stage) partCancelButton.getScene().getWindow();
            modPartStage.close();
            
        }
        
    }
    /**
     *
     * @param actionEvent
     */
    @FXML
    public void displayRadio(){
        if(inHouseButton.isSelected()){
            differLabel.setText("Machine ID");
        }else{
            differLabel.setText("Company Name");
        }
    }
    
    public void modPartInfo(){
        if(inHousePart != null){
            partIdField.setText(String.valueOf(inHousePart.getId()));
            partNameField.setText(String.valueOf(inHousePart.getName()));
            partInvField.setText(String.valueOf(inHousePart.getStock()));
            partCostField.setText(String.valueOf(inHousePart.getPrice()));
            partMaxField.setText(String.valueOf(inHousePart.getMax()));
            partMinField.setText(String.valueOf(inHousePart.getMin()));
            differField.setText(String.valueOf(inHousePart.getMachineId()));
            
        }else if(outSourcedPart != null){
            partIdField.setText(String.valueOf(outSourcedPart.getId()));
            partNameField.setText(String.valueOf(outSourcedPart.getName()));
            partInvField.setText(String.valueOf(outSourcedPart.getStock()));
            partCostField.setText(String.valueOf(outSourcedPart.getPrice()));
            partMaxField.setText(String.valueOf(outSourcedPart.getMax()));
            partMinField.setText(String.valueOf(outSourcedPart.getMin()));
            differField.setText(String.valueOf(outSourcedPart.getCompanyName()));
        }
        System.out.println(partMaxField.getText() + " "+ partMinField.getText());
    }
}
