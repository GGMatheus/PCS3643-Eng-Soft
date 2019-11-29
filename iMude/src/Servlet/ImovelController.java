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
// Listar imoveis
	private void listImovel(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Imovel> listImovel = imovelDAO.selectAllImoveis();
		request.setAttribute("listImovel", listImovel);
		RequestDispatcher dispatcher = request.getRequestDispatcher("imovel-list.jsp");
		dispatcher.forward(request, response);
	}
// Mostrar imovel
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("imovel-form.jsp");
		dispatcher.forward(request, response);
	}
// Mostrar imovel para edição
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Imovel existingUser = imovelDAO.selectImovel(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("imovel-form.jsp");
		request.setAttribute("imovel", existingUser);
		dispatcher.forward(request, response);

	}
// Inserção de imovel
	private void insertImovel(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		Float preco = Float.valueOf(request.getParameter("preco"));
		String endereco = request.getParameter("endereco");
		String status = request.getParameter("status");
		String descricao = request.getParameter("descricao");
		String foto = request.getParameter("foto");
		int data = Integer.parseInt(request.getParameter("data"));
		Imovel newImovel = new Imovel(preco, endereco, status, descricao, foto, data);
		imovelDAO.insertImovel(newImovel);
		response.sendRedirect("list");
	}
// Atualização de imovel
	private void updateImovel(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Float preco = Float.valueOf(request.getParameter("preco"));
		String endereco = request.getParameter("endereco");
		String status = request.getParameter("status");
		String descricao = request.getParameter("descricao");
		String foto = request.getParameter("foto");
		int data = Integer.parseInt(request.getParameter("data"));

		Imovel imovel = new Imovel(id, preco, endereco, status, descricao, foto, data);
		imovelDAO.updateImovel(imovel);
		response.sendRedirect("list");
	}
// Eliminar imovel
	private void deleteImovel(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		imovelDAO.deleteImovel(id);
		response.sendRedirect("list");

	}

}
