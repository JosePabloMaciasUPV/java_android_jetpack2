package edu.upv.cdm.contactos_navigation.models.Results;

import com.google.gson.annotations.SerializedName;

import edu.upv.cdm.contactos_navigation.models.CredencialesModel;

public class TryLoginResult {
    @SerializedName("_error")
    private String error;

    @SerializedName("login")
    private CredencialesModel login;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public CredencialesModel getLogin() {
        return login;
    }

    public void setLogin(CredencialesModel login) {
        this.login = login;
    }
}
