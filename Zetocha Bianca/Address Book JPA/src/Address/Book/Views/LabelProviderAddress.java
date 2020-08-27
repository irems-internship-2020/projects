package Address.Book.Views;

import java.util.List;
import org.eclipse.jface.viewers.TableViewerColumn;
import addressBook.tableviewer.model.Address;
import addressBook.tableviewer.model.Contact;

public class LabelProviderAddress extends LabelProvider {

	public LabelProviderAddress(List<TableViewerColumn> columnList) {
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
			case "Country":
				return contact.getAddress().getCountry();
			case "City":
				return contact.getAddress().getCity();
			case "Street":
				return contact.getAddress().getStreet();
			case "Postal Code":
				return contact.getAddress().getPostalCode();
			default:
				throw new IllegalArgumentException("Not implemented");
			}
		}
		return null;
	}
}