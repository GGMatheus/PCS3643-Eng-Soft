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

import DAO.GerenteDAO;
import Model.Gerente;

/**
 * Servlet implementation class TesteEntidade
 */
@WebServlet("/TesteEntidade")
public class GerentesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GerenteDAO gerenteDAO;
	
	public void init() {
		gerenteDAO = new GerenteDAO();
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
				insertGerente(request, response);
				break;
			case "/delete":
				deleteGerente(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateGerente(request, response);
				break;
			default:
				listGerente(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
// Listar gerentes
	private void listGerente(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Gerente> listGerente = gerenteDAO.selectAllGerentes();
		request.setAttribute("listGerente", listGerente);
		RequestDispatcher dispatcher = request.getRequestDispatcher("gerente-list.jsp");
		dispatcher.forward(request, response);
	}
// Mostrar formulario
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("gerente-form.jsp");
		dispatcher.forward(request, response);
	}
// Mostrar forumulario para edicao
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Gerente existingUser = gerenteDAO.selectGerente(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("gerente-form.jsp");
		request.setAttribute("gerente", existingUser);
		dispatcher.forward(request, response);

	}
// Insercao de Gerente
	private void insertGerente(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String CPF = request.getParameter("CPF");
		Gerente newGerente = new Gerente(nome, email, CPF);
		gerenteDAO.insertGerente(newGerente);
		response.sendRedirect("list");
	}
// Atualizacao de Gerente
	private void updateGerente(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String CPF = request.getParameter("CPF");

		Gerente book = new Gerente(id, nome, email, CPF);
		gerenteDAO.updateGerente(book);
		response.sendRedirect("list");
	}
// Eliminar Gerente
	private void deleteGerente(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		gerenteDAO.deleteGerente(id);
		response.sendRedirect("list");

	}
}