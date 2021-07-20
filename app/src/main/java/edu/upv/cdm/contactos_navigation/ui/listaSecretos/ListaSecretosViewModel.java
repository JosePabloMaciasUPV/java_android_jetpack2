package edu.upv.cdm.contactos_navigation.ui.listaSecretos;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import edu.upv.cdm.contactos_navigation.models.CredencialesModel;
import edu.upv.cdm.contactos_navigation.models.SecretoModel;

public class ListaSecretosViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private List<SecretoModel> listaSecretos;
    private CredencialesModel credencial;
    private String error;

    public List<SecretoModel> getListaSecretos() {
        if(this.listaSecretos==null){
            return new ArrayList<SecretoModel>();
        }
        return listaSecretos;
    }

    public void setListaSecretos(List<SecretoModel> listaSecretos) {
        this.listaSecretos = listaSecretos;
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