package addressbook.server;

import java.util.List;

import javax.ejb.Remote;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import addressbook.model.Contact;

@Remote
public interface JpaOperationsRemote {
	public EntityManager createEntityManager();

	public void insertJpa(Contact contact);

	public Contact updateJpa(Contact contact);

	public void beginTransaction();

	public void closeTransaction();

	public void deleteJpa(Contact contact);

	public List<Contact> loadJpa();
}
