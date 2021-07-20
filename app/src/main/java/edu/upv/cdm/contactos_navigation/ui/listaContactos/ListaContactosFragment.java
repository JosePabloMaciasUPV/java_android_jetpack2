package edu.upv.cdm.contactos_navigation.ui.listaContactos;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import edu.upv.cdm.contactos_navigation.R;
import edu.upv.cdm.contactos_navigation.db.AppDb;
import edu.upv.cdm.contactos_navigation.models.Contacto;
import edu.upv.cdm.contactos_navigation.services.contactos.ContactoAdapter;
import edu.upv.cdm.contactos_navigation.services.contactos.ContactosListener;
import edu.upv.cdm.contactos_navigation.ui.editarContacto.EditarContactoViewModel;

import static android.content.ContentValues.TAG;

public class ListaContactosFragment extends Fragment {

    private ListaContactosViewModel mViewModel;
    private RecyclerView rvContactos;
    private ContactoAdapter contactoAdapter;
    private EditarContactoViewModel editarContactoViewModel;
    public static ListaContactosFragment newInstance() {
        return new ListaContactosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.lista_contactos_fragment, container, false);
        rvContactos=root.findViewById(R.id.main_rv_contactos);

        return root;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ListaContactosViewModel.class);
        // TODO: Use the ViewModel
        mViewModel.start();
        contactoAdapter  = new ContactoAdapter(new ContactosEvents());
        contactoAdapter.setContactos(mViewModel.getContactos());
        rvContactos.setAdapter(contactoAdapter);

    }

    class ContactosEvents implements ContactosListener {
        public static final String TAG = "MainActivity";
        @Override
        public void onContactoSeleccionado(Contacto contacto, View view) {
            //Log.d(TAG, "onContactoSeleccionado: Seleccionado el contacto" + contacto.getNombre());
            //Log.d(TAG, "onContactoSeleccionado: Seleccionado el contacto con id" + contacto.getId());

            ListaContactosFragmentDirections.ActionNavListaContactosToNavEditarContacto2
                    action=ListaContactosFragmentDirections.actionNavListaContactosToNavEditarContacto2(contacto.getId());
            Navigation.findNavController(view).navigate(action);
        }
    }
}