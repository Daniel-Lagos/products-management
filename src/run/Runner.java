package run;

import javax.swing.JOptionPane;

import console.ViewConsole;
import controll.ControllProduct;
import gui.MainWindow;
import model.ViewActions;

public class Runner {

	public static void main(String...args) {
		
		String[] options=new String[]{"Vista Consola","Vista Grafica"};
		
		int option=JOptionPane.showOptionDialog(null, "Seleccione Vista", "Opciones", JOptionPane.INFORMATION_MESSAGE, 0, null, options, options[0]);
		ViewActions view=null;
		switch(option){
		case 0:view=new ViewConsole();
			break;
		case 1:view=new MainWindow();
			break;
		}
		
		ControllProduct controll=new ControllProduct(view);
		view.setControll(controll);
		view.begin();
	}

}
