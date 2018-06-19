

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class AlertBox{

    private static Boolean answer;
    private String title;
    private String message;
    private Button yes, no;
    public AlertBox(String title, String message){

        this.answer = false;
        this.message = message;
        this.title = title;
    }

    public boolean alert(){
        Stage alertWindow = new Stage();
        Label label;


        alertWindow.initModality(Modality.APPLICATION_MODAL);
        //not allowing user to access other windows of AddressBook without closing AlertBox.

        alertWindow.setTitle(title);
        label = new Label(message);
        this.yes = new Button("Yes");
        this.no = new Button("No");

        this.yes.setOnAction(event -> {
            answer = true;
            alertWindow.close();
        });

        this.no.setOnAction(event -> {
            answer =false;
            alertWindow.close();
        });

        HBox hBox = new HBox(20);
        hBox.getChildren().addAll(this.no,this.yes);
        hBox.setAlignment(Pos.CENTER);

        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(label,hBox);
        vBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vBox,300,150);
        alertWindow.setScene(scene);
        alertWindow.showAndWait();

        return answer;
    }

}

