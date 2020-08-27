package Address.Book.Views;

import java.util.List;

import org.eclipse.jface.viewers.TableViewerColumn;
import addressBook.tableviewer.model.Contact;

public class LabelProviderContact extends LabelProvider {

	public LabelProviderContact(List<TableViewerColumn> columnList) {
		super(columnList);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		if (element instanceof Contact) {
			Contact contact = (Contact) element;
			switch (columnList.get(columnIndex).getColumn().getText()) {
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
