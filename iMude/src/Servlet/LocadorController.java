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

import DAO.LocadorDAO;
import Model.Locador;

public class LocadorController {
	private static final long serialVersionUID = 1L;
	private LocadorDAO locadorDAO;
	
	public void init() {
		locadorDAO = new LocadorDAO();
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
				insertLocador(request, response);
				break;
			case "/delete":
				deleteLocador(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateLocador(request, response);
				break;
			default:
				listLocador(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
// Listar locadores
	private void listLocador(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Locador> listLocador = locadorDAO.selectAllLocadores();
		request.setAttribute("listLocador", listLocador);
		RequestDispatcher dispatcher = request.getRequestDispatcher("locador-list.jsp");
		dispatcher.forward(request, response);
	}
// Mostrar locador
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("locador-form.jsp");
		dispatcher.forward(request, response);
	}
// Mostrar locador para edição
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Locador existingLocador = locadorDAO.selectLocador(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("locador-form.jsp");
		request.setAttribute("locador", existingLocador);
		dispatcher.forward(request, response);

	}
// Inserção de locador
	private void insertLocador(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String cpf = request.getParameter("cpf");
		Locador newLocador = new Locador(nome, email, cpf);
		locadorDAO.insertLocador(newLocador);
		response.sendRedirect("list");
	}
// Atualização de locador
	private void updateLocador(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String cpf = request.getParameter("cpf");

		Locador locador = new Locador(id, nome, email, cpf);
		locadorDAO.updateLocador(locador);
		response.sendRedirect("list");
	}
// Eliminar locador
	private void deleteLocador(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		locadorDAO.deleteLocador(id);
		response.sendRedirect("list");

	}

}
