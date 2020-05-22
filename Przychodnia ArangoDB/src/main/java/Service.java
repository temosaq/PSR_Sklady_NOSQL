import com.arangodb.ArangoCollection;
import com.arangodb.ArangoCursor;
import com.arangodb.ArangoDB;
import com.arangodb.ArangoDatabase;
import com.arangodb.util.MapBuilder;

import java.util.ArrayList;
import java.util.List;

public class Service {

    private final ArangoDB arangoDB;

    public Service() {
        arangoDB=new ArangoDB.Builder().user("root").password("admin").build();
    }

    private ArangoDatabase getPatientsDB() {
        if (!arangoDB.db("patientsDB").exists()) {
            arangoDB.createDatabase("patientsDB");
        }
        return arangoDB.db("patientsDB");
    }

    private ArangoCollection getPatientsCollection() {
        if (!getPatientsDB().collection("patientsCollection").exists()) {
            getPatientsDB().createCollection("patientsCollection");
        }
        return getPatientsDB().collection("patientsCollection");
    }

    public Patient getPatient(String id) {
        return getPatientsCollection().getDocument(id, Patient.class);
    }

    public Patient getPatientByFullName(String fullName) {
        ArangoCursor<Patient> cursor=getPatientsDB().query("FOR i in patientsCollection FILTER i.fullname == @fullname RETURN i",
                new MapBuilder().put("fullname", fullName).get(),
                Patient.class);
        if (cursor.hasNext()) {
            return cursor.first();
        }
        return null;
    }

    public List<Patient> getAllPatients() {
        ArangoCursor<Patient> cursor=getPatientsDB().query("FOR i in patientsCollection RETURN i",
                Patient.class);

        ArrayList<Patient> patients=new ArrayList<Patient>();
        while (cursor.hasNext()) {
            patients.add(cursor.next());
        }
        return patients;
    }

    public void removePatient(String id) {
        getPatientsCollection().deleteDocument(id);
    }

    public void updatePatientFullName(String patientToUpdateId, String fullname) {
        Patient patientToUpdate=getPatientsCollection().getDocument(patientToUpdateId, Patient.class);
        patientToUpdate.setFullname(fullname);
        getPatientsCollection().updateDocument(patientToUpdateId, patientToUpdate);
    }

    public void updateBirthDate(int to) {
        getPatientsDB().query("FOR patient in patientsCollection UPDATE patient WITH {birthyear: patient.birthyear + @plus } IN patientsCollection",
                new MapBuilder().put("plus", to).get(),
                Object.class);
    }

    public List<Patient> findByYear(int minBirthYear, int maxBirthYear) {
        ArangoCursor<Patient> cursor=getPatientsDB().query("FOR patient in patientsCollection FILTER patient.birthyear > @min && patient.birthyear < @max RETURN patient",
                new MapBuilder().put("min", minBirthYear).put("max", maxBirthYear).get(),
                Patient.class);
        ArrayList<Patient> patients=new ArrayList<Patient>();
        while (cursor.hasNext()) {
            patients.add(cursor.next());
        }
        return patients;
    }

    public void insertPatients() {

        ArrayList<Patient> patients=new ArrayList<Patient>();
        patients.add(new Patient("Adam Nowak", 1997, 970220920, "Kielce, Warszawska 9/63"));
        patients.add(new Patient("Tomasz Kowalski", 1995, 950624400, "Kielce, Radomska 33"));
        patients.add(new Patient("Andrzej Stepien", 1989, 890111140, "Kielce, Warszawska 3/91"));
        patients.add(new Patient("Anna Nowak", 1994, 941209134, "Kielce, Wiosenna 5/24"));
        patients.add(new Patient("Joanna Nowacka", 1970, 701108239, "Kielce, Jesionowa 59"));
        patients.add(new Patient("Piotr Jurecki", 1983, 830430195, "Kielce, Seminaryjska 7/78"));

        patients.forEach(patient -> {
            getPatientsCollection().insertDocument(patient);
        });
    }

}
