package edu.upv.cdm.contactos_navigation.services.contactos;

import android.view.View;

import edu.upv.cdm.contactos_navigation.models.Contacto;

/**
 * Definición de la interacción o los eventos correspondientes a los items del RecyclerView de
 * contactos.
 */
public interface ContactosListener {

    /**
     * Evento de que un contacto (item de contacto) fue seleccionado o se le dió click.
     * @param contacto El contacto seleccionado.
     */
    void onContactoSeleccionado(Contacto contacto, View view);

}
