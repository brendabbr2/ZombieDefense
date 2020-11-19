package Zombie_Defense;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.*;

import static javax.swing.JOptionPane.showMessageDialog;

public class Estructura extends JFrame {

    private Object JButton;
    private String nombre;
    private boolean bItem;
    private boolean bUsar;
    private boolean bAtacar;
    Component component;
    private final JLabel mensaje = new JLabel("¡Defiende la base de los zombies!");
    private final JLabel men_base = new JLabel("                                    Base↓");

    public boolean isbItem() {
        return bItem;
    }

    public void setbItem(boolean bItem) {
        this.bItem = bItem;
    }

    public boolean isbUsar() {
        return bUsar;
    }

    public void setbUsar(boolean bUsar) {
        this.bUsar = bUsar;
    }

    public boolean isbAtacar() {
        return bAtacar;
    }

    public void setbAtacar(boolean bAtacar) {
        this.bAtacar = bAtacar;
    }

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

        JButton Items = new JButton("Items"); //Boton para ver la lista de items
        Items.addActionListener(new MostrarItems());
        Items.setEnabled(true);


        JButton Usar = new JButton("Usar Item"); //Boton para usar Item
        Usar.addActionListener(new UsarItem());
        Usar.setEnabled(true);

        JButton Atacar = new JButton("Atacar"); //Boton para usar Item
        Atacar.addActionListener(new Atacar());
        Atacar.setEnabled(true);

        tools.add(Items);
        tools.add(Usar);
        tools.add(Atacar);

        tools.addSeparator();
        tools.add(mensaje);
        tools.add(men_base);
 
        this.setLocation(200, 50);
        this.pack();
        this.setVisible(true);




    }



}
