package GUI;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.GridLayout;

public class deneme extends JPanel {

	/**
	 * Create the panel.
	 */
	public deneme() {
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		setLayout(new GridLayout(0, 2, 0, 0));
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("New label");
		add(lblNewLabel_5);

	}

}
