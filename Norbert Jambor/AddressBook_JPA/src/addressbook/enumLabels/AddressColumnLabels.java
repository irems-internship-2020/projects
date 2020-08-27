package addressbook.enumLabels;

public enum AddressColumnLabels {
	ID("ID"), 
	FIRSTNAME("First Name"), 
	LASTNAME("Last Name"), 
	COUNTRY("Country"), 
	City("City"), 
	STREET("Street"),
	POSTAL("Postal Code");

	private String text;

	AddressColumnLabels(final String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
