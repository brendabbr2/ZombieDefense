package Personajes;

import Zombie_Defense.Tablero;
import java.util.Random;

public class Atacante extends Personaje {

    private int vision; //Espacios que pueden ver
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
        setVision();
        setDrop();
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

    public int getVision() {
        return vision;
    }

    public void setVision() {
        if ((this.getDirectorio()).equals("Fantasma.png")){ this.vision = 3;}
        else if ((this.getDirectorio()).equals("Vampiro.png")){this.vision = 2;}
        else if ((this.getDirectorio()).equals("Zombie.png")){this.vision = 1;}
        else if ((this.getDirectorio()).equals("Caballero.png")){this.vision = 2;}
        else if ((this.getDirectorio()).equals("Soldado.png")){this.vision = 1;}
        else{this.vision = 3;} //Guerreros

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
