package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Gerente;

public class GerenteDAO {
	private String jdbcURL = "jdbc:mariadb://db-labsoft.ml:3306/t1g5?useSSL=false";
	private String jdbcUsername = "t1g5";
	private String jdbcPassword = "VnzHBEh";
	
	private static final String INSERT_GERENTE_SQL = "INSERT INTO Gerentes" + "  (nome, email, CPF) VALUES "
			+ " (?, ?, ?);";

	private static final String SELECT_GERENTE_BY_ID = "select id, nome, email, CPF from Gerentes where id =?";
	private static final String SELECT_ALL_GERENTES = "select * from Gerentes";
	private static final String DELETE_GERENTE_SQL = "delete from Gerentes where id = ?;";
	private static final String UPDATE_GERENTE_SQL = "update Gerentes set nome = ?, email = ?, CPF = ? where id = ?;";
	
	public GerenteDAO() {
		
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
	
	public void insertGerente(Gerente gerente) throws SQLException {
		System.out.println(INSERT_GERENTE_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_GERENTE_SQL)) {
			preparedStatement.setString(1, gerente.getNome());
			preparedStatement.setString(2, gerente.getEmail());
			preparedStatement.setString(3, gerente.getCPF());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Gerente selectGerente(int id) {
		Gerente gerente = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_GERENTE_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String nome = rs.getString("nome");
				String email = rs.getString("email");
				String CPF = rs.getString("CPF");
				gerente = new Gerente(id, nome, email, CPF);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return gerente;
	}

	public List<Gerente> selectAllGerentes() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Gerente> gerentes = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_GERENTES);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				String email = rs.getString("email");
				String CPF = rs.getString("CPF");
				gerentes.add(new Gerente(id, nome, email, CPF));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return gerentes;
	}

	public boolean deleteGerente(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_GERENTE_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateGerente(Gerente gerente) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_GERENTE_SQL);) {
			statement.setString(1, gerente.getNome());
			statement.setString(2, gerente.getEmail());
			statement.setString(3, gerente.getCPF());
			statement.setInt(3, gerente.getId());

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
