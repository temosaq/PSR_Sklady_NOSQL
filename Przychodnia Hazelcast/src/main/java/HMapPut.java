import java.net.UnknownHostException;
import java.util.Map;
import java.util.Random;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

public class HMapPut {

	final private static Random r = new Random(System.currentTimeMillis());

	public static void main(String[] args) throws UnknownHostException {
		Config config = HConfig.getConfig();
		HazelcastInstance instance = Hazelcast.newHazelcastInstance(config);
		Map<Long, Patient> patientsInstance = instance.getMap("patients");
		Long key1 = (long) Math.abs(r.nextInt());
		Patient patient1 = new Patient("Adam Nowak", 1997, 970220920, "Kielce, Warszawska 9/63");
		System.out.println("PUT " + key1 + " => " + patient1);
        patientsInstance.put(key1, patient1);

		Long key2 = (long) Math.abs(r.nextInt());
		Patient patient2 = new Patient("Tomasz Kowalski", 1995, 950624400, "Kielce, Radomska 33");
        patientsInstance.put(key2, patient2);
		System.out.println("PUT " + key2 + " => " + patient2);

		Long key3 = (long) Math.abs(r.nextInt());
        Patient patient3 = new Patient("Andrzej Stepien", 1989, 890111140, "Kielce, Warszawska 3/91");
        patientsInstance.put(key3, patient3);
        System.out.println("PUT " + key3 + " => " + patient3);

        Long key4 = (long) Math.abs(r.nextInt());
        Patient patient4 = new Patient("Anna Nowak", 1994, 941209134, "Kielce, Wiosenna 5/24");
        patientsInstance.put(key4, patient4);
        System.out.println("PUT " + key4 + " => " + patient4);

        Long key5 = (long) Math.abs(r.nextInt());
        Patient patient5 = new Patient("Joanna Nowacka", 1970, 701108239, "Kielce, Jesionowa 59");
        patientsInstance.put(key5, patient5);
        System.out.println("PUT " + key5 + " => " + patient5);

        Long key6 = (long) Math.abs(r.nextInt());
        Patient patient6 = new Patient("Piotr Jurecki", 1983, 830430195, "Kielce, Seminaryjska 7/78");
        patientsInstance.put(key6, patient6);
        System.out.println("PUT " + key6 + " => " + patient6);
	}
}
