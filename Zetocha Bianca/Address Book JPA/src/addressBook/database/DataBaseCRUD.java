package addressBook.database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.List;

import javax.persistence.*;

import addressBook.tableviewer.model.Address;
import addressBook.tableviewer.model.Contact;

public class DataBaseCRUD {

	private EntityManager em;

	public DataBaseCRUD() {
		this.em = estabelishConnection();
		em.getTransaction().begin();
	}

	public EntityManager estabelishConnection() {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("AddressBook_JPA");
		return emf.createEntityManager();
	}

	public void dataBaseInsert(Contact contact) {
			Address address = contact.getAddress();
			em.persist(contact);
			em.persist(address);
			em.getTransaction().commit();
	}
	public void beginTransaction() {
		if (!em.getTransaction().isActive()) {
			em.getTransaction().begin();
		}
	}

	public void dataBaseDelete(Contact contact) {
		Address address = contact.getAddress();
		address = em.find(address.getClass(), address.getId());
		contact = em.find(contact.getClass(), contact.getId());
		em.remove(address);
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
