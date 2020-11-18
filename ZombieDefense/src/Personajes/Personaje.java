package Personajes;

import Zombie_Defense.Tablero;

public class Personaje {
    private int x; //Coordenadas
    private int y;
    private final int vida;
    private int distancia;  //Espacios que se puede mover
    /*
    La vida empieza en 5 para todos 
    y puede seguir subiendo si tienen el item
    Muere al llegar a cero.
    */
    private int ataque;
    /* 
    El ataque empieza diferente dependiendo 
    del personaje. 
    Siendo 1 el más débil y 3 el más fuerte
        
       Atacante    Defensor
    1: Fantasma,    Soldado
    2: Vampiro,     Guerrero
    3: Zombie,      Caballero
    */

    private int defensa;
    /*
    La defensa resta al ataque que recibe
    1
    2
    * */

    public boolean movido = false;
    final private boolean es_Defensor; //Identificador entre personajes
    private String directorio;        //Lugar de imagen
    public Tablero tablero;           //Existencia en el tablero

    public Personaje(int x, int y, boolean es_Defensor, String directorio, Tablero tablero, int vida, int ataque, int distancia)
    {
        this.es_Defensor = es_Defensor;
        this.x = x;
        this.y = y;
        this.directorio = directorio;
        this.tablero = tablero;
        this.vida = vida;
        this.ataque = ataque;
        this.distancia = distancia;
    }
    
    /////////////////////////////////////////////Setters y getters
    public String getDirectorio()
    {
        return directorio;
    }
    public void setDirectorio(String directorio)
    {
        this.directorio = directorio;
    }
    public boolean esDefensor()
    {
        return es_Defensor;
    }
    public boolean esAtacante()
    {
        return !es_Defensor;
    }
    public void setX(int x)
    {
        this.x = x;
    }
    public void setY(int y)
    {
        this.y = y;
    }
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public void setMovido(boolean movido)
    {
        this.movido = movido;
    }

    public boolean getMovido()
    {
        return movido;
    }


    /////////////////////////////////////////////Metodos
    public boolean permitirMover(int posX, int posY)
    {
        return false;
    }
    
    public void mover(int x, int y)
    {
        this.setX(x);
        this.setY(y);
    }
    
}
