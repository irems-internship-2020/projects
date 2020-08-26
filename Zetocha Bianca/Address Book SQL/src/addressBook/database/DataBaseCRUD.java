package addressBook.database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import addressBook.tableviewer.model.Address;
import addressBook.tableviewer.model.Contact;

public class DataBaseCRUD {

	private Connection connect = null;
	String sql;

	public Statement estabelishConnection() throws Exception {
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\bbia5\\git\\projects\\Zetocha Bianca\\Address Book\\src\\addressBook\\database\\connection.prop");
		Properties p = new Properties();
		p.load(fis);
		String dname = (String) p.get("Dname");
		String url = (String) p.get("URL");
		String username = (String) p.get("Uname");
		String password = (String) p.get("password");
		Class.forName(dname);
		connect = DriverManager.getConnection(url, username, password);
		return connect.createStatement();
 
	}

	public void dataBaseInsert(Contact contact) throws Exception {
		sql = " with insert_query as (INSERT INTO \"AddressBookSchema\".\"Address\" (country, city, street, postal_code)"
				+ "VALUES ('" + contact.getAddress().getCountry() + "','" + contact.getAddress().getCity() + "','"
				+ contact.getAddress().getStreet() + "','" + contact.getAddress().getPostalCode() + "')"
				+ "returning address_id ) INSERT INTO \"AddressBookSchema\".\"Contact\" (first_name, last_name, phone_number, email, address_fk)"
				+ "VALUES ('" + contact.getFirstName() + "','" + contact.getLastName() + "','"
				+ contact.getPhoneNumber() + "','" + contact.getEmail() + "',(select \"address_id\" FROM insert_query))";
		estabelishConnection().executeUpdate(sql);
	}

	public void dataBaseUpdate(Contact contact) throws Exception {
		
		sql = " with update_query as (UPDATE \"AddressBookSchema\".\"Address\" "
				+ " SET " + "country = '" + contact.getAddress().getCountry() + "',"
				+" city = '" + contact.getAddress().getCity() + "',"
				+ " street = '" + contact.getAddress().getStreet() + "',"
				+ " postal_code = '" + contact.getAddress().getPostalCode()
				+ "' returning address_id) UPDATE \"AddressBookSchema\".\"Contact\" "
				+ " SET " + " first_name = '" + contact.getFirstName() + "',"
				+ " last_name = '" + contact.getLastName() + "',"
				+ " phone_number = '" + contact.getPhoneNumber() + "',"
				+ " email = '" + contact.getEmail() + "',"
				+ " address_fk = (select \"address_id\" from update_query) "
				+ " WHERE address_fk = (select \"address_id\" from update_query);";
		estabelishConnection().executeUpdate(sql);
	}

	public void dataBaseDelete(Contact contact) throws Exception {
		sql = "with delete_query as (DELETE FROM \"AddressBookSchema\".\"Contact\" WHERE contact_id  = " +contact.getId() +"returning address_fk)  " + 
				"DELETE FROM \"AddressBookSchema\".\"Address\" WHERE address_id IN (SELECT address_fk FROM delete_query)";
		estabelishConnection().executeUpdate(sql);
	}

	public String dataBaseLoad() throws Exception {
		sql = "SELECT * FROM \"AddressBookSchema\".\"Contact\" a " 
				+ "FULL JOIN \"AddressBookSchema\".\"Address\" b "
				+ " ON a.address_fk = b.address_id " ;
		return sql;
	}

}
