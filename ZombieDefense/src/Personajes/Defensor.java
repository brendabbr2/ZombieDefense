package Personajes;

import Zombie_Defense.Cuadro;
import Zombie_Defense.Tablero;

public class Defensor extends Personaje {

    private int turno;
    
    public Defensor(int x, int y, boolean es_Defensor, String directorio, Tablero tablero, int vida, int ataque, int distancia)
    {
        super(x,y,true,directorio, tablero, vida, ataque, distancia);

        
    }
    public void setTurno(int turno){
        this.turno=turno;
    }
    
    public int getTurno()
    {
        return this.turno;
    }

    @Override
    public boolean permitirMover(int posX, int posY)
    {                
        Personaje personaje = tablero.getPiece(posX, posY);
        Cuadro cuadro = tablero.getCuadro(posX, posY);

        if (personaje != null)
        {
            return false;
        }
        if ((cuadro.getTipo()).equals("Base"))
        {
            return false;
        }

        if (this.getX() != posX && this.getY() != posY)
        {
            return false;
        }

        String direccion;
        if (posY > this.getY()){direccion = "sur";}
        if (posY < this.getY()){direccion = "norte";}
        if (posX > this.getX()){direccion = "este";}
        if (posX < this.getX()){direccion = "oeste";}


        if (posY > this.getY()){  //Moverse abajo, o SUR
            int diferencia = Math.abs(posY - this.getY());
            if (diferencia != this.getDistancia()){return false;}
            for (int i = 1; i< diferencia; i++){
                Personaje p = tablero.getPiece(this.getX(), this.getY() + i);

                if (p != null){
                    return false;
                }


            }
        }

        if (posY < this.getY())   //Moverse arriba, NORTE
        {
            int diferencia = Math.abs(posY - this.getY());
            if (diferencia != this.getDistancia()){return false;}
            for (int i = 1; i< diferencia; i++){
                Personaje p = tablero.getPiece(this.getX(), this.getY() - i);

                if (p != null){
                    return false;
                }
            }
        }

        if (posX < this.getX())   //Moverse izquierda, OESTE
        {
            int diferencia = Math.abs(posX - this.getX());

            if (diferencia != this.getDistancia()){return false;}

            for (int i = 1; i< diferencia; i++){
                Personaje p = tablero.getPiece(this.getX() - i, this.getY());

                if (p != null){
                    return false;
                }
            }
        }

        if (posX > this.getX())   //Moverse derecha, ESTE
        {
            int diferencia = Math.abs(posX - this.getX());
            if (diferencia != this.getDistancia()){return false;}
            for (int i = 1; i< diferencia; i++){
                Personaje p = tablero.getPiece(this.getX() + i, this.getY());

                if (p != null){
                    return false;
                }
            }
        }

        return true;
    }
    
    @Override
    public void mover(int x, int y)
    {
        this.setX(x);
        this.setY(y);
    }
    
}
