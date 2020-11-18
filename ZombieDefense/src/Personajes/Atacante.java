package Personajes;

import Zombie_Defense.Tablero;
import java.util.Random;

public class Atacante extends Personaje {

    public boolean drop;
    /*
    * El atacante droppea o un item o un arma
    * Item = True
    * Arma = False
    * Se escoge al azar
    * */

    public Atacante(int x, int y, boolean es_Defensor, String directorio, Tablero tablero)
    {
        super(x,y,es_Defensor,directorio, tablero);
    }

    public void setDrop()
        /*Creamos un tipo de la clase random
        * este toma el valor booleano al azar*/
    {
        Random tipo = new Random();
        this.drop = tipo.nextBoolean();
    }

    public boolean isDrop() {
        return drop;
    }

    @Override
    public boolean permitirMover(int posX, int posY)
    {
        return false;
    }   
    
    @Override
    public void mover(int x, int y)
    {
        this.setX(x);
        this.setY(y);
    }
    
}
