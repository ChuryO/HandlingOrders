import parse.CSVParse;
import parse.XMLParse;

import java.util.Objects;

class IncomingFile {

    IncomingFile(String string) {

        String url = getFileExtension(string);
        if (Objects.equals(url,null)) {
            System.out.println("Nieprawidłowy plik");
        } else {
            switch (url) {
                case ".csv":
                    System.out.println("");
                    new CSVParse().csvLoad(string);
                    System.out.println("Data from CSV");
                    System.out.println("");
                    break;
                case ".xml":
                    System.out.println("");
                    new XMLParse().parseXml(string);
                    System.out.println("Data from XML");
                    System.out.println("");
                    break;
                case "":
                    System.out.println("Nieprawidłowy plik");
                    break;
                default:
                    System.out.println("Nieprawidłowy plik");
                    break;
            }
        }
    }

    private static String getFileExtension(String s) {
        int index = s.indexOf('.');
        return index == -1 ? null : s.substring(index).toLowerCase();
    }
}
