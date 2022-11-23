package Kmp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class kmp {
	public static void KMP(String texto, String padrao) {
		System.out.println("================BUSCA POR KMP====================");
		System.out.println(padrao);
		if (padrao == null || padrao.length() == 0) {
			System.out.println("padrao n encontrado");
			return;
		}

		if (texto == null || padrao.length() > texto.length()) {
			System.out.println("padrao n encontrado");
			return;
		}

		int M = padrao.length();
		int N = texto.length();

		int lps[] = new int[M];
		int j = 0,len = 0,i = 1;

		lps[0] = 0;

		while (i < M) {
			if (padrao.charAt(i) == padrao.charAt(len)) {
				len++;
				lps[i] = len;
				i++;
			} else {
				if (len != 0) {
					len = lps[len - 1];

				} else {
					lps[i] = len;
					i++;
				}
			}
		}

		int cont = 0;
		int total = 0;
		i = 0;
		while ((N - i) >= (M - j)) {
			total++;
			if (padrao.charAt(j) == texto.charAt(i)) {
				j++;
				i++;

			}
			if (j == M) {
				cont++;
				System.out.println("padrão ocorre no indice " + (i - j) + " do texto");
				j = lps[j - 1];
				System.out.println("Total de Comparações: " + total);

			}

			else if (i < N && padrao.charAt(j) != texto.charAt(i)) {

				if (j != 0) {
					j = lps[j - 1];

				} else {
					i = i + 1;
				}
			}
		}

		if (cont == 0) {
			System.out.println("padrao nao encontrado");
		} else {
			System.out.println("Quantiadade de ocorrencias no texto: " + cont);
		}
		System.out.println("=================================================");
	}

	public static void FB(String padrao, String txt) {
		System.out.println("================BUSCA POR FB====================");
		System.out.println(padrao);
		int j, M = padrao.length();
		int i, N = txt.length();
		int cont = 0;
		int total = 0;
		for (i = 0; i <= N - M; i++) {
			for (j = 0; j < M; j++) {
				total++;
				if (txt.charAt(i + j) != padrao.charAt(j))
					break;
			}
				
			if (j == M) {
				System.out.println("padrão ocorre no indice " + i + " do texto");
				
				System.out.println("Total de Comparações: " + total);
				cont++;
			}
		}

		if (cont == 0) {
			System.out.println("padrao n encontrado");
		} else {
			System.out.println("Quantiadade de ocorrencias no texto: " + cont);
		}

		System.out.println("================================================");
	}

	public static void main(String[] args) throws IOException {
		String everything;
		BufferedReader br = new BufferedReader(new FileReader("src/Kmp/file.txt"));
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			everything = sb.toString();
		} finally {
			br.close();
		}
		String text = everything;
		String pattern = "TATATAAGAAAAAAG";

		KMP(text, pattern);
		FB(pattern, text);
		
		pattern = "AGACTCTG";
		KMP(text, pattern);
		FB(pattern, text);
		
		pattern = "GAGAGCGG";
		KMP(text, pattern);
		FB(pattern, text);
		
		pattern = "TCCTCCCAC";
		KMP(text, pattern);
		FB(pattern, text);
	}
}
