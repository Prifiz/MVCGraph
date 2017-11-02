package view;

import javax.swing.*;

public class TableFloatEditor extends DefaultCellEditor {
    InputVerifier verifier = null;


    public TableFloatEditor(InputVerifier verifier) {
        super(new JTextField());
        this.verifier = verifier;
    }

    @Override
    public boolean stopCellEditing() {
        if(verifier.verify(editorComponent)) {
            return stopCellEditing();
        } else {
            cancelCellEditing();
            return false;
        }
    }
}
