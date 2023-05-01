package lpv.viniciussilva.sus.gui;

import static lpv.viniciussilva.sus.util.Constants.MENU_HELP;
import static lpv.viniciussilva.sus.util.Constants.MENU_LANGUAGE;
import static lpv.viniciussilva.sus.util.Constants.MENU_OPTIONS;
import static lpv.viniciussilva.sus.util.Constants.MENU_RECENT_FILES;
import static lpv.viniciussilva.sus.util.Constants.MENU_SUS;
import static lpv.viniciussilva.sus.util.Constants.MSG_ERROR_OPEN_FILE;
import static lpv.viniciussilva.sus.util.Constants.MSG_OPEN_FILE;
import static lpv.viniciussilva.sus.util.Constants.MSG_OPERATION_CANCELED;
import static lpv.viniciussilva.sus.util.Constants.OPTIONS_ABOUT;
import static lpv.viniciussilva.sus.util.Constants.OPTION_EXIT;
import static lpv.viniciussilva.sus.util.Constants.OPTION_INSTRUCTIONS;
import static lpv.viniciussilva.sus.util.Constants.OPTION_OPEN_FILE;
import static lpv.viniciussilva.sus.util.Constants.PATH_ICON_TITLE_BAR;
import static lpv.viniciussilva.sus.util.Constants.RADIO_LANGUAGE_ENGLISH;
import static lpv.viniciussilva.sus.util.Constants.RADIO_LANGUAGE_PORTUGUESE;
import static lpv.viniciussilva.sus.util.Constants.STR_EMPTY;
import static lpv.viniciussilva.sus.util.Constants.STR_ZERO;
import static lpv.viniciussilva.sus.util.Constants.SUS_TITLE;
import static lpv.viniciussilva.sus.util.Constants.TITLE_ACCEPTANCE;
import static lpv.viniciussilva.sus.util.Constants.TITLE_ADJECTIVE;
import static lpv.viniciussilva.sus.util.Constants.TITLE_SCORE;
import static lpv.viniciussilva.sus.util.Constants.WINDOW_HEIGHT;
import static lpv.viniciussilva.sus.util.Constants.WINDOW_POSITION_X;
import static lpv.viniciussilva.sus.util.Constants.WINDOW_POSITION_Y;
import static lpv.viniciussilva.sus.util.Constants.WINDOW_WIDTH;
import static lpv.viniciussilva.sus.util.Constants.language.ENGLISH;
import static lpv.viniciussilva.sus.util.Constants.language.PORTUGUESE;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import lpv.viniciussilva.sus.model.SusScore;
import lpv.viniciussilva.sus.util.SusAnalyzer;
import lpv.viniciussilva.sus.util.TextReader;

public class IgSystemUsabilityScale extends JFrame {

	private static final long serialVersionUID = 1L;
	
	// Limita a quantidade de arquivos recentes que serão exibidos.
	private final int MAX_SCORES = 5;

	private final Dimension STD_SIDE_PANEL_DIMENSION = new Dimension(300, 60);
	private final String STD_MAIN_FONT = "Segoe UI";
	private final LineBorder STD_LINE_BORDER = new LineBorder(new Color(128, 128, 128));
	
	// Idioma padrão da aplicação.
	private int currentLanguage = PORTUGUESE.ordinal();
	
	// Lista de arquivos abertos anteriormente.
	private List<SusScore> scoresList = new ArrayList<>();
	
	private SusScore score = null;	
	
	private JMenu susMenu;
	private JMenu optionsMenu;
	private JMenu languageOptionsMenu;
	private JRadioButtonMenuItem portugueseRadioItem;
	private JRadioButtonMenuItem englishRadioItem;
	private JMenu helpMenu;
	private JMenuItem instructionsMenuItem;
	private JMenuItem aboutMenuItem;
	private JMenuItem openFileMenuItem;
	private JSeparator susSeparator;
	private JMenuItem exitMenuItem;
	private Panel footerPanel; 				// Containers: estruturas para organizar e posicionar widgets.
	private Button openFileFooterButton; 	// Widgets: elemento visual, como um botão.
	private JPanel mainPanel;
	private JLabel finalScoreLabel;
	private JLabel scoreTitleLabel;
	private TextArea commentstArea;
	private Panel adjectivePanel;
	private JLabel adjectiveTitleLabel;
	private JLabel adjectiveLabel;
	private Panel acceptancePanel;
	private JLabel acceptanceTitleLabel;
	private JLabel acceptanceLabel;
	private JMenu recentFilesMenu;

