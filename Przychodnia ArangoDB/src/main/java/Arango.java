import java.util.List;
import java.util.Scanner;

public class Arango {
    public static void main(String[] args) {
        Service service = new Service();

        do {
            Scanner scanner=new Scanner(System.in);
            System.out.println("<--------------PRZYCHODNIA-------------->");
            System.out.println("-- 1. Dodaj pacjentów");
            System.out.println("-- 2. Wyświetl wszystkich pacjentów");
            System.out.println("-- 3. Wyświetl pacjenta o podanym ID");
            System.out.println("-- 4. Wyszukaj pacjentów urodzonych w podanych latach");
            System.out.println("-- 5. Usuń wybranego pacjenta");
            System.out.println("-- 6. Aktualizuj");
            System.out.println("-- 7. Powieksz rok urodzenia pacjentow");


            System.out.println("Podaj numer operacji do wykonania: ");
            String option=scanner.nextLine();
            switch (option) {
                case "1":
                    service.insertPatients();
                    break;

                case "2":
                    List<Patient> allPatients=service.getAllPatients();
                    allPatients.forEach(patient -> {
                        System.out.println(patient);
                    });
                    break;

                case "3":
                    System.out.println("Podaj ID pacjenta do wyświetlenia: ");
                    String id=scanner.nextLine();
                    System.out.println(service.getPatient(id));
                    break;

                case "4":
                    System.out.println("Rok urodzenia większy niż: ");
                    int minBirthyear=scanner.nextInt();
                    System.out.println("Rok urodzenia mniejszy niż4: ");
                    int maxBirthyear=scanner.nextInt();
                    List<Patient> patients=service.findByYear(minBirthyear, maxBirthyear);
                    patients.forEach(patient -> {
                        System.out.println(patient);
                    });
                    break;

                case "5":
                    System.out.println("Którego pacjenta usunąć? Podaj ID: ");
                    id=scanner.nextLine();
                    service.removePatient(id);
                    break;

                case "6":
                    System.out.println("Podaj id pacjenta do aktualizacji: ");
                    String patietnId = scanner.nextLine();
                    System.out.println("Podaj nowe nazwisko: ");
                    String newFullName = scanner.nextLine();
                    service.updatePatientFullName(patietnId, newFullName);
                    break;

                case "7":
                    System.out.println("Podaj liczbę: ");
                    int to = scanner.nextInt();
                    scanner.nextLine();
                    service.updateBirthDate(to);
                    break;

                default:
                    System.out.println("Błędna intrukcja");
            }
        } while (true);
    }
}
