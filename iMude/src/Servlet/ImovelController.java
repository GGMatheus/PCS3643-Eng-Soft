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

import DAO.ImovelDAO;
import Model.Imovel;

public class ImovelController {
	private static final long serialVersionUID = 1L;
	private ImovelDAO imovelDAO;
	
	public void init() {
		imovelDAO = new ImovelDAO();
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
				insertImovel(request, response);
				break;
			case "/delete":
				deleteImovel(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateImovel(request, response);
				break;
			default:
				listImovel(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
// Listar usuarios
	private void listImovel(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Contrato> listImovel = imovelDAO.selectAllContratos();
		request.setAttribute("listImovel", listImovel);
		RequestDispatcher dispatcher = request.getRequestDispatcher("imovel-list.jsp");
		dispatcher.forward(request, response);
	}
// Mostrar formulario
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("imovel-form.jsp");
		dispatcher.forward(request, response);
	}
// Mostrar forumulario para edição
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Contrato existingUser = imovelDAO.selectImovel(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("imovel-form.jsp");
		request.setAttribute("imovel", existingUser);
		dispatcher.forward(request, response);

	}
// Inserção de Usuario
	private void insertImovel(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String data = request.getParameter("data");
		String descricao = request.getParameter("descricao");
		Imovel newImovel = new Imovel(data, descricao);
		imovelDAO.insertContrato(newImovel);
		response.sendRedirect("list");
	}
// Atualização de Usuario
	private void updateImovel(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String data = request.getParameter("data");
		String descricao = request.getParameter("descricao");

		Imovel book = new Imovel(id, data, descricao);
		imovelDAO.updateContrato(book);
		response.sendRedirect("list");
	}
// Eliminar Usuario
	private void deleteImovel(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		imovelDAO.deleteImovel(id);
		response.sendRedirect("list");

	}

}
