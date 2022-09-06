package Classes;


import Classes.Part;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Crystal Chavez
 */
/**
 * 
 * Product class used to represent data for Products and associatedParts
 */
public class Product {
    private ObservableList <Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;    
    // Constructor for Product, super similar to Part Class!
    /**
     @param id Product ID,
     @param name Product Name,
     @param price Product Price per part,
     @param stock Product Inventory amount,
     @param min Product min amount inventory,
     @param max Product max amount inventory,
     */
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }
    
    /**
     * @return the stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * @param stock the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * @return the min
     */
    public int getMin() {
        return min;
    }

    /**
     * @param min the min to set
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * @return the max
     */
    public int getMax() {
        return max;
    }

    /**
     * @param max the max to set
     */
    public void setMax(int max) {
        this.max = max;
    }
    
    /**
     * 
     * @param part 
     * add Parts to associatedParts
     */
    public void addAssociatedPart(Part part){
        associatedParts.add(part);
    }
    
    
    /**
     * Removes selectedAssociatedPart from associatedParts
     * @param selectedAssociatedPart
     * @return true if selectedAssociatedPart is removed
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart){
        return associatedParts.remove(selectedAssociatedPart);
    }
    
    
    /**
     * Getter method for the associatedParts
     * @return list
     */
    public ObservableList <Part> getAllAssociatedParts(){
        return associatedParts;
    }
}
