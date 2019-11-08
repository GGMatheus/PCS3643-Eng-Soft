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

import DAO.ContratoDAO;
import Model.Contrato;

/**
 * Servlet implementation class TesteEntidade
 */
@WebServlet("/TesteEntidade")
public class ContratosController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ContratoDAO contratoDAO;
	
	public void init() {
		contratoDAO = new ContratoDAO();
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
				insertContrato(request, response);
				break;
			case "/delete":
				deleteContrato(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateContrato(request, response);
				break;
			default:
				listContrato(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
// Listar usuarios
	private void listContrato(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Contrato> listContrato = contratoDAO.selectAllContratos();
		request.setAttribute("listContrato", listContrato);
		RequestDispatcher dispatcher = request.getRequestDispatcher("contrato-list.jsp");
		dispatcher.forward(request, response);
	}
// Mostrar formulario
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("contrato-form.jsp");
		dispatcher.forward(request, response);
	}
// Mostrar forumulario para edição
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Contrato existingUser = contratoDAO.selectContrato(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("contrato-form.jsp");
		request.setAttribute("contrato", existingUser);
		dispatcher.forward(request, response);

	}
// Inserção de Usuario
	private void insertContrato(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String data = request.getParameter("data");
		String descricao = request.getParameter("descricao");
		Contrato newContrato = new Contrato(data, descricao);
		contratoDAO.insertContrato(newContrato);
		response.sendRedirect("list");
	}
// Atualização de Usuario
	private void updateContrato(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String data = request.getParameter("data");
		String descricao = request.getParameter("descricao");

		Contrato book = new Contrato(id, data, descricao);
		contratoDAO.updateContrato(book);
		response.sendRedirect("list");
	}
// Eliminar Usuario
	private void deleteContrato(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		contratoDAO.deleteContrato(id);
		response.sendRedirect("list");

	}


}
