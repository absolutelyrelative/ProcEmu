package View.Panels;

import Util.AbstractCellTable;
import View.Listeners.RegisterListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RegisterPanel extends JPanel {

    //Registers
    private ArrayList<JLabel> registerlabels;
    private ArrayList<JTextField> registertextfields;
    private final JLabel registertitle = new JLabel("Registers:");
    JLabel programcounterlabel = new JLabel("PC");
    JTextField programcounterfield = new JTextField();

    //Current operation
    private JButton loadprogrambutton = new JButton("Reload PC address");
    private JLabel currentinstructionlabel = new JLabel("Instruction to execute:");
    private JTextField currentinstructiontextfield = new JTextField(20);
    private JButton executebutton = new JButton("Execute");


    public RegisterPanel(AbstractCellTable instructionmemory, AbstractCellTable datamemory) {
        //Frame properties
        setSize(1000, 800);
        setVisible(true);
        setLayout(new BorderLayout());

        //Border panels
        JPanel northpanel = new JPanel(new FlowLayout());
        JPanel linestartpanel = new JPanel(new GridLayout(17, 4, 20, 20));
        JPanel lineendpanel = new JPanel(new FlowLayout());
        JPanel southpanel = new JPanel(new FlowLayout());

        //North panel text
        linestartpanel.add(registertitle);
        linestartpanel.add(new JLabel()); //I hate FlowLayout
        linestartpanel.add(new JLabel()); //I hate FlowLayout
        linestartpanel.add(new JLabel()); //I hate FlowLayout

        //Component properties
        registerlabels = new ArrayList<>(32);
        registertextfields = new ArrayList<>(32);
        currentinstructiontextfield.setEditable(false);

        //Populate Arrays and add to panel
        for (int registercount = 0; registercount < 32; registercount++) {
            //Populate ArrayLists
            JLabel currentlabel = new JLabel();
            currentlabel.setText("R" + (registercount + 1));
            registerlabels.add(currentlabel);
            JTextField currenttf = new JTextField();
            currenttf.setColumns(8);
            currenttf.setEditable(true);
            registertextfields.add(currenttf);

            //Add ArrayLists to panel
            linestartpanel.add(registerlabels.get(registercount));
            linestartpanel.add(registertextfields.get(registercount));
        }

        //Current operation panel
        programcounterfield.setColumns(8);
        programcounterfield.setEditable(false);
        programcounterfield.setText(String.format("%08X", 0));
        southpanel.add(programcounterlabel);
        southpanel.add(programcounterfield);
        lineendpanel.add(loadprogrambutton);
        lineendpanel.add(currentinstructionlabel);
        lineendpanel.add(currentinstructiontextfield);
        lineendpanel.add(executebutton);


        //Add to main panel
        add(northpanel, BorderLayout.NORTH);
        add(linestartpanel, BorderLayout.LINE_START);
        add(lineendpanel, BorderLayout.LINE_END);
        add(southpanel, BorderLayout.SOUTH);

        //Initial population
        PopulateArrayList(32, registerlabels, registertextfields);

        //Add listeners
        RegisterListener panellistener = new RegisterListener(registertextfields, currentinstructiontextfield, executebutton, instructionmemory, datamemory, programcounterfield, loadprogrambutton);
        loadprogrambutton.addActionListener(panellistener);
        executebutton.addActionListener(panellistener);
    }

    private void PopulateArrayList(int size, ArrayList<JLabel> labels, ArrayList<JTextField> fields) {
        for (JTextField register : fields) {
            register.setText(String.format("%08X", 0));
            register.setEditable(true);
        }
    }

}
