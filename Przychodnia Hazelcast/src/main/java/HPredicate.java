import java.net.UnknownHostException;
import java.util.Collection;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.hazelcast.query.Predicate;
import com.hazelcast.query.Predicates;

public class HPredicate {

    public void findByYear(int minBirthyear, int maxBirthyear) throws UnknownHostException {
        ClientConfig clientConfig = HConfig.getClientConfig();
		final HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
		IMap<Long, Patient> patients = client.getMap("patients");


		Predicate<?,?> minBirthyearPredicate = Predicates.greaterThan("birthyear", minBirthyear);
		Predicate<?,?> maxBirthyearPredicate = Predicates.lessThan("birthyear", maxBirthyear);


		Collection<Patient> patient = patients.values(Predicates.and(minBirthyearPredicate, maxBirthyearPredicate));
		for (Patient s : patient ) {
			System.out.println(s);
		}
	}
}
