package gui;

import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import model.ViewActions;

public class PanelSort extends JPanel{
	
	private JRadioButton chkId;
	private JRadioButton chkBrand;
	private JRadioButton chkDescription;
	private JRadioButton chkCost;
	private JRadioButton chkStock;
	private MainWindow window;
	
	public PanelSort(MainWindow window){
		this.window=window;
		setLayout(new GridLayout(1,5));
		beginComponetns();
		addElements();
	}
	
	private void beginComponetns(){
		chkId=new JRadioButton("ID");
		chkId.setActionCommand(ViewActions.SORT_ID);
		chkId.addActionListener(window.getControll());
		
		chkBrand=new JRadioButton("Marca");
		chkBrand.setActionCommand(ViewActions.SORT_BRAND);
		chkBrand.addActionListener(window.getControll());
		
		chkDescription=new JRadioButton("Descripción");
		chkDescription.setActionCommand(ViewActions.SORT_DESCRIPTION);
		chkDescription.addActionListener(window.getControll());
		
		chkCost=new JRadioButton("Valor Unitario");
		chkCost.setActionCommand(ViewActions.SORT_COST);
		chkCost.addActionListener(window.getControll());
		
		chkStock=new JRadioButton("Existencias");
		chkStock.setActionCommand(ViewActions.SORT_STOCK);
		chkStock.addActionListener(window.getControll());
		ButtonGroup group=new ButtonGroup();
		group.add(chkId);
		group.add(chkBrand);
		group.add(chkDescription);
		group.add(chkCost);
		group.add(chkStock);
	}
	
	private void addElements(){
		add(chkId);
		add(chkBrand);
		add(chkDescription);
		add(chkCost);
		add(chkStock);
	}

}
