
package com.example.group2_comp228lab5;

        import javafx.beans.Observable;
        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Scene;
        import javafx.scene.control.*;
        import javafx.scene.control.cell.PropertyValueFactory;
        import javafx.stage.Stage;

        import java.io.IOException;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.text.ParseException;
        import java.text.SimpleDateFormat;
        import java.util.Date;

        import static java.lang.Integer.parseInt;

public class PlayerAndGameController {

    @FXML
    private TextField txtPlayerGameId;
    @FXML
    private ComboBox cmbPlayer;
    @FXML
    private ComboBox cmbGame;
    @FXML
    private DatePicker datePlayngDate;
    @FXML
    private TextField txtScore;
    @FXML
    private Button btnAddPlayerGame;
    @FXML
    private Button btnDeletePlayerGame;
    @FXML
    private Button btnClearPlayerGame;
    @FXML
    private Button btnPlayer;
    @FXML
    private Button btnGame;
    @FXML
    private TableView tablePlayerGame;
    @FXML
    private TableColumn colPlayerGameId;
    @FXML
    private TableColumn colPlayerName;
    @FXML
    private TableColumn colGameTitle;
    @FXML
    private TableColumn colPlayingDate;
    @FXML
    private TableColumn colScore;

    private ObservableList<PlayerAndGame> data;

    public void initialize() throws  SQLException {
        onSetPlayer();
        onSetGame();
        populateData();
        txtPlayerGameId.focusedProperty().addListener((ov, oldV, newV) ->  {
            if (!newV) {
                try {
                    getPlayerGameInfo();
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("getting PlayerGame data failed");
                } catch (NumberFormatException e) {
                    System.out.println("entered invalid value");
                }
            }
        });
    }

    public void onSetPlayer() throws  SQLException {
        String sql = "select player_id, first_name || ' ' || last_name as name from player order by player_id";
        ResultSet rs = DBUtil.query(sql);
        ObservableList<String> players = FXCollections.observableArrayList();

        while (rs.next()) {
            players.add(rs.getString("name"));
        }

        cmbPlayer.setItems(players);
    }

    public void onSetGame() throws  SQLException {
        String sql = "select * from game order by game_id";
        ResultSet rs = DBUtil.query(sql);
        ObservableList<String> games = FXCollections.observableArrayList();

        while (rs.next()) {
            games.add(rs.getString("game_title"));
        }

        cmbGame.setItems(games);
    }

    // show game window method
    public void onClickGame(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GamePlayerApplication.class.getResource("game-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 359, 497);
        Stage stage = new Stage();
        stage.setTitle("Game");
        stage.setScene(scene);
        stage.setOnCloseRequest(event -> {
            try {
                onSetGame();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        stage.showAndWait();
    }

    // show player window method
    public void onClickPlayer(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GamePlayerApplication.class.getResource("player-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 762, 495);
        Stage stage = new Stage();
        stage.setTitle("Player");
        stage.setScene(scene);
        stage.setOnCloseRequest(event -> {
            try {
                onSetPlayer();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        stage.showAndWait();
    }

    public void onSubmitPlayerGame(ActionEvent actionEvent) throws SQLException, ParseException {
        // player
        String str = cmbPlayer.getValue().toString();
        String[] keyword = str.split("\\s+");

        String sqlPlayer = "select * from player where first_name = '" + keyword[0] + "' and last_name = '" + keyword[1] + "'";
        ResultSet rsPlayer = DBUtil.query(sqlPlayer);

        // game
        String sqlGame = "select * from game where game_title = '" + cmbGame.getValue() + "'";
        ResultSet rsGame = DBUtil.query(sqlGame);

        // date
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        date = sdf.parse(datePlayngDate.getValue().toString());

        rsPlayer.first();
        rsGame.first();

        DBUtil.procPlayerAndGame(parseInt(txtPlayerGameId.getText()), rsPlayer.getInt("player_id"), rsGame.getInt("game_id"), date, parseInt(txtScore.getText()));
        populateData();
    }

    public void onDeletePlayerGame(ActionEvent actionEvent) throws SQLException {
        try {
            DBUtil.delete("playerandgame", "player_game_id", parseInt(txtPlayerGameId.getText()));

            populateData();
        } catch (NumberFormatException e) {
            // dialog for warning
            Alert dialog = new Alert(Alert.AlertType.INFORMATION);
            dialog.setHeaderText(null);
            dialog.setContentText("Please enter valid id on text field");
            dialog.showAndWait();
        }
    }

    public void onClearPlayerGame(ActionEvent actionEvent) {
        txtPlayerGameId.clear();
        cmbPlayer.setValue(null);
        cmbGame.setValue(null);
        datePlayngDate.setValue(null);
        txtScore.clear();
    }

    private void getPlayerGameInfo() throws SQLException{
        Integer id = Integer.parseInt(txtPlayerGameId.getText());

        String sql = "select t1.player_game_id, t1.game_id, t2.game_title, t1.player_id, t3.first_name || ' ' || t3.last_name as player_name, t1.playing_date, t1.score " +
                "from playerandgame t1, game t2, player t3 " +
                "where t1.game_id = t2.game_id " +
                "and t1.player_id = t3.player_id " +
                "and t1.player_game_id = " + id;
        ResultSet rs = DBUtil.query(sql);
        rs.first();

        cmbPlayer.setValue(rs.getString("player_name"));
        cmbGame.setValue(rs.getString("game_title"));
        txtScore.setText(rs.getString("score"));
        datePlayngDate.setValue(rs.getDate("playing_date").toLocalDate());
    }

    public void populateData() throws SQLException {
        String sql = "select t1.player_game_id, t1.game_id, t2.game_title, t1.player_id, t3.first_name || ' ' || t3.last_name as player_name, t1.playing_date, t1.score \n" +
                "from playerandgame t1, game t2, player t3\n" +
                "where t1.game_id = t2.game_id\n" +
                "and t1.player_id = t3.player_id";
        ResultSet rs = DBUtil.query(sql);

        // create list of objects that we want to show in the table
        ObservableList<PlayerAndGame> playerAndGames = FXCollections.observableArrayList();

        // add objects one by one to the list
        while (rs.next()) {
            PlayerAndGame playerAndGame = new PlayerAndGame(
                    rs.getInt("player_game_id"),
                    rs.getInt("player_id"),
                    rs.getInt("game_id"),
                    rs.getDate("playing_date"),
                    rs.getInt("score"),
                    rs.getString("game_title"),
                    rs.getString("player_name")
            );
            playerAndGames.add(playerAndGame);
        }

        // assign each attribute of the PlayerAndGame class (entity) to each column of the table
        colPlayerGameId.setCellValueFactory(new PropertyValueFactory("player_game_id"));
        colPlayerName.setCellValueFactory(new PropertyValueFactory("player_name"));
        colGameTitle.setCellValueFactory(new PropertyValueFactory("game_title"));
        colPlayingDate.setCellValueFactory(new PropertyValueFactory("playing_date"));
        colScore.setCellValueFactory(new PropertyValueFactory("score"));




        // show DB data on the table
        data = FXCollections.observableArrayList();
        tablePlayerGame.itemsProperty().setValue(data);
        tablePlayerGame.setItems(playerAndGames);

        // sort by Date ???
        colPlayingDate.setSortType(TableColumn.SortType.ASCENDING);
        tablePlayerGame.getSortOrder().add(colPlayingDate);
    }



}

