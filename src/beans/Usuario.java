package beans;

public class Usuario {
	
	private Long id;
	
	private String nome;
	
	private String login;
	
	private String senha;
	
	private String cep;
	
	private String rua;
	
	private String bairro;
	
	private String cidade;
	
	private String ibge;
	
	private String uf;
	
	private String fotoBase64;
	
	private String curriculoBase64;
	
	private String contentType;
	
	private String contentTypeCurriculo;
	
	private String tempFotoUser;
	
	private String fotoBase64Miniatura;
	
	private boolean atualizarImage = true;
	
	private boolean atualizarPdf = true;
	
	private boolean ativo;
	
	private String sexo;
	
	private String perfil;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getCep() {
		return cep;
	}
	
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getIbge() {
		return ibge;
	}

	public void setIbge(String ibge) {
		this.ibge = ibge;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getFotoBase64() {
		return fotoBase64;
	}
	
	public void setFotoBase64(String fotoBase64) {
		this.fotoBase64 = fotoBase64;
	}
	
	public String getContentType() {
		return contentType;
	}
	
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	public String getCurriculoBase64() {
		return curriculoBase64;
	}
	
	public void setCurriculoBase64(String curriculoBase64) {
		this.curriculoBase64 = curriculoBase64;
	}
	
	public String getContentTypeCurriculo() {
		return contentTypeCurriculo;
	}
	
	public void setContentTypeCurriculo(String contentTypeCurriculo) {
		this.contentTypeCurriculo = contentTypeCurriculo;
	}
	
	public String getTempFotoUser() {
		tempFotoUser = "data:" + contentType + ";base64," + fotoBase64;
		return tempFotoUser;
	}
	
	public String getFotoBase64Miniatura() {
		return fotoBase64Miniatura;
	}
	
	public void setFotoBase64Miniatura(String fotoBase64Miniatura) {
		this.fotoBase64Miniatura = fotoBase64Miniatura;
	}

	public boolean isAtualizarImage() {
		return atualizarImage;
	}
	
	public void setAtualizarImage(boolean atualizarImage) {
		this.atualizarImage = atualizarImage;
	}
	
	public boolean isAtualizarPdf() {
		return atualizarPdf;
	}
	
	public void setAtualizarPdf(boolean atualizarPdf) {
		this.atualizarPdf = atualizarPdf;
	}
	
	public boolean isAtivo() {
		return ativo;
	}
	
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	public String getSexo() {
		return sexo;
	}
	
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public boolean validarLoginSenha(String login, String senha) {
		if(login.equalsIgnoreCase("admin") && senha.equalsIgnoreCase("admin")) {
			return true;
		}else {
			return false;
		}
	}

}