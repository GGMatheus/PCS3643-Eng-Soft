package Servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.VendedorDAO;
import Model.Vendedor;

public class VendedorController {
	private static final long serialVersionUID = 1L;
	private VendedorDAO vendedorDAO;
	
	public void init() {
		vendedorDAO = new VendedorDAO();
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
				insertVendedor(request, response);
				break;
			case "/delete":
				deleteVendedor(request, response);
				break;
			case "/update":
				updateVendedor(request, response);
				break;
			default:
				listVendedor(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
// Listar usuarios
	private void listVendedor(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Vendedor> listCorretor = vendedorDAO.selectAllVendedores();
		request.setAttribute("listCorretor", listCorretor);
		RequestDispatcher dispatcher = request.getRequestDispatcher("contrato-list.jsp");
		dispatcher.forward(request, response);
	}

// Inserção de Usuario
	private void insertVendedor(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String CPF = request.getParameter("CPF");
		Vendedor newVendedor = new Vendedor(id, nome, email, CPF);
		vendedorDAO.insertVendedor(newVendedor);
		response.sendRedirect("list");
	}
// Atualização de Usuario
	private void updateVendedor(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String CPF = request.getParameter("CPF");
		Vendedor book = new Vendedor(id, nome, email, CPF);
		vendedorDAO.updateVendedor(book);
		response.sendRedirect("list");
	}
// Eliminar Usuario
	private void deleteVendedor(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		vendedorDAO.deleteVendedor(id);
		response.sendRedirect("list");
	}
}
