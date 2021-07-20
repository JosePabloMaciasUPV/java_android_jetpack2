package edu.upv.cdm.contactos_navigation.models;

import com.google.gson.annotations.SerializedName;

public class CredencialesModel {
    @SerializedName("correo")
    private String correo;
    @SerializedName("contrasenia")
    private String contrasenia;
    @SerializedName("usuarioId")
    private String identificador;

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }
}
