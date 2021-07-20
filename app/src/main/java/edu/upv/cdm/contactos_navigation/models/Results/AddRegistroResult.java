package edu.upv.cdm.contactos_navigation.models.Results;

import com.google.gson.annotations.SerializedName;

import edu.upv.cdm.contactos_navigation.models.CredencialesModel;
import edu.upv.cdm.contactos_navigation.models.RegistroUsuarioModel;

public class AddRegistroResult {
    @SerializedName("_error")
    private String error;

    @SerializedName("registro")
    private RegistroUsuarioModel registro;

    @SerializedName("credenciales")
    private CredencialesModel credenciales;

    public String getError() {
        return error;
    }

    public RegistroUsuarioModel getRegistro() {
        return registro;
    }

    public CredencialesModel getCredenciales() {
        return credenciales;
    }


}
