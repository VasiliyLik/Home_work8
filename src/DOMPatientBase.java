import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

//класс для формироавния и чтения базы пациентов из xml файла через DOM
public class DOMPatientBase {
    public void readDOMPatientsFromNetXMLFile() throws IOException {
        //устанавливаем соединение с файлом на ГитХабе
        String urlAddress = "https://raw.githubusercontent.com/VasiliyLik/Home_work8/main/FilePatientsForGit.xml";
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            URL url = new URL(urlAddress);
            HttpURLConnection myUrlConnection = (HttpURLConnection) url.openConnection();
            int responsceCode = myUrlConnection.getResponseCode();
            if (responsceCode == HttpURLConnection.HTTP_OK) {
                inputStream = myUrlConnection.getInputStream();

                File file = new File("PatientsFileFromNet.xml");
                fileOutputStream = new FileOutputStream(file);

                int byteRead;
                byte[] buffer = new byte[256];
                while ((byteRead = inputStream.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, byteRead);
                }
            }
        } catch (IOException e) {
            System.out.println("Internet connection error " + e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
   //читаем с помощью DOM xml файл, полученный из ГитХаба
        File xmlFile = new File("PatientsFileFromNet.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder;
        try {
            documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFile);
            document.getDocumentElement().normalize();
            System.out.println("корневой элемент: " + document.getDocumentElement().getNodeName());
            // получаем узлы с именем Patient.XML полностью загружен в память в виде объекта Document
            NodeList nodeList = document.getElementsByTagName("Patient");
            // создадим из него список объектов Patient
            final List<Patient> patientList = new ArrayList<>();
            for (int i = 0; i < nodeList.getLength(); i++) {
                patientList.add(getPatient(nodeList.item(i)));
            }
            // печатаем в консоль информацию по каждому объекту Patient
                for (Patient patient: patientList) {
                    System.out.println(patient.toString());
                }
            }catch (Exception e) {
            e.printStackTrace();
        }
        }
    // создаем из узла документа объект Patient
    private static Patient getPatient(Node node) {
        Patient myPatient = new Patient();
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            myPatient.setName(getTagValue("name", element));
            myPatient.setSurName(getTagValue("surName", element));
            myPatient.setBirthDate(getTagValue("birthDate", element));
            myPatient.setHealthy(Boolean.parseBoolean(getTagValue("healthy", element)));
        }
        return myPatient;
    }
    // получаем значение элемента по указанному тегу
    private static String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }
}
