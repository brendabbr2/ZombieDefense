package Zombie_Defense;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UsarItem implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("Usar Item"))
        {
            JOptionPane.showMessageDialog(null, "Usando Item");
        }
    }
}

