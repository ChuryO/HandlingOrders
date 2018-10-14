import model.Orders;

import java.io.*;
import java.util.List;

/**
 * class with additional methods for Main
 */

public class MainHelper {

    /**
     * This method average prices
     * @param price
     * @param count
     * @return
     */
    public double average(double price, int count) {
        double tmp = price / count;
        return Math.round(tmp);
    }

    /**
     * This method write data to new CSV file
     * @param filename
     * @param data
     */

    public void writeCSV(String filename, String data) {
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

    /**
     * This method checks if you want to write data to a new CSV File
     * @param check
     * @param data
     */
    public void checkYoN(String check, String data) {
        if (check.equals("y")) {
            System.out.println("Wprowadź adres do nowego pliku");
            String url;
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

    /**
     * This method checks if you want to write data list to a new CSV File
     * @param check
     * @param data
     * @param s
     */
    public void checkYONList(String check, List<Orders> data, String s) {
        if (check.equals("y")) {
            System.out.println("Wprowadź adres do nowego pliku");
            String url;
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
