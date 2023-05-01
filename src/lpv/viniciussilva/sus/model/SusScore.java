package lpv.viniciussilva.sus.model;

import java.util.Objects;

/**
 * Representa o System Usability Scale.
 * @author Vinicius J P Silva
 *
 */
public class SusScore {
	
	public final String[][] ACCEPTANCE_LEVEL = {{"Inaceitável","Marginal","Aceitável"},
												{"Unacceptable","Marginal","Acceptable"}},
							ADJECTIVE_LEVEL = {{"Pior Imáginável", "Pobre", "OK", "Bom", "Excelente", "Melhor Imáginável"},
												{"Worst Imaginable", "Poor", "OK", "Good", "Great", "Best Imaginable"}};
			
	private int score;
	private int currentLanguage;
	private String fileName, comments, acceptance, adjective;

	public SusScore(int score, String fileName, String comments, int currentLanguage) {
		this.currentLanguage = currentLanguage;
		this.fileName = fileName;
		this.comments = comments;
		setScore(score);
	}


	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		if(score < 0 || score > 100) return;
		this.score = score;
		defineTextualNotes(currentLanguage);
	}
	
	public int getCurrentLanguage() {
		return currentLanguage;
	}

	public void setCurrentLanguage(int currentLanguage) {
		this.currentLanguage = currentLanguage;
		defineTextualNotes(currentLanguage);
	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getComments() {
		return comments;
	}
	
	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getAcceptance() {
		return acceptance;
	}

	public String getAdjective() {
		return adjective;
	}
	
	@Override
	public String toString() {
		return String.format("System Usability Scale = %d\n\t%s\n\t%s", score, acceptance, adjective);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SusScore other = (SusScore) obj;
		return Objects.equals(fileName, other.fileName);
	}

	private void defineTextualNotes(int currentLanguage) {
		acceptance = ACCEPTANCE_LEVEL[currentLanguage][generateAcceptanceIndex()];
		adjective = ADJECTIVE_LEVEL[currentLanguage][generateAdjectiveIndex()];
		
	}
	
	private int generateAcceptanceIndex() {
		if(score < 50) return 0;
		if(score < 70) return 1;
		return 2;
	}

	private int generateAdjectiveIndex() {
		if(score < 25) return 0;
		if(score < 50) return 1;
		if(score < 70) return 2;
		if(score < 80) return 3;
		if(score < 85) return 4;
		return 5;
	}
} // class SusScore
