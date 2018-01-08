import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;


//Cormac Buckley 15534413

public class Main_Inventory implements Serializable {

	private static final long serialVersionUID = 1936412560272341499L; // Stops strange serial ID error from occuring.

	public static void main(String args[]) throws IOException {

		ArrayList<Inventory> inv = new ArrayList<>();

		inv.add(new Inventory("1000", "Apple", 30, 2.50, 1.25));
		inv.add(new Inventory("1001", "Orange", 40, 2, 1.00));
		inv.add(new Inventory("2001", "Milk", 10, 2.39, 1.50));
		inv.add(new Inventory("2002", "Orange Juice", 20, 1.99, 1.25));
		inv.add(new Inventory("3001", "Blue Cheese", 10, 2.25, 1.50));
		inv.add(new Inventory("3002", "Cheddar", 20, 2.79, 1.60));
		inv.add(new Inventory("4001", "Chocolate", 40, 2.99, 1.70));
		inv.add(new Inventory("4002", "Candy", 30, 0.99, 0.50));
		inv.add(new Inventory("5001", "Beef", 10, 5.00, 3.00));
		inv.add(new Inventory("5002", "Chicken", 10, 4.00, 2.00));

		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream("C:\\Users\\I342042\\Desktop/InventoryList.txt");
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

		for (int i = 0; i < inv.size(); i++) {
			System.out.println(inv.get(i).toString());

		}
	}
}
