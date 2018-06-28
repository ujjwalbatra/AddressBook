import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Search implements EventHandler<ActionEvent> {

    private Stage window;
    private Button search;
    private Button cancel;
    private TextField details; // text field to take input from user, which will be used for search querry
    private Label heading;

    public Search(){

        this.window = new Stage();

        this.search = new Button("Ok");
        this.cancel = new Button("Cancel");
        this.details = new TextField();
        this.heading = new Label("Search using ID, first name or phone number");


    }

    public void search(){

        this.window.setTitle("Search - AddressBook");
        this.details.setPromptText("Enter phone number or Name");
        this.heading.setAlignment(Pos.CENTER);
        this.details.setAlignment(Pos.CENTER_LEFT);

        HBox hBox = new HBox(10);
        hBox.getChildren().addAll(this.cancel, this.search);
        hBox.setAlignment(Pos.CENTER);

        this.cancel.setOnAction(this);
        this.search.setOnAction(this);

        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(this.heading, this.details, hBox);

        this.window.setScene(new Scene(vBox, 300, 150));
        this.window.show();

    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == this.cancel) {
            this.details.clear();
            this.window.close();
        } else if (event.getSource() == this.search) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                System.err.println(e);
                System.exit(-1);
            }
            try {
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/AddressBook", "root", "Ujjwal123"); // Allocate a database 'Connection' object
                Statement stmt = conn.createStatement();  // Allocate a 'Statement' object in the Connection
                String search = null;


                try {
                    double detail = Integer.parseInt(this.details.getText());
                    search = "SELECT *  FROM AddressBook.details WHERE AddressBook.details.id = "  + detail + " OR AddressBook.details.phone  = "+ detail +";";
                } catch (NumberFormatException e) {
                    search = "SELECT *  FROM AddressBook.details WHERE LOWER (AddressBook.details.firstname) = "+"\'" + details.getText().toLowerCase() + "\';";
                }

                System.out.println("The SQL query is: " + search);

                ResultSet rset = stmt.executeQuery(search);
                while (rset.next()) {
                    System.out.println(rset.getRow());
                    String output = "First Name = " + rset.getString("firstname") + "\nLast Name = " + rset.getString("lastname") + "\nPhone Number = " + rset.getString("phone") + "\nAddress Line 1 = " + rset.getString("addressline1") + "\nAddress Line 2 = " + rset.getString("addressline2") + "\nCity = " + rset.getString("city") + "\nState = " + rset.getString("state") + "\nZip = " + rset.getString("zip") + "\nCountry = " + rset.getString("country");
                    System.out.println(output);
                }
                window.close();



            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
