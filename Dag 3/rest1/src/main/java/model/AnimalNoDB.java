/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Rasmus
 */
public class AnimalNoDB {
    
    private String Type;
    private String Sound;

    public AnimalNoDB() {
    }

    public AnimalNoDB(String Type, String Sound) {
        this.Type = Type;
        this.Sound = Sound;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getSound() {
        return Sound;
    }

    public void setSound(String Sound) {
        this.Sound = Sound;
    }
    
    
}
