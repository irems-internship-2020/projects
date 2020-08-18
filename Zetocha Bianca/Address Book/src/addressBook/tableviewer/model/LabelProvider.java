package addressBook.tableviewer.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.graphics.Image;

public class LabelProvider implements ITableLabelProvider{

	private List<TableViewerColumn> columnList = new ArrayList<TableViewerColumn>();

	public LabelProvider(final List<TableViewerColumn> columnList){
			this.columnList = columnList;
		}
	
	@Override
	public void addListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub
		
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
	public Image getColumnImage(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		if(element instanceof Contact) {
			Contact contact = (Contact) element;
			switch(columnList.get(columnIndex).getColumn().getText())
			{
			case "id":
				return contact.getId().toString();
			case "First Name":
				return contact.getFirstName();
			case "Last Name": 
				return contact.getLastName();
			case "Address":
				return contact.getAddress().getStreet();
			case "Phone Number":
				return contact.getPhoneNumber();
			case "Email": 
				return contact.getEmail();
			default:
				throw new IllegalArgumentException("Not implemented");
					
			}
		}
		return null;
	}
	
	

}
