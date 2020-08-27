package addressBook.tableviewer.model;

import java.util.ArrayList;
import java.util.List;

public enum ModelProvider {
	INSTANCE;

	private List<Contact> contacts;

	private ModelProvider() {
		contacts = new ArrayList<Contact>();
		// Image here some fancy database access to read the persons and to
		// put them into the model
		contacts.add(new Contact("Maria", " Popescu", new Address("China", "Beijing", "str Principala","65892"),"076625386", "frumusica@yahoo.com"));
		contacts.add(new Contact( "Laviniu", "Szucs", new Address("Japonia", "Tokio", "Str Florilor", "95464"), "056465464", "gamerPeViata@yahoo.com"));
		contacts.add(new Contact( "Mihaela", "Suplacsa",new Address("Romania", "Bucuresti", "Str Masinilor", "13284"), "0786513284", "makeupArtist@yahoo.com"));
		contacts.add(new Contact("Sorin", "Craciun", new Address("Slovacia", "Bratislava", "Str Cadourilor", "32645"), "0765312645", "hohoho.mosCraciun@yahoo.com"));
	}

	public List<Contact> getContacts() {
		return contacts;
	}
}
