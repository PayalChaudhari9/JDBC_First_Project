package servlets_http;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CustomerCRUD {
	
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlets?user=root&password=root");
		return connection;
	}
	
	public int saveCustomer(Customer customer) throws SQLException, ClassNotFoundException {
		
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO(ID,NAME,PHONE,EMAIL,PASSWORD) VALUES (?,?,?,?,?)");
		
        preparedStatement.setInt(1, customer.getId());
        preparedStatement.setString(2, customer.getName());
        preparedStatement.setLong(3,customer.getPhone() );
        preparedStatement.setString(4, customer.getEmail());
        preparedStatement.setString(5, customer.getPassword());
        
		int result = preparedStatement.executeUpdate();
		connection.close();		
		
			return result;
	}
	
	public String getPassword(String email) throws ClassNotFoundException, SQLException {
		
		Connection connection = getConnection();
		
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT PASSWORD FROM CUSTOMER WHERE EMAIL=?");
		
		preparedStatement.setString(1, email);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		String password = null;
		
		while(resultSet.next()) {
			password = resultSet.getString("password");
	}
		connection.close();
		return password;
		
	}

}
