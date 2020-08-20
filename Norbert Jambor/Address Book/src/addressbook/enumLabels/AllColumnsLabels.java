package addressbook.enumLabels;

public enum AllColumnsLabels {
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

	AllColumnsLabels(final String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
