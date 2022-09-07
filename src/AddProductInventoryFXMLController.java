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
 * @author Redfi
 */
public class AddProductInventoryFXMLController implements Initializable {

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
    @FXML
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
    @FXML
    private AnchorPane addIdLabel;
    @FXML
    private TextField productSearchField;
    
    /**
     * product table
     */
    public  TableView productTable;
    /**
     * product associated table
     */
    public TableView<Part> productAscTable;
    /**
     * button to delete
     *
     */
    @FXML
    private Button deleteProductButton;
    /**
     * button to add
     */
    @FXML
    private Button addProductButton;
    /**
     * table column for id
     */
    @FXML
    private TableColumn partIdCol;
    /**
     *  table column for name
     */
    @FXML
    private TableColumn partNameCol;
    @FXML
    private TableColumn partInvCol;
    @FXML
    private TableColumn partCostCol;
    @FXML
    private TableColumn apartIdCol;
    @FXML
    private TableColumn apartNameCol;
    @FXML
    private TableColumn apartInvCol;
    @FXML
    private TableColumn apartCostCol;
    private Product temp;
    private Part chosenPart = null;
    private Part chosenAscPart = null;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        int id = InventorySystemFXMLController.partId;
        productIdFIeld.setText(Integer.toString(id));
        temp = new Product(0,"",0.0,0,0,0);
        productTable.setItems(InventorySystemFXMLController.getAllParts());
        productAscTable.setItems(temp.getAllAssociatedParts());
        
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partCostCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        apartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        apartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        apartInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        apartCostCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
    }    
    
    /**
     * represents current part we have clicked each time
     */
    public void selectedPart(){
        if(productTable != null){
            chosenPart = (Part) productTable.getSelectionModel().getSelectedItem();
        }
    }
    
    /**
     * represents current part we have clicked each time
     */
    public void selectedAscPart(){
        if(productAscTable != null){
            chosenAscPart = (Part) productAscTable.getSelectionModel().getSelectedItem();
        }
    }
    
    /**
     * Checks whether the input of each field is valid
     * @return true or false
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
     * @param actionEvent 
     * add product to associated part
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
     * @param actionEvent 
     * closes the window without making changes
     */
    @FXML
    public void cancelButton(ActionEvent actionEvent){
        System.out.println("Cancel button has been clicked");
        Stage addProductStage = (Stage) cancelButton.getScene().getWindow();
        addProductStage.close();
    }
    
    /**
     * 
     * @param actionEvent 
     * removes associated part clicked on
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
     * @param actionEvent 
     * the input we filled to create a product is then saved
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
            InventorySystemFXMLController.addProduct(temp);
            InventorySystemFXMLController.increaseId(1);
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        }
    }
    
    
    /**
     * 
     * @param keyEvent 
     * allows for text field to be a search bar
     */
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
     * @param mouseEvent 
     * allows to click on table 
     */
    @FXML
    public void onProductTableClick(MouseEvent mouseEvent){
        selectedPart();
    }
    
    /**
     * 
     * @param mouseEvent 
     * allows to click on table 
     */
    @FXML
    public void onProductAscTableClick(MouseEvent mouseEvent){
        selectedAscPart();
    }
    
}


