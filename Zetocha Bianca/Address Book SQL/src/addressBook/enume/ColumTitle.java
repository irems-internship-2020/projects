package addressBook.enume;

public enum ColumTitle {
	ID("id"), FIRSTNAME("First Name"), LASTNAME("Last Name"), ADDRESS("Address"), PHONENUMBER("Phone Number"),
	EMAIL("Email");

	private String txt;

	ColumTitle(String txt) {
		this.txt = txt;
	}

	public String getTxt() {
		return txt;
	}

}
