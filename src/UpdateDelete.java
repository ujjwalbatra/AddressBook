import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateDelete {
    private String ID;
    private Connection conn;

    public UpdateDelete(String ID){
        this.ID = ID;
    }

    public void updateDB(){

    }

    public void DeleteFromDB(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(e);
            System.exit(-1);
        }

        try {
            this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/AddressBook", "root", "Ujjwal123"); // Allocate a database 'Connection' object
            Statement stmt = conn.createStatement();  // Allocate a 'Statement' object in the Connection

            String sqlDelete = "delete from AddressBook.details where id = " + Integer.parseInt(this.ID);
            System.out.println("The SQL query is: " + sqlDelete);

            int countInserted = stmt.executeUpdate(sqlDelete);

            System.out.println(countInserted + " records inserted.\n");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
