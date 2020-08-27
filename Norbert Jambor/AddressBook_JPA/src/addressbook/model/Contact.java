package addressbook.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
@Table(name="contact")
public class Contact extends BaseEntity{
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "phone_number")
	private String phoneNumber;
	private String email;
	
	//@OneToOne(mappedBy = "contact", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
	
	@OneToOne
	private Address address;
	
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

	public Contact() {
	}

	public Contact(Integer id, String firstName, String lastName, Address address, String phoneNumber, String email) {
		super(id);
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

	public String getLastName() {
		return lastName;
	}
	
	public Address getAddress() {
		return address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setFirstName(String firstName) {
		propertyChangeSupport.firePropertyChange("firstName", this.firstName, this.firstName = firstName);
	}

	public void setLastName(String lastName) {
		propertyChangeSupport.firePropertyChange("lastName", this.lastName, this.lastName = lastName);
	}
	
	public void setAddress(Address address) {
		propertyChangeSupport.firePropertyChange("address", this.address, this.address = address);
	}

	public void setPhoneNumber(String phoneNumber) {
		propertyChangeSupport.firePropertyChange("married", this.phoneNumber, this.phoneNumber = phoneNumber);
	}

	public void setEmail(String email) {
		propertyChangeSupport.firePropertyChange("married", this.email, this.email = email);
	}
}