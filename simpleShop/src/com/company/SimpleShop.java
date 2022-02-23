package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

//sorry that most of it is written in main & the class names are a bit weird, I was having trouble thinking of them on the fly :)
public class SimpleShop {

    //setup order history
    private static HashMap<String, ArrayList<ShoppingCart>> orderHistory = new HashMap();

    //setup new cart
    private static ShoppingCart cart = new ShoppingCart();

    //setup scanner
    private static Scanner scanner = new Scanner(System.in);


//    //set categories
//    private static ArrayList<Categories> categories = new ArrayList<>(Arrays.asList(
//            new Categories("toys", "toys for Oreo!"),
//            new Categories("food", "food for Oreo!")
//    ));
    //would implement categories in similar fashion to stock if given more time :)

    //stock the shelves
    private static ArrayList<ShoppingItem> stock = new ArrayList(Arrays.asList(
            new ShoppingItem("toy1", 13.00, 10, "red fox"),
            new ShoppingItem("toy2", 13.50, 10, "blue dinosaur"),
            new ShoppingItem("toy3", 25.00, 10, "green pheasant"),
            new ShoppingItem("toy4", 13.99, 10, "purple duck"),
            new ShoppingItem("toy5", 12.99, 10, "gold doughnut"),

            new ShoppingItem("food1", 10.99, 10, "wet food"),
            new ShoppingItem("food2", 5.99, 10, "dry food"),
            new ShoppingItem("food3", 1.00, 10, "bone"),
            new ShoppingItem("food4", 6.99, 10, "treat"),
            new ShoppingItem("food5", 20.00, 10, "raw food")
    ));

//    private static void printCategories() {
//        System.out.println(categories);
//    }

    private static void printStock() {
        //prints stock to command line to show user
        for (int i = 0; i < stock.size(); i++) {
            String outOfStock = "";
            ShoppingItem currentItem = stock.get(i);

            //prints only when item is out of stock, usually an empty String
            if (currentItem.getQuantity() < 1) {
                outOfStock = "Sorry, out of stock";
            }

            //formats the stock into a nice table
            //adapted from 'https://stackoverflow.com/a/35536806/14499253'
            System.out.format(
                    "%-20s %-8s %-30s %-6s \n",
                    (i + 1) + ". " + currentItem.getItemName(),
                    "£" + String.format("%.2f", currentItem.getItemCost()),
                    currentItem.getDescription(),
                    outOfStock
            );
        }
    }

    private static void startNewOrder() {
        //allows user to start a new cart and add/delete things
        int itemChoice;

        while (true) {
//            //prints categories to command line
//            System.out.println("Please choose a category! Press 0 to return to Main Menu!");
//
//            printCategories();

            //prints stock to command line
            System.out.println("Please choose your items :)  Press 0 to return to Main Menu!");

            printStock();

            itemChoice = scanner.nextInt();

            //checks if in stock and either tells you it's been added or has not
            if (itemChoice > 0 && itemChoice <= stock.size()) {
                boolean result = cart.addToCart(stock.get(itemChoice - 1));

                if (result) {
                    System.out.println("Added item to cart!");
                } else {
                    System.out.println("Sorry, could not be added to your cart :(");
                }
                System.out.println(cart.getCartItems());
            } else if (itemChoice == 0) {
                return;
            } else {
                System.out.println("Invalid choice");
            }
        }
    }

    private static void viewPreviousOrder() {
        //shows previous shopping carts and lists their items

        //asks for name to check for orders
        System.out.println("What is your name?");

        //scanner for name input
        String userName = scanner.nextLine();

        //uses hashmap to check for orders against a username
        if (orderHistory.containsKey(userName)) {
            ArrayList<ShoppingCart> previousShoppingCarts = orderHistory.get(userName);

            //looks through array of shopping carts
            for (int i = 0; i < previousShoppingCarts.size(); i++) {
                ShoppingCart currentShoppingCart = previousShoppingCarts.get(i);
                ArrayList<ShoppingItem> cartItems = currentShoppingCart.getCartItems();

                System.out.println("Order Number " + (i + 1));

                //looks through arraylist of items inside each shopping cart
                for (int j = 0; j < cartItems.size(); j++) {
                    ShoppingItem currentItem = cartItems.get(j);

                    //nice table again
                    System.out.format(
                            "%-20s %-8s %-30s \n",
                            (i + 1) + ". " + currentItem.getItemName(),
                            "£" + String.format("%.2f", currentItem.getItemCost()),
                            currentItem.getDescription()
                    );
                }
            }
        } else {
            System.out.println("Could not find an order with that name.  Please try again.");
        }

        int userChoice;

        //adds exit function
        while (true) {
            System.out.println("Press 0 to go to main menu");
            userChoice = scanner.nextInt();
            if (userChoice == 0) {
                return;
            } else {
                System.out.println("Please choose again!");
            }
        }
    }

