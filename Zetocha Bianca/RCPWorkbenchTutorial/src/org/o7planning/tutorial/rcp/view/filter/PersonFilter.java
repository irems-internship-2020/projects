package org.o7planning.tutorial.rcp.view.filter;



import java.util.Comparator;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.o7planning.tutorial.rcp.view.model.Person;
import org.o7planning.tutorial.rcp.view.sorter.MyViewerComparator;

public class PersonFilter extends ViewerFilter {

    private PersonFilter filter;
    private String searchString;
    private Viewer viewer;
    private Comparator comparator;

    public void setSearchText(String s) {
        // ensure that the value can be used for matching
        this.searchString = ".*" + s + ".*";
    }

    @Override
    public boolean select(Viewer viewer,
            Object parentElement,
            Object element) {
        if (searchString == null || searchString.length() == 0) {
            return true;
        }
        Person p = (Person) element;
        if (p.getFirstName().matches(searchString)) {
            return true;
        }
        if (p.getLastName().matches(searchString)) {
            return true;
        }

        return false;
    }
 // sort according to due date
 
}

