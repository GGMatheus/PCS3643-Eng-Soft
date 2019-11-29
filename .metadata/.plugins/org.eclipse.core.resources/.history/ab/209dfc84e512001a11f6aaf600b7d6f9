package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Visita;

public class VisitaDAO {
	private String jdbcURL = "jdbc:mariadb://db-labsoft.ml:3306/t1g5?useSSL=false";
	private String jdbcUsername = "t1g5";
	private String jdbcPassword = "VnzHBEh";
	
	private static final String INSERT_VISITA_SQL = "INSERT INTO Visitas" + "  (idImovel, idCorretor, idComprador, idLocatario, data, hora) VALUES "
			+ " (?, ?, ?, ?, ?, ?);";

	private static final String SELECT_VISITA_BY_ID = "select id, idImovel, idCorretor, idComprador, idLocatario, data, horario from Visitas where id =?";
	private static final String SELECT_ALL_VISITAS = "select * from Visitas";
	private static final String DELETE_VISITA_SQL = "delete from Visitas where id = ?;";
	private static final String UPDATE_VISITA_SQL = "update Visitas set idImovel = ?, idCorretor = ?, idComprador = ?, idLocatario = ?, data = ?, horario= ? where id = ?;";
	
	public VisitaDAO() {
		
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
	
	public void insertVisita(Visita visita) throws SQLException {
		System.out.println(INSERT_VISITA_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_VISITA_SQL)) {
			preparedStatement.setInt(1, visita.getIdImovel());
			preparedStatement.setInt(2, visita.getIdCorretor());
			preparedStatement.setInt(3, visita.getIdComprador());
			preparedStatement.setInt(4, visita.getIdLocatario());
			preparedStatement.setString(5, visita.getData());
			preparedStatement.setString(6, visita.getHorario());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Visita selectVisita(int id) {
		Visita visita = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_VISITA_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int idImovel = rs.getInt("idImovel");
				int idCorretor = rs.getInt("idCorretor");
				int idComprador = rs.getInt("idComprador");
				int idLocatario = rs.getInt("idLocatario");
				String data = rs.getString("data");
				String horario = rs.getString("horario");
				visita = new Visita(id, idImovel, idCorretor, idComprador, idLocatario, data, horario);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return visita;
	}

	public List<Visita> selectAllVisitas() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Visita> visitas = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_VISITAS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				int idImovel = rs.getInt("idImovel");
				int idCorretor = rs.getInt("idCorretor");
				int idComprador = rs.getInt("idComprador");
				int idLocatario = rs.getInt("idLocatario");
				String data = rs.getString("data");
				visitas.add(new Visita(id, idImovel, idCorretor, idComprador, idLocatario, data));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return visitas;
	}

	public boolean deleteVisita(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_VISITA_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateVisita(Visita visita) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_VISITA_SQL);) {
			statement.setInt(1, visita.getId());
			statement.setInt(2, visita.getIdImovel());
			statement.setInt(3, visita.getIdCorretor());
			statement.setInt(4, visita.getIdComprador());
			statement.setInt(5, visita.getIdLocatario());
			statement.setString(6, visita.getData());
			statement.setString(7, visita.getHorario());
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
