package addressBook.tableviewer.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Contact {
	private Integer id;
	private String firstName;
	private String lastName;
	private Address address;
	private String phoneNumber;
	private String email;
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

	public Contact() {

	}

	public Contact(Integer id,String firstName, String lastName, Address address, String phoneNumber, String email) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		propertyChangeSupport.firePropertyChange("firstName", this.firstName,
                this.firstName = firstName);
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		 propertyChangeSupport.firePropertyChange("lastName", this.lastName,
	                this.lastName = lastName);
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		 this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		propertyChangeSupport.firePropertyChange("phoneNumber", this.phoneNumber, this.phoneNumber = phoneNumber);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		propertyChangeSupport.firePropertyChange("email", this.email, this.email = email);
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		propertyChangeSupport.firePropertyChange("id", this.id, this.id = id);
	}



}
