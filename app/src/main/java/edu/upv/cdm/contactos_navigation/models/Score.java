package edu.upv.cdm.contactos_navigation.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Score {

    @SerializedName("id")
    private int id;

    @SerializedName("score")
    private int score;

    @SerializedName("player")
    private String player;

    @SerializedName("game")
    private String game;

    @SerializedName("date")
    private Date date;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public int getScore() { return score; }

    public void setScore(int score) { this.score = score; }

    public String getPlayer() { return player; }

    public void setPlayer(String player) { this.player = player; }

    public String getGame() { return game; }

    public void setGame(String game) { this.game = game; }

    public Date getDate() { return date; }

    public void setDate(Date date) { this.date = date; }

}
