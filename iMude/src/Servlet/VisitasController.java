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

import DAO.VisitaDAO;
import Model.Visita;

/**
 * Servlet implementation class TesteEntidade
 */
@WebServlet("/TesteEntidade")
public class VisitasController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private VisitaDAO visitaDAO;
	
	public void init() {
		visitaDAO = new VisitaDAO();
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
				insertVisita(request, response);
				break;
			case "/delete":
				deleteVisita(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateVisita(request, response);
				break;
			default:
				listVisita(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
// Listar visitas
	private void listVisita(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Visita> listVisita = visitaDAO.selectAllVisitas();
		request.setAttribute("listVisita", listVisita);
		RequestDispatcher dispatcher = request.getRequestDispatcher("visita-list.jsp");
		dispatcher.forward(request, response);
	}
// Mostrar formulario
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("visita-form.jsp");
		dispatcher.forward(request, response);
	}
// Mostrar forumulario para edicao
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Visita existingUser = visitaDAO.selectVisita(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("visita-form.jsp");
		request.setAttribute("visita", existingUser);
		dispatcher.forward(request, response);

	}
// Insercao de Visita
	private void insertVisita(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int idImovel = Integer.parseInt(request.getParameter("idImovel"));
		int idCorretor = Integer.parseInt(request.getParameter("idCorretor"));
		int idComprador = Integer.parseInt(request.getParameter("idComprador"));
		int idLocatario = Integer.parseInt(request.getParameter("idLocatario"));
		String data = request.getParameter("data");
		String horario = request.getParameter("horario");
		Visita newVisita= new Visita(idImovel, idCorretor, idComprador, idLocatario, data, horario);
		visitaDAO.insertVisita(newVisita);
		response.sendRedirect("list");
	}
// Atualizacao de Visita
	private void updateVisita(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		int idImovel = Integer.parseInt(request.getParameter("idImovel"));
		int idCorretor = Integer.parseInt(request.getParameter("idCorretor"));
		int idComprador = Integer.parseInt(request.getParameter("idComprador"));
		int idLocatario = Integer.parseInt(request.getParameter("idLocatario"));
		String data = request.getParameter("data");
		String horario = request.getParameter("horario");

		Visita book = new Visita(id, idImovel, idCorretor, idComprador, idLocatario, data, horario);
		visitaDAO.updateVisita(book);
		response.sendRedirect("list");
	}
// Eliminar Visita
	private void deleteVisita(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		visitaDAO.deleteVisita(id);
		response.sendRedirect("list");

	}


}