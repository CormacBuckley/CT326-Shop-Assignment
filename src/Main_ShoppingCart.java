import java.io.IOException;


//Cormac Buckley 15534413

public class Main_ShoppingCart {
	
	

	public static void main(String args[]) throws ClassNotFoundException, IOException {

		Main_Inventory.main(args); //Running this main class to initialize the inventory file and print out the starting inventory list
		

		ShoppingCart cart1 = new ShoppingCart("Bob", "16/10/2017");
		System.out.println("\n\nCart 1 ---------------------------");
		cart1.addItem("Apple", 2);
		cart1.addItem("Orange", 5);
		cart1.addItem("Milk", 2);
		cart1.addItem("Blue Cheese", 4);
		cart1.addItem("Candy", 25);
		cart1.removeItem("Candy", 5);
		cart1.viewCart();
		System.out.println("----------------------------------\n");
		System.out.println("Cart 2 ---------------------------");
		ShoppingCart cart2 = new ShoppingCart("Drew", "17/10/2017");
		cart2.addItem("Apple",2);
		cart2.addItem("Orange", 5);
		cart2.addItem("Milk", 2);
		cart2.addItem("Blue Cheese", 4);
		cart2.addItem("Cheddar", 3);
		cart2.addItem("Beef", 6);
		cart2.addItem("Candy", 20);
		cart2.addItem("Chocolate", 10);
		cart2.addItem("Chicken", 2);
		cart2.removeItem("Chocolate", 5);
		cart2.removeItem("Blue Cheese", 1);
		cart2.sortByPrice();
		cart2.viewCart();
		System.out.println("----------------------------------");

	}
}