	/**
	 * Cria e exibe a janela da aplicação.
	 */
	public IgSystemUsabilityScale() {
		// Tenta alterar o "look and feel" da interface da aplicação para o padrão do SO.
		try {
	        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    } catch (Exception e) {}
		
		// Definições iniciais.
		setBounds(WINDOW_POSITION_X, WINDOW_POSITION_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
		setMinimumSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
		
		setIconImage(new ImageIcon(PATH_ICON_TITLE_BAR).getImage());
		
		// Modificações no content pane.
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout(0, 0));
		
		// Finaliza a aplicação por completo.
		addWindowListener(new WindowAdapter() {	
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		// Menu do cabeçalho.
		JMenuBar headerMenuBar = new JMenuBar();
		setJMenuBar(headerMenuBar);
		
		// Opções do menu do cabeçalho
		susMenu = new JMenu(MENU_SUS);
		optionsMenu = new JMenu(MENU_OPTIONS[currentLanguage]);
		helpMenu = new JMenu(MENU_HELP[currentLanguage]);
		
		headerMenuBar.add(susMenu);
		headerMenuBar.add(optionsMenu);
		headerMenuBar.add(helpMenu);
		
		// Opções no menu "SUS"
		openFileMenuItem = new JMenuItem(OPTION_OPEN_FILE[currentLanguage]);
		susSeparator = new JSeparator();
		exitMenuItem = new JMenuItem(OPTION_EXIT[currentLanguage]);
		
		susMenu.add(openFileMenuItem);
		
		recentFilesMenu = new JMenu(MENU_RECENT_FILES[currentLanguage]);
		susMenu.add(recentFilesMenu);
		susMenu.add(susSeparator);
		susMenu.add(exitMenuItem);
		
		
		// Opções do menu "Opções"
		languageOptionsMenu = new JMenu(MENU_LANGUAGE[currentLanguage]);
		optionsMenu.add(languageOptionsMenu);
		
		// Itens do menu "Idioma"
		ButtonGroup languageButtonGroup = new ButtonGroup();
		portugueseRadioItem = new JRadioButtonMenuItem(RADIO_LANGUAGE_PORTUGUESE[currentLanguage]);
		englishRadioItem = new JRadioButtonMenuItem(RADIO_LANGUAGE_ENGLISH[currentLanguage]);

		languageOptionsMenu.add(portugueseRadioItem);
		languageOptionsMenu.add(englishRadioItem);
		
		languageButtonGroup.add(portugueseRadioItem);
		languageButtonGroup.add(englishRadioItem);
		
		portugueseRadioItem.setSelected(true);
		
		
		// Opções do menu "Ajuda"
		instructionsMenuItem = new JMenuItem(OPTION_INSTRUCTIONS[currentLanguage]);
		JSeparator helpSeparator = new JSeparator();
		aboutMenuItem = new JMenuItem(OPTIONS_ABOUT[currentLanguage]);
		
		helpMenu.add(instructionsMenuItem);
		helpMenu.add(helpSeparator);
		helpMenu.add(aboutMenuItem);
		
		
		
		// Rodapé
		footerPanel = new Panel();
		((FlowLayout) footerPanel.getLayout()).setAlignment(FlowLayout.RIGHT);
		contentPane.add(footerPanel, BorderLayout.SOUTH);
		
		openFileFooterButton = new Button(OPTION_OPEN_FILE[currentLanguage]);
		openFileFooterButton.setFont(new Font(STD_MAIN_FONT, Font.PLAIN, 12));

		footerPanel.add(openFileFooterButton);
		
		
		// Painel principal
		mainPanel = new JPanel();
		contentPane.add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new BorderLayout(0, 0));
		
		finalScoreLabel = new JLabel(STR_ZERO);
		finalScoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		finalScoreLabel.setFont(new Font(STD_MAIN_FONT, Font.BOLD, 85));
		mainPanel.add(finalScoreLabel, BorderLayout.CENTER);
		
		scoreTitleLabel = new JLabel(TITLE_SCORE[currentLanguage]);
		scoreTitleLabel.setMinimumSize(STD_SIDE_PANEL_DIMENSION);
		scoreTitleLabel.setPreferredSize(STD_SIDE_PANEL_DIMENSION);
		scoreTitleLabel.setMaximumSize(STD_SIDE_PANEL_DIMENSION);
		scoreTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		scoreTitleLabel.setFont(new Font(STD_MAIN_FONT, Font.PLAIN, 25));
		mainPanel.add(scoreTitleLabel, BorderLayout.NORTH);
		
		commentstArea = new TextArea();
		commentstArea.setEditable(false);
		commentstArea.setFont(new Font(STD_MAIN_FONT, Font.PLAIN, 15));
		mainPanel.add(commentstArea, BorderLayout.SOUTH);
		
		adjectivePanel = new Panel();
		mainPanel.add(adjectivePanel, BorderLayout.WEST);
		adjectivePanel.setLayout(new BorderLayout(0, 0));
		
		adjectiveTitleLabel = new JLabel(TITLE_ADJECTIVE[currentLanguage]);
		adjectiveTitleLabel.setBorder(STD_LINE_BORDER);
		adjectiveTitleLabel.setPreferredSize(STD_SIDE_PANEL_DIMENSION);
		adjectiveTitleLabel.setMaximumSize(STD_SIDE_PANEL_DIMENSION);
		adjectiveTitleLabel.setMinimumSize(STD_SIDE_PANEL_DIMENSION);
		adjectiveTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		adjectiveTitleLabel.setFont(new Font(STD_MAIN_FONT, Font.PLAIN, 20));
		adjectivePanel.add(adjectiveTitleLabel, BorderLayout.NORTH);
		
		adjectiveLabel = new JLabel();
		adjectiveLabel.setFont(new Font(STD_MAIN_FONT, Font.BOLD, 20));
		adjectiveLabel.setHorizontalAlignment(SwingConstants.CENTER);
		adjectiveLabel.setBorder(STD_LINE_BORDER);
		adjectivePanel.add(adjectiveLabel, BorderLayout.CENTER);
		
		acceptancePanel = new Panel();
		mainPanel.add(acceptancePanel, BorderLayout.EAST);
		acceptancePanel.setLayout(new BorderLayout(0, 0));
		
		acceptanceTitleLabel = new JLabel(TITLE_ACCEPTANCE[currentLanguage]);
		acceptanceTitleLabel.setPreferredSize(STD_SIDE_PANEL_DIMENSION);
		acceptanceTitleLabel.setMinimumSize(STD_SIDE_PANEL_DIMENSION);
		acceptanceTitleLabel.setMaximumSize(STD_SIDE_PANEL_DIMENSION);
		acceptanceTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		acceptanceTitleLabel.setFont(new Font(STD_MAIN_FONT, Font.PLAIN, 20));
		acceptanceTitleLabel.setBorder(STD_LINE_BORDER);
		acceptancePanel.add(acceptanceTitleLabel, BorderLayout.NORTH);
		
		acceptanceLabel = new JLabel();
		acceptanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		acceptanceLabel.setFont(new Font(STD_MAIN_FONT, Font.BOLD, 20));
		acceptanceLabel.setBorder(STD_LINE_BORDER);
		acceptancePanel.add(acceptanceLabel, BorderLayout.CENTER);
		
		
		// Tratamento de eventos: associar uma função de callback a um determinado evento, fazendo algo acontecer quando o evento for disparado.
		// Acões dos botões. 
		openFileMenuItem.addActionListener((e) -> openFileAndCalculateSus());
		openFileFooterButton.addActionListener((e) -> openFileAndCalculateSus());
		
		exitMenuItem.addActionListener((e) -> System.exit(0));
		
		portugueseRadioItem.addActionListener((e) -> changeLanguage(PORTUGUESE.ordinal()));
		englishRadioItem.addActionListener((e) -> changeLanguage(ENGLISH.ordinal()));
		
		aboutMenuItem.addActionListener((e) -> new IgAbout(this));
		
		
		// Definições finais.
		setTitle(SUS_TITLE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setVisible(true);
	}

	
	// Callbacks: funções ou métodos que serão chamados assincronamente, quando um evento ocorre.
	/**
	 * Abre uma interface de seleção de arquivos .csv, lê o arquivo selecionado, obtém e trata os dados, gera um novo score,
	 * adiciona o novo score à lista, atualiza a interface.
	 */
	private void openFileAndCalculateSus() {
		// Obtém o nome do arquivo com seu caminho absoluto.
		String filePath = IgFileChooser.openFileDialog(this, MSG_OPEN_FILE[currentLanguage], currentLanguage);
		
		try {
			List<String> textContent = TextReader.readFile(filePath);

			// Remove o cabeçalho da planilha.
			textContent.remove(0);
			score = new SusScore(SusAnalyzer.calculateSus(textContent), new File(filePath).getName(), SusAnalyzer.getComments(textContent), currentLanguage);
			
			if(!scoresList.contains(score))
				addScore();
			
		} catch (NullPointerException exception) {
			new IgMessage(this, MSG_OPERATION_CANCELED[currentLanguage], SUS_TITLE);
		} catch (Exception e) {
			new IgMessage(this, MSG_ERROR_OPEN_FILE[currentLanguage], SUS_TITLE);
			score = null;
		}
		
		updateInterface();
	} // openCsvFile()

	/**
	 * Adiciona um SusScore à lista de scores.
	 */
	private void addScore() {
		if(scoresList.size() > MAX_SCORES) scoresList.remove(0);
		
		scoresList.add(score);
		
		JMenuItem newScore = new JMenuItem(score.getFileName());
		newScore.addActionListener((e) -> {
			score = scoresList.stream().filter(sc -> sc.getFileName().equalsIgnoreCase(newScore.getText())).collect(Collectors.toList()).get(0);
			updateInterface();
		});
		
		recentFilesMenu.add(newScore);		
	}

	/**
	 * Altera o idioma da aplicação e atualiza toda a interface.
	 * @param language código do idioma conforme enumeração <code>language</code>.
	 * 
	 * @see lpv.viniciussilva.sus.util.Constants.language
	 */
	private void changeLanguage(int language) {
		this.currentLanguage = language;
		updateInterface();
	}
	
	/**
	 * Atualiza alguns componentes da interface da aplicação.
	 */
	private void updateInterface() {
		optionsMenu.setText(MENU_OPTIONS[currentLanguage]);
		languageOptionsMenu.setText(MENU_LANGUAGE[currentLanguage]);
		portugueseRadioItem.setText(RADIO_LANGUAGE_PORTUGUESE[currentLanguage]);
		englishRadioItem.setText(RADIO_LANGUAGE_ENGLISH[currentLanguage]);
		helpMenu.setText(MENU_HELP[currentLanguage]);
		instructionsMenuItem.setText(OPTION_INSTRUCTIONS[currentLanguage]);
		aboutMenuItem.setText(OPTIONS_ABOUT[currentLanguage]);
		openFileMenuItem.setText(OPTION_OPEN_FILE[currentLanguage]);
		exitMenuItem.setText(OPTION_EXIT[currentLanguage]);
		openFileFooterButton.setLabel(OPTION_OPEN_FILE[currentLanguage]);
		scoreTitleLabel.setText(TITLE_SCORE[currentLanguage]);
		adjectiveTitleLabel.setText(TITLE_ADJECTIVE[currentLanguage]);
		acceptanceTitleLabel.setText(TITLE_ACCEPTANCE[currentLanguage]);
		recentFilesMenu.setText(MENU_RECENT_FILES[currentLanguage]);
		commentstArea.setText(score.getComments());
		
		if(score != null) {
			score.setCurrentLanguage(currentLanguage);
			finalScoreLabel.setText(String.valueOf(score.getScore()));
			adjectiveLabel.setText(score.getAdjective());
			acceptanceLabel.setText(score.getAcceptance());
		} else {
			finalScoreLabel.setText(STR_ZERO);
			adjectiveLabel.setText(STR_EMPTY);
			acceptanceLabel.setText(STR_EMPTY);
		}
		
	} // updateInterface()
} // class IgSystemUsabilityScale
