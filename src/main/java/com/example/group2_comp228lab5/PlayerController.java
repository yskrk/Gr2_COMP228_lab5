package com.example.group2_comp228lab5;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

import static java.lang.Integer.parseInt;



public class PlayerController {

    @FXML
    private TextField txtPlayerId;
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtPostalCode;
    @FXML
    private TextField txtProvince;
    @FXML
    private TextField txtPhoneNum;
    @FXML
    private Button addPlayer;
    @FXML
    private Button updatePlayer;
    @FXML
    private Button deletePlayer;
    @FXML
    private TableView tablePlayer;
    @FXML
    private TableColumn colPlayerId;
    @FXML
    private TableColumn colFirstName;
    @FXML
    private TableColumn colLastName;
    @FXML
    private TableColumn colAddress;
    @FXML
    private TableColumn colPostalCode;
    @FXML
    private TableColumn colProvince;
    @FXML
    private TableColumn colPhoneNum;

    private ObservableList<Player> data;

    public void initialize() throws  SQLException {
        populateData();
    }

    public void onSubmitPlayer(ActionEvent actionEvent) throws SQLException {
        DBUtil.procPlayer(parseInt(txtPlayerId.getText()), txtFirstName.getText(), txtLastName.getText(),
                                    txtAddress.getText(),txtPostalCode.getText(),txtProvince.getText(),Long.parseLong(txtPhoneNum.getText()));
        populateData();
    }

    public void onDeletePlayer(ActionEvent actionEvent) throws SQLException {
        try {
            DBUtil.delete("player", "player_id", parseInt(txtPlayerId.getText()));

            populateData();
        } catch (NumberFormatException e) {
            // dialog for warning
            Alert dialog = new Alert(Alert.AlertType.INFORMATION);
            dialog.setHeaderText(null);
            dialog.setContentText("Please enter valid id on text field");
            dialog.showAndWait();
        }
    }

    public void populateData() throws SQLException {
        String sql = "select * from player";
        ResultSet rs = DBUtil.query(sql);

        // create list of objects that we want to show in the table
        ObservableList<Player> players = FXCollections.observableArrayList();

        // add objects one by one to the list
        while (rs.next()) {
            Player player = new Player(rs.getInt("player_id"), rs.getString("first_name"),
                                        rs.getString("last_name"), rs.getString("address"),
                                        rs.getString("postal_code"), rs.getString("province"),
                                        rs.getLong("phone_number"));
            players.add(player);
        }

        // assign each attribute of the Player class (entity) to each column of the table
        colPlayerId.setCellValueFactory(new PropertyValueFactory("player_id"));
        colFirstName.setCellValueFactory(new PropertyValueFactory("first_name"));
        colLastName.setCellValueFactory(new PropertyValueFactory("last_name"));
        colAddress.setCellValueFactory(new PropertyValueFactory("address"));
        colPostalCode.setCellValueFactory(new PropertyValueFactory("postal_code"));
        colProvince.setCellValueFactory(new PropertyValueFactory("province"));
        colPhoneNum.setCellValueFactory(new PropertyValueFactory("phone_number"));




        // show DB data on the table
        data = FXCollections.observableArrayList();
        tablePlayer.itemsProperty().setValue(data);
        tablePlayer.setItems(players);

        // sort by id
        colPlayerId.setSortType(TableColumn.SortType.ASCENDING);
        tablePlayer.getSortOrder().add(colPlayerId);
    }
}
