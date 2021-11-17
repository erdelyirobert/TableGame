package bead2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameGUI {
    private final JFrame frame;
    private JBoard jboard;

    public GameGUI() {
        frame = new JFrame("4-es játék");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jboard = new JBoard(3,3);                                                   //panel paraméterezés
        frame.getContentPane().add(jboard.getBoardPanel(), BorderLayout.CENTER);           // ContentPane-be boardpanel beletétele

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu gameMenu = new JMenu("Táblaméret");
        menuBar.add(gameMenu);


        JMenuItem smallMenu = new JMenuItem(3 + "x" + 3);
        gameMenu.add(smallMenu);
        JMenuItem mediumMenu = new JMenuItem(5 + "x" + 5);
        gameMenu.add(mediumMenu);
        JMenuItem largeMenu = new JMenuItem(7 + "x" + 7);
        gameMenu.add(largeMenu);


        smallMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().remove(jboard.getBoardPanel());
                jboard = new JBoard(3, 3);
                frame.getContentPane().add(jboard.getBoardPanel(), BorderLayout.CENTER);
                frame.pack();
            }
        });

        mediumMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().remove(jboard.getBoardPanel());
                jboard = new JBoard(5, 5);
                frame.getContentPane().add(jboard.getBoardPanel(), BorderLayout.CENTER);
                frame.pack();
            }
        });

        largeMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().remove(jboard.getBoardPanel());
                jboard = new JBoard(7, 7);
                frame.getContentPane().add(jboard.getBoardPanel(), BorderLayout.CENTER);
                frame.pack();
            }
        });

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
