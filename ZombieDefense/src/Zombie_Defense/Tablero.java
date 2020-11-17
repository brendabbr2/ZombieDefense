package Zombie_Defense;

import Personajes.Defensor;
import Personajes.Atacante;
import Personajes.Personaje;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;

                                 ///La GUI en otras palabras//

@SuppressWarnings("serial")
public class Tablero extends JComponent {
    
    
    public int contadorTurno = 1;  //Contador de turnos. Cada 4 no juega 
    
    //Creamos a NULL_IMAGE porque se debe retornar mucho en las funciones con imagenes
    private static final Image NULL_IMAGE = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);

    private final int Cuadro_ancho = 65;    //El tama;o de cada cuadro es 65x65
    public ArrayList<Personaje> Defensores; //Lista de defensores (nosotros)
    public ArrayList<Personaje> Atacantes; //Lista de atacantes (zombies)
    
    public ArrayList<DrawingShape> Inmoviles;  //Imagenes que no interactuan con el usuario: tablero 
    public ArrayList<DrawingShape> Moviles;    //Imagenes de los personajes

    public Personaje personajeActual;            //Personaje clickeado

    private final int filas = 8;                
    private final int columnas = 8;
    
    public ArrayList<Cuadro> Cuadros;       //Lista de los cuadros
    
    private final Integer[][] pieza;              //Cada cuadro del tablero
    private final String tablero_directorio = "imágenes" + File.separator + "tablero.png";

    public void crearCuadros()
    //Añade en las listas los cuadros y personajes iniciales
    {
        for (int i = 0; i < filas; i++) //De acuerdo al numero de 
                                        //Filas y columnas
        {
            for (int j = 0; j < columnas; j++)
            {
                pieza[i][j] = 0;
                Cuadros.add(new Cuadro(i, j, "vacio"));
            }
        }

        //Parametros:   int x, int y, boolean esDefensor, String direccion, Tablero tablero, int vida, int ataque//
        Defensores.add(new Defensor(3,1,true,"Guerrero.png",this, 5, 2));
        Defensores.add(new Defensor(4,1,true,"Caballero.png",this, 5, 3));
        Defensores.add(new Defensor(5,1,true,"Soldado.png",this, 5, 1));


        Atacantes.add(new Atacante(5,7,false,"Fantasma.png",this, 5, 1));
        Atacantes.add(new Atacante(1,7,false,"Vampiro.png",this, 5, 2));
        Atacantes.add(new Atacante(6,7,false,"Zombie.png",this, 5, 3));
   

    }

    public Tablero() {

        //Constructor del tablero
        pieza = new Integer[filas][columnas];
        Inmoviles = new ArrayList();
        Moviles = new ArrayList();
        Defensores = new ArrayList();
        Atacantes = new ArrayList();
        Cuadros = new ArrayList();
        crearCuadros();
        
        //Dimensiones
        this.setBackground(new Color(37,13,84));
        this.setPreferredSize(new Dimension(520, 520));
        this.setMinimumSize(new Dimension(100, 100));
        this.setMaximumSize(new Dimension(1000, 1000));

        //Mouse
        this.addMouseListener(mouseAdapter);
        this.addComponentListener(componentAdapter);
        this.addKeyListener(keyAdapter);
   
        //Crear el tablero
        this.setVisible(true);
        this.requestFocus();
        crearTablero();
    }

    private void crearTablero()
    {
        //Asignamos a los cuadros los ocupantes
        
        
      
        Moviles.clear();
        Inmoviles.clear();
        
        Image imgTablero = cargarImagen(tablero_directorio);
        Inmoviles.add(new DrawingImage(imgTablero, new Rectangle2D.Double(0, 0, imgTablero.getWidth(null), imgTablero.getHeight(null))));

        
        //Se activa el cuadro rojo con un click.
        if (personajeActual != null)  //Si clickeamos al jugador
                                     //O donde esta clickeado es un jugador
        {
            Image active_square = cargarImagen("imágenes" + File.separator + "activo.png");
            Inmoviles.add(new DrawingImage(active_square, new Rectangle2D.Double(Cuadro_ancho*personajeActual.getX(),Cuadro_ancho*personajeActual.getY(), active_square.getWidth(null), active_square.getHeight(null))));
        }
        //Se cargan los defensores, tantos como el tamaño de la lista
        for (int i = 0; i < Defensores.size(); i++)
        {
            int COL = Defensores.get(i).getX();
            int ROW = Defensores.get(i).getY();
            Image piece = cargarImagen("imágenes" + File.separator + "defensores" + File.separator + Defensores.get(i).getDirectorio());  
            Moviles.add(new DrawingImage(piece, new Rectangle2D.Double(Cuadro_ancho*COL,Cuadro_ancho*ROW, piece.getWidth(null), piece.getHeight(null))));
        }
        //Lo mismo con los atacantes
        for (int i = 0; i < Atacantes.size(); i++)
        {
            int COL = Atacantes.get(i).getX();
            int ROW = Atacantes.get(i).getY();
            Image piece = cargarImagen("imágenes" + File.separator + "atacantes" + File.separator + Atacantes.get(i).getDirectorio());  
            Moviles.add(new DrawingImage(piece, new Rectangle2D.Double(Cuadro_ancho*COL,Cuadro_ancho*ROW, piece.getWidth(null), piece.getHeight(null))));
        }
        this.repaint();
    }

    
    public Personaje getPiece(int x, int y) {
        //Buscar si quien esta en el cuadro
        //es un defensor o atacante
        //Recorre ambas listas y busca que las coordenadas coincidan
        for (Personaje p : Defensores)
        {
            if (p.getX() == x && p.getY() == y)
            {
                return p;
            }
        }
        for (Personaje p : Atacantes)
        {
            if (p.getX() == x && p.getY() == y)
            {
                return p;
            }
        }
        return null;
    }
    
    

    /////////////////////EL MOUSE/////////////////
    
    private final MouseAdapter mouseAdapter = new MouseAdapter() {

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            int d_X = e.getX();
            int d_Y = e.getY();  
            int Clicked_Row = d_Y / Cuadro_ancho;   //Coordenadas de:
            int Clicked_Column = d_X / Cuadro_ancho;//El cuadro clickeado
                                                    //En espa;ol se ve feo
            boolean en_Turno = true; //Si es nuestro turno de jugar
            
            //Cada cuatro turnos se mueven los zombies
            //En otras palabras: no jugamos nosotros
            if (contadorTurno%4 == 0 && contadorTurno != 0)
            {
                en_Turno = false;
            }
              
            
                                        //Aquí es cuando el usuario clickea una pieza.
            
            //Primero ocupamos saber quien es el personaje (o si hay)
            Personaje clicked_piece = getPiece(Clicked_Column, Clicked_Row);
            
            /*
            Las acciones a realizar dependen de:
            
            A quién tenemos clickeado: personajeActual
            Y a quién acabamos de clickear: clicked_piece
            
            Además de si estamos en nuestro turno o no
            */
            if (personajeActual == null && clicked_piece != null && 
            //Si no hay personaje asigando (clickeado) y clickeamos algo y 
            //es nuestro turno y la pieza clickeada es el defensor       
           //...O no es nuestro turno y la pieza clickeada es atacante
                    ((en_Turno && clicked_piece.esDefensor()) || (!en_Turno && clicked_piece.esAtacante())))
            {
                personajeActual = clicked_piece; //Entonces la pieza clickeada es nuestro personaje
                                                  
            }
            else if (personajeActual != null && personajeActual.getX() == Clicked_Column && personajeActual.getY() == Clicked_Row)
            //Si ya hay un personaje clickeado, y ese personaje coincide con la pieza clickeada
            {
                //Es un doble click, entonces volvemos a lo mismo. No hay nada
                personajeActual = null;
            }
            else if (personajeActual != null && personajeActual.permitirMover(Clicked_Column, Clicked_Row) 
            //Si hay un personaje clickeado, y ese personaje se puede mover (no hay obstaculos)
            //y es nuestro turno y ese personaje clickeado es un defensor
            //O si no es nuestro turno y el turno es del atacante
                    //Eso significa peligro
                    && ((en_Turno && personajeActual.esDefensor()) || (!en_Turno && personajeActual.esAtacante())))
            {
                // Si clickeamos una pieza
                if (clicked_piece != null)
                {
                    //Y esta pieza es defensor
                    if (clicked_piece.esDefensor())
                    {
                        //Eliminamos a nuestro compa;ero
                        Defensores.remove(clicked_piece);
                    }
                    else
                    {
                        //Eliminamos al enemigo
                        Atacantes.remove(clicked_piece);
                    }
                }
                // Finalmente realiza el movimiento de irse al lugar de click
                personajeActual.mover(Clicked_Column, Clicked_Row);
                //Active_Piece.setX(Clicked_Column);
               // personajeActual.setY(Clicked_Row);
               
                
                // si el personaje actual es un defensor
                if (personajeActual.getClass().equals(Defensor.class))
                {
                    //Significa que se ha movido
                    Defensor castedPawn = (Defensor)(personajeActual);
                    //Ponemos la se;al de que se ha movido en True
                    castedPawn.setHasMoved(true);
                }
                
                //Vaciamos el personaje actual 
                personajeActual = null;
                contadorTurno++;
            }
            crearTablero();
        }

        //Funciones para que el mouse responda al movimiento
        @Override
        public void mouseDragged(MouseEvent e) {		
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) 
        {
        }	

        
    };

    
    
    //Funciones para poner las imagenes de las casillas
    private void adjustShapePositions(double dx, double dy) {

        Inmoviles.get(0).adjustPosition(dx, dy);
        this.repaint();

    } 
         
    private Image cargarImagen(String imageFile) {
        try {
                return ImageIO.read(new File(imageFile));
        }
        catch (IOException e) {
                return NULL_IMAGE;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        drawBackground(g2);
        drawShapes(g2);
    }

    private void drawBackground(Graphics2D g2) {
        g2.setColor(getBackground());
        g2.fillRect(0,  0, getWidth(), getHeight());
    }
       

    private void drawShapes(Graphics2D g2) {
        for (DrawingShape shape : Inmoviles) {
            shape.draw(g2);
        }	
        for (DrawingShape shape : Moviles) {
            shape.draw(g2);
        }
    }

    private ComponentAdapter componentAdapter = new ComponentAdapter() {

        @Override
        public void componentHidden(ComponentEvent e) {

        }

        @Override
        public void componentMoved(ComponentEvent e) {

        }

        @Override
        public void componentResized(ComponentEvent e) {

        }

        @Override
        public void componentShown(ComponentEvent e) {

        }	
    };

    private KeyAdapter keyAdapter = new KeyAdapter() {

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }

        @Override
        public void keyTyped(KeyEvent e) {

        }	
    };

}



interface DrawingShape {
    boolean contains(Graphics2D g2, double x, double y);
    void adjustPosition(double dx, double dy);
    void draw(Graphics2D g2);
}


class DrawingImage implements DrawingShape {

    public Image image;
    public Rectangle2D rect;

    public DrawingImage(Image image, Rectangle2D rect) {
            this.image = image;
            this.rect = rect;
    }

    @Override
    public boolean contains(Graphics2D g2, double x, double y) {
            return rect.contains(x, y);
    }

    @Override
    public void adjustPosition(double dx, double dy) {
            rect.setRect(rect.getX() + dx, rect.getY() + dy, rect.getWidth(), rect.getHeight());	
    }

    @Override
    public void draw(Graphics2D g2) {
            Rectangle2D bounds = rect.getBounds2D();
            g2.drawImage(image, (int)bounds.getMinX(), (int)bounds.getMinY(), (int)bounds.getMaxX(), (int)bounds.getMaxY(),
                                            0, 0, image.getWidth(null), image.getHeight(null), null);
    }	
}
