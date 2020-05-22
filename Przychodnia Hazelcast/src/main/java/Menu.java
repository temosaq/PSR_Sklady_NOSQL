import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

import java.net.UnknownHostException;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) throws UnknownHostException {
        ClientConfig clientConfig=HConfig.getClientConfig();
        HazelcastInstance client=HazelcastClient.newHazelcastClient(clientConfig);
        IMap<Long, Patient> patients=client.getMap("patients");

        Config config=HConfig.getConfig();
        HazelcastInstance instance=Hazelcast.newHazelcastInstance(config);
        Map<Long, Patient> patientsInstance=instance.getMap("patients");

        HMapPut hMapPut=new HMapPut();
        HMapGet hMapGet=new HMapGet();
        HMapRemove hMapRemove=new HMapRemove();
        HExecuteOnEntries hExecuteOnEntries=new HExecuteOnEntries();
        HMapUpdate hMapUpdate=new HMapUpdate();
        HPredicate hPredicate=new HPredicate();
        HAgregate hAgregate=new HAgregate();


        do {
            Scanner scanner=new Scanner(System.in);
            System.out.println("<--------------PRZYCHODNIA-------------->");
            System.out.println("-- 1. Dodaj pacjentów");
            System.out.println("-- 2. Wyświetl wszystkich pacjentów");
            System.out.println("-- 3. Wyświetl pacjenta o podanym ID");
            System.out.println("-- 4. Wyszukaj pacjentów urodzonych w podanych latach");
            System.out.println("-- 5. Usuń wybranego pacjenta");
            System.out.println("-- 6. Usuń wszystkich pacjentów");
            System.out.println("-- 7. Aktualizuj");
            System.out.println("-- 8. Wyświetl średni rok urodzenia pacjentów");
            System.out.println("-- 9. Powiększ rok urodzenia pacjentów o 1");


            System.out.println("Podaj numer operacji do wykonania: ");
            String option=scanner.nextLine();
            switch (option) {
                case "1":
                    HMapPut.main(args);
                    break;

                case "2":
                    HMapGet.main(args);
                    break;

                case "3":
                    System.out.println("Podaj ID pacjenta do wyświetlenia: ");
                    long id=scanner.nextLong();
                    hMapGet.getById(id, patients);
                    break;

                case "4":
                    System.out.println("Rok urodzenia większy niż: ");
                    int minBirthyear=scanner.nextInt();
                    System.out.println("Rok urodzenia mniejszy niż4: ");
                    int maxBirthyear=scanner.nextInt();
                    hPredicate.findByYear(minBirthyear, maxBirthyear);
                    break;

                case "5":
                    HMapGet.main(args);
                    System.out.println("Którego pacjenta usunąć? Podaj ID: ");
                    id=scanner.nextLong();
                    hMapRemove.removePatient(id, patients);
                    break;

                case "6":
                    HMapEvict.main(args);
                    break;

                case "7":
                    System.out.println("Podaj id pacjenta do aktualizacji: ");
                    long patietnId = scanner.nextLong();
                    scanner.nextLine();
                    System.out.println("Podaj nowe imię i nazwisko: ");
                    String newFullName = scanner.nextLine();
                    hMapUpdate.updatePatientFullName(patietnId, newFullName, instance);
                    break;

                case "8":
                    HAgregate.main(args);
                    break;

                case "9":
                    HExecuteOnEntries.main(args);
                    break;

                default:
                    System.out.println("Błędna intrukcja");
            }
        } while (true);


    }
}
