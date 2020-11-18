
package Zombie_Defense;

public class Tumba extends Cuadro {
    
    private final String directorio;

    public Tumba(String directorio, int x, int y, String tipo) {
        super(x, y, "tumba");
        this.directorio = directorio;
    }

    public String getDirectorio() {
        return "tumba";
    }


    
    
    
}
