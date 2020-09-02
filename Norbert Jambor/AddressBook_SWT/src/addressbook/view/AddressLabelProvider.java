package addressbook.view;

import java.util.List;

import org.eclipse.jface.viewers.TableViewerColumn;

import addressbook.model.Contact;

public class AddressLabelProvider extends BaseColumnsLabels {
		
	public AddressLabelProvider(final List<TableViewerColumn> columnList){
		super(columnList);
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
		
	    case "Country":
		return contact.getAddress().getCountry();
		
	    case "City":
		return contact.getAddress().getCity();
		
	    case "Street":
		return contact.getAddress().getStreet();
		
	    case "Postal Code":
		return contact.getAddress().getPostalCode().toString();
		
		default:
			throw new IllegalArgumentException("Not implemented");
	    }
	}
		return null;
	}
}
