import java.util.Scanner;

//Store class which has an inventory. There can be multiple store possible representing multiple departments
class Store
{
	//array of objects of items for "store inventory"
	public Item inventory[];
	
	//initial default constructor which is bad but for the purposes of this program I do anyways
	Store()
	{
		inventory = new Item[20];
		
		//constructor of store calls the Item constructor with several details
		inventory[0] = new Item("Screws (2 in.)", 0, 2.50, "Construction");
		inventory[1] = new Item("Douglas Fir Plank (2x4)", 25, 8.75, "Raw Materials");
		inventory[2] = new Item("Glue", 10, 5.50, "Construction");
		inventory[3] = new Item("Stain (Ebony)", 10, 5.50, "Finishing");
		inventory[4] = new Item("Stain (Mahogany)", 10, 5.50, "Finishing");
		inventory[5] = new Item("Clamps (16 in.)", 30, 16.50, "Construction");
		inventory[6] = new Item("Paint (White)", 5, 9.50, "Finishing");
		inventory[7] = new Item("Paint (Black)", 0, 9.50, "Finishing");
		inventory[8] = new Item("Hard Maple Plank (2x4)", 10, 28.50, "Raw Materials");
		inventory[9] = new Item("Beech Plank (2x4)", 10, 22.50, "Raw Materials");
		inventory[10] = new Item("Oak Plank (2x4)", 10, 32.50, " Raw Materials");
		inventory[11] = new Item("Drawer Slides (16 inch)", 10, 20.00, "Raw Materials");
		inventory[12] = new Item("Dowels (Pack of 10)", 9, 2.50, "Construction");
		inventory[13] = new Item("Pocket Hole Jig", 8, 125.50, "Construction");
		inventory[14] = new Item("Polyurethane (18 oz)", 0, 15.00, "Finishing");
		inventory[15] = new Item("Sandpaper (Assorted Grits)", 12, 12.50, "Finishing");
		inventory[16] = new Item("Miter Saw", 7, 125, "Machinery");
		inventory[17] = new Item("Table Saw", 2, 300, "Machinery");
		inventory[18] = new Item("Random Orbital Sander", 5, 75, "Machinery");
		inventory[19] = new Item("Air Compressor", 3, 250, "Machinery");
	}
	
	//will display the inventory array
	void displayInventory()
	{
		for (int i = 0; i < inventory.length; i++)
		{
			//viewable boolean check for later sorting by tags etc
			if (inventory[i].viewable)
			{
				System.out.print(i + ": " + inventory[i].name + "     ");
				System.out.print("Amount :" + inventory[i].amount + "     ");
				System.out.println("Price: " + inventory[i].price + "     \n");
			}
		}
		System.out.println();
	}
	
