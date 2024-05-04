package org.example;
import org.example.GUI.*;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        JFrame frame = new JFrame("ListPacientsGUI");
        frame.setContentPane(new ListPacientsGUI().getMainPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


        }
}
