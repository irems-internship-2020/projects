package addressBook.tableviewer.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.eclipse.jface.viewers.Viewer;

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
		
		this.country = country;
		this.city = city;
		this.street = street;
		this.postalCode = postalCode;
	}

	public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);

	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		propertyChangeSupport.firePropertyChange("country", this.country, this.country=country);
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		propertyChangeSupport.firePropertyChange("city", this.city, this.city = city);
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		propertyChangeSupport.firePropertyChange("street", this.street, this.street=street);
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		propertyChangeSupport.firePropertyChange("postalCode", this.postalCode, this.postalCode = postalCode);
	}


}
