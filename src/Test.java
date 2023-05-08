import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        DOMPatientBase patientBase = new DOMPatientBase();
        patientBase.readDOMPatientsFromNetXMLFile();
        SAXPatientBase saxPatientBase = new SAXPatientBase();
        saxPatientBase.readSAXPatientsFromLocalXMLFile();
    }
}
