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
public class InHouse extends Part{
    /**
     @param id Part ID,
     @param name Part Name,
     @param price Part Price per part,
     @param stock Part Inventory amount,
     @param min Part min amount inventory,
     @param max Part max amount inventory,
     @param machineId id of machine
     */
    private int machineId;
    
    public InHouse(int id, String name, double price, int stock, int min, int max,int machineId){
        super(id,name,price,stock,min,max);
        this.machineId = machineId;
    }
    
    /**
     * 
      @return the machineId
     */
    public int getMachineId() {
        return machineId;
    }
    
    /**
     * @param machineId the machineId to set
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
}
