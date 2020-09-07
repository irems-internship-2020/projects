package com.addressBook.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

import javax.persistence.*;
@MappedSuperclass
public class BaseEntity implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

	public BaseEntity() {
	}

	public BaseEntity(Integer id) {
		this.id=id;
	}

	public Integer getId() {
		return id;
	}
	
	public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}

	public void setId(Integer id) {
		propertyChangeSupport.firePropertyChange("lastName", this.id, this.id = id);
	}
}