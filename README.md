1. Project Title
Online Order Processing System

2. Project Description
This is a console-based Java application designed to help a small business manage its customer orders. Instead of writing things down on paper, this system lets the user enter customer orders (even with multiple items), stores them in lists, and puts them into a processing line. It solves the problem of losing track of pending orders by using a strict First-In, First-Out (FIFO) queue, and it allows the business to sort their data to see the highest-paying orders or the smallest item quantities. 

3. Objectives
* Create a working Java console application using basic procedural programming.
* Use parallel ArrayLists to store and manage related data without using custom classes or objects.
* Implement a Queue data structure to handle tasks using the FIFO principle.
* Manually code sorting algorithms (Bubble Sort and Selection Sort) to organize data.
* Validate user input so the program doesn't crash when someone types a letter instead of a number.

4. Use Cases and Features
 **Add New Order:** Users can enter a customer's name and add multiple items, quantities, and prices under a single Order ID.
 **View All Orders:** Displays all recorded items in a tabular format.
 **Sort Orders:** Sorts the data by highest Total Amount or lowest Quantity.
 **Search:** Users can type a customer's name to find all items they ordered.
 **Queue Management:** 
  * Add an Order ID to the processing queue.
  * View all Order IDs currently waiting in the queue.
  * Peek at the next Order ID to be processed.
  * Process (dequeue) the next order, which prints a full receipt with a grand total for that specific ID.

5. Data Structure Explanation
Since this project does not use Object-Oriented Programming (OOP), it uses **parallel ArrayLists** to manage data. We have five lists: `orderIds`, `customerNames`, `orderItems`, `quantities`, and `totalAmounts`. The data at index `0` in all these lists belongs to the same order item. If we delete or swap something, we have to do it across all five lists at the same time to keep the data synchronized.

For processing, we use a Queue (LinkedList). Instead of storing all the order details in the queue, the queue only stores the `Integer` Order IDs. When we dequeue an ID, the program loops through the parallel ArrayLists to find all items matching that ID and processes them.

6. Sorting Algorithm Explanation
**Bubble Sort:** Used to sort the records by **Total Amount (Highest to Lowest)**. It works by repeatedly stepping through the list, comparing adjacent amounts, and swapping them if they are in the wrong order. Since we want descending order, it pushes the smaller numbers to the end of the list.
**Selection Sort:** Used to sort the records by **Quantity (Lowest to Highest)**. It works by finding the minimum quantity from the unsorted part of the list and swapping it with the first unsorted element. 

7. How to Run the Program
1. Make sure you have Java installed on your computer.
2. Open your terminal or command prompt.
3. Navigate to the folder where the file is saved.
4. Compile the code by typing: `javac OrderProcessingSystem.java`
5. Run the program by typing: `java OrderProcessingSystem`

8. Screenshots of the System

Main Menu & Adding an Order
Main Menu
<img width="377" height="322" alt="image" src="https://github.com/user-attachments/assets/905c00e5-4b99-4fd5-af44-886bb0513a3a" />

Adding an Order
<img width="551" height="332" alt="image" src="https://github.com/user-attachments/assets/8c2c5bd2-5a6b-429a-b9cb-37002387dc7e" />
<img width="517" height="287" alt="image" src="https://github.com/user-attachments/assets/52545208-1d1f-4da4-97d5-a84a8f49a7a7" />

### Viewing and Sorting Orders

Viewing an Order
<img width="777" height="227" alt="image" src="https://github.com/user-attachments/assets/24e36275-c2b4-4307-acc3-80781b0d5250" />

Sorting an Order
Bubble Sort
<img width="762" height="442" alt="image" src="https://github.com/user-attachments/assets/5040c422-4bb9-4166-82fd-5c5b164f3f73" />

Selection Sort
<img width="777" height="447" alt="image" src="https://github.com/user-attachments/assets/4660491e-9951-4fc3-ace3-6c0015f8b904" />

Search Order by Customer Name
<img width="597" height="205" alt="image" src="https://github.com/user-attachments/assets/a1875b70-0d05-41a3-a653-f013646c821f" />

Adding Order ID to Processing Queue
<img width="437" height="91" alt="image" src="https://github.com/user-attachments/assets/93fad565-a469-4725-aa87-43aec119c3ab" />

Viewing and Peeking in Queue
<img width="471" height="726" alt="image" src="https://github.com/user-attachments/assets/bc58b0ee-ff5f-490d-8169-fe30790da0ce" />

Processing/ Dequeue
<img width="367" height="242" alt="image" src="https://github.com/user-attachments/assets/da7a3e74-7760-4483-aa2c-eb141e7741c5" />











### Queue Operations and Processing Receipt
![Queue Processing Placeholder](screenshot_queue.png)
