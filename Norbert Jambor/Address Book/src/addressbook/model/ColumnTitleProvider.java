package addressbook.model;

public enum ColumnTitleProvider {
	ID("ID"), FIRSTNAME("First Name"), LASTNAME("Last Name"), STREET("Street"), PHONENUMBER("Phone Number"), EMAIL("Email");

	private String text;

	ColumnTitleProvider(final String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
