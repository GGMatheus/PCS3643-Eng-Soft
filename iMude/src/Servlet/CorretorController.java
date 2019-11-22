package Servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CorretorDAO;
import Model.Corretor;

public class CorretorController {
	private static final long serialVersionUID = 1L;
	private CorretorDAO corretorDAO;
	
	public void init() {
		corretorDAO = new CorretorDAO();
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
			case "/insert":
				insertCorretor(request, response);
				break;
			case "/delete":
				deleteCorretor(request, response);
				break;
			case "/update":
				updateCorretor(request, response);
				break;
			default:
				listCorretor(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
// Listar usuarios
	private void listCorretor(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Corretor> listCorretor = corretorDAO.selectAllCorretores();
		request.setAttribute("listCorretor", listCorretor);
		RequestDispatcher dispatcher = request.getRequestDispatcher("contrato-list.jsp");
		dispatcher.forward(request, response);
	}

// Inserção de Usuario
	private void insertCorretor(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String CPF = request.getParameter("CPF");
		Corretor newCorretor = new Corretor(id, nome, email, CPF);
		corretorDAO.insertCorretor(newCorretor);
		response.sendRedirect("list");
	}
// Atualização de Usuario
	private void updateCorretor(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String CPF = request.getParameter("CPF");
		Corretor book = new Corretor(id, nome, email, CPF);
		corretorDAO.updateCorretor(book);
		response.sendRedirect("list");
	}
// Eliminar Usuario
	private void deleteCorretor(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		corretorDAO.deleteCorretor(id);
		response.sendRedirect("list");
	}
}
