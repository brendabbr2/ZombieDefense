package Personajes;

import Zombie_Defense.Tablero;
import java.util.Random;

public class Atacante extends Personaje {
    
    public Atacante(int x, int y, boolean es_Defensor, String directorio, Tablero tablero, int vida, int ataque)
    {
        super(x,y,es_Defensor,directorio, tablero, vida, ataque);        
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
