
package Zombie_Defense;
//Clase con los items que se pueden obtener
//Una vez que matan a un atacante

import Personajes.Defensor;

import java.util.concurrent.ThreadLocalRandom;

public class Item {
    
    public Defensor tablero;
    private String tipo; 
    /* Los tres tipos de items:
        
        Collar: Aumenta en 1 la vida
        Pocion: Aumenta en 2 la vida
        Flecha: Aumenta el ataque en 1
        */
    
    private int x;   //Coordenadas
    private int y;
    
    
    //Setters y getters
    public Item(int x, int y, Defensor tablero, String tipo)
    {
        this.x = x;
        this.y = y;
        this.tablero = tablero;
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo){
        this.tipo = tipo;
    }
    /*public void setTipo() {
        
        int num = ThreadLocalRandom.current().nextInt(1, 4);
        //Escogemos el item random
        if (num == 1) {
            this.tipo = "Collar";
        } else if (num == 2){
            this.tipo = "Flecha";
        } else {
            this.tipo = "Pocion";
        }
    }*/

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
}
