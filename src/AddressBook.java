import java.sql.*;

public class AddressBook {
    private String userInput;
    private Connection conn;


    public AddressBook(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(e);
            System.exit(-1);
        }
    }
    public AddressBook(String userInput){

        this();
        this.userInput = userInput;

    }

    public void updateDB(){

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/AddressBook", "root", "Ujjwal123"); // Allocate a database 'Connection' object
            Statement stmt = conn.createStatement();  // Allocate a 'Statement' object in the Connection
            String search = null;
            search = "SELECT *  FROM AddressBook.details WHERE AddressBook.details.id = "  + this.userInput + ";";

            System.out.println("The SQL query is: " + search);
            ResultSet rset = stmt.executeQuery(search);
            rset.next();
            UpdateAndInsertUI updateAndInsertUI = new UpdateAndInsertUI(rset,"Update");
            updateAndInsertUI.updateInsertUI();
            this.DeleteFromDB();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void DeleteFromDB(){


        try {
            this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/AddressBook", "root", "Ujjwal123"); // Allocate a database 'Connection' object
            Statement stmt = conn.createStatement();  // Allocate a 'Statement' object in the Connection

            String sqlDelete = "delete from AddressBook.details where id = " + Integer.parseInt(this.userInput);
            System.out.println("The SQL query is: " + sqlDelete);

            int countInserted = stmt.executeUpdate(sqlDelete);

            System.out.println(countInserted + " records inserted.\n");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void insert(Person person) {
        try {
            this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/AddressBook", "root", "Ujjwal123"); // Allocate a database 'Connection' object
            Statement stmt = conn.createStatement();  // Allocate a 'Statement' object in the Connection

            String sqlInsert = "insert into AddressBook.details (firstname, lastname, phone, addressline1, addressline2, city, state, zip, country)" // need a space
                    + "values (\'" + person.getFirstName() + "\',\'" + person.getLastName() + "\',\'" + person.getPhone() + "\',\'" + person.getAddressLine1() + "\',\'" + person.getAddressLine2() + "\',\'" + person.getCity() + "\',\'" + person.getState() + "\',\'" + person.getZip() + "\',\'" + person.getCountry() + "\');";
            System.out.println("The SQL query is: " + sqlInsert);

            int countInserted = stmt.executeUpdate(sqlInsert);
            System.out.println(countInserted + " records inserted.\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void search(){
        try {
            this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/AddressBook", "root", "Ujjwal123"); // Allocate a database 'Connection' object
            Statement stmt = conn.createStatement();  // Allocate a 'Statement' object in the Connection
            String search = null;


            try {
                double detail = Integer.parseInt(this.userInput);
                search = "SELECT *  FROM AddressBook.details WHERE AddressBook.details.id = "  + this.userInput + " OR AddressBook.details.phone  = "+ this.userInput +";";
            } catch (NumberFormatException e) {
                search = "SELECT *  FROM AddressBook.details WHERE LOWER (AddressBook.details.firstname) = "+"\'" + this.userInput.toLowerCase() + "\';";
            }

            System.out.println("The SQL query is: " + search);

            ResultSet rset = stmt.executeQuery(search);
            Table table = new Table();
            table.generateTable(rset);


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
