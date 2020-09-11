package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

public class Handling {

	private ArrayList<Hardware> hardwares;
	private Comparator<Hardware> comparator;
	
	public Handling(Comparator<Hardware> comp){
		comparator=comp;
		hardwares=new ArrayList<>();		
	}
	
	private int find(ArrayList<Hardware> vector, String id, int begin, int end){
		/*if(begin>end){
			return -1;
		}
		int medium=(begin+end)/2;
		if(vector.get(medium).getIdHardware().equals(id)){
			return medium;
		}
		if(vector.get(medium).getIdHardware().compareTo(id)<0){
			return find(vector, id, medium+1,end);
		}else{
			return find(vector, id, begin, medium-1);
			
		}*/
		
		if(begin<=end){
			int medium=(begin+end)/2;
			if(vector.get(medium).getIdHardware().equals(id)){
				return medium;
				
			}else if(vector.get(medium).getIdHardware().compareTo(id)>0){
				return find(vector,id,begin,medium-1);
			}else{
				return find(vector, id,medium+1,end);
			}
		}
		return -1;
	}
	
	public Hardware getHardware(int pos){
		ArrayList<Hardware> clon= sort(comparator);
		return clon.get(pos);
	}
	
	public int findHardware(String id){
		ArrayList<Hardware> clon= sort(comparator);
		return find(clon, id, 0, clon.size()-1);
	}

	public ArrayList<Hardware> sort(Comparator<Hardware> comparator) {
		ArrayList<Hardware> clonHardwares=(ArrayList<Hardware>) hardwares.clone();
		Collections.sort(clonHardwares, comparator);
		return clonHardwares;
	}
	
	public boolean addHardware(String idHardware, String brand, String description, double cost, short stock, Calendar dateMake, String observartions){
		if(findHardware(idHardware)==-1){
			hardwares.add(new Hardware(idHardware,brand,description,cost,stock,dateMake,observartions));
			return true;
		}
		return false;
	}

	public ArrayList<Hardware> getHardwares() {
		return (ArrayList<Hardware>) hardwares.clone();
	}
	
}
