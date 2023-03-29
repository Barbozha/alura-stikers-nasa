package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import entidade.Conteudo;
import util.interfaces.ExtratorDeConteudo;

public class ExtratorDeConteudoDoIMDB implements ExtratorDeConteudo{

	public List<Conteudo> extraiConteudos(String json) {

		// EXTRAI OS DADOS QUE INTERESSAM (NOME DO CONTEÚDO, CLASSIFICAÇÃO,
		// DADOS DA IMAGEM DO CONTEÚDO)
		JsonParser parser = new JsonParser();
		List<Map<String, String>> listaDeAtributos = parser.parse(json);

		List<Conteudo> conteudos = new ArrayList<>();

		// POPULAR A LISTA DE CONTEÚDO
		for (Map<String, String> atribudos : listaDeAtributos) {
			String titulo = atribudos.get("title");
			String urlImagem = atribudos.get("image").replaceAll("(@+)(.*).jpg$", "$1.jpg");
			Conteudo conteudo = new Conteudo(titulo, urlImagem);

			conteudos.add(conteudo);
		}

		return conteudos;
	}
}
