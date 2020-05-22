import java.io.Serializable;
import java.net.UnknownHostException;
import java.util.Map.Entry;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.hazelcast.map.EntryProcessor;

public class HExecuteOnEntries {

    public static void main( String[] args ) throws UnknownHostException {
        ClientConfig clientConfig = HConfig.getClientConfig();
		final HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);

		IMap<Long, Patient> patients = client.getMap("patients");
		patients.executeOnEntries(new HEntryProcessor());

		for (Entry<Long, Patient> e : patients.entrySet()) {
			System.out.println(e.getKey() + " => " + e.getValue());
		}
	}
}

class HEntryProcessor implements EntryProcessor<Long, Patient, Integer>, Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public Integer process(Entry<Long, Patient> e) {
		Patient patient = e.getValue();
		int birthyear = patient.getBirthyear();
		patient.setBirthyear(birthyear+1);

		System.out.println("Processing = " + patient);
		e.setValue(patient);

		return birthyear;
	}
}
