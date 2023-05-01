package lpv.viniciussilva.sus.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public interface TextReader {
	
	/**
	 * Abre, lê o conteúdo e fecha um arquivo.
	 * 
	 * @param path caminho do arquivo no sistema de arquivos.
	 * @return O conteúdo do arquivo como uma lista, linha por linha.
	 * 
	 * @throws Exception 
	 * @see List
	 */
	public static List<String> readFile(String path) throws Exception {
		// String temporária.
		List<String> buffer = new ArrayList<>();
		
		// Buffer de entrada.
		BufferedReader br = new BufferedReader(new FileReader(new File(path)));
		
        String line;
        while ((line = br.readLine()) != null) buffer.add(line);
        
        br.close();
        
		return buffer;
	} // readFile

} // interface TextReader
