package Classes;


import Classes.Part;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Crystal Chavez
 */
public class OutSourced extends Part{
    /**
     * differ variable to for company name when outsourced part
     */
    private String companyName;
    
    /**
     @param id Part ID,
     @param name Part Name,
     @param price Part Price per part,
     @param stock Part Inventory amount,
     @param min Part min amount inventory,
     @param max Part max amount inventory,
     @param companyName name of company
     */
    public OutSourced(int id, String name, double price, int stock, int min, int max, String companyName){
        super(id,name,price,stock,min,max);
        this.companyName = companyName;
    }
    
    /**
     * 
     * @return the companyName
     */
    public String getCompanyName() {
        return companyName;
    }
    
    /**
     * @param companyName the companyName to set
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
