import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

//класс для формироавния и чтения базы пациентов из xml файла через SAX
public class SAXPatientBase {

    public void readSAXPatientsFromLocalXMLFile() {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            // определяем класс, расширяющий класс DefaultHandler
            SAXParserHandler handler = new SAXParserHandler();
            ArrayList<Patient> patientList = handler.getPatientList();
            saxParser.parse(new File("C:\\Users\\User\\Desktop\\FilePatientsForGit.xml"), handler);
            System.out.println(patientList);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
