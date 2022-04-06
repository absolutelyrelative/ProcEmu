package ProcessorEmulator;

import Assembler.Encoder;
import Util.AbstractCellTable;
import Util.Result;

import javax.swing.*;
import java.util.ArrayList;

public class InstructionExecutor {

    private ArrayList<JTextField> registerfields;
    private JTextField currentinstructiontextfield;
    private JButton executebutton;
    private AbstractCellTable instructionmemory;
    private AbstractCellTable datamemory;
    private JTextField programcounterfield;

    public InstructionExecutor(ArrayList<JTextField> registerfields, JTextField currentinstructiontextfield, JButton executebutton, AbstractCellTable instructionmemory, AbstractCellTable datamemory, JTextField programcounterfield) {
        this.registerfields = registerfields;
        this.currentinstructiontextfield = currentinstructiontextfield;
        this.executebutton = executebutton;
        this.instructionmemory = instructionmemory;
        this.datamemory = datamemory;
        this.programcounterfield = programcounterfield;
    }

    //Executes current instruction in text field, increments PC, loads next instruction
    public void execute(){
        //Parse first command
        String[] output = currentinstructiontextfield.getText().split("[ ,()]+"); //TODO: Find better Regex, ex. [\p{Punct}\s]+
        switch (output[0].toUpperCase()) {
            case "ADD": {
                //Parse registers from instruction
                int rd, rs, rt;
                Result regresult = Encoder.getInstance().RegisterFinder(output[1]);
                if (regresult.IsSuccessful()) {
                    rd = Integer.parseInt(regresult.GetMessage());
                } else {
                    JOptionPane.showMessageDialog(null,"ERROR: Could not parse register.");
                    break;
                }
                regresult = Encoder.getInstance().RegisterFinder(output[2]);
                if (regresult.IsSuccessful()) {
                    rs = Integer.parseInt(regresult.GetMessage());
                } else {
                    JOptionPane.showMessageDialog(null,"ERROR: Could not parse register.");
                    break;
                }
                regresult = Encoder.getInstance().RegisterFinder(output[3]);
                if (regresult.IsSuccessful()) {
                    rt = Integer.parseInt(regresult.GetMessage());
                } else {
                    JOptionPane.showMessageDialog(null,"ERROR: Could not parse register.");
                    break;
                }

                //Get values from registers to add (rs + rt) |-> rd
                try {
                    long rsvalue = Long.parseLong(registerfields.get(rs - 1).getText(), 16);
                    long rtvalue = Long.parseLong(registerfields.get(rt - 1).getText(), 16);
                    int result = (int)((rsvalue + rtvalue) & 0xFFFF_FFFF);
                    //Save result
                    registerfields.get(rd - 1).setText(String.format("%08X", result));
                } catch (Exception e){ //In case wrong data is manually inserted
                    JOptionPane.showMessageDialog(null,"ERROR: Wrong data inserted in register. Program will not continue execution.");
                    return;
                }
                break;
            }

            //TODO: Check for overflow
            case "SUB": {
                //Parse registers from instruction
                int rd, rs, rt;
                Result regresult = Encoder.getInstance().RegisterFinder(output[1]);
                if (regresult.IsSuccessful()) {
                    rd = Integer.parseInt(regresult.GetMessage());
                } else {
                    JOptionPane.showMessageDialog(null,"ERROR: Could not parse register.");
                    break;
                }
                regresult = Encoder.getInstance().RegisterFinder(output[2]);
                if (regresult.IsSuccessful()) {
                    rs = Integer.parseInt(regresult.GetMessage());
                } else {
                    JOptionPane.showMessageDialog(null,"ERROR: Could not parse register.");
                    break;
                }
                regresult = Encoder.getInstance().RegisterFinder(output[3]);
                if (regresult.IsSuccessful()) {
                    rt = Integer.parseInt(regresult.GetMessage());
                } else {
                    JOptionPane.showMessageDialog(null,"ERROR: Could not parse register.");
                    break;
                }

                //Get values from registers to add (rs - rt) |-> rd
                try {
                    long rsvalue = Long.parseLong(registerfields.get(rs - 1).getText(), 16);
                    long rtvalue = Long.parseLong(registerfields.get(rt - 1).getText(), 16);
                    int result = (int)((rsvalue - rtvalue) & 0xFFFF_FFFF);
                    //Save result
                    registerfields.get(rd - 1).setText(String.format("%08X", result));
                } catch (Exception e){ //In case wrong data is manually inserted
                    JOptionPane.showMessageDialog(null,"ERROR: Wrong data inserted in register. Program will not continue execution.");
                    return;
                }
                break;
            }
        }

        //Sets PC to next instruction
        long nextinstruction_logicalposition = Long.parseUnsignedLong(programcounterfield.getText());
        long nextinstruction_virtualposition = nextinstruction_logicalposition / 32; //TODO: Remove hard-code of 32 bits
        try {
            if ((long) instructionmemory.getValueAt((int) nextinstruction_virtualposition, 0) == nextinstruction_logicalposition) {
                //Safety check
                if (instructionmemory.getValueAt((int) (nextinstruction_virtualposition + 1), 0) != null) { //if next PC is not invalid
                    programcounterfield.setText(String.format("%08X", instructionmemory.getValueAt((int) (nextinstruction_virtualposition + 1), 0)));
                    currentinstructiontextfield.setText(String.valueOf(instructionmemory.getValueAt((int) (nextinstruction_virtualposition), 2)));
                } else { //Reset program
                    currentinstructiontextfield.setText(String.valueOf(instructionmemory.getValueAt((int) (nextinstruction_virtualposition), 2)));
                    JOptionPane.showMessageDialog(null, "Program terminated. PC will be reset to 0.");
                    SetPCToPosition(0);
                }
            } else {
                //Does not match
            }
        } catch (NullPointerException nullpointerexception){
            JOptionPane.showMessageDialog(null, "There is no next instruction to execute.");
        }
    }

    //Sets PC to specific address
    public void SetPCToPosition(long virtualposition){
        if( instructionmemory.getValueAt((int)virtualposition, 0) != null  ){
            programcounterfield.setText(String.format("%08X",instructionmemory.getValueAt((int)virtualposition, 0)));
        }
    }
}
