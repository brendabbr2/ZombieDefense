package Zombie_Defense;

import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.*;

public class Estructura extends JFrame {
    Component component;
    private final JLabel mensaje = new JLabel("¡Defiende la base de los zombies!                                  Base↓");
    public Estructura()
    {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("Zombie Defense!");
        this.setResizable(false);
        component = new Tablero();
        this.add(component, BorderLayout.CENTER); 
        
        JToolBar tools = new JToolBar();       //Aquí vienen los botones
        tools.setFloatable(false);
        this.add(tools, BorderLayout.PAGE_START);
        tools.add(new JButton("Items"));            //Boton para ver la lista de items
        tools.add(new JButton("Atacar")); //Boton para ver la lista de items
        tools.add(new JButton("Usar Item")); //Boton para usar Item
        tools.addSeparator();
        tools.add(mensaje);
 
        this.setLocation(200, 50);
        this.pack();
        this.setVisible(true);
    }
}
