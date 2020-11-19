package Personajes;

import Zombie_Defense.Cuadro;
import Zombie_Defense.Estructura;
import Zombie_Defense.Item;
import Zombie_Defense.Tablero;

import java.util.ArrayList;

public class Defensor extends Personaje {

    private int turno;
    
    public Defensor(int x, int y, boolean es_Defensor, String directorio, Tablero tablero)
    {
        super(x,y,true,directorio, tablero);
        agregarItem(2,3,this,"collar");
    }
    public void setTurno(int turno){
        this.turno=turno;
    }
    
    public int getTurno()
    {
        return this.turno;
    }

    public ArrayList<Item> items = new ArrayList<Item>();


    public ArrayList<Item> getItems() {
        return items;
    }

    public Item getItem (String tipo){
        for (int i = 0; i <= items.size(); i ++){
            if (((items.get(i)).getTipo()).equals(tipo))
            {
                return items.get(i);
            }
        }
        return null;
    }

    public void agregarItem(int x, int y, Defensor tablero, String tipo)
    {
        items.add(new Item(x, y, tablero, tipo));
    }

    public boolean permitirMover(int posX, int posY)
    {                
        Personaje personaje = tablero.getPiece(posX, posY);
        Cuadro cuadro = tablero.getCuadro(posX, posY);

        if (personaje != null)
        {
            return false;
        }
        if ((cuadro.getDirectorioC()).equals("Base.png") || (cuadro.getDirectorioC()).equals("tumba.png"))
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


        /*
        Hay tres opciones:
            El lugar clickeado es mayor al rango:
                    Reviso todas las casillas hasta llegar al rango
                    Porque no puedo ir mas alla de lo que ve el defensor
            El lugar clickeado es menor al rango:
                    Reviso todas las casillas hasta llegar al clickeado
                    Porque no me importa mas alla de lo que selecciono
            Ambos son el mismo:
                    Da igual

        Nota: int diferencia: El total de casillas a revisar
                              Resultado de la resta entre
                              donde me interesa ir y de donde estoy
        */

        if (posY > this.getY()) {  //Moverse abajo, o SUR

            int diferencia;

            diferencia = Math.abs(posY - this.getY());

            for (int i = 1; i < diferencia; i++) {
                Personaje p = tablero.getPiece(this.getX(), this.getY() + i);
                Cuadro c = tablero.getCuadro(this.getX(), this.getY() + i);
                if (diferencia > this.getDistancia()){
                    return false;
                }
                /*if ((c.getDirectorioC()).equals("tumba.png")){return true;}
                if (p != null){
                    if (p.esDefensor()) {
                        return false;
                    }else {return true;}
                }*/
            }
        }

        if (posY < this.getY()) {  //Moverse arriba, NORTE

            int diferencia;

            diferencia = Math.abs(posY - this.getY());

            for (int i = 1; i < diferencia; i++) {
                Personaje p = tablero.getPiece(this.getX(), this.getY() - i);
                Cuadro c = tablero.getCuadro(this.getX(), this.getY() - i);
                if (diferencia > this.getDistancia()){
                    return false;
                }
              
            }
        }

        if (posX < this.getX()) {  //Moverse izquierda, OESTE

            int diferencia;

            diferencia = Math.abs(posX - this.getX());

            for (int i = 1; i < diferencia; i++) {
                Personaje p = tablero.getPiece(this.getX() - i, this.getY());
                Cuadro c = tablero.getCuadro(this.getX() -i, this.getY());
                if (diferencia > this.getDistancia()){
                    return false;
                }
            }
        }

        if (posX > this.getX()){   //Moverse derecha, ESTE

            int diferencia;

            diferencia = Math.abs(posX - this.getX());

            for (int i = 1; i < diferencia; i++) {
                Personaje p = tablero.getPiece(this.getX() + i, this.getY());
                Cuadro c = tablero.getCuadro(this.getX() + i, this.getY());
                if (diferencia > this.getDistancia()){
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

