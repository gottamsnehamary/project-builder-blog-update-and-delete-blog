package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;
import utility.ConnectionManager;

public class UserDAO implements UserDaoInterface {

	public int signUp(User user) {
		String INSERT_USERS_SQL = "INSERT INTO USERS(email, password)VALUES(?,?)";

		int result = 0;
		try
		{
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);
			preparedStatement.setString(1,user.getEmail());
			preparedStatement.setString(2,user.getPassword());
			System.out.println(preparedStatement);
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return result;
	}
	
	public boolean loginUser(User user) {
		boolean status = false;
		try{
			Connection connection = ConnectionManager.getConnection();
		
		PreparedStatement preparedStatement = connection.prepareStatement("select * from users where email = ? and password = ? ");
		
			preparedStatement.setString(1, user.getEmail());
			preparedStatement.setString(2, user.getPassword());

			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			status = rs.next();

		} catch (SQLException e) {
			System.out.println(e);
		}
		return status;
	}

}
