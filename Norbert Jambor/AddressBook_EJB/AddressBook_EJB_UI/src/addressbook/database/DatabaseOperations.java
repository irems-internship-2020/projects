package addressbook.database;

import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import addressbook.server.JpaOperations;
import addressbook.server.JpaOperationsRemote;

public class DatabaseOperations {

	public JpaOperationsRemote getDatabase() throws NamingException {
		JpaOperationsRemote bean = null;

		Properties props = new Properties();
		props.put("java.naming.factory.url.pkgs", "org.jboss.ejb.client.naming");
		InitialContext context = new InitialContext(props);

		String appName = "";
		String moduleName = "AddressBook_EJB_Server";
		String distinctName = "";
		String beanName = JpaOperations.class.getSimpleName();
		String interfaceName = JpaOperationsRemote.class.getName();
		String name = "ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + interfaceName;
		System.out.println(name);
		bean = (JpaOperationsRemote) context.lookup(name);

		return bean;
	}
}