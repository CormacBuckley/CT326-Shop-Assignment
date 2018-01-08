import java.io.Serializable;
import java.text.DecimalFormat;

//Cormac Buckley 15534413

public class Item implements Serializable {

	private static final long serialVersionUID = 1936412560272341499L; // Stops strange serial ID error from occuring.

	private String itemName;
	private double cost;
	private int quant;

	private static DecimalFormat df2 = new DecimalFormat(".##");

	public Item(String name, double price, int quantity) {
		itemName = name;
		cost = price;
		quant = quantity;
	}

	public String toString() {
		return quant + "\t" + itemName + ' ' + "\t" + "€" + df2.format(quant * cost);

	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public int getQuant() {
		return quant;
	}

	public void setQuant(int quant) {
		this.quant = quant;
	}

}
