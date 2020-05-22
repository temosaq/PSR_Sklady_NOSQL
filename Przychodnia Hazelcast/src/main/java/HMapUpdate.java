import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

public class HMapUpdate {
    public void updatePatientFullName(long id, String fullName, HazelcastInstance instance) {
        IMap<Object, Object> patients=instance.getMap("patients");
        Patient patientToUpdate = (Patient) patients.get(id);
        patientToUpdate.setFullname(fullName);
        patients.set(id, patientToUpdate);
    }
}
