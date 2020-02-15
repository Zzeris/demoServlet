package br.com.demoServlet.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.demoServlet.interfaces.UserInterface;
import br.com.demoServlet.jdbc.factory.ConnectionFactory;
import br.com.demoServlet.models.User;

public class UserDao implements UserInterface {
	private Connection connection;
	
	public UserDao(){
		try {
			this.connection = ConnectionFactory.getConnect();
		} catch (SQLException erro) {
			throw new RuntimeException(erro);
		} catch (ClassNotFoundException erro) {
			throw new RuntimeException(erro);
		}
	}

	@Override
	public List<User> show() {
		List<User> users = new ArrayList<User>();
		String sql = "select * from users;";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getInt("id"));
				user.setName(resultSet.getString("name"));
				user.setEmail(resultSet.getString("email"));
				users.add(user);
			}
			resultSet.close();
			ps.close();
		} catch (SQLException erro) {
			throw new RuntimeException(erro);
		}
		return users;
	}

	@Override
	public void store(User user) {
		String sql = "insert into users (name, email) values (?, ?);";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.execute();
			ps.close();
		} catch (SQLException erro) {
			throw new RuntimeException(erro);
		}
	}

	@Override
	public void update(User user) {
		String sql = "update users set name=?, email=? where id = ?;";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.setInt(3, user.getId());
			ps.execute();
			ps.close();
		} catch (SQLException erro) {
			throw new RuntimeException(erro);
		}
	}

	@Override
	public void remove(Integer userId) {
		String sql = "delete from users where id = ?;";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, userId);
			ps.execute();
			ps.close();
		} catch (SQLException erro) {
			throw new RuntimeException(erro);
		}
	}
	
	@Override
	public User index(Integer userId) {
		User user = new User();
		String sql = "select id, name, email from users where id = ?;";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, userId);
			ResultSet resultSet = ps.executeQuery();
			if (resultSet.next()) {
				user = new User(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3));
			}
			resultSet.close();
			ps.close();
		} catch (SQLException erro) {
			throw new RuntimeException(erro);
		}
		return user;
	}

}
