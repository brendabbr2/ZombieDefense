package Zombie_Defense;

import Personajes.Defensor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MostrarItems implements ActionListener {
    Defensor defensor;

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("Items")){
            JOptionPane.showMessageDialog(null, "Mostrando Items");

        //Aqui va mostrar la lista de items


        }


    }
}
