package addressbook.model;

import java.util.ArrayList;
import java.util.List;

public enum ModelProvider {
    INSTANCE;

    private List<Contact> contacts;

    private ModelProvider() {
    	contacts = new ArrayList<Contact>();
    	contacts.add(new Contact(1, "Bruchi", "Seba", "War Street", 911, "lady@little.boss"));
    	contacts.add(new Contact(2, "Rola", "Szevasz", "Peace Street", 777, "man@big.boss"));
    }

    public List<Contact> getContacts() {
        return contacts;
    }

}