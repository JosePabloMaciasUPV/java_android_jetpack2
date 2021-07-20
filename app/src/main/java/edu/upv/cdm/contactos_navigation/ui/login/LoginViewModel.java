package edu.upv.cdm.contactos_navigation.ui.login;

import androidx.lifecycle.ViewModel;

import edu.upv.cdm.contactos_navigation.models.CredencialesModel;

public class LoginViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private CredencialesModel credencial;
    private String error;

    public CredencialesModel getCredencial() {
        return credencial;
    }

    public void setCredencial(CredencialesModel credencial) {
        this.credencial = credencial;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}