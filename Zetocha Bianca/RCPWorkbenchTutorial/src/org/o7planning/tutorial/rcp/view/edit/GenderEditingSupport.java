package org.o7planning.tutorial.rcp.view.edit;

import javax.swing.CellEditor;

import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.o7planning.tutorial.rcp.view.model.Person;

public class GenderEditingSupport extends EditingSupport {

    private final TableViewer viewer;

    public GenderEditingSupport(TableViewer viewer) {
        super(viewer);
        this.viewer = viewer;
    }

    protected org.eclipse.jface.viewers.CellEditor getCellEditor(Object element) {
        String[] gender = new String[2];
        gender[0] = "male";
        gender[1] = "female";

        return (org.eclipse.jface.viewers.CellEditor) new ComboBoxCellEditor(viewer.getTable(), gender);
    }

    protected boolean canEdit(Object element) {
        return true;
    }

    protected Object getValue(Object element) {
        Person person = (Person) element;
        if (person.getGender().equals("male")) {
            return 0;
        }
        return 1;

    }

    protected void setValue(Object element, Object value) {
        Person pers = (Person) element;
        if (((Integer) value) == 0) {
            pers.setGender("male");
        } else {
            pers.setGender("female");
        }
        viewer.update(element, null);
    }
}