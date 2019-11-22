package Servlet;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.LocatarioDAO;
import Model.Locatario;

public class LocatarioController {
	private static final long serialVersionUID = 1L;
	private LocatarioDAO locatarioDAO;
	
	public void init() {
		locatarioDAO = new LocatarioDAO();
	}
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertLocatario(request, response);
				break;
			case "/delete":
				deleteLocatario(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateLocatario(request, response);
				break;
			default:
				listLocatario(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
// Listar locadores
	private void listLocatario(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Locatario> listLocatario = locatarioDAO.selectAllLocatarios();
		request.setAttribute("listLocatario", listLocatario);
		RequestDispatcher dispatcher = request.getRequestDispatcher("locatario-list.jsp");
		dispatcher.forward(request, response);
	}
// Mostrar locador
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("locatario-form.jsp");
		dispatcher.forward(request, response);
	}
// Mostrar locador para edição
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Locatario existingLocatario = locatarioDAO.selectLocatario(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("locatario-form.jsp");
		request.setAttribute("locatario", existingLocatario);
		dispatcher.forward(request, response);

	}
// Inserção de locador
	private void insertLocatario(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String cpf = request.getParameter("cpf");
		Locatario newLocatario = new Locatario(nome, email, cpf);
		locatarioDAO.insertLocatario(newLocatario);
		response.sendRedirect("list");
	}
// Atualização de locador
	private void updateLocatario(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String cpf = request.getParameter("cpf");

		Locatario locatario = new Locatario(id, nome, email, cpf);
		locatarioDAO.updateLocatario(locatario);
		response.sendRedirect("list");
	}
// Eliminar locador
	private void deleteLocatario(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		locatarioDAO.deleteLocatario(id);
		response.sendRedirect("list");

	}

}
