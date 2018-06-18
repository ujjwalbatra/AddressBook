

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application implements EventHandler<ActionEvent>{
    private Button add;
    private Button search;
    private Button update;
    private Button delete;
    private Button exportJson;
    private Button exit;
    private Stage window;


    @Override
    public void start(Stage primaryStage) throws Exception{

        VBox layout = new VBox(20);
        this.window = primaryStage;
        this.window.setTitle("MyAddressBook");
        Label welcome = new Label("Welcome to the Address Book");

        this.add = new Button("Add");
        this.search = new Button("Search");
        this.update = new Button("Update");
        this.delete = new Button("Delete");
        this.exportJson = new Button("Export JSON");
        this.exit = new Button("Exit");

        this.add.setOnAction(this);
        this.search.setOnAction(this);
        this.update.setOnAction(this);
        this.delete.setOnAction(this);
        this.exportJson.setOnAction(this);
        this.exit.setOnAction(this);


        layout.getChildren().addAll(welcome, this.add, this.search, this.update, this.delete, this.exportJson, this.exit);
        layout.setAlignment(Pos.CENTER);
        this.window.setScene(new Scene(layout, 280, 380));
        primaryStage.show();


        //Not allowing user to close program from the red cross button.
        //And giving user choice b/w, to close or not.
        this.window.setOnCloseRequest(e -> {
            e.consume();
           closeProgram();
        });


    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == this.exit){
            closeProgram();
        }

    }

    public void closeProgram(){
        AlertBox alertBox = new AlertBox();

        Boolean answer = alertBox.alert("close - AddressBook", "Are you sure you want to close AddressBook?");

        if (answer){
            this.window.close();
        }

    }
    public static void main(String[] args) {
        launch(args);
    }
}
