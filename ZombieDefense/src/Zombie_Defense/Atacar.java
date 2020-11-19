package Zombie_Defense;

import Personajes.Atacante;
import Personajes.Defensor;
import Personajes.Personaje;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Atacar extends Tablero implements ActionListener {

    Atacante a;
    Defensor d;
    /*public MouseAdapter coordenadas (){

        @Override
        public void mousePressed(MouseEvent e) {
        int d_X = e.getX();
        int d_Y = e.getY();
        int Clicked_Row = d_Y / 65;           //Coordenadas del cuadro clickeado
        int Clicked_Column = d_X / 65;
    }
    */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if (actionEvent.getActionCommand().equals(("Atacar"))) {
            Personaje defensor = null;
            for (int i = 0; i < 8; i++) {
                Personaje p = getPiece(i, getY());           //Leer en x
                System.out.println(getY());
                if (p != null) {
                    if (p.esDefensor() && p != null) {
                        defensor = getPiece(i, getY());
                        //Defensor defender = (Defensor) defensor;
                        d = (Defensor) defensor;
                        Personaje atacante = null;
                        for (int j = 0; j < 8; j++) {
                            Personaje q = getPiece(j, getY());           //Leer en x
                            if (q != null) {
                                if (q.esAtacante()) {
                                    atacante = getPiece(getX(), getY());
                                    //Atacante atacar = (Atacante) atacante;
                                    //atacante.setVida(atacante.getVida() - );

                                }
                            }
                        }
                        a = (Atacante) atacante;
                        a.setVida(a.getVida()-d.getAtaque());
                        if(a.getVida()<=0){
                            System.out.println("Muerto1");
                            a.tablero.morir(a.getX(), a.getY());
                        }
                        System.out.println("Vida: " + Integer.toString(a.getVida()));

                        JOptionPane.showMessageDialog(null, "Enemigo " + a.getDirectorio() + " perdió " +
                                Integer.toString(d.getAtaque()) + " por " + d.getDirectorio());
                        break;

                    }
                }
            }
        }
    }
}
        //if (actionEvent.getActionCommand().equals("Atacar"))
       // {
       //     JOptionPane.showMessageDialog(null, "¿Estamos en: " +
      //              Integer.toString(getX()) + " , " + Integer.toString(getY()) + " ?");
            /*for (int i = 0; i <= this.getX(); i++){
                Personaje p = getPiece(this.getX(), i);
                if (p != null){
                    if (p.esAtacante()){
                        JOptionPane.showMessageDialog(null, "Atacante en: " +
                            Integer.toString(this.getX()) + " , " + Integer.toString(i) );
                    }
                }
            }
            for (int i = 0; i <= this.getY(); i++){
                Personaje p = getPiece(i, this.getY());
                if (p != null) {
                    if (p.esAtacante()) {
                        JOptionPane.showMessageDialog(null, "Atacante en: " +
                                Integer.toString(i) + " , " + Integer.toString(this.getY()));
                    }
                }
            }*/
     //   }



/*
*    public void activarAtaque(int fil, int col) {
        Estructura estructura = null;
        for (int i = 0; i <= 7; i++) {
            Personaje p = tablero.getPiece(fil, i);
            if (p.esAtacante()) {
                estructura.setbAtacar(true);
            }
        }
        for (int a = 0; a <= 7; a++) {
            Personaje p = tablero.getPiece(a, col);
            if (p.esAtacante()) {
                estructura.setbAtacar(true);
            }
        }
    } */