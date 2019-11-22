package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Locatario;


public class LocatarioDAO {
	private String jdbcURL = "jdbc:mariadb://db-labsoft.ml:3306/t1g5?useSSL=false";
	private String jdbcUsername = "t1g5";
	private String jdbcPassword = "VnzHBEh";
	
	private static final String INSERT_LOCATARIO_SQL = "INSERT INTO Locatarios" + "  (nome, email, CPF) VALUES "
			+ " (?, ?, ?);";

	private static final String SELECT_LOCATARIO_BY_ID = "select id, nome, email, CPF from Locatarios where id =?";
	private static final String SELECT_ALL_LOCATARIOS = "select * from Locatarios";
	private static final String DELETE_LOCATARIO_SQL = "delete from Locatarios where id = ?;";
	private static final String UPDATE_LOCATARIO_SQL = "update Locatarios set nome = ?, email = ?, CPF = ? where id = ?;";
	
	public LocatarioDAO() {
		
	}
	
	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			//Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	
	public void insertLocatario(Locatario locatario) throws SQLException {
		System.out.println(INSERT_LOCATARIO_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LOCATARIO_SQL)) {
			preparedStatement.setString(1, locatario.getNome());
			preparedStatement.setString(2, locatario.getEmail());
			preparedStatement.setString(3, locatario.getCPF());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Locatario selectLocatario(int id) {
		Locatario locatario = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LOCATARIO_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String nome = rs.getString("nome");
				String email = rs.getString("email");
				String cpf = rs.getString("cpf");
				locatario = new Locatario(id, nome, email, cpf);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return locatario;
	}

	public List<Locatario> selectAllLocatarios() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Locatario> locatarios = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_LOCATARIOS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				String email = rs.getString("email");
				String cpf = rs.getString("cpf");
				locatarios.add(new Locatario(id, nome, email, cpf));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return locatarios;
	}

	public boolean deleteLocatario(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_LOCATARIO_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateLocatario(Locatario locatario) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_LOCATARIO_SQL);) {
			statement.setString(1, locatario.getNome());
			statement.setString(2,  locatario.getEmail());
			statement.setString(3, locatario.getCPF());
			statement.setInt(4, locatario.getId());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
}
