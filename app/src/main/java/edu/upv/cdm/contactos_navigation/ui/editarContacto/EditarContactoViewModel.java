package edu.upv.cdm.contactos_navigation.ui.editarContacto;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import edu.upv.cdm.contactos_navigation.db.AppDb;
import edu.upv.cdm.contactos_navigation.models.Contacto;

import static android.content.ContentValues.TAG;

public class EditarContactoViewModel extends ViewModel {
    private AppDb appDb=AppDb.getInstance();
    private Contacto contacto;
    private  long id;
    public Contacto getContacto() {
        return contacto;
    }
    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }
    // TODO: Implement the ViewModel
    public long getId() {
        return id;
    }
    public final void setId(long id){
        this.id=id;
    }
    private void obtenerContactoDesdeDb(){
        setContacto(appDb.contactoDao().getByd(id));
        //Log.d(TAG, "obtenerContactoDesdeDb: "+contacto.getNombre());
    }
    public void start(){
        obtenerContactoDesdeDb();
    }
}