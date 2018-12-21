package mini.project.pkg1;

import java.beans.XMLDecoder;
import java.io.*;
import java.net.*;

/**
 * @author Joseph Muchengeti && Joseph-Ben Okanlawon
 */
public class Server {

    private static final String SERIALIZED_FILE_NAME = "invoiceOfOrders.xml";

    public static void main(String args[]) throws Exception {
        ServerSocket sSocket = new ServerSocket(1892);
        System.out.println("The server is now accepting connections ");
        Socket socket = sSocket.accept();

        System.out.println(ConsoleColors.GREEN + "The client is now connected to the server ");
        try {
            
            byte[] mybytearray = new byte[1048576];
            InputStream is = socket.getInputStream();
            FileOutputStream fos = new FileOutputStream(SERIALIZED_FILE_NAME);
            try (BufferedOutputStream bos = new BufferedOutputStream(fos)) {
                int bytesRead = is.read(mybytearray, 0, mybytearray.length);
                bos.write(mybytearray, 0, bytesRead);
                bos.flush();
                System.out.println("A new order:  " + SERIALIZED_FILE_NAME + " has been placed");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        
        XMLDecoder decoder = null;
        try {
            FileInputStream fis = new FileInputStream(SERIALIZED_FILE_NAME);
            BufferedInputStream bis = new BufferedInputStream(fis);
            decoder = new XMLDecoder(bis);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        AllOrders allOrders = (AllOrders) decoder.readObject();
        System.out.println("The decoded object is: " + ((Orders) allOrders.getAllOrders().get(0)).getProductName());
      //  System.out.println("The decoded object is: " + ((Orders) allOrders.getAllOrders().get(1)).getProductName());
        socket.close();

    }
}
