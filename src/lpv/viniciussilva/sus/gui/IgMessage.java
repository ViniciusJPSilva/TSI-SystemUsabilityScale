package lpv.viniciussilva.sus.gui;

import static lpv.viniciussilva.sus.util.Constants.*;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class IgMessage extends JDialog {

	private static final long serialVersionUID = 1L;

	public IgMessage(JFrame container, String message, String title) {
		setBounds(WINDOW_POSITION_X, WINDOW_POSITION_Y, MESSAGE_WIDTH, MESSAGE_HEIGHT);
		
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		contentPane.add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton(STR_OK);
		okButton.setActionCommand(STR_OK);
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		
		JTextPane textPane = new JTextPane();
		textPane.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		textPane.setEditable(false);
		textPane.setText(message);
		contentPane.add(textPane, BorderLayout.CENTER);
		
		okButton.addActionListener((e) -> dispose());
		
		setLocationRelativeTo(container);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle(title);
		setModal(true);
		setVisible(true);
	}
} // class IgMessage
