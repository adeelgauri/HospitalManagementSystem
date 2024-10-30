import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Doctors {
    private Connection connection;

    public Doctors(Connection connection) {

        this.connection = connection;

    }

    public void viewDoctors() {

        String query = "select * from doctors ;";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

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


    //    Replace void boolean this method
    public boolean getDoctorById(int doctorId) {

        String query = "select * from doctors where doctor_id = ? ;";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, doctorId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
