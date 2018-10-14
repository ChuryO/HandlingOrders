import parse.CSVParse;
import parse.XMLParse;

import java.util.Objects;

class IncomingFile {
    /**
     * This method checks the extension and calls the required parser
     *
     * @param string
     */
    IncomingFile(String string) {

        String url = getFileExtension(string);
        if (Objects.equals(url, null)) {
            System.out.println("Nieprawidłowy plik");
        } else {
            switch (url) {
                case ".csv":
                    new CSVParse().csvLoad(string);
                    System.out.println();
                    System.out.println("Data from CSV");
                    System.out.println();
                    break;
                case ".xml":
                    new XMLParse().parseXml(string);
                    System.out.println();
                    System.out.println("Data from XML");
                    System.out.println();
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * the method checks the extension
     * @param s
     * @return
     */
    private static String getFileExtension(String s) {
        int index = s.indexOf('.');
        return index == -1 ? null : s.substring(index).toLowerCase();
    }
}
