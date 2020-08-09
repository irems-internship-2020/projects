package org.o7planning.tutorial.rcp.view.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.jface.viewers.ViewerFilter;

public class Person extends ViewerFilter {
    private String firstName;
    private String lastName;
    private boolean married;
    private String gender;
    private Integer age;
    private String searchString;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
            this);

    public Person() {
    }

    public Person(String firstName, String lastName, String gender,
            boolean married) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.married = married;
        
    }
 
    public void setSearchText(String s) {
        // ensure that the value can be used for matching
        this.searchString = ".*" + s + ".*";
    }
    
    public void addPropertyChangeListener(String propertyName,
            PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getGender() {
        return gender;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean isMarried() {
        return married;
    }

    public void setFirstName(String firstName) {
        propertyChangeSupport.firePropertyChange("firstName", this.firstName,
                this.firstName = firstName);
    }

    public void setGender(String gender) {
        propertyChangeSupport.firePropertyChange("gender", this.gender,
                this.gender = gender);
    }

    public void setLastName(String lastName) {
        propertyChangeSupport.firePropertyChange("lastName", this.lastName,
                this.lastName = lastName);
    }

    public void setMarried(boolean isMarried) {
        propertyChangeSupport.firePropertyChange("married", this.married,
                this.married = isMarried);
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        propertyChangeSupport.firePropertyChange("age", this.age,
                this.age = age);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		if (searchString == null || searchString.length() == 0) {
            return true;
        }
        Person p = (Person) element;
        if (p.getFirstName().matches(searchString)) {
            return true;
        }
        if (p.getLastName().matches(searchString)) {
            return true;
        }

        return false;
	}
	

}
