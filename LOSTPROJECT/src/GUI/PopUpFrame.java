package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class PopUpFrame extends JFrame {

	private JPanel contentPane;
	JFrame popUpFram = new JFrame();
	/**
	 * Create the frame.
	 */
	public PopUpFrame() {
		setName("popUpFrame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		//setContentPane(contentPane);
	}

}
