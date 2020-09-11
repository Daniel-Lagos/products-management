package controll;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import model.Handling;
import model.Hardware;
import model.ViewActions;

public class ControllProduct implements ActionListener{
	
	private Handling model;
	private ViewActions view;
	
	public ControllProduct(ViewActions view){
		this.view=view;
		model=new Handling(Hardware.compIdHardware);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()){
		case ViewActions.ADD_PRODUCT:addProduct();
			break;
		case ViewActions.DEL_PRODUCT:delProduct();
			break;
		case ViewActions.FIND_PRODUCT:findProduct();
			break;
		case ViewActions.SORT_ID: sort(Hardware.compIdHardware);
			break;
		case ViewActions.SORT_BRAND: sort(Hardware.compBrand);
			break;
		case ViewActions.SORT_DESCRIPTION: sort(Hardware.compDescription);
			break;
		case ViewActions.SORT_COST: sort(Hardware.compCost);
			break;
		case ViewActions.SORT_STOCK: sort(Hardware.compStock);
			break;
		}
	}
	
	private void addProduct(){
		String[] dates=view.readInput(ViewActions.ADD_PRODUCT);
		if(dates!=null){
			String id=dates[0];
			String brand=dates[1];
			String description=dates[2];
			double value=Double.parseDouble(dates[3]);
			short stock=Short.parseShort(dates[4]);
			String[] date=dates[5].split("/");
			String year=date[2];
			String month=date[1];
			String day=date[0];
			GregorianCalendar makeDate=new GregorianCalendar(year.length(),month.length(),day.length());
			String observations=dates[6];
			if(model.addHardware(id, brand, description, value, stock, makeDate, observations)){
				view.writeOutput(ViewActions.ADD_PRODUCT, new String[]{id}, true);
			}else{
				view.writeOutput(ViewActions.ADD_PRODUCT, new String[]{id}, false);
			}
		}
	}
	
	private void findProduct(){
		System.out.println(ViewActions.FIND_PRODUCT);
	}
	
	private void delProduct(){
		System.out.println(ViewActions.DEL_PRODUCT);
	}
	
	private void sort(Comparator<Hardware> comparator){
		ArrayList<Hardware> sorted=model.sort(comparator);
		String[] months=new String[]{"Ene","Fed","Mar","Abr","May","Jun","Jul","Ago","Sep","Oct","Nov","Dic"};
		String[] out=new String[sorted.size()];
		int cant=0;
		for (Hardware hardware : sorted) {
			String id=hardware.getIdHardware();
			String brand=hardware.getBrand();
			String description=hardware.getDescription();
			DecimalFormat df=new DecimalFormat("$###,###.##");
			String cost=df.format(hardware.getCost());
			String stock=String.valueOf(hardware.getStock());
			Calendar date=hardware.getDateMake();
			String day=String.valueOf(date.get(Calendar.DAY_OF_MONTH));
			String month=months[date.get(Calendar.MONTH)];
			String year=String.valueOf(date.get(Calendar.YEAR));
			String makedate=day+"/"+month+"/"+year;
			String obs=hardware.getObservartions();
			out[cant++]=id+";"+brand+";"+description+";"+cost+";"+stock+";"+makedate+";"+obs;
		}
		view.writeOutput("List", out, true);
	}
}
