package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import java.util.ResourceBundle.Control;
import java.util.StringTokenizer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import controll.ControllProduct;
import model.ViewActions;

public class MainWindow extends JFrame implements ViewActions{
	
	private ControllProduct controll;
	private PanelTitle panelTitle;
	private PanelProduct panelProduct;
	private PanelSort panelSort;
	
	public MainWindow() {
		setTitle("Gestion de Hardware");
		setSize(new Dimension(640,560));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setResizable(true);
	}

	@Override
	public void begin() {
		beginElements();
		addElements();
		setVisible(true);
	}

	private void beginElements() {
		panelTitle=new PanelTitle();
		panelProduct=new PanelProduct(this);
		panelSort=new PanelSort(this);
	}

	private void addElements() {
		add(panelTitle,BorderLayout.NORTH);
		add(panelProduct,BorderLayout.CENTER);
		add(panelSort,BorderLayout.SOUTH);
	}

	@Override
	public void exit() {
		
	}

	@Override
	public String[] readInput(String option) {
		switch(option){
		case ViewActions.ADD_PRODUCT: return readProduct();

			
		}
		return null;
	}

	private String[] readProduct() {
		String id=panelProduct.getTxtId().getText();
		String brand=panelProduct.getTxtBrand().getText();
		String description=panelProduct.getTxtDescription().getText();
		String cost=panelProduct.getTxtCost().getText(); //Validar campos vacios
		String stock=panelProduct.getStock().getValue().toString();
		String dates=panelProduct.getMakeDate().getDateFormatString();
		String obs=panelProduct.getObservations().getText();
		String[] dates1=new String[] {id,brand,description,cost,stock,dates,obs};
		return dates1;
	}

	@Override
	public void writeOutput(String option, String[] output, boolean state) {
		switch(option){
		case ViewActions.ADD_PRODUCT:
			if(state){
				JOptionPane.showMessageDialog(null, "Producto Agregado con exito","OK",JOptionPane.INFORMATION_MESSAGE);
				controll.actionPerformed(new ActionEvent(this,0, ViewActions.SORT_ID));
				cleanFields();
				//refreshTable(output);
			}else{
				JOptionPane.showMessageDialog(null, "El Produto ya existe","ERROR",JOptionPane.ERROR_MESSAGE);
			}
			break;
		case "List":refreshTable(output);
			break;
		}
	}

	private void refreshTable(String[] output) {
		DefaultTableModel dtm=panelProduct.getModelTab();
		while(dtm.getRowCount()>0){
			dtm.removeRow(0);
		}
		for (String line : output) {
			/*Object[] row=new Object[]{panelProduct.getTxtId().getText(),
					panelProduct.getTxtBrand().getText(),panelProduct.getTxtDescription(),
					panelProduct.getTxtCost().toString(),panelProduct.getStock().getValue(),
					panelProduct.getMakeDate().getDateFormatString(),panelProduct.getObservations().getText().length()};*/
			StringTokenizer tokens=new StringTokenizer(line, ";");
			String id=tokens.nextToken();
			String brand=tokens.nextToken();
			String description=tokens.nextToken();
			String cost=tokens.nextToken();
			String stock=tokens.nextToken();
			String dates=tokens.nextToken();
			String obs=tokens.nextToken();
			Object[] row=new Object[]{id,brand,description,cost,stock,dates,obs}; 
			dtm.addRow(row);
		}
		
	}

	private void cleanFields() {
		panelProduct.getTxtId().setText("");
		panelProduct.getTxtBrand().setText("");
		panelProduct.getTxtDescription().setText("");
		panelProduct.getTxtCost().setText("");
		panelProduct.getObservations().setText(""); 
	}

	public ControllProduct getControll(){
		return controll;
	}

	@Override
	public void setControll(ControllProduct controll) {
		this.controll=controll;
	}	
}
