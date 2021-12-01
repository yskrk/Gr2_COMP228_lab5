package com.example.group2_comp228lab5;

public class Game {
    Integer game_id;    // number(3)
    String game_title;  // varchar2(100)

    public Game(Integer game_id, String game_title) {
        this.game_id = game_id;
        this.game_title = game_title;
    }

    public Integer getGame_id() {
        return game_id;
    }

    public void setGame_id(Integer game_id) {
        this.game_id = game_id;
    }

    public String getGame_title() {
        return game_title;
    }

    public void setGame_title(String game_title) {
        this.game_title = game_title;
    }
}
