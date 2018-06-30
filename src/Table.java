import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Table  {
    private ObservableList<ObservableList> data = FXCollections.observableArrayList();;
    private TableView table = new TableView();
    Stage stage = new Stage();

    public void generateTable(ResultSet rset) throws SQLException{
        stage.setTitle("Search/Update -AddressBook");
        stage.setWidth(900);
        stage.setHeight(500);

        final Label label = new Label("Address Book");
        label.setFont(new Font("Arial", 20));

//                Table column added dynamically.
        for (int i = 0; i < rset.getMetaData().getColumnCount();  i++){
            final int j = i;
            TableColumn column = new TableColumn(rset.getMetaData().getColumnName(i+1));
            column.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                    return  new SimpleStringProperty(param.getValue().get(j).toString());
                }
            });
            table.getColumns().addAll(column);
            System.out.println("Column ["+i+"] ");

        }

//                    adding data to observable list
        while (rset.next()) {
            ObservableList<String> row = FXCollections.observableArrayList();
            for(int i =1 ; i <= rset.getMetaData().getColumnCount(); i++){
                //Iterate Column
                row.add(rset.getString(i));
            }
            System.out.println("Row  added "+row );
            data.add(row);

            table.setItems(data);
        }

        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(label, this.table);

        stage.setScene(new Scene(vbox));
        stage.show();
    }
}