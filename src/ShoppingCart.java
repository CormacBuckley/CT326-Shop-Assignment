import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

//Cormac Buckley 15534413

public class ShoppingCart implements Serializable {

	private static final long serialVersionUID = 1936412560272341499L; // Stops strange serial ID error from occuring.

	private static DecimalFormat df2 = new DecimalFormat(".##");
	private String name;
	private String date;
	private Inventory selected;
	private int loc; //Location of item in arraylist

	private String file = "C:\\Users\\I342042\\Desktop/InventoryList.txt";
	ArrayList<Inventory> inv = new ArrayList<Inventory>();
	ArrayList<Item> cart = new ArrayList<Item>();

	private Inventory[] shoppingList;

	public ShoppingCart(String name, String date) throws IOException, ClassNotFoundException {
		this.name = name;
		this.date = date;
		loadInvent();
	}

	public void loadInvent() throws IOException, ClassNotFoundException {
		inv.clear();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			while (true) {
				ObjectInputStream ois = new ObjectInputStream(fis);
				inv.add((Inventory) ois.readObject());
			}
		} catch (EOFException ignored) {
			// as expected
		} finally {
			if (fis != null)
				fis.close();
		}
		shoppingList = new Inventory[inv.size()];
		shoppingList = inv.toArray(new Inventory[inv.size()]);
	}

	public void addItem(String item, int quant) {

		selected = searchInventory(item);

		for (int i = 0; i < inv.size(); i++) {
			if (inv.get(i).equals(selected))
				this.loc = i;
		}

		if (inv.get(loc).getQuant() <= 0) {
			System.out.println(item + " is currently out of stock.");
			selected.setQuant(0);
		} else if (quant > inv.get(loc).getQuant()) {
			System.out.println("Only " + inv.get(loc).getQuant() + " of " + item + " are available for purchase.");
			cart.add(new Item(item, selected.getCost(), inv.get(loc).getQuant()));
			inv.get(loc).setQuant(0);
		} else

			cart.add(new Item(item, selected.getCost(), quant));
		inv.get(loc).setQuant(inv.get(loc).getQuant() - quant);
		if (inv.get(loc).getQuant() <= 0) {
			inv.get(loc).setQuant(0);
		}

	}

	public void removeItem(String item, int quant) {
		selected = searchInventory(item);

		for (int i = 0; i < inv.size(); i++) {
			if (inv.get(i).equals(selected))
				this.loc = i;
			break;
		}

		for (int i = 0; i < cart.size(); i++) {
			if (cart.get(i).getItemName().equalsIgnoreCase(item))
				cart.remove(cart.get(i));
			inv.get(loc).setQuant(inv.get(loc).getQuant() + quant);
		}

		inv.get(loc).setQuant(selected.getQuant() + quant);
	}

	public Inventory searchInventory(String item) throws ArrayIndexOutOfBoundsException {
		Inventory test = new Inventory(item);
		Arrays.sort(shoppingList, new InvComparator());
		int pos = Arrays.binarySearch(shoppingList, test, new InvComparator());
		if (pos < 0) {
			throw new ArrayIndexOutOfBoundsException("Item " + item + " not found." + shoppingList[pos]);
		}
		return shoppingList[pos];
	}

	public void viewCart() throws IOException, ClassNotFoundException {
		double total = 0;
		writeStock();
		System.out.println(date + "\t" + name + "\n");
		for (int i = 0; i < cart.size(); i++) {
			System.out.println(cart.get(i));
			double a = cart.get(i).getCost();
			double b = cart.get(i).getQuant();
			total += a * b;
		}
		System.out.println("----------------------------------");
		System.out.println("\t\t Total: " + df2.format(total));
		loadInvent();
		System.out.println("Stock Levels: \n" + Currentstock());

	}

	private void writeStock() throws IOException {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file, false);
			for (int i = 0; i < inv.size(); i++) {
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(inv.get(i));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (fos != null)
				fos.close();
		}
	}

	private String Currentstock() {
		String output = "";
		for (int i = 0; i < inv.size(); i++) {
			output += inv.get(i) + "\n";
		}
		return output;
	}

	public void sortByPrice() {
		cart.sort(new CartComparator());
	}
}

class InvComparator implements Comparator<Inventory> {
	@Override
	public int compare(Inventory a, Inventory b) {
		int res = a.getName().compareToIgnoreCase(b.getName());
		return res;
	}
}

class CartComparator implements Comparator<Item> {
	@Override
	public int compare(Item a, Item b) {
		return a.getCost() * a.getQuant() < b.getCost() * b.getQuant() ? -1
				: a.getCost() * a.getQuant() == b.getCost() * b.getQuant() ? 0 : 1;
	}

}
