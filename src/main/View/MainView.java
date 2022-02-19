package View;

import View.Panels.RegisterPanel;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {
    JTabbedPane mainpanel;

    public MainView(){
        setDefaultLookAndFeelDecorated(true);
        setTitle("Main View");
        mainpanel = new JTabbedPane(JTabbedPane.RIGHT, JTabbedPane.SCROLL_TAB_LAYOUT);

        //tabbedpanel components
        mainpanel.addTab("Register View",new RegisterPanel());

        //Add panel to frame
        getContentPane().add(mainpanel, BorderLayout.CENTER);


        //frame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(1000, 800);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MainView();
    }
}
