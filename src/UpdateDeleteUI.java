import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UpdateDeleteUI {
    private Stage window;
    private Button button;
    private Button cancel;
    private TextField details; // text field to take input from user, which will be used for search query
    private Label heading;

    public UpdateDeleteUI(String title, String heading, String button) {
        this.window = new Stage();
        this.button = new Button(button);
        this.heading = new Label(heading);
        this.window.setTitle(title);
        this.cancel = new Button("Cancel");
        this.details = new TextField();
        this.details.setPromptText("Enter ID");
    }


    public void generateUI(){
        this.heading.setAlignment(Pos.CENTER);
        this.details.setAlignment(Pos.CENTER_LEFT);
        HBox hBox = new HBox(10);
        hBox.getChildren().addAll(this.cancel, this.button);
        hBox.setAlignment(Pos.CENTER);

        this.cancel.setOnAction(event -> {
            this.details.clear();
            this.window.close();
        });

        this.button.setOnAction(event -> {
            UpdateDelete updateDelete = new UpdateDelete(details.getText());
            if (button.getText().toLowerCase().equals("update"))    updateDelete.updateDB();
            else updateDelete.DeleteFromDB();
            this.details.clear();
            this.window.close();
        });

        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(this.heading, this.details, hBox);
        vBox.setPadding(new Insets(10, 10, 10,10));
        this.window.setScene(new Scene(vBox, 300, 150));
        this.window.show();

    }
}
