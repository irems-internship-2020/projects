package org.o7planning.tutorial.rcp.view.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.graphics.Image;

public class ContactsLabelProvider implements ITableLabelProvider {
	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}

	private List<TableViewerColumn> columnList = new ArrayList<TableViewerColumn>();

	public ContactsLabelProvider(final List<TableViewerColumn> columnList) {
		this.columnList = columnList;
	}

	@Override
	public void addListener(ILabelProviderListener listener) {

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getColumnText(Object element, int columnIndex) {

		if(element instanceof Person) {
			Person person = (Person) element;
		switch (columnList.get(columnIndex).getColumn().getText())
	    {
	    case "First Name":
    	return person.getFirstName();
	    case"Last Name":
	    	return person.getLastName();
	    case "Gender":
	    	return person.getGender();
	    case "Married":
	    	return "1";
	    default: 
	    	throw new IllegalArgumentException("Not implemented");
	    }
		
	}
		return null;
	}
}
