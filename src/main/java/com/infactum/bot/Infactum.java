package com.infactum.bot;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.net.URL;
import java.util.Objects;
import java.util.Random;

public class Infactum extends JFrame {
    private static final String WINDOW_TITLE = "Window Title";

    private static final Color TC1 = new Color(188, 222, 206);
    private static final Color TC2 = new Color(222, 188, 188);

    private static final Color C1 = new Color(0, 0, 0);
    private static final Color C2 = new Color(255, 255, 255);

    private static final Font F1 = new Font("Calibri", Font.PLAIN, 16);

    private static final Border B1 = BorderFactory.createLineBorder(Color.BLACK, 3, true);

    private static final Random rand = new Random();

    public Infactum() {
        super(WINDOW_TITLE);
        setContentPane(new JPanel(new BorderLayout()) {{
        }});
        pack();
        setSize(new Dimension(getWidth(), getHeight()));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Infactum();
    }

    private static URL loadPath(String path) throws NullPointerException {
        return Objects.requireNonNull(Infactum.class.getResource(path));
    }

    private void errorMessage(String title, String text) {
        JOptionPane.showMessageDialog(getContentPane(), text, title, JOptionPane.ERROR_MESSAGE);
    }
}
