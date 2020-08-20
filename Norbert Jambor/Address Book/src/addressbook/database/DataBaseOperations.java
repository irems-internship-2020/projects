package addressbook.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import addressbook.model.Contact;

public class DataBaseOperations {
		
	Connection connection;
	
	private String sql;
	
	public Statement establishConnection() throws Exception {
		
		String url = "jdbc:postgresql://localhost:5432/example";
		Properties props = new Properties();
		props.setProperty("user","postgres");
		props.setProperty("password","sqldatabase");
		props.setProperty("ssl","false");
		Connection conn = DriverManager.getConnection(url, props);
		return conn.createStatement();
//		ResultSet result = statement.executeQuery("select * from cars");
//		while (result.next()) {
//			System.out.println(result.getString("country") + " " + result.getString(2));
//		}
//		result.close();
//		statement.close();
//		connection.close();
	}
	
	public void insertDB(Contact contact) throws Exception {
		sql = " with insert_query as (INSERT INTO AddressBookSchema.Address (country, city, street, postal_code)"
			    + "VALUES ('" + contact.getAddress().getCountry() + "','" + contact.getAddress().getCity() + "','"
			    + contact.getAddress().getStreet() + "','" + contact.getAddress().getPostalCode() + "')"
			    + "returning address_id) INSERT INTO AddressBookSchema.Contact (first_name, last_name, phone_number, email, address_fk)"
			    + "VALUES ('" + contact.getFirstName() + "','" + contact.getLastName() + "','" + contact.getPhoneNumber()
			    + "','" + contact.getEmail() + "',(select address_id from insert_query))";
		establishConnection().executeUpdate(sql);
	}
	
	public void updateDB(Contact contact) throws Exception {
		sql = " with update_query as (UPDATE AddressBookSchema.Address"
			    + "SET " + "country=" + contact.getAddress().getCountry() + "','" +"city=" + contact.getAddress().getCity() + "','"
			    + "street=" + contact.getAddress().getStreet() + "','" + "postal_code=" + contact.getAddress().getPostalCode()
			    + "returning address_id) UPDATE INTO AddressBookSchema.Contact (first_name, last_name, phone_number, email, address_fk)"
			    + "SET " + "first_name=" + contact.getFirstName() + "','" + "last_name=" + contact.getLastName() + "','" + 
			    "phone_number=" + contact.getPhoneNumber() + "','" + "email=" + contact.getEmail() + "',(select address_id from insert_query)"
			    + "WHERE id=(select address_id from update_query))";
		establishConnection().executeUpdate(sql);
	}
	
	public void deleteDB(Contact contact) throws Exception{
		sql = " with delete_query as (DELETE FROM \"AddressBookSchema\".\"Contact\" WHERE contact_id = " +contact.getId() +"returning address_fk) "
				+ "DELETE FROM \"AddressBookSchema\".\"Address\" WHERE address_id IN (SELECT address_fk FROM delete_query)";
		establishConnection().executeUpdate(sql);
	}
	
	public String loadDB() throws SQLException{
		sql = " SELECT * FROM \"AddressBookSchema\".\"Contact\" a " +
			  " FULL JOIN \"AddressBookSchema\".\"Address\" b " +
			  " ON a.address_fk = b.address_id ";
		return sql;
	}
}