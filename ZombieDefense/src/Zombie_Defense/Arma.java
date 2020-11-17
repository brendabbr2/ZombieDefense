/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Zombie_Defense;

import java.util.*;

public class Arma {
         
    private boolean tipoA; 
    /* Los dos tipos de items:
    
    Corto alcance: False  
        
        Espada:  Ruido 2
        Navaja:  Ruido 1
    
    Largo alcance: True       
    
        Arco:    Ruido 3
        Lanza:   Ruido 4
    */

    public Tablero tablero;
    private int ataque;
    
    
    //Setters y getters
    public Arma(Tablero tablero)
    {

        this.tablero = tablero;
    }
    
    public void setTipoA()
    {
        Random tipo = new Random(); //Se escoge una arma random
        this.tipoA = tipo.nextBoolean();
    }
    
    public boolean getTipoA()
    {
        return this.tipoA;
    }
    
    public void setAtaque() {
        //Si tipo = True entonces es una Espada
        if (this.tipoA)
        {
            this.ataque = 2;
        }
        else
        {
            this.ataque = 1; //Sino es arco y tiene ataque 1
        }
    }
    
    public int getAtaque() {
        return this.ataque;
    }

    
    

}
