package addressbook.model;

public enum AddressTitleProvider {
	ID("ID"), 
	FIRSTNAME("First Name"), 
	LASTNAME("Last Name"), 
	COUNTRY("Country"), 
	City("City"), 
	STREET("Street"),
	POSTAL("Postal Code");

	private String text;

	AddressTitleProvider(final String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
