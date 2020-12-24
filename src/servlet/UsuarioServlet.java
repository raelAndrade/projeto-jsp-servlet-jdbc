package servlet;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.codec.binary.Base64;

import dao.UsuarioDao;
import model.Usuario;

@SuppressWarnings("static-access")
@WebServlet("/salvarUsuario")
@MultipartConfig
public class UsuarioServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private UsuarioDao usuarioDao = new UsuarioDao();

	public UsuarioServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String acao = request.getParameter("acao") != null ? request.getParameter("acao") : "listartodos";
			String user = request.getParameter("user");

			if (acao != null && acao.equalsIgnoreCase("delete") && user != null) {
				usuarioDao.delete(user);

				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", usuarioDao.listar());
				view.forward(request, response);
			} else if (acao != null && acao.equalsIgnoreCase("editar")) {
				Usuario usuario = usuarioDao.consulta(user);

				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("user", usuario);
				
				view.forward(request, response);
				
			} else if (acao != null && acao.equalsIgnoreCase("listartodos")) {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", usuarioDao.listar());
				
				view.forward(request, response);
				
			} else if (acao != null && acao.equalsIgnoreCase("download")) {
				Usuario usuario = usuarioDao.consulta(user);
				if(usuario != null) {
					
					String contentType = "";
					byte[] fileBytes = null;
					
					String tipo = request.getParameter("tipo");
					
					if(tipo.equalsIgnoreCase("image")) {
						contentType = usuario.getContentType();
						
						// Converte a base64 da imagem do banco para  byte[]
						fileBytes = new Base64().decodeBase64(usuario.getFotoBase64());
						
					} else if(tipo.equalsIgnoreCase("curriculo")) {
						contentType = usuario.getContentTypeCurriculo();
						
						// Converte a base64 da curriculo do banco para  byte[]
						fileBytes = new Base64().decodeBase64(usuario.getCurriculoBase64());
					}
					
					// Seta o cabeçalho da resposta
					response.setHeader("Content-Disposition", "attachment;filename=arquivo." + contentType.split("\\/")[1]);
					
					// Coloca os bytes em um objeto de entrada para processar
					InputStream is = new ByteArrayInputStream(fileBytes);
					
					// Escrever na resposta para o navegador
					int read =0;
					byte[] bytes = new byte[1024];
					OutputStream os = response.getOutputStream();
					
					while ((read = is.read(bytes)) != -1) {
						os.write(bytes, 0, read);
					}
	
					os.flush();
					os.close();
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");

		if (acao != null && acao.equalsIgnoreCase("reset")) {
			try {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", usuarioDao.listar());
				view.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {

			String id = request.getParameter("id");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			String nome = request.getParameter("nome");
			String cep = request.getParameter("cep");
			String rua = request.getParameter("rua");
			String bairro = request.getParameter("bairro");
			String cidade = request.getParameter("cidade");
			String uf = request.getParameter("uf");
			String ibge = request.getParameter("ibge");
			String sexo = request.getParameter("sexo");
			String perfil = request.getParameter("perfil");

			Usuario usuario = new Usuario();
			usuario.setId(!id.isEmpty() ? Long.parseLong(id) : null);
			usuario.setLogin(login);
			usuario.setSenha(senha);
			usuario.setNome(nome);
			usuario.setCep(cep);
			usuario.setRua(rua);
			usuario.setBairro(bairro);
			usuario.setCidade(cidade);
			usuario.setUf(uf);
			usuario.setIbge(ibge);
			usuario.setSexo(sexo);
			usuario.setPerfil(perfil);
			
			if(request.getParameter("ativo") != null
					&& (request.getParameter("ativo").equalsIgnoreCase("on") || request.getParameter("ativo").equalsIgnoreCase("true"))){
				usuario.setAtivo(true);
			} else {
				usuario.setAtivo(false);
			}
			
			try {
				
				/* Inicio File upload de imagens e PDF */
				if(ServletFileUpload.isMultipartContent(request)) {
					
					// Percorre até o campo name="foto" no formulário
					Part imagemFoto = request.getPart("foto");
					if(imagemFoto != null && imagemFoto.getInputStream().available() > 0) {
						
						String fotoBase64 = new Base64().encodeBase64String(converteStreamParaByte(imagemFoto.getInputStream()));
							
						usuario.setFotoBase64(fotoBase64);
						usuario.setContentType(imagemFoto.getContentType());
						
						/* Inicio miniatura imagem */
						
						/* Transformar em um bufferedImage */
						byte[] imageByteDecode = new Base64().decodeBase64(fotoBase64);
						BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageByteDecode));
						
						/* Pega o tipo da imagem */
						int type = bufferedImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : bufferedImage.getType();
						
						/* Cria imagem em miniatura */
						BufferedImage resizedImage = new BufferedImage(100, 100, type);
						Graphics2D g = resizedImage.createGraphics();
						g.drawImage(bufferedImage, 0, 0, 100, 100, null);
						g.dispose();
						
						/* Escrever imagem novamente */
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						ImageIO.write(resizedImage, "png", baos);
						
						String miniaturaBase64 = "data:image/png;base64," + DatatypeConverter.printBase64Binary(baos.toByteArray());
						
						usuario.setFotoBase64Miniatura(miniaturaBase64);
						
						/* Fim miniatura imagem */
						
					}else {
						usuario.setAtualizarImage(false);
					}
					
					/* Processa PDF */
					Part curriculoPdf = request.getPart("curriculo");
					if(curriculoPdf != null && curriculoPdf.getInputStream().available() > 0) {
						String curriculoBase64 = new Base64().encodeBase64String(converteStreamParaByte(curriculoPdf.getInputStream()));
							
						usuario.setCurriculoBase64(curriculoBase64);;
						usuario.setContentTypeCurriculo(curriculoPdf.getContentType());							
					}else {
						usuario.setAtualizarPdf(false);
					}
				}

				String msg = null;
				boolean podeInserir = true;
				
				/* Validando campos obrigatórios */
				if(login == null || login.isEmpty()) {
					msg = "Login deve ser informado";
					podeInserir = false;
				} else if(senha == null || senha.isEmpty()) {
					msg = "A senha deve ser informado";
					podeInserir = false;
				} else if(nome == null || nome.isEmpty()) {
					msg = "O nome deve ser informado";
					podeInserir = false;
				} else if (id == null || id.isEmpty() && !usuarioDao.validarLogin(login)) { // Quando for novo usuário
					msg = "Usuário já existe com o mesmo Login!";
					podeInserir = false;
				} 
				/* else if (id == null || id.isEmpty() && !usuarioDao.validarSenha(senha)){ // Quando for usuário novo
					msg = "\n A Senha já existe para outro usuário!";
					podeInserir = false;
				}*/
				
				/* Fim da validação dos campos obrigatórios */
				if (msg != null) {
					request.setAttribute("msg", msg);
				} else if (id == null || id.isEmpty() && usuarioDao.validarLogin(login) && podeInserir) {
					usuarioDao.salvar(usuario);
				} else if (id != null && !id.isEmpty() && podeInserir){
					usuarioDao.atualizar(usuario);												
				}
				
				if (!podeInserir) {
					request.setAttribute("user", usuario);
				}

				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", usuarioDao.listar());
				view.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/* Converte a entrada de fluxo de dados da imagem para um array de byte[] */
	private byte[] converteStreamParaByte(InputStream imagem) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int reads = imagem.read();
		while (reads != -1) { // Enquanto estiver dados na variavel reads 
			baos.write(reads); // Insere o fluxo de dados
			reads = imagem.read(); // Verifica se existe dados, esse fluxo só finaliza quando não tem mais dados para ser lido
		}
		return baos.toByteArray();
	}

}
