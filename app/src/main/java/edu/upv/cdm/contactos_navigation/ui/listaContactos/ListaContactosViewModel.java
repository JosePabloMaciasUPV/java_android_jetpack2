package edu.upv.cdm.contactos_navigation.ui.listaContactos;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.ViewModel;
import androidx.navigation.fragment.NavHostFragment;

import java.util.ArrayList;
import java.util.List;

import edu.upv.cdm.contactos_navigation.db.AppDb;
import edu.upv.cdm.contactos_navigation.models.Contacto;
import edu.upv.cdm.contactos_navigation.services.contactos.ContactoAdapter;
import edu.upv.cdm.contactos_navigation.services.contactos.ContactosListener;

import static android.content.ContentValues.TAG;

public class ListaContactosViewModel extends ViewModel {
    private AppDb appDb=AppDb.getInstance();
    private List<Contacto> contactos;
    public List<Contacto> getContactos() {
        return contactos;
    }


    public void setContactos(List<Contacto> contactos) {
        this.contactos = contactos;
        for(Contacto c: contactos){
            Log.d(TAG, "readFromDb: " +c.getNombre() );
        }
    }


    private void readFromDb(){
        setContactos(appDb.contactoDao().getAll());
    }

    // TODO: Implement the ViewModel
    public void start(){
        readFromDb();
    }



}