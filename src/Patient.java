import java.sql.*;
import java.util.Scanner;

public class Patient {
    private Connection connection;
    private Scanner scanner;

    public Patient(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
    }

    public void addPatient() {

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

    public void viewPatient() {

        String query = "select * from patients ;";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

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

    public boolean getPatientById(int patientId) {

        String query = "select * from patients where patient_id = ? ;";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,patientId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                return true;
            }else{
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
