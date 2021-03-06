package com.addressBook.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.persistence.*;

import com.addressBook.model.BaseEntity;
import com.addressBook.model.Contact;


@Entity
@Table(name = "Address", schema = "ABD")
public class Address extends BaseEntity {

	private static final long serialVersionUID = 1L;	
	@Column(name = "country")
	private String country;
	@Column(name = "city")
	private String city;
	@Column(name = "street")
	private String street;
	@Column(name = "postal_code")
	private String postalCode;
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	 @OneToOne(mappedBy = "address")
	private Contact contact;
	public Address() {

	}

	public Address(String country, String city, String street, String postalCode) {

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
		propertyChangeSupport.firePropertyChange("country", this.country, this.country = country);
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
		propertyChangeSupport.firePropertyChange("street", this.street, this.street = street);
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		propertyChangeSupport.firePropertyChange("postalCode", this.postalCode, this.postalCode = postalCode);
	}

}
