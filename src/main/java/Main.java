import model.Orders;
import services.OrderService;

import java.io.*;
import java.util.List;

/**
 * @author Churychev Oleksandr
 *
 */
public class Main {
    static OrderService orderService = new OrderService();

    public static void main(String[] args) throws IOException {
        arguments();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println();
            System.out.println("Ilość zamówień łącznie - Napisz 1");
            System.out.println("Ilość zamówień do klienta o wskazanym identyfikatorze - Napisz 2");
            System.out.println("Łączna kwota zamówień - Napisz  3");
            System.out.println("Łączna kwota zamówień do klienta o wskazanym identyfikatorze - Napisz 4");
            System.out.println("Lista wszystkich zamówień - Napisz 5");
            System.out.println("Lista zamówień do klienta o wskazanym identyfikatorze - Napisz 6");
            System.out.println("Średnia wartość zamówienia - Napisz 7");
            System.out.println("Średnia wartość zamówienia do klienta o wskazanym identyfikatorze - Napisz 8");
            System.out.println("Wyjście napisz 0 lub Exit");
            System.out.println();
            String incomingString = reader.readLine().toLowerCase();
            //
            MainHelper mainHelper = new MainHelper();
            String s;
            switch (incomingString) {
                case "1":
                    s = "Ilość zamówień łącznie - ";
                    List<Orders> countAllQuantityList = orderService.findAll();
                    int countAllQuantity = 0;
                    for (Orders o : countAllQuantityList)
                        countAllQuantity += Integer.parseInt(String.valueOf(o.getQuantity()));
                    System.out.println();
                    System.out.println(s + countAllQuantity);
                    System.out.println("Czy chcesz zapisac w pliku csv \"Y\" albo \"N\"");
                    incomingString = reader.readLine().toLowerCase();
                    mainHelper.checkYoN(incomingString, s + countAllQuantity);
                    break;
                case "2":
                    System.out.println("Wprowadź identyfikator klienta:");
                    incomingString = reader.readLine();
                    s = "Ilość zamówień do klienta o wskazanym identyfikatorze (" + incomingString + ") - ";
                    if (!incomingString.equals("")) {
                        List<Orders> countQuantityByClientIdList = orderService.findAllOrdersByClientId(incomingString);
                        if (countQuantityByClientIdList.size() > 0) {
                            int countQuantityByClientId = 0;
                            for (Orders o : countQuantityByClientIdList)
                                countQuantityByClientId += Integer.parseInt(String.valueOf(o.getQuantity()));
                            System.out.println();
                            System.out.println(s + countQuantityByClientId);
                            System.out.println("Czy chcesz zapisac w pliku csv \"Y\" albo \"N\"");
                            incomingString = reader.readLine().toLowerCase();
                            mainHelper.checkYoN(incomingString, s + countQuantityByClientId);
                        } else {
                            System.out.println();
                            System.out.println("Nie znaleziono klienta o takim ID spróbuj ponownie");
                        }
                    }
                    break;
                case "3":
                    s = "Łączna kwota zamówień - ";
                    List<Orders> countTotalCoastList = orderService.findAll();
                    double countTotalCoast = 0;
                    for (Orders o : countTotalCoastList)
                        countTotalCoast += Double.parseDouble(String.valueOf(o.getPrice()));
                    System.out.println();
                    System.out.println("Łączna kwota zamówień - " + Math.round(countTotalCoast));
                    System.out.println("Czy chcesz zapisac w pliku csv \"Y\" albo \"N\"");
                    incomingString = reader.readLine().toLowerCase();
                    mainHelper.checkYoN(incomingString, s + countTotalCoast);
                    break;
                case "4":
                    System.out.println("Wprowadź identyfikator klienta:");
                    incomingString = reader.readLine();
                    s = "Łączna kwota zamówień do klienta o wskazanym identyfikatorze (" + incomingString + ") - ";
                    if (!incomingString.equals("")) {
                        List<Orders> countCoastByClientIdList = orderService.findAllOrdersByClientId(incomingString);
                        if (countCoastByClientIdList.size() > 0) {
                            double countCoastByClientId = 0;
                            for (Orders o : countCoastByClientIdList)
                                countCoastByClientId += Double.parseDouble(String.valueOf(o.getPrice()));
                            System.out.println();
                            System.out.println(s + countCoastByClientId);
                            System.out.println("Czy chcesz zapisac w pliku csv \"Y\" albo \"N\"");
                            incomingString = reader.readLine().toLowerCase();
                            mainHelper.checkYoN(incomingString, s + countCoastByClientId);
                        } else {
                            System.out.println();
                            System.out.println("Nie znaleziono klienta o takim ID spróbuj ponownie");
                        }
                    }
                    break;
                case "5":
                    s = "Lista wszystkich zamówień - ";
                    List<Orders> allOrderList = orderService.findAll();
                    System.out.println();
                    for (Orders o : allOrderList)
                        System.out.println(s + o.getName());
                    System.out.println("Czy chcesz zapisac w pliku csv \"Y\" albo \"N\"");
                    incomingString = reader.readLine().toLowerCase();
                    mainHelper.checkYONList(incomingString, allOrderList, s);

                    break;
                case "6":
                    System.out.println("Wprowadź identyfikator klienta:");
                    incomingString = reader.readLine();
                    s = "Lista zamówień do klienta o wskazanym identyfikatorze (" + incomingString + ") - ";
                    if (!incomingString.equals("")) {
                        List<Orders> allOrdersByClientId = orderService.findAllOrdersByClientId(incomingString);
                        if (allOrdersByClientId.size() > 0) {
                            System.out.println();
                            for (Orders o : allOrdersByClientId) {
                                System.out.println(s + o.getName());
                            }
                            System.out.println("Czy chcesz zapisac w pliku csv \"Y\" albo \"N\"");
                            incomingString = reader.readLine().toLowerCase();
                            mainHelper.checkYONList(incomingString, allOrdersByClientId, s);
                        } else {
                            System.out.println();
                            System.out.println("Nie znaleziono klienta o takim ID spróbuj ponownie");
                        }
                    }
                    break;
                case "7":
                    s = "Średnia wartość zamówienia - ";
                    List<Orders> countTotalAverageCoastList = orderService.findAll();
                    double countTotalAverageCoast = 0;
                    int countTotal = 0;
                    for (Orders o : countTotalAverageCoastList) {
                        countTotalAverageCoast += Double.parseDouble(String.valueOf(o.getPrice()));
                        countTotal++;
                    }

                    System.out.println();
                    System.out.println(s + mainHelper.average(countTotalAverageCoast, countTotal));
                    System.out.println("Czy chcesz zapisac w pliku csv \"Y\" albo \"N\"");
                    incomingString = reader.readLine().toLowerCase();
                    mainHelper.checkYoN(incomingString, String.valueOf(mainHelper.average(countTotalAverageCoast, countTotal)));
                    break;
                case "8":
                    System.out.println("Wprowadź identyfikator klienta:");
                    incomingString = reader.readLine();
                    s = "Średnia wartość zamówienia do klienta o wskazanym identyfikatorze (" + incomingString + ") - ";
                    if (!incomingString.equals("")) {
                        List<Orders> countTotalAverageCostByClientIdList = orderService.findAllOrdersByClientId(incomingString);
                        if (countTotalAverageCostByClientIdList.size() > 0) {
                            double countAverageCoastByClientId = 0;
                            int countId = 0;
                            for (Orders o : countTotalAverageCostByClientIdList) {
                                countAverageCoastByClientId += Double.parseDouble(String.valueOf(o.getPrice()));
                                countId++;
                            }
                            System.out.println();
                            System.out.println(s + mainHelper.average(countAverageCoastByClientId, countId));
                            System.out.println("Czy chcesz zapisac w pliku csv \"Y\" albo \"N\"");
                            incomingString = reader.readLine().toLowerCase();
                            mainHelper.checkYoN(incomingString, String.valueOf(mainHelper.average(countAverageCoastByClientId, countId)));
                        } else {
                            System.out.println();
                            System.out.println("Nie znaleziono klienta o takim ID spróbuj ponownie");
                        }
                    }
                    break;
                case "0":
                    reader.close();
                    System.exit(0);
                    break;
                case "exit":
                    reader.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Wybór nie pasuje do proponowanych opcji, spróbuj ponownie");
            }
        }
    }

    /**This method checks if you want to write data to a new CSV File*/
    private static void arguments() {
        System.out.println("Proszę podać argumenty:");
        String arg = null;
        String[] substr = null;
        try {
            arg = new BufferedReader(new InputStreamReader(System.in)).readLine();
            substr = arg.split(" ");
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert arg != null;
        if (arg.length() == 0) {
            System.out.println("Brak danych");
            System.exit(0);
        } else {
            if (substr.length != 0) {
                for (String args : substr) {
                    new IncomingFile(args);
                }
            }
        }
    }
}

