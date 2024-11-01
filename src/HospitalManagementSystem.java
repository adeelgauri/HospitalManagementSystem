import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class HospitalManagementSystem {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        JDBC connection = new JDBC();
        Doctors doctors = new Doctors(connection.jdbcConnection());
        Patient patient = new Patient(connection.jdbcConnection(),scanner);

        while (true) {

            System.out.println("1 Add Patient");
            System.out.println("2 View All Patient");
            System.out.println("3 View All Doctors");
            System.out.println("4 Book Appointment");
            System.out.println("0 Exit");
            System.out.print("Choose an option : ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    patient.addPatient();
                    break;
                case 2:
                    patient.viewPatient();
                    break;
                case 3:
                    doctors.viewDoctors();
                    break;
                case 4:
                    bookAppointment(patient,doctors,connection.jdbcConnection(),scanner);
                    break;
                case 0:
                    exit();
                    return;
                default:
                    System.out.println("Invalid choice Try again!");
            }
        }

    }


    public static void bookAppointment(Patient patient, Doctors doctors, Connection connection, Scanner scanner){
        System.out.println("Enter Patient Id");
        int patientId = scanner.nextInt();
        System.out.println("Enter Doctor Id");
        int doctorId = scanner.nextInt();
        System.out.println("Enter appointment date YYYY-MM-DD");
        String appointmentDate = scanner.next();


        if(patient.getPatientById(patientId) && doctors.getDoctorById(doctorId)){
            if(checkDoctorAvailability(doctorId,appointmentDate,connection)){
                String query = "INSERT INTO appointments (patient_id,doctor_id,ap_date) VALUES (?,?,?) ";

                try {

                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setInt(1,patientId);
                    preparedStatement.setInt(2,doctorId);
                    preparedStatement.setString(3,appointmentDate);

                    int effectedRows = preparedStatement.executeUpdate();

                    if(effectedRows > 0){
                        System.out.println("Appointment booked");
                    }else{
                        System.out.println("Booking failed");
                    }

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }else{
                System.out.println("Doctor doesn't available");
            }
        }
    }

    public static boolean checkDoctorAvailability(int doctorId, String appointmentDate, Connection connection){
        String query = "select count(*) from appointments where doctor_id = ? AND ap_date = ? ";

        try{

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,doctorId);
            preparedStatement.setString(2,appointmentDate);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
               int count = resultSet.getInt(1);
               if(count == 0){
                   return true;
               }else{
                   return false;
               }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return false;

    }

    public static void exit() {

        System.out.print("Exiting System");

        int i = 0;
        while (i <= 5) {
            System.out.print(".");
            try {
                Thread.sleep(250);
                i++;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println();

        System.out.println("Thank you for using hospital management system");
    }
}