import java.util.Scanner;

public class HospitalManagementSystem {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        JDBC connection = new JDBC();

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
                    Patient.addPatient(connection.jdbcConnection(), scanner);
                    break;
                case 2:
                    Patient.checkPatient(connection.jdbcConnection(), scanner);
                    break;
                case 3:
                    Patient.viewPatient(connection.jdbcConnection(), scanner);
                    break;
                case 4:
                    Doctors.viewDoctors(JDBC.jdbcConnection(), scanner);
                    break;
                case 5:
                    Doctors.checkDoctors(JDBC.jdbcConnection(), scanner);
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