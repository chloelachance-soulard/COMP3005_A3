import java.sql.*;

public class Main {
    //define connection object as static variable
    static Connection connection;

    public static void main(String[] args) {
        //variables to connect to database
        String url = "jdbc:postgresql://localhost:5432/A3";
        String user = "postgres";
        String password = "admin";
        try {
            Class.forName("org.postgresql.Driver");
            //initialize connection object
            connection = DriverManager.getConnection(url, user, password);
            //FUNCTION TESTING//
            //Get all students
            getAllStudents();
            //Add student
            addStudent("maria", "smith", "test@test.com", "2023-01-02");
            //Update student email
            updateStudentEmail(1, "updated@test.com");
            //Delete student
            deleteStudent(2);
            //get all students again to show database updates
            getAllStudents();
            //END OF FUNCTION TESTING//
            
            //close connection
            connection.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    //Retrieves and displays all records from the students table
    public static void getAllStudents() {
        try {
            //statement/query to retrieve all students from database
            Statement statement = connection.createStatement();
            statement.executeQuery("SELECT * FROM student ORDER BY student_id ASC");
            ResultSet resultSet = statement.getResultSet();
            //go through results and display all of the students' information
            while (resultSet.next()) {
                System.out.print(resultSet.getInt("student_id") + "\t");
                System.out.print(resultSet.getString("first_name") + "\t");
                System.out.print(resultSet.getString("last_name") + "\t");
                System.out.print(resultSet.getString("email") + "\t");
                System.out.println(resultSet.getString("enrollment_date"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //Inserts a new student record into the students table
    public static void addStudent(String first_name, String last_name, String email, String enrollment_date) {
        //INSERT Operation
        String insertSQL = "INSERT INTO student(first_name, last_name, email, enrollment_date) VALUES (?,?,?,?)";
        try (PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {
            pstmt.setString(1, first_name);
            pstmt.setString(2, last_name);
            pstmt.setString(3, email);
            pstmt.setDate(4, Date.valueOf(enrollment_date));
            pstmt.executeUpdate();
            System.out.println("Student added");
        }catch (Exception e) {
            System.out.println(e);
        }
    }

    //Updates the email address for a student with the specified student_id
    public static void updateStudentEmail(int student_id, String new_email){
        //Update operation
        String updateSQL = "UPDATE student SET email = ? WHERE student_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(updateSQL)) {
            //create statement to check student ids to make sure student we are trying to delete exists
            Statement checkID = connection.createStatement();
            checkID.executeQuery("SELECT student_id FROM student");
            ResultSet resultSet = checkID.getResultSet();
            //variable to keep track of if we found id or not - to be used to print out statement if id does not exist
            int idFound = 0;
            //while loop to go through data set to try and find student id
            while (resultSet.next()) {
                //if id is found, execute the update (update that student's email)
                if(resultSet.getInt("student_id") == student_id){
                    pstmt.setString(1, new_email);
                    pstmt.setInt(2, student_id);
                    pstmt.executeUpdate();
                    System.out.println("Student email updated");
                    idFound = 1;
                    break;
                }
            }
            //If student id was not found, print out message saying student email could not be updated
            if(idFound == 0){
                System.out.println("Student id not found in database, student email could not be updated");
            }

        } catch(Exception e) {
            System.out.println(e);
        }
    }

    //Deletes the record of the student with the specified student_id
    public static void deleteStudent(int student_id){
        //delete operation
        String deleteSQL = "DELETE FROM student WHERE student_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(deleteSQL)) {
            //create statement to check student ids to make sure student we are trying to delete exists
            Statement checkID = connection.createStatement();
            checkID.executeQuery("SELECT student_id FROM student");
            ResultSet resultSet = checkID.getResultSet();
            //variable to keep track of if we found id or not - to be used to print out statement if id does not exist
            int idFound = 0;
            //while loop to go through data set to try and find student id
            while (resultSet.next()) {
                //if id is found, execute the update and the delete student
                if(resultSet.getInt("student_id") == student_id){
                    pstmt.setInt(1, student_id);
                    pstmt.executeUpdate();
                    System.out.println("Student deleted successfully");
                    idFound = 1;
                    break;
                }
            }
            //If student id was not found, print out message saying student could not be deleted
            if(idFound == 0){
                System.out.println("Student id not found in database, student could not be deleted");
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }
}
