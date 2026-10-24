import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class OrderProcessingSystem {

    static ArrayList<Integer> orderIds = new ArrayList<>();
    static ArrayList<String> customerNames = new ArrayList<>();
    static ArrayList<String> orderItems = new ArrayList<>();
    static ArrayList<Integer> quantities = new ArrayList<>();
    static ArrayList<Double> totalAmounts = new ArrayList<>();

    static Queue<Integer> processingQueue = new LinkedList<>();

    static int nextOrderId = 101; // Starting ID for orders
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean isRunning = true;

        while (isRunning == true) {
            System.out.println("\n=========================================");
            System.out.println("      ONLINE ORDER PROCESSING SYSTEM     ");
            System.out.println("=========================================");
            System.out.println("1. Add New Order ");
            System.out.println("2. View All Order Items");
            System.out.println("3. Sort Order Items");
            System.out.println("4. Search Order by Customer Name");
            System.out.println("5. Add Order ID to Processing Queue");
            System.out.println("6. View Pending Processing Queue");
            System.out.println("7. Peek Next Order to Process");
            System.out.println("8. Process Next Order (Dequeue)");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");

            int choice = 0;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); 
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine(); 
                continue;
            }

            if (choice == 1) {
                addOrder();
            } else if (choice == 2) {
                viewAllOrders();
            } else if (choice == 3) {
                sortMenu();
            } else if (choice == 4) {
                searchOrder();
            } else if (choice == 5) {
                enqueueOrder();
            } else if (choice == 6) {
                viewQueue();
            } else if (choice == 7) {
                peekNextOrder();
            } else if (choice == 8) {
                processNextOrder();
            } else if (choice == 9) {
                System.out.println("Exiting the system. Thank you!");
                isRunning = false;
            } else {
                System.out.println("Invalid choice. Please select 1-9.");
            }
        }
        scanner.close();
    }

    // Method to add a new order with multiple items
    public static void addOrder() {
        System.out.println("\n--- Add New Order ---");
        System.out.print("Enter Customer Name: ");
        String name = scanner.nextLine();

        boolean addingItems = true;

        while (addingItems == true) {
            System.out.print("Enter Item Name: ");
            String item = scanner.nextLine();

            int qty = 0;
            double price = 0.0;
            
            try {
                System.out.print("Enter Quantity: ");
                qty = scanner.nextInt();
                System.out.print("Enter Price per item: ");
                price = scanner.nextDouble();
                scanner.nextLine(); 
            } catch (Exception e) {
                System.out.println("Invalid number input. Skipping this item.");
                scanner.nextLine(); 
                continue; 
            }

            if (qty <= 0 || price < 0) {
                System.out.println("Quantity and price must be greater than zero.");
                continue;
            }

            double total = qty * price;

            
            orderIds.add(nextOrderId);
            customerNames.add(name);
            orderItems.add(item);
            quantities.add(qty);
            totalAmounts.add(total);

            System.out.println("Item added to cart!");

            
            System.out.print("Do you want to add another item to this order? (y/n): ");
            String choice = scanner.nextLine();
            
            if (choice.equalsIgnoreCase("n")) {
                addingItems = false; // This breaks the loop
            }
        }

        System.out.println("Order completed successfully! Order ID: " + nextOrderId);
        
        nextOrderId++; 
    }

    // Method to view all records
    public static void viewAllOrders() {
        System.out.println("\n--- All Order Items ---");
        if (orderIds.size() == 0) {
            System.out.println("No orders found.");
            return;
        }

        System.out.println("ID\tCustomer\tItem\t\tQty\tTotal Amount");
        System.out.println("------------------------------------------------------------");
        for (int i = 0; i < orderIds.size(); i++) {
            System.out.println(orderIds.get(i) + "\t" + 
                               customerNames.get(i) + "\t\t" + 
                               orderItems.get(i) + "\t\t" + 
                               quantities.get(i) + "\t$" + 
                               totalAmounts.get(i));
        }
    }

    // Menu for sorting operations
    public static void sortMenu() {
        if (orderIds.size() == 0) {
            System.out.println("\nNo data to sort.");
            return;
        }

        System.out.println("\n--- Sort Order Items ---");
        System.out.println("1. Sort by Total Amount (Highest to Lowest) - Bubble Sort");
        System.out.println("2. Sort by Quantity (Lowest to Highest) - Selection Sort");
        System.out.print("Enter choice: ");

        int choice = 0;
        try {
            choice = scanner.nextInt();
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Invalid input!");
            scanner.nextLine();
            return;
        }

        System.out.println("\nBefore Sorting:");
        viewAllOrders();

        if (choice == 1) {
            bubbleSortByAmount();
            System.out.println("\nAfter Sorting by Total Amount:");
            viewAllOrders();
        } else if (choice == 2) {
            selectionSortByQuantity();
            System.out.println("\nAfter Sorting by Quantity:");
            viewAllOrders();
        } else {
            System.out.println("Invalid choice.");
        }
    }

    // Sorting Algorithm 1: Bubble Sort (Descending by Total Amount)
    public static void bubbleSortByAmount() {
        int n = totalAmounts.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (totalAmounts.get(j) < totalAmounts.get(j + 1)) {
                    // Swap all parallel elements so data stays synchronized
                    swapRecords(j, j + 1);
                }
            }
        }
        System.out.println("Orders sorted by Total Amount using Bubble Sort.");
    }

    // Sorting Algorithm 2: Selection Sort (Ascending by Quantity)
    public static void selectionSortByQuantity() {
        int n = quantities.size();
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (quantities.get(j) < quantities.get(minIndex)) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                swapRecords(i, minIndex);
            }
        }
        System.out.println("Orders sorted by Quantity using Selection Sort.");
    }

    public static void swapRecords(int index1, int index2) {
        // Swap ID
        int tempId = orderIds.get(index1);
        orderIds.set(index1, orderIds.get(index2));
        orderIds.set(index2, tempId);

        // Swap Name
        String tempName = customerNames.get(index1);
        customerNames.set(index1, customerNames.get(index2));
        customerNames.set(index2, tempName);

        // Swap Item
        String tempItem = orderItems.get(index1);
        orderItems.set(index1, orderItems.get(index2));
        orderItems.set(index2, tempItem);

        // Swap Quantity
        int tempQty = quantities.get(index1);
        quantities.set(index1, quantities.get(index2));
        quantities.set(index2, tempQty);

        // Swap Total Amount
        double tempAmount = totalAmounts.get(index1);
        totalAmounts.set(index1, totalAmounts.get(index2));
        totalAmounts.set(index2, tempAmount);
    }

    public static void searchOrder() {
        System.out.print("\nEnter Customer Name to search: ");
        String searchName = scanner.nextLine();
        boolean found = false;

        System.out.println("\nSearch Results:");
        System.out.println("ID\tCustomer\tItem\t\tQty\tTotal Amount");
        System.out.println("------------------------------------------------------------");
        
        for (int i = 0; i < customerNames.size(); i++) {
            if (customerNames.get(i).equalsIgnoreCase(searchName)) {
                System.out.println(orderIds.get(i) + "\t" + 
                                   customerNames.get(i) + "\t\t" + 
                                   orderItems.get(i) + "\t\t" + 
                                   quantities.get(i) + "\t$" + 
                                   totalAmounts.get(i));
                found = true;
            }
        }

        if (found == false) {
            System.out.println("No orders found for customer: " + searchName);
        }
    }

    // Queue Operation: Enqueue
    public static void enqueueOrder() {
        if (orderIds.size() == 0) {
            System.out.println("\nNo orders exist. Please add an order first.");
            return;
        }

        System.out.print("\nEnter Order ID to add to processing queue: ");
        int idToAdd = 0;
        try {
            idToAdd = scanner.nextInt();
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Invalid input.");
            scanner.nextLine();
            return;
        }

        boolean exists = false;
        for (int i = 0; i < orderIds.size(); i++) {
            if (orderIds.get(i) == idToAdd) {
                exists = true;
                break;
            }
        }

        if (exists == true) {
            if (processingQueue.contains(idToAdd)) {
                System.out.println("Order ID " + idToAdd + " is already in the queue.");
            } else {
                processingQueue.add(idToAdd); // Enqueue
                System.out.println("Order ID " + idToAdd + " added to the queue.");
            }
        } else {
            System.out.println("Order ID not found.");
        }
    }

    // Queue Operation: Display Queue
    public static void viewQueue() {
        System.out.println("\n--- Current Processing Queue ---");
        if (processingQueue.isEmpty()) {
            System.out.println("The queue is empty.");
        } else {
            System.out.println("Order IDs in queue (FIFO order): " + processingQueue);
        }
    }

    // Queue Operation: Peek
    public static void peekNextOrder() {
        System.out.println("\n--- Peek Next Order ---");
        if (processingQueue.isEmpty()) {
            System.out.println("The queue is empty. Nothing to peek.");
        } else {
            int nextId = processingQueue.peek();
            System.out.println("The next order to be processed is Order ID: " + nextId);
        }
    }

    
    public static void processNextOrder() {
        System.out.println("\n--- Process Next Order ---");
        if (processingQueue.isEmpty()) {
            System.out.println("The queue is empty. No orders to process.");
            return;
        }

        
        int processedId = processingQueue.poll();
        System.out.println("Processing completed for Order ID: " + processedId);
        
        double grandTotal = 0.0;
        boolean customerPrinted = false;

        System.out.println("CABRALDA DE CAFE RECEIPT");
        System.out.println("--------------------------------");
        
        
        for (int i = 0; i < orderIds.size(); i++) {
            if (orderIds.get(i) == processedId) {
             
                if (customerPrinted == false) {
                    System.out.println("Customer: " + customerNames.get(i));
                    customerPrinted = true;
                }
                
             
                System.out.println("- " + orderItems.get(i) + " (Qty: " + quantities.get(i) + ") : $" + totalAmounts.get(i));
                
            
                grandTotal = grandTotal + totalAmounts.get(i);
            }
        }
        System.out.println("--------------------------------");
        System.out.println("Grand Total: $" + grandTotal);
    }
}