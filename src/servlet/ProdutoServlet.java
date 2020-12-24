package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProdutoDao;
import model.Produto;

@WebServlet("/salvarProduto")
public class ProdutoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private ProdutoDao produtoDao = new ProdutoDao();
       
    public ProdutoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String acao = request.getParameter("acao") != null ? request.getParameter("acao") : "listartodos";
			String produtoId = request.getParameter("produto");

			RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
			
			if (acao != null && acao.equalsIgnoreCase("delete")) {
				produtoDao.delete(produtoId);
				request.setAttribute("produtos", produtoDao.listar());
				
			} else if (acao != null && acao.equalsIgnoreCase("editar")) {
				Produto produto = produtoDao.consulta(produtoId);
				request.setAttribute("produto", produto);
				
			} else if (acao != null && acao.equalsIgnoreCase("listartodos")) {
				request.setAttribute("produtos", produtoDao.listar());
				
			} else {
				request.setAttribute("produtos", produtoDao.listar());
				
			}
			request.setAttribute("categorias", produtoDao.listaCategorias());			
			view.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = request.getParameter("acao");

		if (acao != null && acao.equalsIgnoreCase("reset")) {
			try {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("produtos", produtoDao.listar());
				view.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {

			String id = request.getParameter("id");
			String descricao = request.getParameter("descricao");
			String quantidade = request.getParameter("quantidade");
			String valor = request.getParameter("valor");
			String categoria = request.getParameter("categoriaId");
			
			try {
				String msg = null;
				boolean podeInserir = true;
				
				if (descricao == null || descricao.isEmpty()) {
					msg = "A descrição deve ser informado";
					podeInserir = false;
					
				} else if (quantidade == null || quantidade.isEmpty()) {
					msg = "A quantidade deve ser informado";
					podeInserir = false;
					
				} else if (valor == null || valor.isEmpty()) {
					msg = "O valor R$ deve ser informado";
					podeInserir = false;
					
				}

				if (id == null || id.isEmpty() && !produtoDao.validarDescricao(descricao)) { // Quando for novo produto
					msg = "Produto já existe com a mesma descrição!";
					podeInserir = false;
				}

				Produto produto = new Produto();
				produto.setId(!id.isEmpty() ? Long.parseLong(id) : null);
				produto.setDescricao(descricao);
				produto.setCategoriaId(Long.parseLong(categoria));
				
				if (quantidade != null && !quantidade.isEmpty()) {
					produto.setQuantidade(Double.parseDouble(quantidade));
				}
				
				if (valor != null && !valor.isEmpty()) {
					String valorProduto = valor.replaceAll("\\.", ""); // 10500,20
					valorProduto = valorProduto.replaceAll("\\,", "."); // 10500.20
					produto.setValor(Double.parseDouble(valorProduto));
				}
				
				if (msg != null) {
					request.setAttribute("msg", msg);
					
				} else if (id == null || id.isEmpty() && produtoDao.validarDescricao(descricao) && podeInserir) {
					produtoDao.salvar(produto);
					
				} else if (id != null && !id.isEmpty() && podeInserir){
					produtoDao.atualizar(produto);												
				}
				
				if (!podeInserir) {
					request.setAttribute("produto", produto);
				}

				RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("produtos", produtoDao.listar());
				request.setAttribute("categorias", produtoDao.listaCategorias());
				
				view.forward(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
