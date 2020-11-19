package Zombie_Defense;

public class Main {
    
    public Estructura estructura;
    public Tablero tablero;

    public static void main(String[] args) {
        Main GUI = new Main();
        GUI.estructura = new Estructura();
        GUI.estructura.setVisible(true);
    }
}
