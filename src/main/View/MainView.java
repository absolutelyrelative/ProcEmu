package View;

import Components.CellAbstractModel;
import Util.AbstractCellTable;
import View.Panels.AssemblerViewPanel;
import View.Panels.MemoryViewPanel;
import View.Panels.RegisterPanel;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainView extends JFrame {
    JTabbedPane mainpanel;

    public MainView(AbstractCellTable instructionmemory, AbstractCellTable datamemory) {

        //Attempt to mimic system UI
        try {
            FlatLightLaf.setup();
            //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (Exception e) {
            setDefaultLookAndFeelDecorated(true);
            e.printStackTrace();
        }

        setTitle("Main View");
        mainpanel = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
        RegisterPanel registerpanel = new RegisterPanel(instructionmemory, datamemory);
        MemoryViewPanel memoryviewpanel = new MemoryViewPanel(instructionmemory);
        MemoryViewPanel datamemoryviewpanel = new MemoryViewPanel(datamemory);
        AssemblerViewPanel assemblerviewpanel = new AssemblerViewPanel(instructionmemory);

        //tabbedpanel components
        mainpanel.addTab("Register View", registerpanel);
        mainpanel.addTab("Instruction Memory View", memoryviewpanel);
        mainpanel.addTab("Data Memory View", datamemoryviewpanel);
        mainpanel.addTab("Assembler", assemblerviewpanel);

        //Add panel to frame
        getContentPane().add(mainpanel, BorderLayout.CENTER);


        //frame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(1000, 800);
        setVisible(true);

    }

    public static void main(String[] args) {
        AbstractCellTable instructionmemory = new AbstractCellTable(new ArrayList<CellAbstractModel>());
        AbstractCellTable datamemory = new AbstractCellTable(new ArrayList<CellAbstractModel>());
        new MainView(instructionmemory, datamemory);
    }
}
