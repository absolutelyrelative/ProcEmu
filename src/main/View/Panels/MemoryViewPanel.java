package View.Panels;

import Assembler.IMUploader;
import Components.CellAbstractModel;
import Components.InstructionMemory;
import Util.AbstractCellTable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MemoryViewPanel extends JPanel {
    private JTextField jumptolinetextfield = new JTextField();
    private JButton jumptolinebutton = new JButton("Jump to line");


    public MemoryViewPanel() {
        setSize(1000, 800);
        setVisible(true);
        setLayout(new BorderLayout());

        //Border panels
        JPanel northpanel = new JPanel(new FlowLayout());
        JPanel linestartpanel = new JPanel(new GridLayout(4, 2, 2, 2));
        JPanel centerpanel = new JPanel(new FlowLayout());
        JPanel lineendpanel = new JPanel(new FlowLayout());
        JPanel southpanel = new JPanel(new FlowLayout());

        //Table contents don't need to be editable so I'm not going to make an abstract model

        //Component settings
        jumptolinetextfield.setColumns(16);

        AbstractCellTable memorytable = new AbstractCellTable(InstructionMemory.getInstance().getCells());
        JTable table = new JTable(memorytable);
        JScrollPane pane = new JScrollPane(table);
        //table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        northpanel.add(jumptolinetextfield);
        northpanel.add(jumptolinebutton);
        centerpanel.add(pane);

        //Add to main panel
        add(northpanel, BorderLayout.NORTH);
        add(linestartpanel, BorderLayout.LINE_START);
        add(centerpanel, BorderLayout.CENTER);
        add(lineendpanel, BorderLayout.LINE_END);
        add(southpanel, BorderLayout.SOUTH);

    }
}
