package parse;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CSVParse {
    int INDEX0;
    int INDEX1;
    int INDEX2;
    int INDEX3;
    int INDEX4;

    public void csvLoad(String fileUrl) {
        Path pathToFile = Paths.get(fileUrl);
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
            //Find and assign column names
            String[] columnLine = br.readLine().split(",");
            for (int i = 0; i < columnLine.length; i++) {
                String tmp = columnLine[i].toLowerCase();
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
            //Sets the correct index
            String data = br.readLine();
            String[] attributesIn;
            String[] attributesOut = new String[columnLine.length];
            while (data != null) {
                attributesIn = data.split(",");
                attributesOut[0] = attributesIn[INDEX0];
                attributesOut[1] = attributesIn[INDEX1];
                attributesOut[2] = attributesIn[INDEX2];
                attributesOut[3] = attributesIn[INDEX3];
                attributesOut[4] = attributesIn[INDEX4];
                Helper.addOrderCSV(attributesOut);
                data = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


