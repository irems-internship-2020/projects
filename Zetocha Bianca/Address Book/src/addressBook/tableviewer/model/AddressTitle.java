package addressBook.tableviewer.model;

public enum AddressTitle {
	ID("id"), FIRSTNAME("First Name"), LASTNAME("Last Name"), COUNTRY("Country"), CITY("City"), STREET("Street"),
	POSTALCODE("Postal Code");

	private String txt;

	AddressTitle(String txt) {
		this.txt = txt;
	}

	public String getTxt() {
		return txt;
	}
}
