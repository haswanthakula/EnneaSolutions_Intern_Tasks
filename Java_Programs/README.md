# Documentation: Java Application Utilizing Collections

## 1. Objective

To develop a Java application that demonstrates the usage of different collection types (List, Set, and Map) by implementing functionalities like adding, removing, and searching elements. The application will also showcase the strengths and use cases of each collection type. Unit tests will be included to verify the correctness of the implementation.

## 2. Application Overview

The application simulates an e-commerce platform with the following functionalities:

  * Inventory Management: Uses a Map to store items with their prices.
  * Shopping Cart: Uses a List to allow duplicate items and preserve insertion order.
  * Wishlist: Uses a Set to store unique items.

## 3. Collections Used and Their Use Cases

### A. List
* Type: ArrayList
* Use Case: Represents the shopping cart where duplicate items and order preservation are required.
* Advantages:
 * Maintains the order of insertion.
 * Allows duplicate items.
 * Provides fast random access by index.

### B. Set
* Type: HashSet
* Use Case: Represents the wishlist, ensuring only unique items are stored.
* Advantages:
 * No duplicate entries.
 * Offers fast insertion and lookup for unique elements.

### C. Map
* Type: LinkedHashMap
* Use Case: Represents the inventory where items are mapped to their prices.
* Advantages:
 * Stores key-value pairs (item and price).
 * Maintains the order of insertion.
 * Fast retrieval of values based on keys.

## 4. Key Functionalities

### A. Inventory Management (Map)
* Add new items with prices.
* Remove items from the inventory.
* Update prices of existing items.
* Search for items by name.

### B. Shopping Cart (List)
* Add items to the cart.
* Remove items from the cart.
* View the cart's contents.

### C. Wishlist (Set)
* Add items to the wishlist.
* Remove items from the wishlist.
* Modify the wishlist by replacing an item.

## 5. Code Structure

### Classes
* MyntraApp: Main class implementing the application logic.
* MyntraAppTest: A class for unit testing the application functionalities.
  
### Key Methods
* Inventory Management: addToInventory(), removeFromInventory(), modifyInventory(), searchItem().
* Shopping Cart: addToCart(), removeFromCart(), viewCart().
* Wishlist: addToWishlist(), removeFromWishlist(), modifyWishlist(), viewWishlist().





