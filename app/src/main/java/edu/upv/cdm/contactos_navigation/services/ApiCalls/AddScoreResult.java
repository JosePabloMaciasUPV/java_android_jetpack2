package edu.upv.cdm.contactos_navigation.services.ApiCalls;

import com.google.gson.annotations.SerializedName;

import edu.upv.cdm.contactos_navigation.models.Score;

public class AddScoreResult {
    @SerializedName("_error")
    private String error;
    @SerializedName("score")
    private Score score;

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
