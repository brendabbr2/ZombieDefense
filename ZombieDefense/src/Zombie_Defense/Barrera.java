
package Zombie_Defense;


public class Barrera extends Cuadro{
    
    private int intensidad;
    private String directorio;
    /* 
    Intensidad: Que tan fuerte es la barrera
    1: Debil
    2: Intemerdio
    3: Fuerte
    */
    public Barrera(int x, int y, String tipo)
    {
        super(x, y, "Barrera.png");
    }

    public void setIntensidad(int intensidad) {
        this.intensidad = intensidad;
    }

    public void setDirectorio(String directorio) { this.directorio = directorio; }

    public String getDirectorio() {
        return directorio;
    }
    
    public int getIntensidad() {
        return intensidad;
    }
    
    
    
}
