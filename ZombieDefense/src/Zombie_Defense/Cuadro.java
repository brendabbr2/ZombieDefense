package Zombie_Defense;


public class Cuadro {
    private final int x;
    private final int y;
    private final String tipo;
    
    public Cuadro(int x, int y, String tipo){
        this.x = x;
        this.y = y;
        this.tipo = tipo;
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getTipo() {
        return tipo;
    }
    
    
    
}

