package View.Listeners;

import Assembler.Encoder;
import Assembler.IMUploader;
import Components.InstructionMemory;
import Util.AbstractCellTable;
import Util.Result;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class AssemblerListener implements ActionListener {
    JTextArea inputarea;
    JTextArea outputarea;
    JButton assemble;
    JButton upload;
    AbstractCellTable datamemory;

    public AssemblerListener(JTextArea inputarea, JTextArea outputarea, JButton assemble, JButton upload, AbstractCellTable datamemory){
        this.inputarea = inputarea;
        this.outputarea = outputarea;
        this.assemble = assemble;
        this.upload = upload;
        this.datamemory = datamemory;
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource().equals(assemble)){
            Collection<String> unfilteredlist = new ArrayList<>(Arrays.asList(inputarea.getText().split("\n")));
            unfilteredlist.removeIf(s -> s.matches("[\\n\\r\\s]*")); //Remove empty lines
            ArrayList<String> instructions = new ArrayList<String>(unfilteredlist);
            ArrayList<String> machinecode = new ArrayList<>();

            Encoder ec = new Encoder();
            for(String s : instructions) {
                Result encoderresult = ec.GetMachineCode(s);
                if(encoderresult.IsSuccessful()){
                    machinecode.add(encoderresult.GetMessage());
                } else {
                    JOptionPane.showMessageDialog(null,"Error assemblying instruction '" + s + "'");
                }
            }
            outputarea.setText(String.join("\n", machinecode));
            inputarea.setText(String.join("\n", instructions));
        }
        if(e.getSource().equals(upload)){
            Collection<String> unfilteredlist = new ArrayList<>(Arrays.asList(outputarea.getText().split("\n")));
            unfilteredlist.removeIf(s -> s.matches("[\\n\\r\\s]*")); //Remove empty lines
            ArrayList<String> machinecode = new ArrayList<>(unfilteredlist);

            IMUploader.getInstance().DecodeAndUploadInstructions(machinecode, datamemory, null, 0, true);
        }
    }
}
