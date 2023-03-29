package util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
public class GeradoraDeFigurinhas {
public void criar(InputStream inputStream, String nomeArquivo) {
	// ler a imagem
	try {
		// InputStream inputStream = new FileInputStream(new File("entrada/filme.jpg"));
		// InputStream inputStream = new
		// URL("https://jsonplaceholder.typicode.com/album/1/photos").openStream();
		BufferedImage imagemOriginal = ImageIO.read(inputStream);
		//String classificacao;
		// CRIAR A IMAGEM EM MEM�RIA COM TRANSPAR�NCIA E COM TAMANHO NOVO
		int largura = imagemOriginal.getWidth();
		int altura = imagemOriginal.getHeight();
		int novaAltura = altura + 200;
		BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

		// COPIAR A IMAGEM ORIGINAL PARA NOVA IMAGEM EM (MEM�RIA)
		Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
		graphics.drawImage(imagemOriginal, 0, 0, null);

		// CONFIGURAR A FONTE
		Font fonte = new Font(Font.SANS_SERIF, Font.BOLD, 50);
		graphics.setFont(fonte);
		graphics.setColor(Color.YELLOW);

		// ESCREVER UMA FRASE NA NOVA IMAGEM
		
		graphics.drawString("TOPZERA", 50, novaAltura - 100);

		// ESCREVER A NOVA IMAGEM EM UM ARQUIVO
		ImageIO.write(novaImagem, "png", new File("saida/" + nomeArquivo));
		System.out.println("Imagem gerada com sucesso!");
	} catch(IOException e) {
		throw new SaidaErros("Erro ao gerar o arquivo. " + e.getMessage());
	}

}
}
