package gui;

import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelTitle extends JPanel{
	
	private JLabel title;
	
	public PanelTitle(){
		Font font=new Font(Font.MONOSPACED,Font.BOLD,32);
		title=new JLabel("Gestión de Productos");
		title.setFont(font);
		setLayout(new FlowLayout());
		add(title);
	}
}