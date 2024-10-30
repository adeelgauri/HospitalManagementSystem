import java.util.Scanner;

public class HospitalManagementSystem {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        JDBC connection = new JDBC();
        Doctors doctors = new Doctors(connection.jdbcConnection());
        Patient patient = new Patient(connection.jdbcConnection(),scanner);

        while (true) {

            System.out.println("1 Add Patient");
            System.out.println("2 Check Patient");
            System.out.println("3 View All Patient");
            System.out.println("4 View All Doctors");
            System.out.println("5 Check Doctors");
            System.out.println("0 Exit");
            System.out.print("Choose an option : ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    patient.addPatient();
                    break;
                case 2:
                    patient.getPatientById();
                    break;
                case 3:
                    patient.viewPatient();
                    break;
                case 4:
                    doctors.viewDoctors();
                    break;
                case 5:
                    doctors.getDoctorById();
                    break;
                case 0:
                    exit();
                    return;
                default:
                    System.out.println("Invalid choice Try again!");
            }
        }

    }

    public static void exit() {

        System.out.print("Exiting System");

        int i = 0;
        while (i <= 5) {
            System.out.print(".");
            try {
                Thread.sleep(350);
                i++;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println();

        System.out.println("Thank you for using hospital management system");
    }
}