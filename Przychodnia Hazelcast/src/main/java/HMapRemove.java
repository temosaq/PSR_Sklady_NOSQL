import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import java.net.UnknownHostException;
import java.util.Map;

public class HMapRemove {
    public void removePatient(long id, Map<Long, Patient> patients ) throws UnknownHostException {
        patients.remove(id);
    }
}
