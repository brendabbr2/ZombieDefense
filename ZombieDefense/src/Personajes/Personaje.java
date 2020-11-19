package Personajes;

import Zombie_Defense.Estructura;
import Zombie_Defense.Tablero;

import java.util.ArrayList;
import java.util.stream.Stream;

public class Personaje extends ArrayList<Personaje> {
    private int x; //Coordenadas
    private int y;
    private int vida;
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

    public Personaje(int x, int y, boolean es_Defensor, String directorio, Tablero tablero) {
        this.x = x;
        this.y = y;
        this.es_Defensor = es_Defensor;
        this.directorio = directorio;
        this.tablero = tablero;
        this.vida = 5;
        setDistancia();
        setAtaque();
        setDefensa();

    }

    /////////////////////////////////////////////Setters y getters
    public String getDirectorio() {
        return directorio;
    }

    public void setDirectorio(String directorio) {
        this.directorio = directorio;
    }

    public boolean esDefensor() {
        return es_Defensor;
    }

    public boolean esAtacante() {
        return !es_Defensor;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public int getDefensa() {
        return defensa;
    }

    public void setDefensa() {
        if ((this.directorio).equals("Fantasma.png")) {
            this.defensa = 3;
        } else if ((this.directorio).equals("Vampiro.png")) {
            this.defensa = 2;
        } else if ((this.directorio).equals("Zombie.png")) {
            this.defensa = 1;
        } else if ((this.directorio).equals("Caballero.png")) {
            this.defensa = 3;
        } else if ((this.directorio).equals("Soldado.png")) {
            this.defensa = 1;
        } else {
            this.defensa = 2;
        } //Guerreros
    }


    public int getDistancia() {
        return this.distancia;
    }

    public void setDistancia() {
        if ((this.directorio).equals("Fantasma.png")) {
            this.distancia = 1;
        } else if ((this.directorio).equals("Vampiro.png")) {
            this.distancia = 2;
        } else if ((this.directorio).equals("Zombie.png")) {
            this.distancia = 3;
        } else if ((this.directorio).equals("Caballero.png")) {
            this.distancia = 2;
        } else if ((this.directorio).equals("Soldado.png")) {
            this.distancia = 3;
        } else {
            this.distancia = 1;
        } //Guerreros

    }

    public void setMovido(boolean movido) {
        this.movido = movido;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;

    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque() {
        if ((this.directorio).equals("Fantasma.png")) {
            this.ataque = 3;
        } else if ((this.directorio).equals("Vampiro.png")) {
            this.ataque = 2;
        } else if ((this.directorio).equals("Zombie.png")) {
            this.ataque = 1;
        } else if ((this.directorio).equals("Caballero.png")) {
            this.ataque = 3;
        } else if ((this.directorio).equals("Soldado.png")) {
            this.ataque = 1;
        } else {
            this.ataque = 2;
        } //Guerreros

    }


    public boolean getMovido() {
        return movido;
    }

    /////////////////////////////////////////////Metodos
    public boolean permitirMover(int posX, int posY) {
        return true;
    }

    public void mover(int x, int y) {
        this.setX(x);
        this.setY(y);
    }


    @Override
    public Stream<Personaje> stream() {
        return null;
    }
}