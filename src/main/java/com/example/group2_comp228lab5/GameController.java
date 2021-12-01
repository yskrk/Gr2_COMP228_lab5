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

public class GameController {
    @FXML
    private TextField txtGameId;
    @FXML
    private TextField txtGameTitle;
    @FXML
    private Button addGame;
    @FXML
    private Button deleteGame;
    @FXML
    private TableView tableGame;
    @FXML
    private TableColumn colGameId;
    @FXML
    private TableColumn colGameTitle;

    private ObservableList<Game> data;

    public void initialize() throws  SQLException {
        populateData();
    }

    public void onSubmitGame(ActionEvent actionEvent) throws SQLException {
        DBUtil.procGame(parseInt(txtGameId.getText()), txtGameTitle.getText());
        populateData();
    }

    public void onDeleteGame(ActionEvent actionEvent) throws SQLException {
        DBUtil.delete("game", "game_id", 0);
        populateData();
    }

    public void populateData() throws SQLException {
        String sql = "select * from game";
        ResultSet rs = DBUtil.query(sql);

        // create list of objects that we want to show in the table
        ObservableList<Game> games = FXCollections.observableArrayList();

        // add objects one by one to the list
        while (rs.next()) {
            Game game = new Game(rs.getInt("game_id"), rs.getString("game_title"));
            games.add(game);
        }

        // assign each attribute of the Game class (entity) to each column of the table
        colGameId.setCellValueFactory(new PropertyValueFactory("game_id"));
        colGameTitle.setCellValueFactory(new PropertyValueFactory("game_title"));

        // show DB data on the table
        data = FXCollections.observableArrayList();
        tableGame.itemsProperty().setValue(data);
        tableGame.setItems(games);

        // sort by id
        colGameId.setSortType(TableColumn.SortType.ASCENDING);
        tableGame.getSortOrder().add(colGameId);
    }
}