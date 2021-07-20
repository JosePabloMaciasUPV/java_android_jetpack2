package edu.upv.cdm.contactos_navigation.ui.editarContacto;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.upv.cdm.contactos_navigation.R;
import edu.upv.cdm.contactos_navigation.databinding.EditarContactoFragmentBinding;
import edu.upv.cdm.contactos_navigation.db.AppDb;
import edu.upv.cdm.contactos_navigation.models.Contacto;

import static android.content.ContentValues.TAG;

public class EditarContactoFragment extends Fragment {

    private EditarContactoViewModel mViewModel;
    private EditarContactoFragmentArgs args;
    private EditarContactoFragmentBinding viewBinding;
    private AppDb appDb=AppDb.getInstance();
    public static EditarContactoFragment newInstance() {
        return new EditarContactoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        viewBinding=EditarContactoFragmentBinding.inflate(inflater,container,false);
        View view=viewBinding.getRoot();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(EditarContactoViewModel.class);
        // TODO: Use the ViewModel
        args=EditarContactoFragmentArgs.fromBundle(getArguments());
        Long id=args.getIdContacto();
        mViewModel.setId(id);
        mViewModel.start();


        //Se modifican elementos de la interfaz grafica

        Contacto contacto=mViewModel.getContacto();
        viewBinding.mainEtEmail.setText(contacto.getEmail());
        viewBinding.mainEtNombre.setText(contacto.getNombre());
        viewBinding.mainEtTelefono.setText(contacto.getTelefono());
        viewBinding.editarBtnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditarContactoFragment.this.editarContacto();
            }
        });
        viewBinding.editarBtnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditarContactoFragment.this.borrarContacto();
            }
        });

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        viewBinding = null;
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

    private void editarContacto(){
        if(validarAgregar()){
            Contacto contacto=new Contacto();
            contacto.setNombre(viewBinding.mainEtNombre.getText().toString());
            contacto.setTelefono(viewBinding.mainEtTelefono.getText().toString());
            contacto.setEmail(viewBinding.mainEtEmail.getText().toString());
            contacto.setId(mViewModel.getContacto().getId());
            Log.d(TAG,contacto.getEmail());
            appDb.contactoDao().update(contacto);
        }
    }
    private void borrarContacto(){
        appDb.contactoDao().delete(mViewModel.getContacto());
    }

}