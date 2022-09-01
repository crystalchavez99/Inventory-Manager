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
    private int machineId;
    
    public InHouse(int id, String name, double price, int stock, int min, int max,int machineId){
        super(id,name,price,stock,min,max);
        this.machineId = machineId;
    }
    
    public int getMachineId() {
        return machineId;
    }
    
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
}
