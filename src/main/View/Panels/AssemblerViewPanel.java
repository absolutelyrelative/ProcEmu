package View.Panels;

import Util.AbstractCellTable;
import View.Listeners.AssemblerListener;

import javax.swing.*;
import java.awt.*;

public class AssemblerViewPanel extends JPanel {
    private JTextArea inputarea = new JTextArea();
    private JTextArea outputarea = new JTextArea();
    private JButton assemble = new JButton("Assemble code");
    private JButton upload = new JButton("Upload code to memory");
    AbstractCellTable datamemory;

    public AssemblerViewPanel(AbstractCellTable datamemory){
        this.datamemory = datamemory;
        setSize(1000, 800);
        setVisible(true);
        setLayout(new BorderLayout());

        //Border panels
        JPanel northpanel = new JPanel(new FlowLayout());
        JPanel linestartpanel = new JPanel(new GridLayout(2, 1, 2, 2));
        JPanel centerpanel = new JPanel(new GridLayout(1, 2, 20, 20));
        JPanel lineendpanel = new JPanel(new GridLayout(2, 1, 2, 2));
        JPanel southpanel = new JPanel(new FlowLayout());


        inputarea.setToolTipText("Insert Assembly code here.");
        outputarea.setEditable(false);
        centerpanel.add(inputarea);
        centerpanel.add(outputarea);
        southpanel.add(assemble);
        southpanel.add(upload);


        //Add to main panel
        //add(northpanel, BorderLayout.NORTH);
        //add(linestartpanel, BorderLayout.LINE_START);
        add(centerpanel, BorderLayout.CENTER);
        //add(lineendpanel, BorderLayout.LINE_END);
        add(southpanel, BorderLayout.SOUTH);


        //Add listeners
        AssemblerListener panellistener = new AssemblerListener(inputarea, outputarea, assemble, upload, datamemory);
        assemble.addActionListener(panellistener);
        upload.addActionListener(panellistener);
    }
}
