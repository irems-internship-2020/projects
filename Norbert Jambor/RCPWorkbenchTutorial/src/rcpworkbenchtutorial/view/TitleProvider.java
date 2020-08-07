package rcpworkbenchtutorial.view;

public enum TitleProvider {
	FIRSTNAME("First name"), LASTNAME("Last name"), GENDER("Gender"), MARRIED("Married");

	private String text;

	TitleProvider(final String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
