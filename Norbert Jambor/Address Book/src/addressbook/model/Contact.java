package addressbook.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Contact {
	private Integer id;
    private String firstName;
    private String lastName;
    private String street;
    private Integer phoneNumber;
    private String email;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
            this);

    public Contact() {
    }

    public Contact(Integer id, String firstName, String lastName, String street,
    		Integer phoneNumber, String email) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public void addPropertyChangeListener(String propertyName,
            PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }
    
    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getStreet() {
        return street;
    }
    
    public Integer getPhoneNumber() {
        return phoneNumber;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setId(Integer id) {
        propertyChangeSupport.firePropertyChange("firstName", this.id,
                this.id = id);
    }

    public void setFirstName(String firstName) {
        propertyChangeSupport.firePropertyChange("firstName", this.firstName,
                this.firstName = firstName);
    }

    public void setLastName(String lastName) {
        propertyChangeSupport.firePropertyChange("lastName", this.lastName,
                this.lastName = lastName);
    }
    
    public void setStreet(String street) {
        propertyChangeSupport.firePropertyChange("lastName", this.street,
                this.street = street);
    }

    public void setPhoneNumber(Integer phoneNumber) {
        propertyChangeSupport.firePropertyChange("married", this.phoneNumber,
                this.phoneNumber = phoneNumber);
    }
    
    public void setEmail(String email) {
        propertyChangeSupport.firePropertyChange("married", this.email,
                this.email = email);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

}