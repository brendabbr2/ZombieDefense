package Zombie_Defense;

public class Juego {
    
    public Estructura estructura;
    public static void main(String[] args) {
        Juego GUI = new Juego();
        GUI.estructura = new Estructura();
        GUI.estructura.setVisible(true);
        
    }
}
