package addressbook.model;

public enum ContactColumnLabels {
	ID("ID"), FIRSTNAME("First Name"), LASTNAME("Last Name"), STREET("Street"), PHONENUMBER("Phone Number"), EMAIL("Email");

	private String text;

	ContactColumnLabels(final String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}