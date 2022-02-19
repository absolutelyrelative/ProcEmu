package View.Panels;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RegisterPanel extends JPanel {

    //Registers
    private ArrayList<JLabel> registerlabels;
    private ArrayList<JTextField> registertextfields;
    private final JLabel registertitle = new JLabel("Registers:");

    //Current operation
    private JLabel currentinstructionlabel = new JLabel("Instruction to execute:");
    private JTextField currentinstructiontextfield = new JTextField(20);
    private JButton executebutton = new JButton("Execute");


    public RegisterPanel() {
        //Frame properties
        setSize(1000, 800);
        setVisible(true);
        setLayout(new BorderLayout());

        //Border panels
        JPanel northpanel = new JPanel(new FlowLayout());
        JPanel linestartpanel = new JPanel(new GridLayout(32, 2, 2, 2));
        JPanel lineendpanel = new JPanel(new FlowLayout());
        JPanel southpanel = new JPanel(new FlowLayout());

        //North panel text
        northpanel.add(registertitle);

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
            currenttf.setColumns(16);
            currenttf.setEditable(false);
            registertextfields.add(currenttf);

            //Add ArrayLists to panel
            linestartpanel.add(registerlabels.get(registercount));
            linestartpanel.add(registertextfields.get(registercount));
        }

        //Current operation panel
        lineendpanel.add(currentinstructionlabel);
        lineendpanel.add(currentinstructiontextfield);
        lineendpanel.add(executebutton);


        //Add to main panel
        add(northpanel, BorderLayout.NORTH);
        add(linestartpanel, BorderLayout.LINE_START);
        add(lineendpanel, BorderLayout.LINE_END);
        add(southpanel, BorderLayout.SOUTH);
    }

    private void PopulateArrayList(int size, ArrayList<JLabel> labels, ArrayList<JTextField> fields) {

    }
}
