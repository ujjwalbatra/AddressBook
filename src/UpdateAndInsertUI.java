
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

import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateAndInsertUI {
    private Stage window;
    private Button ok, cancel;
    private TextField firstName;
    private TextField lastName;
    private TextField phone;
    private TextField addressLine1;
    private TextField addressLine2;
    private TextField city;
    private TextField state;
    private TextField zip;
    private TextField country;
    private Label label;


    public UpdateAndInsertUI(ResultSet resultSet, String button) throws SQLException{


        this.ok = new Button(button);
        this.cancel = new Button("Cancel");
        this.window = new Stage();
        this.window.setTitle("Update - AddressBook");
        this.firstName = new TextField(resultSet.getString("firstname"));
        this.lastName = new TextField(resultSet.getString("lastname"));
        this.phone = new TextField(resultSet.getString("phone"));
        this.addressLine1 = new TextField(resultSet.getString("addressline1"));
        this.addressLine2 = new TextField(resultSet.getString("addressline2"));
        this.city = new TextField(resultSet.getString("city"));
        this.state = new TextField(resultSet.getString("state"));
        this.zip = new TextField(resultSet.getString("zip"));
        this.country = new TextField(resultSet.getString("country"));
        this.label = new Label("Update AddressBook");

    }

    public UpdateAndInsertUI(String button){

        this.ok = new Button(button);
        this.cancel = new Button("Cancel");
        this.window = new Stage();
        this.window.setTitle("Insert - AddressBook");
        this.firstName = new TextField();
        this.lastName = new TextField();
        this.phone = new TextField();
        this.addressLine1 = new TextField();
        this.addressLine2 = new TextField();
        this.city = new TextField();
        this.state = new TextField();
        this.zip = new TextField();
        this.country = new TextField();
        this.firstName.setPromptText("First Name");
        this.lastName.setPromptText("Last Name");
        this.phone.setPromptText("Phone Number");
        this.addressLine1.setPromptText("Address Line 1");
        this.addressLine2.setPromptText("Address Line 2");
        this.city.setPromptText("City");
        this.state.setPromptText("State");
        this.zip.setPromptText("Zip");
        this.country.setPromptText("Country");
        this.label = new Label("Insert into AddressBook");
    }

    public void updateInsertUI() {

        GridPane form = new GridPane();
        form.setPadding(new Insets(10, 10, 10, 10));
        form.setVgap(10);
        form.setHgap(10);
        GridPane.setConstraints(this.firstName, 0, 1);
        GridPane.setConstraints(this.lastName, 1, 1);
        GridPane.setConstraints(this.phone, 0, 2);
        GridPane.setConstraints(this.addressLine1, 0, 3);
        GridPane.setConstraints(this.addressLine2, 1, 3);
        GridPane.setConstraints(this.city, 0, 4);
        GridPane.setConstraints(this.state, 1, 4);
        GridPane.setConstraints(this.zip, 0, 5);
        GridPane.setConstraints(this.country, 1, 5);

        this.cancel.setOnAction(event -> {
            closeProgram();
            closeProcedure();
        });

        this.ok.setOnAction(event -> {

            Person person = new Person(this.firstName.getText(),this.lastName.getText(),this.phone.getText(),this.addressLine1.getText(), this.addressLine2.getText(),this.city.getText(),this.state.getText(), this.zip.getText(), this.country.getText());
            AddressBook addressBook = new AddressBook();
            addressBook.insert(person);
            closeProcedure();
            this.window.close();

        });

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
