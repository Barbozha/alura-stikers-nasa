package entidade;

public class Conteudo {

	private final String titulo;
	private final String urlImagem;

	public Conteudo(String titulo, String url) {
		this.titulo = titulo;
		this.urlImagem = url;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getUrlImagem() {
		return urlImagem;
	}

}
