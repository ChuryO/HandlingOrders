package parse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class XMLParse {

    private String loadXml(String filePath) {
        StringBuilder xml = null;
        try {
            xml = new StringBuilder();
            Scanner scanner = new Scanner(new File(filePath)); // i.e. C:\\testXML.xml

            while (scanner.hasNextLine()) {
                xml.append(scanner.nextLine());
            }
            return xml.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return xml.toString();
    }

    public void parseXml(String xml) {
        Document doc = Jsoup.parse(loadXml(xml));
        String clientId = null;
        String requestId = null;
        String name = null;
        String quantity = null;
        String price = null;
        for (Element request : doc.select("request")) {
            for (int x = 0; x < request.children().size(); x++) {
                if (request.children().get(x).tagName().toLowerCase().equals("client_id") || request.children().get(x).tagName().equals("clientid")) {
                    clientId = request.children().get(x).text();
                } else if (request.children().get(x).tagName().equals("request_id") || request.children().get(x).tagName().equals("requestid")) {
                    requestId = request.children().get(x).text();
                } else if (request.children().get(x).tagName().equals("name")) {
                    name = request.children().get(x).text();
                } else if (request.children().get(x).tagName().equals("quantity")) {
                    quantity = request.children().get(x).text();
                } else if (request.children().get(x).tagName().equals("price")) {
                    price = request.children().get(x).text();
                } else {
                    System.out.println("____________________");
                    System.out.println("invalid row or incorrect column names");
                    System.out.println("____________________");
                }
            }
            Helper helper = new Helper();
            helper.addOrderXML(clientId, requestId, name, quantity, price);
        }

    }
}