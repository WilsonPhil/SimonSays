package com.simon;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class SimonSays extends JFrame {

    public SimonSays() {

        initUI();
    }

    private void initUI() {

        add(new Board());

        setResizable(false);
        pack();

        setTitle("Simon Says");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            JFrame ex = new SimonSays();
            ex.setVisible(true);
        });
    }
}