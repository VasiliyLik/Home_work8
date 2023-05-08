import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class DOMWritFileXml2 {
    public static void main(String[] args) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            // создаем пустой объект Document, в котором будем создавать наш xml-файл
            Document document = builder.newDocument();
            // создаем корневой элемент
            Element rootElement = document.createElement("Patients");
            // добавляем корневой элемент в объект Document
            document.appendChild(rootElement);
            // добавляем дочерние элементы к корневому
            rootElement.appendChild(getPatient(document, "1", "Mikhael", "Schummaher", "19.11.1969", true));
            rootElement.appendChild(getPatient(document, "2", "Bruce", "Lee", "05.04.1947", false));
            rootElement.appendChild(getPatient(document, "3", "Jim", "Kerry", "23.09.1991", true));

            //создаем объект TransformerFactory для печати в консоль
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            // для красивого вывода в консоль
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(document);

            //печатаем в консоль и в файл
            StreamResult console = new StreamResult(System.out);
            StreamResult file = new StreamResult(new File("C:\\Users\\User\\Desktop\\FilePatientsForGit.xml"));
            transformer.transform(source, console);
            transformer.transform(source, file);
            System.out.println("File created");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // метод для создания нового узла XML-файла
    private static Node getPatient(Document document, String id, String name, String surName, String birthDate, boolean healthy) {
        Element patient = document.createElement("Patient");
        // устанавливаем атрибут id
        patient.setAttribute("id", id);
        // создаем элемент name
        patient.appendChild(getPatientElements(document, patient, "name", name));
        // создаем элемент surName
        patient.appendChild(getPatientElements(document, patient, "surName", surName));
        // создаем элемент birthDate
        patient.appendChild(getPatientElements(document, patient, "birthDate", birthDate));
        // создаем элемент healthy
        patient.appendChild(getPatientElements(document, patient, "healthy", String.valueOf(healthy)));

        return patient;
    }

    // утилитный метод для создания нового узла XML-файла
    private static Node getPatientElements(Document doc, Element element, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }
}
