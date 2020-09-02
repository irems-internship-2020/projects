package addressbook.view;

import java.util.List;

import org.eclipse.jface.viewers.TableViewerColumn;

import addressbook.model.Contact;

public class ContactLabelProvider extends BaseColumnsLabels {

	public ContactLabelProvider(final List<TableViewerColumn> columnList) {
		super(columnList);
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		if (element instanceof Contact) {
			Contact contact = (Contact) element;
			switch (columnList.get(columnIndex).getColumn().getText()) {
			case "ID":
				return contact.getId().toString();

			case "First Name":
				return contact.getFirstName();

			case "Last Name":
				return contact.getLastName();

			case "Street":
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
