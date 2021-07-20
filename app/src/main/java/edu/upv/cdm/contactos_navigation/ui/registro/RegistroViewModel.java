package edu.upv.cdm.contactos_navigation.ui.registro;

import androidx.lifecycle.ViewModel;

import edu.upv.cdm.contactos_navigation.models.CredencialesModel;
import edu.upv.cdm.contactos_navigation.models.RegistroUsuarioModel;

public class RegistroViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private RegistroUsuarioModel status;
    private CredencialesModel credencial;
    private String error;

    public RegistroUsuarioModel getStatus() {
        return status;
    }

    public void setStatus(RegistroUsuarioModel status) {
        this.status = status;
    }

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