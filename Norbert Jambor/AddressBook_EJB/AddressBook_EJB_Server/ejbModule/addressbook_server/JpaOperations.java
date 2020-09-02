package addressbook_server;

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

import addressbook.model.Contact;

/**
 * Session Bean implementation class JpaOperations
 */
@Stateless
@LocalBean
public class JpaOperations implements JpaOperationsRemote {
	private EntityManager em;

	public JpaOperations() {
		this.em = createEntityManager();
		em.getTransaction().begin();
	}

	public EntityManager createEntityManager() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("AddressBook_JPA");
		return emf.createEntityManager();
	}

	public void insertJpa(Contact contact) {
		em.persist(contact);
		em.getTransaction().commit();
	}

	public Contact updateJpa(Contact contact) {
		contact = em.find(contact.getClass(), contact.getId());
		return contact;
	}

	public void beginTransaction() {
		if (!em.getTransaction().isActive()) {
			em.getTransaction().begin();
		}
	}

	public void closeTransaction() {
		if (!em.getTransaction().isActive()) {
			em.getTransaction().begin();
		}
		em.getTransaction().commit();
	}

	public void deleteJpa(Contact contact) {
		contact = em.find(contact.getClass(), contact.getId());
		em.remove(contact);
	}

	public List<Contact> loadJpa() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Contact> cq = cb.createQuery(Contact.class);
		Root<Contact> con = cq.from(Contact.class);
		CriteriaQuery<Contact> all = cq.select(con);
		TypedQuery<Contact> allQuery = em.createQuery(all);
		return allQuery.getResultList();
	}
}
