
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Insert implements EventHandler<ActionEvent>{
    private Stage window;
    private Connection conn;
    private Button ok, cancel;
    private TextField firstName = new TextField();
    private TextField lastName = new TextField();
    private TextField phone = new TextField();
    private TextField addressLine1 = new TextField();
    private TextField addressLine2 = new TextField();
    private TextField city = new TextField();
    private TextField state = new TextField();
    private TextField zip = new TextField();
    private TextField country = new TextField();

    public Insert(){
        this.ok = new Button("Ok");
        this.cancel = new Button("Cancel");
        this.window = new Stage();
        this.window.setTitle("Insert - AddressBook");
    }

    public void insert(){

        GridPane form = new GridPane();
        form.setPadding(new Insets(10, 10, 10, 10));

        form.setVgap(10);
        form.setHgap(10);

        Label label = new Label("Insert into AddressBook");


        this.firstName.setPromptText("First name");
        this.firstName.getText();
        GridPane.setConstraints(this.firstName, 0, 1);


        this.lastName.setPromptText("Last Name");
        GridPane.setConstraints(this.lastName, 1, 1);


        this.phone.setPromptText("Phone number");
        GridPane.setConstraints(this.phone, 0, 2);


        this.addressLine1.setPromptText("Address Line 1");
        GridPane.setConstraints(this.addressLine1, 0, 3);


        this.addressLine2.setPromptText("Address Line 2");
        GridPane.setConstraints(this.addressLine2, 1, 3);


        this.city.setPromptText("City");
        GridPane.setConstraints(this.city, 0, 4);


        this.state.setPromptText("State");
        GridPane.setConstraints(this.state, 1, 4);


        this.zip.setPromptText("Zip");
        GridPane.setConstraints(this.zip, 0, 5);

        this.country.setPromptText("Country");
        GridPane.setConstraints(this.country, 1, 5);

        this.cancel.setOnAction(this);

        this.ok.setOnAction(this);

        GridPane.setConstraints(this.ok, 1, 6);
        GridPane.setConstraints(this.cancel, 0, 6);

        HBox hBox = new HBox(10);
        hBox.getChildren().addAll(this.cancel,this.ok);
        hBox.setAlignment(Pos.CENTER);

        form.getChildren().addAll( this.firstName, this.lastName, this.phone, this.addressLine1, this.addressLine2, this.city, this.state, this.zip, this.country);
        form.setAlignment(Pos.CENTER);

        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(label,form,hBox);
        vBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vBox, 400, 350);
        this.window.setScene(scene);

        this.window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });

        this.window.show();

    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == this.ok){
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                System.err.println(e);
                System.exit(-1);
            }

            try {
                this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/AddressBook", "root", "Ujjwal123"); // Allocate a database 'Connection' object
                Statement stmt = conn.createStatement();  // Allocate a 'Statement' object in the Connection

                String sqlInsert = "insert into AddressBook.details (firstname, lastname, phone, addressline1, addressline2, city, state, zip, country)" // need a space
                        + "values (\'" + this.firstName.getText() + "\',\'" + this.lastName.getText() + "\',\'" + this.phone.getText() + "\',\'" + this.addressLine1.getText() + "\',\'" + this.addressLine2.getText() + "\',\'" + this.city.getText() + "\',\'" + this.state.getText() + "\',\'" + this.zip.getText() + "\',\'" + this.country.getText() + "\')";
                System.out.println("The SQL query is: " + sqlInsert);

                int countInserted = stmt.executeUpdate(sqlInsert);

                System.out.println(countInserted + " records inserted.\n");

                closeProcedure();

                this.window.close();


            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        } else if (event.getSource() == this.cancel) {
            closeProgram();
            closeProcedure();
        }


    }


    public void closeProgram() {
        boolean answer;
        AlertBox alertBox = new AlertBox("Exit - AddressBook", "Are you sure you want to exit?");
        answer = alertBox.alert();
        if (answer)
            this.window.close();

    }

    public void closeProcedure() {
        this.firstName.clear();
        this.lastName.clear();
        this.phone.clear();
        this.addressLine2.clear();
        this.addressLine1.clear();
        this.city.clear();
        this.state.clear();
        this.zip.clear();
        this.country.clear();

    }
}
