package org.o7planning.tutorial.rcp.view.model;

public enum ColumTitle {
	FIRSTNAME("Last Name"), LASTNAME("Last Name"), GENDER("Gender"), MARRIED("Married");

	private String s;

	public String getS() {
		return s;
	}

	ColumTitle(String string) {
		this.s = string;
	}

}
