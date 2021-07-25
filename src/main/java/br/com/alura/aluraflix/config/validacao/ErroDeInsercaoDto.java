package br.com.alura.aluraflix.config.validacao;

public class ErroDeInsercaoDto {

	private String campo;
	private String erro;

	public ErroDeInsercaoDto(String campo, String erro) {
		this.campo = campo;
		this.erro = erro;
	}

	public String getCampo() {
		return campo;
	}

	public String getErro() {
		return erro;
	}
	
	
	
	
}
