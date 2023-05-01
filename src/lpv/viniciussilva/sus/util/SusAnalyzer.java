package lpv.viniciussilva.sus.util;

import java.util.List;

/**
 * Interface responsável por prover métodos que permitem analizar textos, identificar os padrões e 
 * gerar dados compatíveis com o System Usability Scale.
 * 
 * @author Vinícius J P Silva
 */
public interface SusAnalyzer {
	
	/**
	 * Varre uma lista de strings obtida de um arquivo .cvs padronizado, identifica as notas individuais e calcula a escala de usabilidade do sistema.
	 * @param csvFileText lista de strings que contém as linhas do arquivo .csv.
	 * @return Nota final do SUS.
	 */
	public static int calculateSus(List<String> csvFileText) {
		final int FIRST_INDEX = 1, LAST_INDEX = 10;
		float totalScore = 0;
		
		// Percorre cada uma das linhas
		for(String answer : csvFileText) {
			answer = answer.replace("\"", "");
			String[] line = answer.split(",");
			int grade = 0;
			for(int index = FIRST_INDEX; index <= LAST_INDEX; index++) {
				int tempValue = Integer.parseInt(line[index]);
				grade += (index % 2 == 0) ? (5 - tempValue) : (tempValue - 1);
			}
			totalScore += grade * 2.5;
		}
		
		return Math.round(totalScore / csvFileText.size());
	} // calculateSus()
	
	/**
	 * Varre uma lista de strings obtida de um arquivo .cvs padronizado, identifica os comentários não vazios e os adiciona à uma String
	 * um comentário por linha.
	 * @param csvFileText lista de strings que contém as linhas do arquivo .csv.
	 * @return String que contém todos os comentários.
	 */
	public static String getComments(List<String> csvFileText) {
		final int INDEX = 23;
		StringBuilder buffer = new StringBuilder();
		int count = 0;
		
		// Percorre cada uma das linhas
		for(String answer : csvFileText) {
			String[] line = answer.split("\"");
			
			if(line.length >= 24)
				if(!line[INDEX].isBlank()) 
					buffer.append(String.format("%d. %s\n",++count, line[INDEX]));
		}
		
		return buffer.toString();
	} // getComments()

} // interface SusAnalyzer
