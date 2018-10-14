package parse;

import model.Orders;
import services.OrderService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helper {
    /**
     * This class creates an object of the class orders and transfers to the designer data from CSV and XML files
     * @param clientId
     * @param requestId
     * @param name
     * @param quantity
     * @param price
     */
    public void addOrderXML(String clientId, String requestId, String name, String quantity, String price) {
        Pattern pattern = Pattern.compile("^[a-z0-9]{0,6}$");
        Matcher matcher = pattern.matcher(clientId);

        /**verification of incoming data*/
        try {
            long parseRequest = Long.parseLong(String.valueOf(requestId));
            int parseQuantity = Integer.parseInt(String.valueOf(quantity));
            double parsePrice = Double.parseDouble(String.valueOf(price));
            if (!clientId.isEmpty() && matcher.matches()
                    && requestId != null && !requestId.isEmpty()
                    && name != null && !name.isEmpty()
                    && quantity != null && !quantity.isEmpty()
                    && price != null && !price.isEmpty()) {
                OrderService orderService = new OrderService();
                Orders orders = new Orders(clientId, parseRequest, name, parseQuantity, parsePrice);
                orderService.persist(orders);
                System.out.println();
                System.out.println("Order from XML file is added");
            } else
                System.out.println("Order from XML file are not added: invalid data format" + clientId + " " + requestId + " " + name + " " + quantity + " " + price);
        } catch (NumberFormatException e) {
            System.out.println("Order from XML file are not added: invalid data format");
        }finally {
            System.out.println();
        }
    }


    public void addOrderCSV(String[] metadata) {
        Pattern pattern = Pattern.compile("^[a-z0-9]{0,6}$");
        Matcher matcher = pattern.matcher(metadata[0]);
        /**verification of incoming data*/
        try {
            String clientId = metadata[0];
            long requestId = Long.parseLong(metadata[1]);
            String name = metadata[2];
            int quantity = Integer.parseInt(metadata[3]);
            double price = Double.parseDouble(metadata[4]);
            if ((clientId != null && !clientId.isEmpty() && matcher.matches())
                    && (metadata[1] != null && !metadata[1].isEmpty())
                    && (name != null && !name.isEmpty())
                    && (metadata[3] != null && !metadata[3].isEmpty())
                    && (metadata[4] != null && !metadata[4].isEmpty())) {
                OrderService orderService = new OrderService();
                Orders orders = new Orders(clientId, requestId, name, quantity, price);
                orderService.persist(orders);
                System.out.println();
                System.out.println("Order from CSV file is added");
            } else
                System.out.println("Order from CSV file are not added: invalid data format");

        } catch (Exception e) {
            System.out.println("Order from CSV file are not added: invalid data format");
        } finally {
            System.out.println();
        }
    }
}
