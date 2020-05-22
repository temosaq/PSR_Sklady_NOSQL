import com.hazelcast.client.config.ClientConfig;

import java.net.UnknownHostException;
import java.util.Map.Entry;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

public class HMapGet {
    public static void main( String[] args ) throws UnknownHostException {
        ClientConfig clientConfig = HConfig.getClientConfig();
        HazelcastInstance client = HazelcastClient.newHazelcastClient( clientConfig );
        IMap<Long, Patient> patients = client.getMap( "patients" );
        System.out.println("List of all patients: ");
        for(Entry<Long, Patient> e : patients.entrySet()){
        	System.out.println(e.getKey() + " => " + e.getValue());
        }
    }
    public void getById(long id, IMap<Long, Patient> patients) throws UnknownHostException {
        System.out.println(patients.get(id));
    }
}

