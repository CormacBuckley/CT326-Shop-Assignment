import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;

//Cormac Buckley 15534413

public class Inventory implements Serializable {

	private static final long serialVersionUID = 1936412560272341499L; // Stops strange serial ID error from occuring.

	private String sku;
	private String name;
	private int quant;
	private double cost;
	private double value;
	private Item item;

	public Inventory(String sku, String name, int quant, double cost, double value) {
		this.sku = sku;
		this.name = name;
		this.quant = quant;
		this.cost = cost;
		this.value = value;
		item = new Item(name, cost, quant);
	}

	public Inventory(String itemName) {
		this.name = itemName;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuant() {
		return this.quant;
	}

	public void setQuant(int quant) {
		this.quant = quant;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public double getMystery() {
		return value;
	}

	public Item getItem() {
		return item;
	}

	public void setMystery(double value) {
		this.value = value;
	}

	public String toString() {
		return String.format("%-5s %-15s %-10s %-10s", sku, item.getItemName(), this.getQuant(), this.getCost());

	}
}