	//get stock for a certain item in the inventory
	//only return booleans because integer return does not concern this program
	boolean getStock(int itemid)
	{
		if (inventory[itemid].amount > 0)
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	//obtains the name of the item for better and more specific UI response
	String getName(int itemid)
	{
		return inventory[itemid].name;
	}
	
	//Management feature for removing item from inventory when a customer puts it in their cart.
	void removeFromInventory(int itemid, int amountwanted)
	{
		inventory[itemid].amount -= amountwanted;
	}
	
	//getAmount which is used to get the number of items in order to check if the customer can order that many.
	int getAmount(int itemid)
	{
		return inventory[itemid].amount;
	}
	
	//get price for cart and price calculation purposes
	double getPrice(int itemid)
	{
		return inventory[itemid].price;
	}
	
	//sortMenu which presents all the sorting choices and then calls the appropriate function
	void sortMenu()
	{
		int choice;
		Scanner scan = new Scanner(System.in);
		
		System.out.println("How would you like to sort our inventory?");
		System.out.println("1. A-Z");
		System.out.println("2. By Tag");
		System.out.println("3. In Stock Only");
		System.out.println("4. Price Ascending");
		System.out.println("5. Price Descending");
		
		choice = scan.nextInt();
		
		//switch for sort choices
		switch(choice)
		{
			//alphabetical sort
			case 1:
			{
				this.sortAZ();
				break;
			}
			//Tag sort with another incorporated menu
			case 2:
			{
				int choice2;
		
				System.out.println("\nWhat tag would you like to view\n");
				System.out.println("1. Machinery");
				System.out.println("2. Finishing");
				System.out.println("3. Raw Materials");
				System.out.println("4. Construction");
				System.out.println("5. All");
				
				choice2 = scan.nextInt();
				
				this.sortTag(choice2);
				break;
			}
			//instock sort
			case 3:
			{
				this.sortInStock();
				break;
			}
			//price ascending sort
			case 4:
			{
				this.sortPriceAsc();
				break;
			}
			//price descending sort
			case 5:
			{
				this.sortPriceDes();
				break;
			}
		}
	}
	
	//alphabetical sort which utilizes the string compareTo() member function as the basis of our simple bubble sort.
	void sortAZ()
	{
		Item temp = new Item("",0,0.00,"");
		for (int i = 0; i < inventory.length; i++)
		{
			for (int j = 1; j < (inventory.length - i); j++)
			{
				if (inventory[j-1].name.compareTo(inventory[j].name) > 0)
				{
					temp = inventory[j-1];
					inventory[j-1] = inventory[j];
					inventory[j] = temp;
				}
			}
		}
	}
	
	//tag sort which will search through the entire array for a matching tag. If all then it will set all items as viewable
	void sortTag(int tag)
	{
		String look = new String("");
		
		//assigning search tag
		if (tag == 1)
		{
			look = "Machinery";
		}
		else if (tag == 2)
		{
			look = "Finishing";
		}
		else if (tag == 3)
		{
			look = "Raw Materials";
		}
		else if (tag == 4)
		{
			look = "Construction";
		}
		else if (tag == 5)
		{
			//setting everything as viewable if the user selects all
			for (int i = 0; i < inventory.length; i++)
			{
				inventory[i].viewable = true;
			}
		}
		
		for (int i = 0; i < inventory.length && (tag != 5); i++)
		{
			//sets each item to viewable just in case there has already been a tagSort earlier.
			inventory[i].viewable = true;
			
			if (inventory[i].Tag != look)
			{
				inventory[i].viewable = false;
			}
		}
	}
	
	//sets every item with 0 in stock to non-viewable
	void sortInStock()
	{
		for (int i = 0; i < inventory.length; i++)
		{
			if (inventory[i].amount == 0)
			{
				inventory[i].viewable = false;
			}
		}
	}
	
	//uses bubble sort with the price as the basis of sort
	void sortPriceAsc()
	{
		Item temp = new Item("",0,0.00,"");
		for (int i = 0; i < inventory.length; i++)
		{
			for (int j = 1; j < (inventory.length - i); j++)
			{
				if (inventory[j-1].price > inventory[j].price)
				{
					temp = inventory[j-1];
					inventory[j-1] = inventory[j];
					inventory[j] = temp;
				}
			}
		}
	}
	
	//uses bubble sort. same as the above sort but just flipped relational operator
	void sortPriceDes()
	{
		Item temp = new Item("",0,0.00,"");
		for (int i = 0; i < inventory.length; i++)
		{
			for (int j = 1; j < (inventory.length - i); j++)
			{
				if (inventory[j-1].price < inventory[j].price)
				{
					temp = inventory[j-1];
					inventory[j-1] = inventory[j];
					inventory[j] = temp;
				}
			}
		}
	}
	
}

class Item
{
	//basic componenets and details of an "item"
	//viewable boolean is used to determine if the display inventory method will display the item
	public String name;
	public int amount;
	public double price;
	public String Tag;
	public boolean viewable;
	
	//basic constructor for initializing all items with the details taken as parameters/arguemnts. Viewable is set to true by default
	Item(String sname, int samount, double sprice, String sTag)
	{
		int i = 0;
		name = sname;
		amount = samount;
		price = sprice;
		Tag = sTag;
		viewable = true;
	}
	
	//set's the name of the items
	void setName(String sname)
	{
		name = sname;
	}
	
	//sets the price of the item
	void setPrice(double sprice)
	{
		price = sprice;
	}
	
	//sets the amount of the item
	void setAmount(int samount)
	{
		amount = samount;
	}
	
	//gets the name of the item
	String getName()
	{
		return name;
	}
	
	//gets the amount of the item in stock
	int getAmount()
	{
		return amount;
	}
	
	//gets the price of the item
	double getPrice()
	{
		return price;
	}
}

class Menu
{
	//basic menu display
	void display()
	{
		System.out.println("1. View Inventory");
		System.out.println("2. Sort Catalog");
		System.out.println("3. Add an Item to Cart");
		System.out.println("4. View Cart");
		System.out.println("5. Checkout");
		System.out.println("6. Exit");
	}
}

class Customer
{
	//Customer variables includes an array of items (their cart) as well as their subtotal;
	Item cart[];
	double total;
	
	//basic constructor will just give 20 items (max default in this program)
	//also initializes every object in the cart array as blanks
	Customer()
	{
		cart = new Item[20];
		total = 0;
		
		for (int i = 0; i < cart.length; i++)
		{
			cart[i] = new Item("",0,0.00,"");
		}
	}
	
