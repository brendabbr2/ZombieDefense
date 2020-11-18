package Zombie_Defense;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.*;

import static javax.swing.JOptionPane.showMessageDialog;

public class Estructura extends JFrame {

    private final Object JButton;
    Component component;
    private final JLabel mensaje = new JLabel("¡Defiende la base de los zombies!");
    private final JLabel men_base = new JLabel("                                    Base↓");

    public Estructura()
    {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("Zombie Defense!");
        this.setResizable(false);
        component = new Tablero();
        this.add(component, BorderLayout.CENTER); 
        
        JToolBar tools = new JToolBar();                  //Aquí vienen los botones.
        tools.setFloatable(false);
        this.add(tools, BorderLayout.PAGE_START);


        class mostrarItems implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(null, "Lista Items");
            }
        }

        class atacarEnemigo implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(null, "Ataco enemigo");
            }
        }

        class usarItem implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(null, "Usamos item");
            }
        }

        JButton Items = new JButton("Items"); //Boton para ver la lista de items
        Items.addActionListener(new mostrarItems());

        JButton Atacar = new JButton("Atacar"); //Boton para atacar
        Atacar.addActionListener(new atacarEnemigo());

        JButton Usar = new JButton("Usar Item"); //Boton para usar Item
        Usar.addActionListener(new usarItem());

        tools.add(Items);
        tools.add(Atacar);
        tools.add(Usar);

        tools.addSeparator();
        tools.add(mensaje);
        tools.add(men_base, 5);
 
        this.setLocation(200, 50);
        this.pack();
        this.setVisible(true);

        JButton = null;
    }


}
