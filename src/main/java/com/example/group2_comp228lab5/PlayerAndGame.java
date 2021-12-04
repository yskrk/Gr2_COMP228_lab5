package com.example.group2_comp228lab5;

import java.util.Date;

public class PlayerAndGame {
    Integer player_game_id;  // number(3)
    Integer player_id;
    Integer game_id;
    Date playing_date;  // date
    Integer score;  // number(6)
    String game_title;  // game_title(game)
    String player_name; // first_name + last_name(player)

    public PlayerAndGame(Integer player_game_id, Integer player_id, Integer game_id, Date playing_date, Integer score, String game_title, String player_name) {
        this.player_game_id = player_game_id;
        this.player_id = player_id;
        this.game_id = game_id;
        this.playing_date = playing_date;
        this.score = score;
        this.game_title = game_title;
        this.player_name = player_name;
    }

    public Integer getPlayer_game_id() {
        return player_game_id;
    }

    public void setPlayer_game_id(Integer player_game_id) {
        this.player_game_id = player_game_id;
    }

    public Integer getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(Integer player_id) {
        this.player_id = player_id;
    }

    public Integer getGame_id() {
        return game_id;
    }

    public void setGame_id(Integer game_id) {
        this.game_id = game_id;
    }

    public Date getPlaying_date() {
        return playing_date;
    }

    public void setPlaying_date(Date playing_date) {
        this.playing_date = playing_date;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getGame_title() {
        return game_title;
    }

    public void setGame_title(String game_title) {
        this.game_title = game_title;
    }

    public String getPlayer_name() {
        return player_name;
    }

    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }
}
