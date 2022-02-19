package View;

import Assembler.IMUploader;
import Components.CellAbstractModel;
import Components.InstructionMemory;
import View.Panels.MemoryViewPanel;
import View.Panels.RegisterPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainView extends JFrame {
    JTabbedPane mainpanel;

    public MainView(){
        setDefaultLookAndFeelDecorated(true);
        setTitle("Main View");
        mainpanel = new JTabbedPane(JTabbedPane.RIGHT, JTabbedPane.SCROLL_TAB_LAYOUT);

        //tabbedpanel components
        mainpanel.addTab("Register View",new RegisterPanel());
        mainpanel.addTab("Memory View",new MemoryViewPanel());

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
        // Testing
        ArrayList<String> instructions = new ArrayList<>();
        instructions.add("LW R0,100(r1)");
        instructions.add("JMP 500");
        instructions.add("BEQ R1,R2,20");
        instructions.add("JMP 40");
        instructions.add("LW R0,100(r1)");
        instructions.add("JMP 500");
        instructions.add("BEQ R1,R2,20");
        instructions.add("JMP 40");
        instructions.add("LW R0,100(r1)");
        ArrayList<CellAbstractModel> memory = new ArrayList<>();
        IMUploader.getInstance().UploadInstructions(instructions, InstructionMemory.getInstance().getCells(), null,0);
        for(CellAbstractModel c : InstructionMemory.getInstance().getCells()){
            System.out.println(c.getAddress() + ':' + c.getData() + ' ' + c.getInstruction() + ' ' + c.getIncrement() + " " + c.getAddress());
        }
        // Testing
    }
}
