package com.addressBook;

import java.util.List;

import javax.ejb.Remote;
import javax.persistence.EntityManager;

import com.addressBook.model.Contact;

@Remote
public interface AddressBookRemote {
	
	public EntityManager estabelishConnection();
	public void dataBaseInsert(Contact contact);
	public void beginTransaction();
	public void dataBaseDelete(Contact contact);
	public void closeTransaction();
	public List<Contact> dataBaseLoad();
	public Contact dataBaseUpdate(Contact contact);
}
