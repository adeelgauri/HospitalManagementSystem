import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Doctors {
    public static void viewDoctors(Connection connection, Scanner scanner){

        String sql = "select * from doctors ;";

        try {

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            System.out.println("+------------+-----------------+---------------+");
            System.out.println("| Doctor ID  | Doctor Name     | Department    |");
            System.out.println("+------------+-----------------+---------------+");

            while (resultSet.next()) {

                int doctorId = resultSet.getInt("doctor_id");
                String doctorName = resultSet.getString("doctor_name");
                String department = resultSet.getString("department");


                System.out.printf("| %-10d | %-15s | %-13s | \n", doctorId, doctorName, department);


            }

            System.out.println("+------------+-----------------+---------------+");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void checkDoctors(Connection connection , Scanner scanner){

        System.out.println("Enter doctor Id");
        int doctorId = scanner.nextInt();
        String sql = "select * from doctors where doctor_id = " + doctorId + ";";

        try {

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            System.out.println("+------------+-----------------+---------------+");
            System.out.println("| Doctor ID  | Doctor Name     | Department    |");
            System.out.println("+------------+-----------------+---------------+");

            while (resultSet.next()) {

                String doctorName = resultSet.getString("doctor_name");
                String department = resultSet.getString("department");


                System.out.printf("| %-10d | %-15s | %-13s | \n", doctorId, doctorName, department);


            }

            System.out.println("+------------+-----------------+---------------+");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
