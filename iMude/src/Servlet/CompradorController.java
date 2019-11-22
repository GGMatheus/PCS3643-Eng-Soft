package Servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CompradorDAO;
import Model.Comprador;

public class CompradorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CompradorDAO compradorDAO;
	
	public void init() {
		compradorDAO = new CompradorDAO();
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
				insertComprador(request, response);
				break;
			case "/delete":
				deleteComprador(request, response);
				break;
			case "/update":
				updateComprador(request, response);
				break;
			default:
				listComprador(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
// Listar usuarios
	private void listComprador(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Comprador> listContrato = compradorDAO.selectAllCompradores();
		request.setAttribute("listComprador", listContrato);
		RequestDispatcher dispatcher = request.getRequestDispatcher("contrato-list.jsp");
		dispatcher.forward(request, response);
	}

// Inserção de Usuario
	private void insertComprador(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String CPF = request.getParameter("CPF");
		Comprador newComprador = new Comprador(id, nome, email, CPF);
		compradorDAO.insertComprador(newComprador);
		response.sendRedirect("list");
	}
// Atualização de Usuario
	private void updateComprador(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String CPF = request.getParameter("CPF");
		Comprador book = new Comprador(id, nome, email, CPF);
		compradorDAO.updateComprador(book);
		response.sendRedirect("list");
	}
// Eliminar Usuario
	private void deleteComprador(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		compradorDAO.deleteComprador(id);
		response.sendRedirect("list");
	}
}
