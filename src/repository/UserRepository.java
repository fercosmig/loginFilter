/**
 * 
 */
package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import model.User;
import util.ConnectionFactory;

/**
 * @author Fernando Costa Migliorini
 * @email fercosmig@gmail.com
 * @since Dec 27, 2019
 * @file repository.UserRepository.java
 */
public class UserRepository implements GenericRepository<User> {

	private static final Logger LOGGER = LogManager.getLogger(UserRepository.class.getName());

	/**
	 * Attributes
	 */
	private Connection conn;

	/**
	 * Constructors
	 */
	public UserRepository() {
		try {
			this.conn = ConnectionFactory.getConnection();
		} catch (Exception e) {
			LOGGER.error(e);
		}
	}

	/**
	 * Methods
	 */
	@Override
	public void create(User user) throws Exception {
	}

	@Override
	public User retrieveById(User user) throws Exception {
		return null;
	}

	public User retrieveByLogin(final User user) throws Exception {
		LOGGER.info("method retrieveByLogin: " + user.getLogin());

		String sql = "SELECT id, login, password, status, registrationDate FROM tbuser WHERE login = ?";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = this.conn;
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getLogin());
			rs = ps.executeQuery();

			User u = new User();
			while (rs.next()) {
				u.setId(rs.getInt("id"));
				u.setLogin(rs.getString("login"));
				u.setPassword(rs.getString("password"));
				u.setStatus(rs.getBoolean("status"));
				u.setRegistrationDate(rs.getTimestamp("registrationDate"));
			}
			return u;
		} catch (SQLException e) {
			LOGGER.error(e);
		} finally {
			ConnectionFactory.closeConnection(conn, ps, rs);
		}
		return null;
	}

	@Override
	public List<User> retrieveAll() throws Exception {
		return null;
	}

	@Override
	public void update(User user) throws Exception {
	}

	@Override
	public void delete(User user) throws Exception {
	}

}
