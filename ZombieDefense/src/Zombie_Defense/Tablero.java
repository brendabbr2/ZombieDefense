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
import java.util.concurrent.ThreadLocalRandom;
import javax.imageio.*;
import javax.swing.*;

                                 ///La GUI en otras palabras//

@SuppressWarnings("serial")
public class Tablero extends JComponent {

    public boolean en_Turno;

    public int contadorTurno = 0;  //Contador de turnos. Cada 4 no juega
    
    //Creamos a NULL_IMAGE porque se debe retornar mucho en las funciones con imagenes
    private static final Image NULL_IMAGE = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);

    private final int Cuadro_ancho = 65;    //El tamaño de cada cuadro es 65x65
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
    private final String base_directorio = "imágenes" + File.separator + "Base.png";
    private final String tumba_directorio = "imágenes" + File.separator + "tumba.png";


    public boolean isEn_Turno() {
        return en_Turno;
    }

    public void setEn_Turno(boolean en_Turno) {
        this.en_Turno = en_Turno;
    }

    public void crearCuadros()
    //Añade en las listas los cuadros y personajes iniciales
    {
        for (int i = 0; i < filas; i++) //De acuerdo al numero de 
                                        //Filas y columnas
        {
            for (int j = 0; j < columnas; j++)
            {
                pieza[i][j] = 0;
                if (i == 0 && j == 7)
                {
                    Cuadros.add(new Cuadro(j, i, "Base"));
                }
                else if ((i == 0 && j == 0)||(i==0 && j==7)||(i==7 && j==7))
                {
                    Cuadros.add(new Cuadro(j, i, "Tumba"));
                }
                Cuadros.add(new Cuadro(j, i, "Vacio"));
            }
        }

        //Parametros:   int x, int y, boolean esDefensor, String direccion, Tablero tablero, int vida, int ataque//
        Defensores.add(new Defensor(3,0,true,"Guerrero.png",this, 5, 2, 1));
        Defensores.add(new Defensor(3,3,true,"Caballero.png",this, 5, 3, 2));
        Defensores.add(new Defensor(7,3,true,"Soldado.png",this, 5, 1, 3));


        Atacantes.add(new Atacante(0,0,false,"Fantasma.png",this, 5, 1, 1));
        Atacantes.add(new Atacante(0,7,false,"Vampiro.png",this, 5, 2, 1));
        Atacantes.add(new Atacante(7,7,false,"Zombie.png",this, 5, 3, 1));



    }

    public void actionPerformed(ActionEvent e){}



/*
* Image active_square = cargarImagen("imágenes" + File.separator + "activo.png");
  Inmoviles.add(new DrawingImage(active_square, new Rectangle2D.Double(Cuadro_ancho*personajeActual.getX(),
  * Cuadro_ancho*personajeActual.getY(), active_square.getWidth(null), active_square.getHeight(null))));
  *
*        Image tumba = cargarImagen(tumba_directorio);
        Inmoviles.add(new DrawingImage(tumba, new Rectangle2D.Double(0, 0, 65, 65)));
* */

    public Tablero() {

        //Constructor del tablero
        pieza = new Integer[filas][columnas];
        Inmoviles = new ArrayList();
        Moviles = new ArrayList();
        Defensores = new ArrayList();
        Atacantes = new ArrayList();
        Cuadros = new ArrayList();
        en_Turno = true;
        crearCuadros();


        
        //Dimensiones
        this.setBackground(new Color(37,13,84));
        this.setPreferredSize(new Dimension(520, 520));
        this.setMinimumSize(new Dimension(100, 100));
        this.setMaximumSize(new Dimension(1000, 1000));

        //Mouse
        this.addMouseListener(turnoDefensor);
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

        Image base = cargarImagen(base_directorio);                  //65 * 7
        Inmoviles.add(new DrawingImage(base, new Rectangle2D.Double(455, 0,65,65)));

        
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

    public Cuadro getCuadro(int x, int y){
        for (Cuadro c : Cuadros)
        {
            if (c.getX() == x && c.getY() == y)
            {
                return c;
            }
        }
        return null;
    }
    

    /////////////////////EL MOUSE/////////////////


    public MouseAdapter turnoDefensor = new MouseAdapter() {


        int personajes = 0;



        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            int d_X = e.getX();
            int d_Y = e.getY();
            int Clicked_Row = d_Y / Cuadro_ancho;           //Coordenadas del cuadro clickeado
            int Clicked_Column = d_X / Cuadro_ancho;

                                  //Si es nuestro turno de jugar
            boolean activado = true;

            //Aquí es cuando el usuario clickea una pieza.

            Personaje clicked_piece = getPiece(Clicked_Column, Clicked_Row);  //Primero ocupamos saber quien es el personaje (o si hay)

            if (en_Turno) {


            /*
            Las acciones a realizar dependen de:

            A quién tenemos clickeado: personajeActual
            Y a quién acabamos de clickear: clicked_piece
            */

                if (personajeActual == null && clicked_piece != null &&
                        clicked_piece.esDefensor())
                    /*Si no hay personaje asigando (clickeado) y clickeamos algo y
                    es nuestro turno y la pieza clickeada es el defensor*/ {
                    personajeActual = clicked_piece;
                } else if (personajeActual != null && personajeActual.getX() == Clicked_Column && personajeActual.getY() == Clicked_Row)
                //Si ya hay un personaje clickeado, y ese personaje coincide con la pieza clickeada
                {
                    //Es un doble click, entonces volvemos a lo mismo. No hay nada
                    personajeActual = null;
                } else if (personajeActual != null && personajeActual.permitirMover(Clicked_Column, Clicked_Row)
                        && personajeActual.getMovido() == false && personajeActual.esDefensor())
                //Si hay un personaje clickeado, y ese personaje se puede mover (no hay obstaculos)
                //y es nuestro turno y ese personaje clickeado es un defensor
                {
                    personajeActual.mover(Clicked_Column, Clicked_Row);   //Mover defensor

                    // si el personaje actual es un defensor
                    if (personajeActual.getClass().equals(Defensor.class)) {
                        //Significa que se ha movido
                        Defensor jugadorMovido = (Defensor) (personajeActual);
                        //Ponemos la señal de que se ha movido en True
                        jugadorMovido.setMovido(true);
                    }
                    //Vaciamos el personaje actual
                    personajeActual = null;
                    contadorTurno++;
                }
                crearTablero();
            }
        }

        /*
        * for (int a = 0; a <= 3; a++) {
                    int total = 0;
                    Image tumba = cargarImagen(tumba_directorio);
                    while (total <= 3) {
                        int posX = ThreadLocalRandom.current().nextInt(0, 8);
                        int posY = ThreadLocalRandom.current().nextInt(0, 8);

                        for (Cuadro cuadro : Cuadros) {
                            if (cuadro.getX() == posX && cuadro.getY() == posY) {
                                if ((cuadro.getTipo()).equals("Vacio")) {
                                    cuadro.setTipo("Tumba");
                                    Inmoviles.add(new DrawingImage(tumba, new Rectangle2D.Double(posX, posY, 65, 65)));
                                    total++;
                                }
                            }
                        }
                    }
                }
        * */

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

    private final ComponentAdapter componentAdapter = new ComponentAdapter() {

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

    private final KeyAdapter keyAdapter = new KeyAdapter() {

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
