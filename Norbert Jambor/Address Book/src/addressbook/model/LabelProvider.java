package addressbook.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.graphics.Image;

public class LabelProvider implements ITableLabelProvider {
	
    private List<TableViewerColumn> columnList = new ArrayList<TableViewerColumn>();
	
	public LabelProvider(final List<TableViewerColumn> columnList){
		this.columnList = columnList;
	}

	@Override
	public void addListener(ILabelProviderListener listener) {}

	@Override
	public void dispose() {}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {}

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		if(element instanceof Contact) {
			Contact contact = (Contact) element;
		switch (columnList.get(columnIndex).getColumn().getText())
	    {
	    case "ID":
		return contact.getId().toString();
		
	    case "First Name":
		return contact.getFirstName();
		
	    case "Last Name":
		return contact.getLastName();
		
	    case "Street":
		return contact.getAddress().getStreet();
		
	    case "Phone Number":
		return contact.getPhoneNumber().toString();
		
	    case "Email":
		return contact.getEmail();
		
		default:
			throw new IllegalArgumentException("Not implemented");
	    }
	}
		return null;
	}
}
