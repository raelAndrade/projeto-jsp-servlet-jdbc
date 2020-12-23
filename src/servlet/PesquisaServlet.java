package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Usuario;
import dao.UsuarioDao;

@WebServlet("/pesquisaServlet")
public class PesquisaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UsuarioDao dao = new UsuarioDao();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String descricaoPesquisa = request.getParameter("descricaoConsulta");
		
		if(descricaoPesquisa != null && !descricaoPesquisa.trim().isEmpty()) {
			try {
				List<Usuario> listaPesquisa = dao.listar(descricaoPesquisa);
				
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", listaPesquisa);
				view.forward(request, response);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			try {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", dao.listar());
				view.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

}
