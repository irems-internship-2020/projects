package addressBook.bean;

import java.util.*;
import javax.naming.*;

import com.addressBook.AddressBook;
import com.addressBook.AddressBookRemote;

import javax.ejb.*;

public class DatabaseHandler {

	public AddressBookRemote getBean() {
		AddressBookRemote bean = null;
		try {
			Properties props = new Properties();
			props.put("java.naming.factory.url.pkgs", "org.jboss.ejb.client.naming");
			InitialContext context = new InitialContext(props);

			String appName = "";
			String moduleName = "i2_AB_EJB";
			String distinctName = "";
			String beanName = AddressBook.class.getSimpleName();
			String interfaceName = AddressBookRemote.class.getName();
			String name = "ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + interfaceName;
			System.out.println(name);
			bean = (AddressBookRemote) context.lookup(name);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}

}
