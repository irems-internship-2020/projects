package com.addressBook;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.addressBook.model.Contact;

/**
 * Session Bean implementation class AddressBook
 */
@Stateless
@LocalBean
public class AddressBook implements AddressBookRemote {

	private EntityManager em;

	public AddressBook() {
		this.em = estabelishConnection();
		em.getTransaction().begin();
	}

	public EntityManager estabelishConnection() {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("AddressBook_EJB");
		return emf.createEntityManager();
	}

	public void dataBaseInsert(Contact contact) {
			em.persist(contact);
			em.getTransaction().commit();
	}
	public void beginTransaction() {
		if (!em.getTransaction().isActive()) {
			em.getTransaction().begin();
		}
	}

	public void dataBaseDelete(Contact contact) {
		contact = em.find(contact.getClass(), contact.getId());
		em.remove(contact);
	}

	public void closeTransaction() {
		if (!em.getTransaction().isActive()) {
			em.getTransaction().begin();
		}
		em.getTransaction().commit();
	}

	public List<Contact> dataBaseLoad() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Contact> cq = cb.createQuery(Contact.class);
		Root<Contact> rootEntry = cq.from(Contact.class);
		CriteriaQuery<Contact> all = cq.select(rootEntry);
		TypedQuery<Contact> allQuery = em.createQuery(all);
		return allQuery.getResultList();

	}

	public Contact dataBaseUpdate(Contact contact) {
		contact = em.find(contact.getClass(), contact.getId());
		return contact;
	}

}
