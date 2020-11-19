package Zombie_Defense;


public class Cuadro {
    private final int x;
    private final int y;
    private String directorioC;
    /*Hay tres tipos:
    Barrera:
    Tumba: Spawning Points
    Vacio
    * */
    
    public Cuadro(int x, int y, String directorioC){
        this.x = x;
        this.y = y;
        this.directorioC = directorioC;
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getDirectorioC() {

        return directorioC;
    }

    public void setDirectorioC(String directorioC) {
        this.directorioC = directorioC;
    }
}

