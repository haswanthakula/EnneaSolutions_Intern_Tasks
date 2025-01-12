import java.util.*;

public class MyntraApp {
    private Map<String, Inventory> inventory;
    private Map<String, Integer> shoppingCart;
    private Set<String> wishList;

    public MyntraApp() {
        inventory = new LinkedHashMap<>();
        shoppingCart = new HashMap<>();
        wishList = new HashSet<>();

        inventory.put("1", new Inventory(1, "T-shirt", 200.0));
        inventory.put("2", new Inventory(2, "Shoes", 400.0));
        inventory.put("3", new Inventory(3, "Short", 240.0));
    }

    public void addToInventory(int id, String name, double price) {
        inventory.put(String.valueOf(id), new Inventory(id, name, price));
        System.out.println(name + " added to the inventory with price ₹" + price);
    }

    public void removeFromInventory(String id) {
        if (inventory.remove(id) != null) {
            System.out.println("Item with ID " + id + " removed from the inventory.");
        } else {
            System.out.println("Item with ID " + id + " is not in the inventory.");
        }
    }

    public void displayInventory() {
        System.out.println("\nInventory:");
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty. Please add items first.");
        } else {
            inventory.forEach((id, details) -> {
                String name = details.getName();
                double price = details.getPrice();
                System.out.println("ID: " + id + " - " + name + " - ₹" + price);
            });
        }
    }

    public void addToCart(String id) {
        if (inventory.containsKey(id)) {
            String itemName = inventory.get(id).getName();
            double price = inventory.get(id).getPrice();

            shoppingCart.put(id, shoppingCart.getOrDefault(id, 0) + 1);
            System.out.println(itemName + " added to the shopping cart.");
        } else {
            System.out.println("Item with ID " + id + " is not available in the inventory.");
        }
    }

    public void removeFromCart(String id) {
        if (shoppingCart.remove(id) != null) {
        System.out.println("Item with ID " + id + " removed from the shopping cart.");
        } else {
        System.out.println("Item with ID " + id + " is not in the shopping cart.");
        }
    }

    public void viewCart() {
        System.out.println("\nShopping Cart:");
        if (shoppingCart.isEmpty()) {
            System.out.println("The cart is empty.");
        } else {
            shoppingCart.forEach((id, quantity) -> {
                String itemName = inventory.get(id).getName();
                double price = inventory.get(id).getPrice();
                double itemTotal = price * quantity;
                System.out.println(itemName + " - ₹" + price + " x " + quantity + " = ₹" + itemTotal);
            });

            double totalValue = shoppingCart.keySet()
                    .stream()
                    .map(key -> inventory.get(key).getPrice() * shoppingCart.get(key))
                    .reduce(0.0, (val1, val2) -> val1 + val2);

            System.out.println("Total Cart Value: ₹" + totalValue);
        }
    }


    public void addToWishlist(String id) {
        if (inventory.containsKey(id)) {
            if (wishList.add(id)) {
                System.out.println("Item with ID " + id + " added to the wishlist.");
            } else {
                System.out.println("Item with ID " + id + " is already in the wishlist.");
            }
        } else {
            System.out.println("Item with ID " + id + " is not available in the inventory.");
        }
    }

    public void removeFromWishlist(String id) {
        if (wishList.remove(id)) {
            System.out.println("Item with ID " + id + " removed from the wishlist.");
        } else {
            System.out.println("Item with ID " + id + " is not in the wishlist.");
        }
    }

    public void viewWishlist() {
        System.out.println("\nWishlist:");
        if (wishList.isEmpty()) {
            System.out.println("The wishlist is empty.");
        } else {
            wishList.forEach(id -> {
                String name = inventory.get(id).getName();
                double price = inventory.get(id).getPrice();
                System.out.println(name + " - ₹" + price);
            });
        }
    }

    public void searchItem(String id) {
        if (inventory.containsKey(id)) {
            String name = inventory.get(id).getName();
            double price = inventory.get(id).getPrice();
            System.out.println("Item found: " + name + " - ₹" + price);
        } else {
            System.out.println("Item with ID " + id + " is not available in the inventory.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MyntraApp app = new MyntraApp();

        System.out.println("Welcome to Myntra!");

        while (true) {
            System.out.println("\n1. View Inventory");
            System.out.println("2. Add Item to Inventory");
            System.out.println("3. Remove Item from Inventory");
            System.out.println("4. Add to Cart");
            System.out.println("5. Remove from Cart");
            System.out.println("6. View Cart");
            System.out.println("7. Add to Wishlist");
            System.out.println("8. Remove from Wishlist");
            System.out.println("9. View Wishlist");
            System.out.println("10. Search for an Item");
            System.out.println("11. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    app.displayInventory();
                    break;

                case 2:
                    System.out.print("Enter the ID of the item: ");
                    int id = scanner.nextInt();
                    System.out.print("Enter the name of the item: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter the price of " + name + ": ₹");
                    double price = scanner.nextDouble();
                    scanner.nextLine();
                    app.addToInventory(id, name, price);
                    break;

                case 3:
                    System.out.print("Enter the ID of the item to remove: ");
                    String removeId = scanner.nextLine();
                    app.removeFromInventory(removeId);
                    break;

                case 4:
                    System.out.print("Enter the ID of the item to add to the cart: ");
                    String cartId = scanner.nextLine();
                    app.addToCart(cartId);
                    break;

                case 5:
                    System.out.print("Enter the ID of the item to remove from the cart: ");
                    String cartItemId = scanner.nextLine();
                    app.removeFromCart(cartItemId);
                    break;

                case 6:
                    app.viewCart();
                    break;

                case 7:
                    System.out.print("Enter the ID of the item to add to the wishlist: ");
                    String wishlistId = scanner.nextLine();
                    app.addToWishlist(wishlistId);
                    break;

                case 8:
                    System.out.print("Enter the ID of the item to remove from the wishlist: ");
                    String wishlistItemId = scanner.nextLine();
                    app.removeFromWishlist(wishlistItemId);
                    break;

                case 9:
                    app.viewWishlist();
                    break;

                case 10:
                    System.out.print("Enter the ID of the item to search: ");
                    String searchId = scanner.nextLine();
                    app.searchItem(searchId);
                    break;

                case 11:
                    System.out.println("Thank you for shopping with Myntra. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
