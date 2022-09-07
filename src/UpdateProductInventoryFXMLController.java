/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

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
    private TableView<?> productTable;
    @FXML
    private TableView<?> productAscTable;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Button deleteProductButton;
    @FXML
    private Button addProductButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void cancelButton(ActionEvent event) {
    }
    
}
