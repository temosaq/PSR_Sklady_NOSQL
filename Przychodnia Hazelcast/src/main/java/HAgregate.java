import java.net.UnknownHostException;

import com.hazelcast.aggregation.Aggregators;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

public class HAgregate {

    public static void main( String[] args ) throws UnknownHostException {
        ClientConfig clientConfig = HConfig.getClientConfig();
		final HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
		IMap<Long, Patient> patients = client.getMap("patients");
		System.out.println("The average year of birth: " + (patients.aggregate(Aggregators.integerAvg("birthyear"))));
	}
}
