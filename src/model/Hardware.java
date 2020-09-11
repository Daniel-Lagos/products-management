package model;

import java.util.Calendar;
import java.util.Comparator;

public class Hardware {
	
	private String IdHardware;
	private String brand;
	private String description;
	private double cost;
	private short stock;
	private Calendar dateMake;
	private String observartions;
	
	public static Comparator<Hardware> compIdHardware =new Comparator<Hardware>(){

		@Override
		public int compare(Hardware arg0, Hardware arg1) {
			return arg0.getIdHardware().compareTo(arg1.getIdHardware());
		}
		
	};
	
	public static Comparator<Hardware> compBrand =new Comparator<Hardware>(){

		@Override
		public int compare(Hardware arg0, Hardware arg1) {
			return arg1.getBrand().compareTo(arg0.getBrand());
		}
		
	};
	
	public static Comparator<Hardware> compDescription =new Comparator<Hardware>(){

		@Override
		public int compare(Hardware arg0, Hardware arg1) {
			return arg0.getDescription().compareTo(arg1.getDescription());
		}
		
	};
	
	public static Comparator<Hardware> compCost =new Comparator<Hardware>(){

		@Override
		public int compare(Hardware arg0, Hardware arg1) {
			return (int) (arg1.getCost()-arg0.getCost());
		}
		
	};
	
	public static Comparator<Hardware> compStock =new Comparator<Hardware>(){

		@Override
		public int compare(Hardware arg0, Hardware arg1) {
			return arg0.getStock()-arg1.getStock();
		}
		
	};
	
	private Hardware(){
	}
	
	public Hardware(String idHardware, String brand, String description, double cost, short stock, Calendar dateMake, String observartions) {
		this.IdHardware = idHardware;
		this.brand = brand;
		this.description=description;
		this.cost = cost;
		this.stock = stock;
		this.dateMake = dateMake;
		this.observartions = observartions;
	}
	
	public String getIdHardware() {
		return IdHardware;
	}
	public void setIdHardware(String idHarware) {
		IdHardware = idHarware;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public short getStock() {
		return stock;
	}
	public void setStock(short stock) {
		this.stock = stock;
	}
	public Calendar getDateMake() {
		return dateMake;
	}
	public void setDateMake(Calendar dateMake) {
		this.dateMake = dateMake;
	}
	public String getObservartions() {
		return observartions;
	}
	public void setObservartions(String observartions) {
		this.observartions = observartions;
	}

	@Override
	public String toString() {
		return "Hardware [IdHardware=" + IdHardware + ", brand=" + brand + ", description=" + description + ", cost="
				+ cost + ", stock=" + stock + ", dateMake=" + dateMake + ", observartions=" + observartions + "]";
	}
}
