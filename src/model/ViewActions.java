package model;

import controll.ControllProduct;

public interface ViewActions {
	
	String ADD_PRODUCT="Adicionar Producto";
	String FIND_PRODUCT="Buscar Producto";
	String DEL_PRODUCT="Borrar Producto";
	String SORT_ID="Ordenar ID";
	String SORT_BRAND="Ordenar Marca";
	String SORT_DESCRIPTION="Ordenar Descripción";
	String SORT_COST="Ordenar Costo";
	String SORT_STOCK="Ordenar Existencias";
	
	void begin();
	void exit();
	void setControll(ControllProduct cc);
	String[] readInput(String option);
	void writeOutput(String option, String[] output,boolean state); //opcion, id que deberia llegar, estado

}
