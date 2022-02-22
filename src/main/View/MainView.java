package View;

import Assembler.IMUploader;
import Components.CellAbstractModel;
import Components.InstructionMemory;
import Util.AbstractCellTable;
import View.Panels.AssemblerViewPanel;
import View.Panels.MemoryViewPanel;
import View.Panels.RegisterPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainView extends JFrame {
    JTabbedPane mainpanel;

    public MainView(AbstractCellTable datamemory){
        setDefaultLookAndFeelDecorated(true);
        setTitle("Main View");
        mainpanel = new JTabbedPane(JTabbedPane.RIGHT, JTabbedPane.SCROLL_TAB_LAYOUT);

        //tabbedpanel components
        mainpanel.addTab("Register View",new RegisterPanel());
        mainpanel.addTab("Memory View",new MemoryViewPanel(datamemory));
        mainpanel.addTab("Assembler", new AssemblerViewPanel(datamemory));

        //Add panel to frame
        getContentPane().add(mainpanel, BorderLayout.CENTER);


        //frame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(1000, 800);
        setVisible(true);
    }

    public static void main(String[] args) {
        AbstractCellTable datamemory = new AbstractCellTable(new ArrayList<CellAbstractModel>());
        new MainView(datamemory);
    }
}
