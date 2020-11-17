package Personajes;

import Zombie_Defense.Tablero;

public class Defensor extends Personaje {

    private boolean movido;
    private int turno;
    
    public Defensor(int x, int y, boolean es_Defensor, String directorio, Tablero tablero, int vida, int ataque)
    {
        super(x,y,true,directorio, tablero, vida, ataque);
        movido = false;
        
    }
    
    public void setTurno(int turno){
        this.turno=turno;
    }
    
    public int getTurno()
    {
        return this.turno;
    }
    
    public void setHasMoved(boolean movido)
    {
        this.movido = movido;
    }
    
    public boolean getHasMoved()
    {
        return movido;
    }
    
    @Override
    public boolean permitirMover(int posX, int posY)
    {                
        return true;
    }
    
    @Override
    public void mover(int x, int y)
    {
        this.setX(x);
        this.setY(y);
    }
    
}
