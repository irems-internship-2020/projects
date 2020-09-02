package addressbook.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Address {
	private String country;
	private String city;
	private String street;
	private String postalCode;
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

	public Address() {
	}

	public Address(String country, String city, String street, String postalCode) {
		super();
		this.street = street;
		this.country = country;
		this.city = city;
		this.postalCode = postalCode;
	}

	public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}

	public String getStreet() {
		return street;
	}

	public String getCountry() {
		return country;
	}

	public String getCity() {
		return city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setStreet(String street) {
		propertyChangeSupport.firePropertyChange("lastName", this.street, this.street = street);
	}

	public void setCountry(String country) {
		propertyChangeSupport.firePropertyChange("firstName", this.country, this.country = country);
	}

	public void setCity(String city) {
		propertyChangeSupport.firePropertyChange("lastName", this.city, this.city = city);
	}

	public void setPostalCode(String postalCode) {
		propertyChangeSupport.firePropertyChange("lastName", this.postalCode, this.postalCode = postalCode);
	}

}