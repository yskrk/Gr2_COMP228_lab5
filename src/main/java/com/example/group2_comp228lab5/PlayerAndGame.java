package com.example.group2_comp228lab5;

import java.util.Date;

public class PlayerAndGame {
    Integer player_game_id;  // number(3)
    Integer player_id;
    Integer game_id;
    Date playing_date;  // date
    Integer score;  // number(6)

    public PlayerAndGame(Integer player_game_id, Integer player_id, Integer game_id, Date playing_date, Integer score) {
        this.player_game_id = player_game_id;
        this.player_id = player_id;
        this.game_id = game_id;
        this.playing_date = playing_date;
        this.score = score;
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
}
