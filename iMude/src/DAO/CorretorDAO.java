package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Corretor;

public class CorretorDAO {
	private String jdbcURL = "jdbc:mariadb://db-labsoft.ml:3306/t1g5?useSSL=false";
	private String jdbcUsername = "t1g5";
	private String jdbcPassword = "VnzHBEh";
	
	private static final String INSERT_CORRETOR_SQL = "INSERT INTO Corretor" + "  (id, nome, email, CPF) VALUES "
			+ " (?, ?);";

	private static final String SELECT_CORRETOR_BY_ID = "select id, nome, email, CPF from Corretor where id =?";
	private static final String SELECT_ALL_CORRETORES = "select * from Corretor";
	private static final String DELETE_CORRETOR_SQL = "delete from Corretor where id = ?;";
	private static final String UPDATE_CORRETOR_SQL = "update Corretor set nome= ?, email= ?, CPF= ? where id = ?;";
	
	public CorretorDAO() {
		
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
	
	public void insertCorretor(Corretor comprador) throws SQLException {
		System.out.println(INSERT_CORRETOR_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CORRETOR_SQL)) {
			preparedStatement.setString(1, comprador.getCPF());
			preparedStatement.setString(2, comprador.getNome());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Corretor selectCorretor(int id) {
		Corretor comprador = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CORRETOR_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id1 = rs.getInt("id");
				String nome = rs.getString("nome");
				String email = rs.getString("email");
				String CPF = rs.getString("CPF");
				comprador = new Corretor(id1, nome, email, CPF);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return comprador;
	}

	public List<Corretor> selectAllCorretores() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Corretor> corretores = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CORRETORES);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				String email = rs.getString("email");
				String CPF = rs.getString("CPF");
				corretores.add(new Corretor(id, nome, email, CPF));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return corretores;
	}

	public boolean deleteCorretor(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_CORRETOR_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateCorretor(Corretor corretor) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_CORRETOR_SQL);) {
			statement.setString(1, corretor.getCPF());
			statement.setString(2, corretor.getNome());
			statement.setInt(3, corretor.getId());

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
