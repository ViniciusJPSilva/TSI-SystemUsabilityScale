package lpv.viniciussilva.sus.gui;

import static lpv.viniciussilva.sus.util.Constants.*;

import java.awt.Component;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Classe responsável por gerar uma interface gráfica que fornece ao usuário acesso aos arquivos do sistemas.
 * @author Vinícius José Pires Silva
 */
public class IgFileChooser {
	
	private static JFileChooser fileChooser;
	
	private static JFileChooser getFileChooser() {
		return(fileChooser == null) ? fileChooser = new JFileChooser() : fileChooser;
	}
	
	public static String openFileDialog(Component window, String title, int currentLanguage) {
		setProperties(title, MSG_TIP_OPEN_A_FILE[currentLanguage], MNEMONIC_OPEN_FILE[currentLanguage], currentLanguage);
		
		// Exibe a caixa de diálogo "Abrir Arquivo".
		int opcao = fileChooser.showDialog(window, MSG_OPEN[currentLanguage]);
		
		// Verifica se o usuário cancelou a operação; se não, obtém o nome do arquivo digitado ou selecionad pelo usuário na caixa de diálogo.
		try {
			return (opcao == JFileChooser.APPROVE_OPTION) ? fileChooser.getSelectedFile().getCanonicalPath() : null; 
		}catch(Exception e) {
			return null;
		}
	}

	private static void setProperties(String title, String buttonHelpText, int mnemonic, int currentLanguage) {
		final String CURRENT_PATH = ".", CSV_EXTENSION = "csv";
		
		if(fileChooser == null) {
			fileChooser = getFileChooser();

			// Indica que o usuário poderá selecionar apenas nomes de arquivos.
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

			// Define qual é o diretório default.
			fileChooser.setCurrentDirectory(new File(CURRENT_PATH));

			// Cria os filtros de extensão de arquivo que serão usados pelo JFileChooser para filtrar os tipos de arquivos que serão exibidos na janela.
			FileNameExtensionFilter txtExtensionFilter = new FileNameExtensionFilter(MSG_CSV_FILES[currentLanguage], CSV_EXTENSION);
			
			// O último tipo de arquivo acrescentado ao JFileChooser é considerado o filtro (ou tipo) default quando se usa o método abaixo.
			fileChooser.setFileFilter(txtExtensionFilter);
			
		}
		
		// Define o título da caixa de diálogo.
		fileChooser.setDialogTitle(title);
		
		// Define um texto de ajuda para o botão principal.
		fileChooser.setApproveButtonToolTipText(buttonHelpText);
		
		// Define uma letra mnemônica texto de ajuda para o botão principal da caixa de diálogo.
		fileChooser.setApproveButtonMnemonic(mnemonic);
		
	}// definirPropriedades()
	
}// class IgFileChooser
