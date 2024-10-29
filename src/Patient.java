import java.sql.*;
import java.util.Scanner;

public class Patient {
    public static void addPatient(Connection connection, Scanner scanner) {

        System.out.println("Enter patient name ");
        String patientName = scanner.next();
        scanner.nextLine();
        System.out.println("Enter patient age");
        int patientAge = scanner.nextInt();
        System.out.println("Enter patient gender");
        String patientGneder = scanner.next();

        String sql = "insert into patients (patient_name,patient_age,patient_gender) values (?,?,?) ; ";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, patientName);
            preparedStatement.setInt(2, patientAge);
            preparedStatement.setString(3, patientGneder);

            int rowEffacted = preparedStatement.executeUpdate();

            if (rowEffacted > 0) {
                System.out.println("Patient add successfully");
            } else {
                System.out.println("Add patient failed");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void checkPatient(Connection connection, Scanner scanner) {

        System.out.println("Enter patient id");
        int patientId = scanner.nextInt();

        String sql = "select * from patients where patient_id = " + patientId + ";";

        try {

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            System.out.println("+------------+-----------------+---------------+------------------+");
            System.out.println("| Patient ID | Patient Name    | Patient Age   | Patient Gender   |");
            System.out.println("+------------+-----------------+---------------+------------------+");

            while (resultSet.next()) {

                String patientName = resultSet.getString("patient_name");
                int patientAge = resultSet.getInt("patient_age");
                String patientGender = resultSet.getString("patient_gender");


                System.out.printf("| %-10d | %-15s | %-13d | %-16s | \n", patientId, patientName, patientAge,
                        patientGender);


            }

            System.out.println("+------------+-----------------+---------------+------------------+");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void viewPatient(Connection connection, Scanner scanner) {

        String sql = "select * from patients ;";

        try {

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            System.out.println("+------------+-----------------+---------------+------------------+");
            System.out.println("| Patient ID | Patient Name    | Patient Age   | Patient Gender   |");
            System.out.println("+------------+-----------------+---------------+------------------+");

            while (resultSet.next()) {

                int patientId = resultSet.getInt("patient_id");
                String patientName = resultSet.getString("patient_name");
                int patientAge = resultSet.getInt("patient_age");
                String patientGender = resultSet.getString("patient_gender");


                System.out.printf("| %-10d | %-15s | %-13d | %-16s | \n", patientId, patientName, patientAge,
                        patientGender);


            }

            System.out.println("+------------+-----------------+---------------+------------------+");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
