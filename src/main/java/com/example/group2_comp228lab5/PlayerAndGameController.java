
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
        import java.util.Date;

        import static java.lang.Integer.parseInt;

public class PlayerAndGameController {

    @FXML
    private TextField txtPlayerGameId;
    @FXML
    private ComboBox cmbPlayerId;
    @FXML
    private ComboBox cmbGameId;
    @FXML
    private DatePicker datePlayngDate;
    @FXML
    private TextField txtScore;
    @FXML
    private Button btnAddPlayerGame;
    @FXML
    private Button btnDeletePlayerGame;
    @FXML
    private Button btnPlayer;
    @FXML
    private Button btnGame;
    @FXML
    private TableView tablePlayerGame;
    @FXML
    private TableColumn colPlayerGameId;
    @FXML
    private TableColumn colPlayerId;
    @FXML
    private TableColumn colGameId;
    @FXML
    private TableColumn colPlayingDate;
    @FXML
    private TableColumn colScore;

    private ObservableList<PlayerAndGame> data;

    public void initialize() throws  SQLException {
        populateData();
    }

    public void onSetPlayerId() throws  SQLException {
    }

    public void onSetGameId() throws  SQLException {
    }

    public void onSetPlayingDate() throws  SQLException {
    }

    public void onOpenPlayerInfo() throws  SQLException {
    }

    public void onOpenGameInfo() throws  SQLException {
    }



    public void onSubmitPlayerGame(ActionEvent actionEvent) throws SQLException {
        DBUtil.procPlayerAndGame(parseInt(txtPlayerGameId.getText()),(Integer)cmbPlayerId.getValue(), (Integer)cmbPlayerId.getValue(),
                                    datePlayngDate.getValue(), parseInt(txtScore.getText()));
        populateData();
    }

    public void onDeletePlayerGame(ActionEvent actionEvent) throws SQLException {
        DBUtil.delete("playerandgame", "player_game_id", 0);
        populateData();
    }

    public void populateData() throws SQLException {
        String sql = "select * from playerandgame";
        ResultSet rs = DBUtil.query(sql);

        // create list of objects that we want to show in the table
        ObservableList<PlayerAndGame> playerandgames = FXCollections.observableArrayList();

        // add objects one by one to the list
        while (rs.next()) {
            PlayerAndGame playerandgame = new PlayerAndGame(rs.getInt("player_game_id"),rs.getInt("player_id"),rs.getInt("game_id")
                                                        , rs.getDate("playing_date"),rs.getInt("score"));
            playerandgames.add(playerandgame);
        }

        // assign each attribute of the PlayerAndGame class (entity) to each column of the table
        colPlayerGameId.setCellValueFactory(new PropertyValueFactory("player_game_id"));
        colPlayerId.setCellValueFactory(new PropertyValueFactory("player_id"));
        colGameId.setCellValueFactory(new PropertyValueFactory("game_id"));
        colGameId.setCellValueFactory(new PropertyValueFactory("game_id"));
        colPlayingDate.setCellValueFactory(new PropertyValueFactory("playing_date"));
        colScore.setCellValueFactory(new PropertyValueFactory("score"));




        // show DB data on the table
        data = FXCollections.observableArrayList();
        tablePlayerGame.itemsProperty().setValue(data);
        tablePlayerGame.setItems(playerandgames);

        // sort by Date ???
        colPlayingDate.setSortType(TableColumn.SortType.ASCENDING);
        tablePlayerGame.getSortOrder().add(colPlayingDate);
    }



}

