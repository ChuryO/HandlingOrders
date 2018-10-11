import model.Orders;
import services.OrderService;

import java.io.*;
import java.util.List;

public class Main {
    static OrderService orderService = new OrderService();

    public static void main(String[] args) throws IOException {
        checkARGS(args);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String incomingString = null;

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
            incomingString = reader.readLine().toLowerCase();
            String s = null;
            //
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
                    checkYoN(incomingString, s + countAllQuantity);
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
                            checkYoN(incomingString, s + countQuantityByClientId);
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
                    checkYoN(incomingString, s + countTotalCoast);
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
                            checkYoN(incomingString, s + countCoastByClientId);
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
                    checkYONList(incomingString, allOrderList, s);

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
                            checkYONList(incomingString, allOrdersByClientId, s);
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
                    System.out.println(s + average(countTotalAverageCoast, countTotal));
                    System.out.println("Czy chcesz zapisac w pliku csv \"Y\" albo \"N\"");
                    incomingString = reader.readLine().toLowerCase();
                    checkYoN(incomingString, String.valueOf(average(countTotalAverageCoast, countTotal)));
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
                            System.out.println(s + average(countAverageCoastByClientId, countId));
                            System.out.println("Czy chcesz zapisac w pliku csv \"Y\" albo \"N\"");
                            incomingString = reader.readLine().toLowerCase();
                            checkYoN(incomingString, String.valueOf(average(countAverageCoastByClientId, countId)));
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
    //
    private static void checkARGS(String[] args) {
        if (args.length == 0) {
            System.out.println("Nie zarejestrowano żadnych plików");
            System.exit(0);
        } else {
            for (String arg : args) {
                new IncomingFile(arg);
            }
        }
    }

    //Average price
    private static double average(double price, int count) {
        double tmp = price / count;
        return Math.round(tmp);
    }

    //
    private static void writeCSV(String filename, String data) {
        try {
            FileWriter fileWriter = new FileWriter(new File(filename));
            fileWriter.write(data.toString());
            System.out.println("Done " + filename);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Wprowadzony url może nie być dozwolony, spróbuj ponownie.");
            e.printStackTrace();
        }
    }

    //
    private static void checkYoN(String check, String data) {
        if (check.equals("y")) {
            System.out.println("Wprowadź adres do nowego pliku");
            String url = null;
            try {
                url = new BufferedReader(new InputStreamReader(System.in)).readLine();
                if (!url.contains(".csv")) {
                    writeCSV(url + ".csv", String.valueOf(data));
                } else
                    writeCSV(url, String.valueOf(data));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (check.equals("n")) {
            System.out.println();
        } else System.out.println("Wybór jest nieprawidłowy");
    }
    //
    private static void checkYONList(String check, List<Orders> data, String s) {
        if (check.equals("y")) {
            System.out.println("Wprowadź adres do nowego pliku");
            String url = null;
            StringBuilder sb = new StringBuilder();
            try {
                url = new BufferedReader(new InputStreamReader(System.in)).readLine();
                for (Orders o : data) {
                    sb.append(s);
                    sb.append(o.getName());
                    sb.append('\n');
                }
                if (!url.contains(".csv")) {
                    writeCSV(url + ".csv", sb.toString());

                } else
                    writeCSV(url, String.valueOf(data));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (check.equals("n")) {
            System.out.println();
        } else System.out.println("Wybór jest nieprawidłowy");
    }
}

