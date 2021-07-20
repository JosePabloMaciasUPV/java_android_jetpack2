package edu.upv.cdm.contactos_navigation.models.Results;

import com.google.gson.annotations.SerializedName;

import edu.upv.cdm.contactos_navigation.models.SecretoModel;

public class AddSecretoResult {
    @SerializedName("_error")
    private String error;
    @SerializedName("secreto")
    private SecretoModel secreto;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public SecretoModel getSecreto() {
        return secreto;
    }

    public void setSecreto(SecretoModel secreto) {
        this.secreto = secreto;
    }
}
