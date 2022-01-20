# Store

It is necessary to create a lightweight application for an e-commerce solution. The application should consist of two parts located on different screens:


• store-front — the client where goods are viewed and purchased

• back-end — administrative part that allows you to add new products or edit existing ones


Information about the product must contain the name, cost and quantity (units in stock). The test data is in the data.csv file inside the archive with this task. The code should run in Xcode/Android Studio.


Store-front provides the ability to view full information about one item and navigate to the next or previous item. The product is displayed only if its quantity is greater than zero. The transition from one product to another occurs with an animated screen shift to the left / right during horizontal “turning”.


The back-end provides the ability to view, edit, and add products.
At any time, the user can move from one screen to another.


Implement the ability to save and load product data in any convenient way. But it is necessary to provide for the possibility of a painless storage replacement (Adapter or Visitor pattern). For example, to switch to using XML, JSON or other (including binary) data formats.


Buying and saving data after editing takes a long time: 3 and 5 seconds, respectively. And in order not to block the application, these operations must be performed in separate threads. In this case, the user can continue to edit or buy products without waiting for the completion of previous operations. After each operation is completed, the changes should be reflected on the store-front screen.
The purchase operation ends with a decrease in the quantity of the product by 1. The product should disappear from the store-front screen when its quantity reaches 0.
It is necessary to ensure that the user will not be able to buy a product with a quantity of less than 1, and changes made on the back-end screen will not be lost. 
