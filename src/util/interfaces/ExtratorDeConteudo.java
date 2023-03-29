package util.interfaces;

import java.util.List;

import entidade.Conteudo;

public interface ExtratorDeConteudo {
	List<Conteudo> extraiConteudos(String json);
}
