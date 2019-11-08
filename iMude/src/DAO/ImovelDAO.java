package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ImovelDAO {
	private String jdbcURL = "jdbc:mariadb://db-labsoft.ml:3306/t1g5?useSSL=false";
	private String jdbcUsername = "t1g5";
	private String jdbcPassword = "VnzHBEh";
	
	private static final String INSERT_IMOVEL_SQL = "INSERT INTO Imoveis" + "  (data, descricao) VALUES "
			+ " (?, ?);";

	private static final String SELECT_IMOVEL_BY_ID = "select id,data,descricao from Contratos where id =?";
	private static final String SELECT_ALL_IMOVEIS = "select * from Contratos";
	private static final String DELETE_IMOVEL_SQL = "delete from Contratos where id = ?;";
	private static final String UPDATE_IMOVEL_SQL = "update Contratos set data = ?,descricao= ? where id = ?;";
	
	public ImovelDAO() {
		
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
	
	public void insertImovel(Imovel imovel) throws SQLException {
		System.out.println(INSERT_IMOVEL_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_IMOVEL_SQL)) {
			preparedStatement.setString(1, imovel.getData());
			preparedStatement.setString(2, imovel.getDescricao());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Imovel selectImovel(int id) {
		Imovel imovel = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_IMOVEL_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String data = rs.getString("data");
				String descricao = rs.getString("descricao");
				imovel = new Imovel(id, data, descricao);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return imovel;
	}

	public List<Imovel> selectAllImoveis() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Imovel> imoveis = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_IMOVEIS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String data = rs.getString("data");
				String descricao = rs.getString("descricao");
				contratos.add(new Imovel(id, data, descricao));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return contratos;
	}

	public boolean deleteImovel(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_IMOVEL_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateImovel(Imovel imovel) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_IMOVEL_SQL);) {
			statement.setString(1, contrato.getData());
			statement.setString(2, contrato.getDescricao());
			statement.setInt(3, contrato.getId());

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
