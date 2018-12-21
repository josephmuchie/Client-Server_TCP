package mini.project.pkg1;

/**
 * @author Joseph Muchengeti && Joseph-Ben Okanlawon
 */
import java.net.*;
import java.io.*;
import java.beans.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {

    private static final String SERIALIZED_FILE_NAME = "clientOrders.xml";

    public static void main(String[] args) throws Exception {
        try {
            Socket socket = new Socket("localhost", 1892);

            Scanner scan = new Scanner(System.in);
            
            ArrayList ordersList = new ArrayList();

            int number_of_orders;
            double grandTotal = 0, total = 0, placeHolder = 0;
            try {
                System.out.println("Enter number of Orders you want to add: ");
                number_of_orders = scan.nextInt();
                scan.nextLine();
                for (int n = 0; n < number_of_orders; n++) {
                    System.out.println("Enter order: " + (n + 1));

                    System.out.println("Order ID: ");
                    int order_id = scan.nextInt();
                    scan.nextLine();

                    System.out.println("Name of product: ");
                    String name = scan.nextLine();

                    System.out.println("Price: ");
                    double price = scan.nextDouble();
                    scan.nextLine();

                    System.out.println("Quantity: ");
                    int quantity = scan.nextInt();
                    scan.nextLine();

                    total = price * quantity;

                    System.out.printf("The total is: $%.2f\n", total);
                    grandTotal = grandTotal + total;
                    if (n == (number_of_orders - 1)) {

                        System.out.println(ConsoleColors.GREEN + "Your orders have been saved to the Shopping basket :) \nYour grand total of all your orders is: $"+ grandTotal);
                    }
                    
                    
                    Orders order = new Orders(order_id, name, price, quantity,new Total(total), placeHolder);
                    Orders orderTotal = new Orders(order_id, name, price, quantity, new Total(total), grandTotal);
                    ordersList.add(order);
                    ordersList.add(orderTotal.getGrandTotal());

                }

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

            AllOrders collection1 = new AllOrders();
            collection1.setAllOrders(ordersList);

            
            try {
                FileOutputStream fos = new FileOutputStream(SERIALIZED_FILE_NAME);
                XMLEncoder encoder = new XMLEncoder(fos);
                encoder.writeObject(collection1);
                encoder.close();
            } catch (Exception ex) {
                System.out.println("Exception! " + ex.getMessage());
            }

            try {
                
                File serializedFile = new File(SERIALIZED_FILE_NAME);
                while (true) {
                    byte[] mybytearray = new byte[(int) serializedFile.length()];
                    BufferedInputStream bis = new BufferedInputStream(new FileInputStream(serializedFile));
                    bis.read(mybytearray, 0, mybytearray.length);
                    OutputStream os = socket.getOutputStream();
                    System.out.println("Thank you for ordering with us. Your order " + SERIALIZED_FILE_NAME + " has been sent to us and will be dispatched soon!");
                    os.write(mybytearray, 0, mybytearray.length);
                    os.flush();

                    socket.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

        } catch (Exception e) {
            System.out.println(ConsoleColors.RED + e.getMessage() + "\nThe server is not running");
        }
    }
}
