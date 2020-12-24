package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TelefoneDao;
import dao.UsuarioDao;
import model.Telefone;
import model.Usuario;

@WebServlet("/salvarTelefones")
public class TelefonesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UsuarioDao usuarioDao = new UsuarioDao();
	
	private TelefoneDao telefoneDao = new TelefoneDao();
       
    public TelefonesServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			String acao = request.getParameter("acao");
			String user = request.getParameter("user");

			if(user != null) {
				
				Usuario usuario = usuarioDao.consulta(user);
				
				if(acao.endsWith("addFone")) {
					
					request.getSession().setAttribute("userEscolhido", usuario);
					request.setAttribute("userEscolhido", usuario);
					
					RequestDispatcher view = request.getRequestDispatcher("/telefones.jsp");
					request.setAttribute("telefones", telefoneDao.listar(usuario.getId()));
					
					view.forward(request, response);
				} else if(acao.endsWith("deleteFone")) {
					
					// Pega o id do telefone para remover
					String foneId = request.getParameter("foneId");
					
					// Remove o telefone pelo id capturado no parametro
					telefoneDao.delete(foneId);
					
					// Recarrega a página HTML do telefone
					RequestDispatcher view = request.getRequestDispatcher("/telefones.jsp");
					request.setAttribute("userEscolhido", usuario);
					
					// seta o atributo telefones com a lista de telefones referente ao id do usuário, para exibir na tela 
					request.setAttribute("telefones", telefoneDao.listar(Long.parseLong(user)));
					
					// seta o atributo msg que é a mensagem que será exibida para o usuário
					request.setAttribute("msg", "Removido com sucesso!");
					
					view.forward(request, response);
				}
				
			} else {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", usuarioDao.listar());
				view.forward(request, response);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			Usuario usuario = (Usuario) request.getSession().getAttribute("userEscolhido");
			
			String numero = request.getParameter("numero");
			String tipo = request.getParameter("tipo");
			
			String acao = request.getParameter("acao");
			
			if(acao == null || (acao != null && !acao.equalsIgnoreCase("voltar")) ) {
			
				if(numero == null || (numero != null && numero.isEmpty())) {
					RequestDispatcher view = request.getRequestDispatcher("/telefones.jsp");
					request.setAttribute("telefones", telefoneDao.listar(usuario.getId()));
					request.setAttribute("msg", "Informe o número do telefone!");
					
					view.forward(request, response);
				}else {
					Telefone telefone = new Telefone();
					telefone.setNumero(numero);
					telefone.setTipo(tipo);
					telefone.setUsuarioId(usuario.getId());
					
					telefoneDao.salvar(telefone);
					
					request.getSession().setAttribute("userEscolhido", usuario);
					request.setAttribute("userEscolhido", usuario);
					
					RequestDispatcher view = request.getRequestDispatcher("/telefones.jsp");
					request.setAttribute("telefones", telefoneDao.listar(usuario.getId()));
					request.setAttribute("msg", "Salvo com sucesso!");
					
					view.forward(request, response);
				}
			}else {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", usuarioDao.listar());
				
				view.forward(request, response);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
