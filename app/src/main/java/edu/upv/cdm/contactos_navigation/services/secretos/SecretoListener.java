package edu.upv.cdm.contactos_navigation.services.secretos;

import android.view.View;

import edu.upv.cdm.contactos_navigation.models.SecretoModel;

/**
 * Definición de la interacción o los eventos correspondientes a los items del RecyclerView de
 * contactos.
 */
public interface SecretoListener {

    /**
     * Evento de que un contacto (item de contacto) fue seleccionado o se le dió click.
     * @param contacto El contacto seleccionado.
     */
    void onSecretoSeleccionado(SecretoModel secreto, View view);

}
