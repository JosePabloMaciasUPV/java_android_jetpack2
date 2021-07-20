package edu.upv.cdm.contactos_navigation.ui.agregarSecreto;

import androidx.lifecycle.ViewModel;


import edu.upv.cdm.contactos_navigation.models.CredencialesModel;
import edu.upv.cdm.contactos_navigation.models.SecretoModel;

public class AgregarSecretoViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private CredencialesModel credencial;
    private String error;
    private SecretoModel secreto;

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

    public SecretoModel getSecreto() {
        return secreto;
    }

    public void setSecreto(SecretoModel secreto) {
        this.secreto = secreto;
    }
}