	//displays the cart contents
	//does not use "viewable" boolean to determine if it should be displayed
	//also have empty check so that it does not display the "blanks" from the basic constructor
	void displayCart()
	{
		boolean empty = true;
		
		for (int i = 0; i < cart.length; i++)
		{
			if (cart[i].amount != 0)
			{
				System.out.print(cart[i].getName() + "     ");
				System.out.print("Amount :" + cart[i].getAmount() + "     ");
				System.out.println("Price: " + cart[i].getPrice() + "     \n");
				empty = false;
			}
		}
		
		if (empty)
		{
			System.out.print("You have nothing in your cart right now!\n");
		}
		
		System.out.println("\nYour subtotal is: $" + total);
		
		System.out.println();
	}
	
	//adding an item to cart method
	void addToCart(String name, int amount, double price)
	{
		//emptyspace simply is used to find the next available space in the array to write over
		int emptyspace = 0;
		//done boolean is used to calculate the orice of the item, since there are two cases to consider (the item is already in the cart)
		//and if (the item is not already in the cart)
		boolean done = false;
		
		
		for (int i = 0; i < cart.length; i++)
		{
			//for cases when the customer adds the same item to the cart
			if (cart[i].name == name)
			{
				cart[i].amount += amount;
				cart[i].price = price * cart[i].amount;
				done = true;
			}
		}
		
		//if the item is not already in the cart, then find an empty space
		for (int i = 0; i < cart.length; i++)
		{
			if (cart[i].name == "")
			{
				emptyspace = i;
			}
		}
		
		//assign the item to the empty space in the cart found
		if (!done)
		{
			cart[emptyspace].name = name;
			cart[emptyspace].amount = amount;
			cart[emptyspace].price = price * cart[emptyspace].amount;
		}
	}
	
	//updates the total of the cart. 
	void updateTotal()
	{
		total = 0;
		for (int i = 0; i < cart.length; i++)
		{
			total += cart[i].price;
		}
	}
	
	//checkout of the cart which will display the receipt and then empty the cart
	void checkout()
	{
		System.out.println("Here is your receipt: ");
		this.displayCart();
		System.out.println("Sales Tax (1.0875): " + total * .0875 + "\n");
		System.out.println("Total Paid: " + total * 1.0875 + "\n\n");
		this.emptyCart();
	}
	
	//resetting the cart to blanks, updating the total to 0
	void emptyCart()
	{
		for (int i = 0; i < cart.length; i++)
		{
			cart[i].name = "";
			cart[i].amount = 0;
			cart[i].price = 0.00;
		}
		this.updateTotal();
	}
	
}
	


class InventoryDemo
{
	public static void main(String args[])
	{
		//create scanner object, customer object, store object, and menu object.
		Scanner scan = new Scanner(System.in);

		Customer customer = new Customer();
		Store Royce = new Store();
		Menu menu = new Menu();
		int choice;
		
		//basic welcome for UI
		System.out.println("Welcome to Royce's Wood Store: What would you like to do? ");
		
		//do loop for the menu. must be do while in order to take the choice in first.
		do
		{
			//displaying the menu
			menu.display();
			
			//read in user input
			choice = scan.nextInt();
			
			switch(choice)
			{
				//displaying the store inventory
				case 1:
				{
					Royce.displayInventory();
				}
				break;
				
				//sort menu. display the sort choices
				case 2:
				{
					Royce.sortMenu();
				}
				break;
				
				//adding an item to cart. Request item id and amount from user and do simple input validation before moving item
				case 3:
				{
					System.out.println("What is the item id?");
					int itemid = scan.nextInt();
					
					//invalid item id check
					while (itemid > 19 || itemid < 0)
					{
						System.out.println("You entered an invalid item id. Try again");
						{
							itemid = scan.nextInt();
						}
					}
					
					if (Royce.getStock(itemid))
					{
						System.out.println("How many '" + Royce.getName(itemid) + "' would you like?");
						int amountwanted = scan.nextInt();
						if (Royce.getAmount(itemid) >= amountwanted)
						{
							Royce.removeFromInventory(itemid, amountwanted);
							customer.addToCart(Royce.getName(itemid), amountwanted, Royce.getPrice(itemid));
							customer.updateTotal();
						}
						//user asks for more items than availible
						else
						{
							System.out.println("We do not have that many items!");
						}
						
					}
					//user tries to take item that is not in stock
					else 
					{
						System.out.println("Sorry, we are out of stock of " + Royce.getName(itemid));
					}
				}
				break;
				
				//display the cart and total cost
				case 4:
				{
					customer.displayCart();
				}
				break;
				
				//checkout the items in the cart
				case 5:
				{
					customer.checkout();
				}
				break;
			}
		}while (choice > 0 && choice < 6);
		
	}
}
