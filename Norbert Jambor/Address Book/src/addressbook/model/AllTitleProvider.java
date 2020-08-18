package addressbook.model;

public enum AllTitleProvider {
	ID("ID"), 
	FIRSTNAME("First Name"), 
	LASTNAME("Last Name"), 
	COUNTRY("Country"), 
	CITY("City"), 
	STREET("Street"),
	POSTAL("Postal Code"), 
	PHONENUMBER("Phone Number"), 
	EMAIL("Email");

	private String text;

	AllTitleProvider(final String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
