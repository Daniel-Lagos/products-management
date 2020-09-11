package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.*;

import model.ViewActions;

public class PanelProduct extends JPanel {
	
	private JTextField txtId;
	private JTextField txtBrand;
	private JTextField txtDescription;
	private JTextField txtCost;
	private JSpinner stock;
	private JDateChooser makeDate;
	private JTextArea observations;
	private JTable table;
	private JButton btnFind;
	private JButton btnDelete;
	private JButton btnAdd;
	private DefaultTableModel modelTab;
	private MainWindow window;
	
	public PanelProduct(MainWindow window){
		this.window=window;
		setLayout(new GridBagLayout());
		Border loweredbevel= BorderFactory.createLoweredBevelBorder();
		TitledBorder title=BorderFactory.createTitledBorder(loweredbevel, "Datos de Producto");
		title.setTitlePosition(TitledBorder.CENTER);
		this.setBorder(title);
		beginElements();
		addElemtens();
	}
	
	private void beginElements(){
		txtId=new JTextField();
		txtBrand=new JTextField();
		txtDescription=new JTextField(); 
		txtCost=new JTextField();
		txtCost.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				char key=e.getKeyChar();
				if(Character.isDigit(key)==false)
					e.consume();
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
			
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
			}
		}); //solo reciba numeros
		SpinnerNumberModel model= new SpinnerNumberModel(4,1,99,1);
		stock=new JSpinner(model);
		JFormattedTextField tf = ((JSpinner.DefaultEditor)stock.getEditor()).getTextField();
		tf.setEditable(false);
		makeDate=new JDateChooser(Calendar.getInstance().getTime());
		observations=new JTextArea();
		
		btnAdd=new JButton("Agregar");
		btnAdd.setActionCommand(ViewActions.ADD_PRODUCT);
		btnAdd.addActionListener(window.getControll());
		
		btnFind=new JButton("Buscar");
		btnFind.setActionCommand(ViewActions.FIND_PRODUCT);
		btnFind.addActionListener(window.getControll());
		
		btnDelete=new JButton("Eliminar");
		btnDelete.setActionCommand(ViewActions.DEL_PRODUCT);
		btnDelete.addActionListener(window.getControll());
		
		String[] headings=new String[]{"ID","Marca","Descripción","Valor\nUnitario","Existencias"};
		modelTab=new DefaultTableModel(headings,0);
		table=new JTable(modelTab);
		table.setModel(modelTab);
		//table.setAutoResizeMode(HEIGHT);
		
	}
	
	private void addElemtens(){
		GridBagConstraints gbc=new GridBagConstraints();
		/*gbc.gridheight  unir filas
		  gbc.gridwidth unir columnas*/
		gbc.weightx=1.0;
		//titulo id
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.gridwidth=3;
		add(new JLabel("Id Producto"),gbc);
		
		//titulo marca
		gbc.gridx=3;
		gbc.gridwidth=2;
		add(new JLabel("Marca"),gbc);
		
		//cuadro de texto id
		gbc.gridx=0;
		gbc.gridy=1;
		gbc.gridwidth=3;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		add(txtId,gbc);
		
		//cuadro de texto de marca
		gbc.gridx=3;
		gbc.gridwidth=2;
		add(txtBrand,gbc);
		
		//titulo descripcion
		gbc.gridx=0;
		gbc.gridy=2;
		gbc.gridwidth=3;
		gbc.fill=GridBagConstraints.NONE;
		add(new JLabel("Descripción"),gbc);
		
		//titulo costo
		gbc.gridx=3;
		gbc.gridwidth=1;
		add(new JLabel("Valor unitario"),gbc);
		
		//titulo Existencias
		gbc.gridx=4;
		add(new JLabel("Existenicas"),gbc);
		
		//cuadro de texto descripcion
		gbc.gridx=0;
		gbc.gridy=3;
		gbc.gridwidth=3;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		add(txtDescription,gbc);
		
		//cuadro de costos
		gbc.gridx=3;
		gbc.gridwidth=1;
		add(txtCost,gbc);
		
		//Spinner stock
		gbc.gridx=4;
		
		add(stock,gbc);
		
		//titulo Fecha
		gbc.gridx=0;
		gbc.gridy=4;
		gbc.gridwidth=2;
		add(new JLabel("Fecha Fabricaión"),gbc);
		
		//titulo Observaciones
		gbc.gridx=2;
		gbc.gridwidth=1;
		add(new JLabel("Observaciones"),gbc);
		
		
		//Calendario
		gbc.gridx=0;
		gbc.gridy=5;
		gbc.gridwidth=2;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		add(makeDate,gbc);
		
		//escribir observaciones
		gbc.gridx=2;
		gbc.gridwidth=3;
		gbc.gridheight=3;
		gbc.fill=GridBagConstraints.BOTH;
		add(new JScrollPane(observations),gbc);
		
		//Boton eliminar
		gbc.gridx=0;
		gbc.gridy=6;
		gbc.gridwidth=1;
		gbc.gridheight=1;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		add(btnDelete,gbc);
		
		//Boton Aceptar
		gbc.gridx=1;
		add(btnFind,gbc);
		
		//Boton Añadir
		gbc.gridx=0;
		gbc.gridy=7;
		gbc.gridwidth=2;
		add(btnAdd,gbc); 
		
		//tabla
		gbc.gridy=8;
		gbc.gridwidth=5;
		add(new JScrollPane(table),gbc);
		
	}
	
	public JTextField getTxtId() {
		return txtId;
	}

	public void setTxtId(JTextField txtId) {
		this.txtId = txtId;
	}

	public JTextField getTxtBrand() {
		return txtBrand;
	}

	public void setTxtBrand(JTextField txtBrand) {
		this.txtBrand = txtBrand;
	}

	public JTextField getTxtDescription() {
		return txtDescription;
	}

	public void setTxtDescription(JTextField txtDescription) {
		this.txtDescription = txtDescription;
	}

	public JTextField getTxtCost() {
		return txtCost;
	}

	public void setTxtCost(JTextField txtCost) {
		this.txtCost = txtCost;
	}

	public JSpinner getStock() {
		return stock;
	}

	public void setStock(JSpinner stock) {
		this.stock = stock;
	}

	public JDateChooser getMakeDate() {
		return makeDate;
	}

	public void setMakeDate(JDateChooser makeDate) {
		this.makeDate = makeDate;
	}

	public JTextArea getObservations() {
		return observations;
	}

	public void setObservations(JTextArea observations) {
		this.observations = observations;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public JButton getBtnFind() {
		return btnFind;
	}

	public void setBtnFind(JButton btnFind) {
		this.btnFind = btnFind;
	}

	public JButton getBtnDelete() {
		return btnDelete;
	}

	public void setBtnDelete(JButton btnDelete) {
		this.btnDelete = btnDelete;
	}

	public JButton getBtnAdd() {
		return btnAdd;
	}

	public void setBtnAdd(JButton btnAdd) {
		this.btnAdd = btnAdd;
	}

	public DefaultTableModel getModelTab() {
		return modelTab;
	}
	
}