package rcpworkbenchtutorial.view;

import java.util.ArrayList;
import java.util.List;

public enum ModelProvider {
    INSTANCE;

    private List<Person> persons;

    private ModelProvider() {
        persons = new ArrayList<Person>();
        // Image here some fancy database access to read the persons and to
        // put them into the model
        persons.add(new Person("Rainer", "Zufall", "male", "1"));
        persons.add(new Person("Reiner", "Babbel", "male", "1"));
        persons.add(new Person("Marie", "Dortmund", "female", "1"));
        persons.add(new Person("Holger", "Adams", "male", "1"));
        persons.add(new Person("Juliane", "Adams", "female", "1"));
    }

    public List<Person> getPersons() {
        return persons;
    }

}