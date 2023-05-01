package lpv.viniciussilva.sus.util;

import java.awt.event.KeyEvent;

public interface Constants {
	
	public static enum language {
		PORTUGUESE, ENGLISH;
	};
	
	// Alguns textos possuem uma versão em inglês.
	public static final String SUS_TITLE = "System Usability Scale",
			
						// Images paths
						PATH_ICON_TITLE_BAR = "img/icon.png",
			
						// Messages
						MSG_ERROR_INITIALIZE = "Ocorreu um erro ao inicializar a interface!\nO programa será encerrado.\n\nAn error ocurred while starting the interface!\nThe program will be terminated.",
						MSG_TIP_OPEN_A_FILE[] = {"Abre um arquivo.", "Open a file."},
						MSG_OPEN_FILE[] = {"Abrir arquivo", "Open file"},
						MSG_OPEN[] = {"Abrir", "Open"},
						MSG_CSV_FILES[] = {"Arquivos separados por vírgula (*.csv)", "Comma separated files (*.csv)"},
						MSG_OPERATION_CANCELED[] = {"Operação cancelada!", "Operation canceled!"},
						MSG_ERROR_OPEN_FILE[] = {"Ocorreu um erro ao abrir o arquivo!", "An error occurred while opening the file!"},
						
						// Components
						MENU_SUS = "SUS",
						MENU_OPTIONS[] = {"Opções", "Options"},
						MENU_HELP[] = {"Ajuda", "Help"},
						MENU_RECENT_FILES[] = {"Arquivos Recentes", "Recent Files"},
						MENU_LANGUAGE[] = {"Idioma", "Language"},
						RADIO_LANGUAGE_PORTUGUESE[] = {"Português", "Portuguese"},
						RADIO_LANGUAGE_ENGLISH[] = {"Inglês", "English"},
						OPTION_EXIT[] = {"Sair", "Exit"},
						OPTION_OPEN_FILE[] = {"Abrir arquivo...", "Open file..."},
						OPTION_INSTRUCTIONS[] = {"Instruções", "Instructions"},
						OPTIONS_ABOUT[] = {"Sobre", "About"},
						TITLE_SCORE[] = {"Pontuação Final da Interface", "Final Interface Score"},
						TITLE_ADJECTIVE[] = {"Adjetivo", "Adjective"},
						TITLE_ACCEPTANCE[] = {"Aceitação", "Acceptance"},
						
	
						// Outros
						STR_EMPTY = "",
						STR_ZERO = "00",
						STR_OK = "OK" 
						;
	
	public static int MNEMONIC_OPEN_FILE[] = {KeyEvent.VK_A, KeyEvent.VK_O}
						;
	
	public static final int WINDOW_WIDTH = 1280,
							WINDOW_HEIGHT = 720,
							WINDOW_POSITION_X = 100,
							WINDOW_POSITION_Y = 100,
							MESSAGE_WIDTH = 450,
							MESSAGE_HEIGHT = 300
							;

}