    private static void viewCurrentCart() {

        ArrayList<ShoppingItem> cartItems = cart.getCartItems();

        int itemChoice;

        while (true) {
            System.out.println("Choose an item to delete!  Press 0 to return to Main Menu!");

            //gets cart
            for (int j = 0; j < cartItems.size(); j++) {
                ShoppingItem currentItem = cartItems.get(j);

                //nice table again
                System.out.format(
                        "%-20s %-8s %-30s \n",
                        (j + 1) + ". " + currentItem.getItemName(),
                        "£" + String.format("%.2f", currentItem.getItemCost()),
                        currentItem.getDescription()
                );
            }

            itemChoice = scanner.nextInt();

            //checks if in cart and deletes
            if (itemChoice > 0 && itemChoice <= cartItems.size()) {
                boolean result = cart.deleteFromCart(cartItems.get(itemChoice - 1));

                if (result) {
                    System.out.println("Deleted item from cart!");
                } else {
                    System.out.println("Could not find that item to delete!");
                }
                System.out.println(cart.getCartItems());
            } else if (itemChoice == 0) {
                return;
            } else {
                System.out.println("Invalid choice");
            }
        }
    }

    private static void checkoutMenu() {

        ArrayList<ShoppingItem> cartItems = cart.getCartItems();

        int userChoice;

        while (true) {
            System.out.println("Ready for checkout!  Press 1 to Continue or Press 0 to return to Main Menu!");

            //gets cart to show
            for (int j = 0; j < cartItems.size(); j++) {
                ShoppingItem currentItem = cartItems.get(j);

                //nice table again
                System.out.format(
                        "%-20s %-8s %-30s \n",
                        (j + 1) + ". " + currentItem.getItemName(),
                        "£" + String.format("%.2f", currentItem.getItemCost()),
                        currentItem.getDescription()
                );
            }

            userChoice = scanner.nextInt();
            scanner.nextLine(); //put in to clear out leftover new line character not read by nextInt

            //if user chooses 1 they go to checkout method
            if (userChoice == 1) {
                checkout();
                return;
            } else if (userChoice == 0) {
                return;
            } else {
                System.out.println("Invalid choice");
            }
        }
    }

    private static void checkout() {

        System.out.println("Please insert your name to continue with checkout!");
        //scanner for name input
        String userName = scanner.nextLine();

        System.out.println("Please insert address to continue with checkout!");
        //address input
        String address = scanner.nextLine();
        cart.setAddress(address);

        //checks if input is correct, if not, reprints statement until input is correct
        boolean paymentTypeValid = false;
        while (!paymentTypeValid) {
            System.out.println("Please choose payment type to complete checkout (Debit/Credit/PayPal)");
            //payment type
            String paymentType = scanner.nextLine();
            paymentTypeValid = cart.setPaymentType(paymentType);
        }

        //collect all this info and then print it out
        ArrayList<ShoppingItem> cartItems = cart.getCartItems();

        //printing items from cart
        for (int j = 0; j < cartItems.size(); j++) {
            ShoppingItem currentItem = cartItems.get(j);

            //nice table again
            System.out.format(
                    "%-20s %-8s %-30s \n",
                    (j + 1) + ". " + currentItem.getItemName(),
                    "£" + String.format("%.2f", currentItem.getItemCost()),
                    currentItem.getDescription()
            );
        }

        //printing payment details and address
        System.out.println(userName);
        System.out.println(cart.getAddress());
        System.out.println(cart.getPaymentType());

        //allows user to "buy" the items
        while (true) {
            System.out.println("Press 1 to continue or Press 0 to cancel and return to Main Menu");
            int userChoice = scanner.nextInt();

            if (userChoice == 1) {
                ArrayList<ShoppingCart> previousCarts = new ArrayList<>();

                if (orderHistory.containsKey(userName)) {
                    previousCarts = orderHistory.get(userName);
                }

                previousCarts.add(cart);
                orderHistory.put(userName, previousCarts);
                cart = new ShoppingCart();
                System.out.println("Order Complete!");
                return;
            } else if (userChoice == 0) {
                return;
            } else {
                System.out.println("Invalid choice");
            }
        }
    }

    public static void main(String[] args) {
        int userChoice;

        //main menu prints
        while (true) {
            System.out.println("Welcome to the Christmas Shop! Would you like to...");
            System.out.println("1. Start a new order");
            System.out.println("2. View previous orders");
            System.out.println("3. View Cart");
            System.out.println("4. Checkout");
            System.out.println("5. Quit");
            userChoice = scanner.nextInt();
            scanner.nextLine(); //put in to clear out leftover new line character not read by nextInt

            //when result is chosen, runs appropriate method
            switch (userChoice) {
                case 1:
                    startNewOrder();
                    break;
                case 2:
                    viewPreviousOrder();
                    break;
                case 3:
                    viewCurrentCart();
                    break;
                case 4:
                    checkoutMenu();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Please choose again!");
            }
        }
    }
}
