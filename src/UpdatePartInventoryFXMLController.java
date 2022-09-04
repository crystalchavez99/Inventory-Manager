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
    private InHouse inHousePart = null;
    private OutSourced outSourcedPart = null;
    private int modIndex;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("Init! " +InventorySystemFXMLController.chosenPart.getClass());
        System.out.println(InHouse.class);
        ToggleGroup group = new ToggleGroup();
        inHouseButton.setToggleGroup(group);
        outSourceButton.setToggleGroup(group);
        if(InventorySystemFXMLController.chosenPart.getClass() == InHouse.class){
            inHouseButton.setSelected(true);
            inHousePart = (InHouse) InventorySystemFXMLController.chosenPart;
        }else{
            outSourceButton.setSelected(true);
            outSourcedPart = (OutSourced) InventorySystemFXMLController.chosenPart;
        }
        displayRadio();
        modPartInfo();
        
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
                partMachineId = Integer.parseInt(differField.getText());
                System.out.println("valid!" + partMachineId);
            } catch (NumberFormatException nfe) {
                invalid.setContentText("Must be a valid integer number!");
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
            modIndex = InventorySystemFXMLController.allParts.indexOf(InventorySystemFXMLController.chosenPart);
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
    }
}
