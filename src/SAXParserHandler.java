import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import java.util.ArrayList;

//класс для обработки событий парсера
public class SAXParserHandler extends DefaultHandler {

    private final ArrayList<Patient> patientList = new ArrayList<>();

    public ArrayList<Patient> getPatientList() {
        return this.patientList;
    }

    private String name, surName, birthDate, lastElementName;
    private Boolean healthy;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        lastElementName = qName;
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String information = new String(ch, start, length);

        information = information.replace("\n", "").trim();

        if (!information.isEmpty()) {
            if (lastElementName.equals("name"))
                name = information;
            if (lastElementName.equals("surName"))
                surName = information;
            if (lastElementName.equals("birthDate"))
                birthDate = information;
            if (lastElementName.equals("healthy"))
                healthy = Boolean.parseBoolean(information);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if ((name != null) && !name.isEmpty() && (surName != null) && !surName.isEmpty() && (birthDate != null)
                && !birthDate.isEmpty() && (healthy != null)) {
            patientList.add(new Patient(name, surName, birthDate, healthy));
            name = null;
            surName = null;
            birthDate = null;
            healthy = null;
        }
    }
}
