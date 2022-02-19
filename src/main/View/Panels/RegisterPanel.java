package View.Panels;

import Components.RegisterFile;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RegisterPanel extends JPanel {
    /*private JLabel register1label = new JLabel("R1");
    private JLabel register2label = new JLabel("R2");
    private JLabel register3label = new JLabel("R3");
    private JLabel register4label = new JLabel("R4");
    private JLabel register5label = new JLabel("R5");
    private JLabel register6label = new JLabel("R6");
    private JLabel register7label = new JLabel("R7");
    private JLabel register8label = new JLabel("R8");
    private JLabel register9label = new JLabel("R9");
    private JLabel register10label = new JLabel("R10");
    private JLabel register11label = new JLabel("R11");
    private JLabel register12label = new JLabel("R12");
    private JLabel register13label = new JLabel("R13");
    private JLabel register14label = new JLabel("R14");
    private JLabel register15label = new JLabel("R15");
    private JLabel register16label = new JLabel("R16");
    private JLabel register17label = new JLabel("R17");
    private JLabel register18label = new JLabel("R18");
    private JLabel register19label = new JLabel("R19");
    private JLabel register20label = new JLabel("R20");
    private JLabel register21label = new JLabel("R21");
    private JLabel register22label = new JLabel("R22");
    private JLabel register23label = new JLabel("R23");
    private JLabel register24label = new JLabel("R24");
    private JLabel register25label = new JLabel("R25");
    private JLabel register26label = new JLabel("R26");
    private JLabel register27label = new JLabel("R27");
    private JLabel register28label = new JLabel("R28");
    private JLabel register29label = new JLabel("R29");
    private JLabel register30label = new JLabel("R30");
    private JLabel register31label = new JLabel("R31");
    private JLabel register32label = new JLabel("R32");
    private JTextField register1tf = new JTextField("R1");
    private JTextField register2tf = new JTextField("R2");
    private JTextField register3tf = new JTextField("R3");
    private JTextField register4tf = new JTextField("R4");
    private JTextField register5tf = new JTextField("R5");
    private JTextField register6tf = new JTextField("R6");
    private JTextField register7tf = new JTextField("R7");
    private JTextField register8tf = new JTextField("R8");
    private JTextField register9tf = new JTextField("R9");
    private JTextField register10tf = new JTextField("R10");
    private JTextField register11tf = new JTextField("R11");
    private JTextField register12tf = new JTextField("R12");
    private JTextField register13tf = new JTextField("R13");
    private JTextField register14tf = new JTextField("R14");
    private JTextField register15tf = new JTextField("R15");
    private JTextField register16tf = new JTextField("R16");
    private JTextField register17tf = new JTextField("R17");
    private JTextField register18tf = new JTextField("R18");
    private JTextField register19tf = new JTextField("R19");
    private JTextField register20tf = new JTextField("R20");
    private JTextField register21tf = new JTextField("R21");
    private JTextField register22tf = new JTextField("R22");
    private JTextField register23tf = new JTextField("R23");
    private JTextField register24tf = new JTextField("R24");
    private JTextField register25tf = new JTextField("R25");
    private JTextField register26tf = new JTextField("R26");
    private JTextField register27tf = new JTextField("R27");
    private JTextField register28tf = new JTextField("R28");
    private JTextField register29tf = new JTextField("R29");
    private JTextField register30tf = new JTextField("R30");
    private JTextField register31tf = new JTextField("R31");
    private JTextField register32tf = new JTextField("R32");*/
    private ArrayList<JLabel> registerlabels;
    private ArrayList<JTextField> registertextfields;
    private final JLabel registertitle = new JLabel("Registers:");


    public RegisterPanel(){
        //Frame properties
        setSize(1000, 800);
        setVisible(true);
        setLayout(new BorderLayout());

        //Border panels
        JPanel northpanel = new JPanel(new FlowLayout());
        JPanel linestartpanel = new JPanel(new GridLayout(32,2,2,2));
        JPanel southpanel = new JPanel(new FlowLayout());

        //North panel text
        northpanel.add(registertitle);

        //Component properties
        registerlabels = new ArrayList<>(32);
        registertextfields = new ArrayList<>(32);
        //linestartpanel.setSize(200,200);

        //Populate Arrays and add to panel
        for(int registercount = 0; registercount < 32; registercount++){
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

        //Add to main panel
        add(northpanel, BorderLayout.NORTH);
        add(linestartpanel, BorderLayout.LINE_START);
        add(southpanel, BorderLayout.SOUTH);
    }

    private void PopulateArrayList(int size, ArrayList<JLabel> labels, ArrayList<JTextField> fields){

    }
}
