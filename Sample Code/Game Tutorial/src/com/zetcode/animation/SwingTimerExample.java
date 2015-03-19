package com.zetcode.animation;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class SwingTimerExample extends JFrame {

    public SwingTimerExample() {
        
        initUI();
    }
    
    private void initUI() {

        add(new Board());
        
        //setResizable(false);
        //pack();
		setSize(1000, 1000);
        
        setTitle("Star");
        setLocationRelativeTo(null);        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {                
                JFrame ex = new SwingTimerExample();
                ex.setVisible(true);                
            }
        });
    }
}
