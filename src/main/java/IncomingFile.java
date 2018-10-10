import parse.CSVParse;
import parse.XMLParse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class IncomingFile {

    IncomingFile(String string) {
        String url = getFileExtension(string);
        if (url != null && url.equals(".csv")) {
            CSVParse.csvLoad(string);
        } else if (url != null && url.equals(".xml")) {
            XMLParse.parseXml(string);
        } else {
            System.out.println("Wrong file format");
            System.out.println("Please enter the correct file address");
            try {
                url = new BufferedReader(new InputStreamReader(System.in)).readLine();
                new IncomingFile(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String getFileExtension(String s) {
        int index = s.indexOf('.');
        return index == -1 ? null : s.substring(index).toLowerCase();
    }
}
