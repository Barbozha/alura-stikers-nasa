package application;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

import entidade.Conteudo;
import util.GeradoraDeFigurinhas;
import util.SaidaErros;
import util.interfaces.ExtratorDeConteudo;

public class App {
	static String url;

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int opcao = 3;
		while(true) {
			menu(opcao);
			System.out.print("Escolha a opção: --> ");
			int opc = sc.nextInt();
			 if (opc == 0) {
	                System.out.print("\nAté logo!"); 
	                break;
	         }
			 else {
				switch (opc) {
				case 1: {
					 processar(opc);
				break;
					}
				case 2: {
					 processar(opc);
				break;
					}
				default: {
					System.out.println("Opção inválida!");
					}
				}
			 }
		}
		sc.close();
	}

	public static void menu(int opc) {
		System.out.println("*------------------------*");
		System.out.println("|      M   E   N   U     |");
		System.out.println("*------------------------*");
		System.out.println("* 1 - USAR A API DA IMDB *");
		System.out.println("* 2 - USAR A API DA NASA *");
		System.out.println("* 0 - PARA SAIR          *");
		System.out.println("*------------------------*");

	}

	public static void processar(int opc) {
		ExtratorDeConteudo extrator;
		if (opc == 1) {
			// FAZ A CONEXÃO COM O SERVIDOR IMDB
			url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
			extrator = new ExtratorDeConteudoDoIMDB();
		} else {
			// FAZ A CONEXÃO COM O SERVIDOR NASA
			url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-06-12&end_date=2022-06-14";
			extrator = new ExtratorDeConteudoDaNasa();

		}

		ClienteHttp http = new ClienteHttp();
		String json = http.buscaDados(url);

		// EXIBE E MANIPULAR DADOS
		List<Conteudo> conteudos = extrator.extraiConteudos(json);

		GeradoraDeFigurinhas geradora = new GeradoraDeFigurinhas();

		for (int i = 0; i < 3; i++) {

			Conteudo conteudo = conteudos.get(i);
			try {
				InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
				String nomeArquivo = conteudo.getTitulo() + ".png";
				geradora.criar(inputStream, nomeArquivo);

				// IMPRIMIR O TÍTULO DO CONTEÚDO
				System.out.println(conteudo.getTitulo());
				System.out.println();
			} catch (IOException ex) {
				throw new SaidaErros("Error " + ex.getMessage());
			}
		}

	}
}
