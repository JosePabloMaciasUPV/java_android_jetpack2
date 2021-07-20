package edu.upv.cdm.contactos_navigation.ui.crearContacto;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.upv.cdm.contactos_navigation.R;
import edu.upv.cdm.contactos_navigation.databinding.CrearContactoFragmentBinding;
import edu.upv.cdm.contactos_navigation.databinding.EditarContactoFragmentBinding;
import edu.upv.cdm.contactos_navigation.db.AppDb;
import edu.upv.cdm.contactos_navigation.models.Contacto;

import static android.content.ContentValues.TAG;

public class CrearContactoFragment extends Fragment {

    private CrearContactoViewModel mViewModel;
    private AppDb appDb=AppDb.getInstance();
    private CrearContactoFragmentBinding viewBinding;
    public static CrearContactoFragment newInstance() {
        return new CrearContactoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        viewBinding= CrearContactoFragmentBinding.inflate(inflater,container,false);
        View view=viewBinding.getRoot();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(CrearContactoViewModel.class);
        // TODO: Use the ViewModel
        viewBinding.editarBtnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CrearContactoFragment.this.agregarContacto();
            }
        });
    }

    private boolean validarAgregar() {
        if (viewBinding.mainEtNombre.getText().toString().isEmpty()) {
            viewBinding.mainEtNombre.setError(getString(R.string.main_et_nombre_error_falta_dato));
            viewBinding.mainEtNombre.requestFocus();
            return false;
        }
        if (viewBinding.mainEtTelefono.getText().toString().isEmpty()) {
            viewBinding.mainEtTelefono.setError("Ingrese el teléfono");
            viewBinding.mainEtTelefono.requestFocus();
            return false;
        }
        if (viewBinding.mainEtEmail.getText().toString().isEmpty()) {
            viewBinding.mainEtEmail.setError("Ingrese el correo electrónico");
            viewBinding.mainEtEmail.requestFocus();
            return false;
        }
        return true;
    }
    private void agregarContacto(){
        if(validarAgregar()) {
            Contacto contacto = new Contacto();
            contacto.setNombre(viewBinding.mainEtNombre.getText().toString());
            contacto.setTelefono(viewBinding.mainEtTelefono.getText().toString());
            contacto.setEmail(viewBinding.mainEtEmail.getText().toString());
            appDb.contactoDao().insert(contacto);
            //Navigation.findNavController
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        viewBinding = null;
    }
}