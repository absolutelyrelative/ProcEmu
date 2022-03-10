package View.Listeners;

import Components.CellAbstractModel;
import ProcessorEmulator.InstructionExecutor;
import Util.AbstractCellTable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RegisterListener implements ActionListener {

    private ArrayList<JTextField> registerfields;
    private JTextField currentinstructiontextfield;
    private JButton executebutton;
    private AbstractCellTable instructionmemory;
    private AbstractCellTable datamemory;
    private JTextField programcounterfield;
    private JButton loadprogrambutton;

    public RegisterListener(ArrayList<JTextField> registerfields, JTextField currentinstructiontextfield,
                            JButton executebutton, AbstractCellTable instructionmemory, AbstractCellTable datamemory,
                            JTextField programcounterfield, JButton loadprogrambutton){
        this.registerfields = registerfields;
        this.currentinstructiontextfield = currentinstructiontextfield;
        this.executebutton = executebutton;
        this.instructionmemory = instructionmemory;
        this.datamemory = datamemory;
        this.programcounterfield = programcounterfield;
        this.loadprogrambutton = loadprogrambutton;
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource().equals(executebutton)){
            InstructionExecutor executor = new InstructionExecutor(registerfields, currentinstructiontextfield, executebutton, instructionmemory,
                    datamemory, programcounterfield);
            executor.execute();
            /*CellAbstractModel nextinstruction = instructionmemory.getnext();
            if(nextinstruction != null){
                currentinstructiontextfield.setText(nextinstruction.getInstruction());
                programcounterfield.setText(String.valueOf(nextinstruction.getAddress()));
            } else {
                JOptionPane.showMessageDialog(null,"No next instruction to execute.");
            }*/
        }
        if(e.getSource().equals(loadprogrambutton)){
            if(instructionmemory.getdata().size() > 1 ) {
                programcounterfield.setText(String.format("%08X",instructionmemory.getValueAt((int) (1), 0)));
                currentinstructiontextfield.setText(String.valueOf(instructionmemory.getValueAt(0,2)));
            } else if(instructionmemory.getdata().size() == 1){
                programcounterfield.setText(String.format("%08X", instructionmemory.getValueAt(0,0)));
                currentinstructiontextfield.setText(String.valueOf(instructionmemory.getValueAt(0,2)));

            } else {
                JOptionPane.showMessageDialog(null,"No starting point defined for program.");
            }
        }
    }
}
