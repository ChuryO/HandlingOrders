package parse;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This class prepares data from the input CSV file and transfers it to the database
 */
public class CSVParse {
    private int INDEX0;
    private int INDEX1;
    private int INDEX2;
    private int INDEX3;
    private int INDEX4;

    /**
     * this method checks data from CSV File and add them in db
     *
     * @param fileUrl
     */
    public void csvLoad(String fileUrl) {
        Path pathToFile = Paths.get(fileUrl);
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
            //Find and assign column names
            String[] columnLine = br.readLine().split(",");
            for (int i = 0; i < columnLine.length; i++) {
                String tmp = columnLine[i].toLowerCase();
                /**
                 * here I check if the columns in the file are correctly located
                 */
                if (tmp.contains("client_id") || tmp.contains("clientid")) {
                    INDEX0 = i;
                } else if (tmp.contains("request_id") || tmp.contains("requestid")) {
                    INDEX1 = i;
                } else if (tmp.contains("name")) {
                    INDEX2 = i;
                } else if (tmp.contains("quantity")) {
                    INDEX3 = i;
                } else if (tmp.contains("price")) {
                    INDEX4 = i;
                } else {
                    System.out.println("____________________");
                    System.out.println("invalid row or incorrect column names");
                    System.out.println("____________________");
                }
            }
            /**
             * here I change their index
             */

            String data = br.readLine();
            String[] attributesIn;
            String[] attributesOut = new String[columnLine.length];
            Helper helper = new Helper();

            while (data != null) {
                attributesIn = data.split(",");
                if (attributesIn.length == columnLine.length) {
                    attributesOut[0] = attributesIn[INDEX0];
                    attributesOut[1] = attributesIn[INDEX1];
                    attributesOut[2] = attributesIn[INDEX2];
                    attributesOut[3] = attributesIn[INDEX3];
                    attributesOut[4] = attributesIn[INDEX4];
                    helper.addOrderCSV(attributesOut);
                } else {
                    System.out.println("Order from CSV file are not added: invalid data format" +  data);
                }
                data = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println();
        }

    }

}


