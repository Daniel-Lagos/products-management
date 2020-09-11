package console;

import java.awt.event.ActionEvent;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import controll.ControllProduct;
import model.ViewActions;

public class ViewConsole implements ViewActions {
	
	private ControllProduct controll;

	@Override
	public void begin() {
		System.out.println("Has seleccionado Vista de Consola");
		mainMenu();
	}

	private void mainMenu() {
		String[] options =new String[]{ViewActions.ADD_PRODUCT,ViewActions.DEL_PRODUCT,ViewActions.FIND_PRODUCT,"Listar","Salir"};
		while(true){
			int option=JOptionPane.showOptionDialog(null, "Seleccione su opción", "Menu", JOptionPane.DEFAULT_OPTION, 0, null, options, options[0]);
			switch(option){
			case 0:controll.actionPerformed(new ActionEvent(this, 0, ViewActions.ADD_PRODUCT));
				break;
			case 1:controll.actionPerformed(new ActionEvent(this, 0, ViewActions.DEL_PRODUCT));
				break;
			case 2:controll.actionPerformed(new ActionEvent(this, 0, ViewActions.FIND_PRODUCT));
				break;
			case 3:menuSort();
				break;
				default:
					exit();
			}
		}
		
	}

	private void menuSort() {
		String [] options=new String[]{ViewActions.SORT_ID,ViewActions.SORT_BRAND,ViewActions.SORT_DESCRIPTION,
				ViewActions.SORT_COST,ViewActions.SORT_STOCK,"Volver","Salir"};
		String option=null;
		do{
			option=(String)JOptionPane.showInputDialog(null,"Seleccione opcion","MENU LISTAR",JOptionPane.DEFAULT_OPTION,null,options,options[0]);
			option=toString()==null?"Salir":option;
			switch(option){
				case ViewActions.SORT_ID:controll.actionPerformed(new ActionEvent(this, 0, ViewActions.SORT_ID));
					break;
				case ViewActions.SORT_BRAND:controll.actionPerformed(new ActionEvent(this, 0, ViewActions.SORT_BRAND));
					break;	
				case ViewActions.SORT_DESCRIPTION:controll.actionPerformed(new ActionEvent(this, 0, ViewActions.SORT_DESCRIPTION));
					break;
				case ViewActions.SORT_COST:controll.actionPerformed(new ActionEvent(this, 0, ViewActions.SORT_COST));
					break;
				case ViewActions.SORT_STOCK:controll.actionPerformed(new ActionEvent(this, 0, ViewActions.SORT_STOCK));
					break;
				case "Volver":mainMenu();
					break;
				case "Salir":exit();
					break;
			}
			}while(!options.equals("Salir"));
		
		
	}

	@Override
	public void exit() {
		int aux=JOptionPane.showConfirmDialog(null, "¿Desea Salir?","SALIR",JOptionPane.YES_NO_OPTION);
		if(aux==JOptionPane.YES_NO_OPTION){
			JOptionPane.showMessageDialog(null, "Cerrar aplicación","SALIR",JOptionPane.DEFAULT_OPTION);
			System.exit(0);
		}
	}

	@Override
	public void setControll(ControllProduct controll) {
		this.controll=controll;
	}

	@Override
	public String[] readInput(String option) {
		switch(option){
		case ViewActions.ADD_PRODUCT:
			return readProducta();
		case ViewActions.DEL_PRODUCT:
			break;
		}
		return null;
	}

	private String[] readProducta() {
		String id=readChain("Id Producto");
		String brand=readChain("Marca");
		String description=readChain("Descripción");
		String value=null;
		boolean next=false;
		do{
			value=readChain("Valor");
			Pattern pattern = Pattern.compile("^\\d+\\.?\\d*$");
			Matcher match= pattern.matcher(value);
			if(match.matches()){
				next=true;
			}else{
				JOptionPane.showMessageDialog(null, "Digite un valor entero valido","ERROR",JOptionPane.ERROR_MESSAGE);
			}	
		}while(!next);
		//stock
		String stock=null;
		next=false;
		do{
			stock=readChain("Stock");
			Pattern pattern = Pattern.compile("^\\d{1,2}$");
			Matcher match= pattern.matcher(stock);
			if(match.matches()){
				next=true;
			}else{
				JOptionPane.showMessageDialog(null, "Digite un valor entero valido","ERROR",JOptionPane.ERROR_MESSAGE);
			}	
		}while(!next);
		//fecha
		String date=null;
		next=false;
		do{
			date=readChain("Digite la fecha (yyyy/mm/dd)");
			Pattern pattern=Pattern.compile("^\\d{4}\\/\\d{1,2}\\/\\d{1,2}$"); //falta validacion mes y dia
			Matcher match=pattern.matcher(date);
			if(match.matches()){
				next=true;
			}else{
				JOptionPane.showMessageDialog(null, "Digite una fecha correcta","ERROR",JOptionPane.ERROR_MESSAGE);
			}
		}while(!next);
		String observations=readChain("Observación del producto");
		return new String[]{id,brand,description,value,stock,date,observations};
	}
	
	private String readChain(String msg){
		String chain=null;
		do{
			chain=JOptionPane.showInputDialog(msg);
		}while(chain==null);
		return chain;
	}

	@Override
	public void writeOutput(String option, String[] output, boolean state) {
		switch(option){
		case ViewActions.ADD_PRODUCT:
			if(state){
				JOptionPane.showMessageDialog(null, "Se ha agregado con exito el producto","AGREGADO",JOptionPane.INFORMATION_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(null, "Ya existe el producto","ERROR",JOptionPane.ERROR_MESSAGE);
			}
			break;
		case "List": showProduct(output);
			break;
		}
	}

	private void showProduct(String[] output) {
		for (String line : output) {
			StringTokenizer tokens=new StringTokenizer(line, ";");
			while(tokens.hasMoreTokens()){
				String id=tokens.nextToken();
				String brand=tokens.nextToken();
				String description=tokens.nextToken();
				String cost=tokens.nextToken();
				String stock=tokens.nextToken();
				String date=tokens.nextToken();
				String observations=tokens.nextToken();
				System.out.println("Id del Hardware: "+id+"\n"
				                  +"Marca del Hardware: "+brand+"\n"
				                  +"Descripción: "+description+"\n"
				                  +"Costo: "+cost+"\n"
				                  +"Stock: "+stock+"\n"
				                  +"Fecha: "+date+"\n"
				                  +"Observaciones: "+observations+"\n");
			}
		}
	}
}
