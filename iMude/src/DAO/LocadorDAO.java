package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Locador;


public class LocadorDAO {
	private String jdbcURL = "jdbc:mariadb://db-labsoft.ml:3306/t1g5?useSSL=false";
	private String jdbcUsername = "t1g5";
	private String jdbcPassword = "VnzHBEh";
	
	private static final String INSERT_LOCADOR_SQL = "INSERT INTO Locadores" + "  (nome, email, CPF) VALUES "
			+ " (?, ?, ?);";

	private static final String SELECT_LOCADOR_BY_ID = "select id, nome, email, CPF from Locadores where id =?";
	private static final String SELECT_ALL_LOCADORES = "select * from Locadores";
	private static final String DELETE_LOCADOR_SQL = "delete from Locadores where id = ?;";
	private static final String UPDATE_LOCADOR_SQL = "update Locadores set nome = ?, email = ?, CPF = ? where id = ?;";
	
	public LocadorDAO() {
		
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
	
	public void insertLocador(Locador locador) throws SQLException {
		System.out.println(INSERT_LOCADOR_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LOCADOR_SQL)) {
			preparedStatement.setString(1, locador.getNome());
			preparedStatement.setString(2, locador.getEmail());
			preparedStatement.setString(3, locador.getCPF());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Locador selectLocador(int id) {
		Locador locador = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LOCADOR_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String nome = rs.getString("nome");
				String email = rs.getString("email");
				String cpf = rs.getString("cpf");
				locador = new Locador(id, nome, email, cpf);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return locador;
	}

	public List<Locador> selectAllLocadores() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Locador> locadores = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_LOCADORES);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				String email = rs.getString("email");
				String cpf = rs.getString("cpf");
				locadores.add(new Locador(id, nome, email, cpf));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return locadores;
	}

	public boolean deleteLocador(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_LOCADOR_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateLocador(Locador locador) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_LOCADOR_SQL);) {
			statement.setString(1, locador.getNome());
			statement.setString(2,  locador.getEmail());
			statement.setString(3, locador.getCPF());
			statement.setInt(4, locador.getId());

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
