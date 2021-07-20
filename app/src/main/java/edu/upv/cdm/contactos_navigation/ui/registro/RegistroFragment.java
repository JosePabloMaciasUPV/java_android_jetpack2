package edu.upv.cdm.contactos_navigation.ui.registro;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Date;
import java.util.List;

import edu.upv.cdm.contactos_navigation.CredencialViewModel;
import edu.upv.cdm.contactos_navigation.R;
import edu.upv.cdm.contactos_navigation.databinding.LoginFragmentBinding;
import edu.upv.cdm.contactos_navigation.databinding.RegistroFragmentBinding;
import edu.upv.cdm.contactos_navigation.models.Results.AddRegistroResult;
import edu.upv.cdm.contactos_navigation.services.ApiCalls.ApiClient;
import edu.upv.cdm.contactos_navigation.services.common.HttpsService;
import edu.upv.cdm.contactos_navigation.ui.crearContacto.CrearContactoFragment;
import edu.upv.cdm.contactos_navigation.ui.listaSecretos.ListaSecretosFragment;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class RegistroFragment extends Fragment {

    private RegistroViewModel mViewModel;
    private RegistroFragmentBinding viewBinding;
    private ApiClient apiClient = null;
    private CredencialViewModel credencialViewModel;

    public static RegistroFragment newInstance() {
        return new RegistroFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        viewBinding= RegistroFragmentBinding.inflate(inflater,container,false);
        View view=viewBinding.getRoot();
        viewBinding.registroBtnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrar();
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(RegistroViewModel.class);
        // TODO: Use the ViewModel

    }
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        credencialViewModel=new ViewModelProvider(requireActivity()).get(CredencialViewModel.class);
        if(credencialViewModel.getCredencialGlobal().getValue()!=null){
            validarSesion();
        }
    }

    private void registrar(){
        if(validarAgregar()){
            Context context=getActivity().getApplicationContext();
            HttpsService httpsService=new HttpsService(context);
            OkHttpClient https=httpsService.GetHttpsClient();
            apiClient=new ApiClient(https);

            //Obtener los datos del formulario
            String nombre=viewBinding.registroEtNombre.getText().toString();
            String apellido=viewBinding.registroEtApellido.getText().toString();
            String correo=viewBinding.registroEtCorreo.getText().toString();
            String contrasenia=viewBinding.registroEtContrasenia.getText().toString();
            String sexo=viewBinding.registroEtSexo.getText().toString();
            String fechaNacimiento=viewBinding.registroEtFechaNacimiento.toString();
            apiClient.registrar(new RegistrarCallBack(),nombre,apellido,correo,contrasenia,sexo,fechaNacimiento);
        }
    }
    private boolean validarAgregar() {
        if (viewBinding.registroEtNombre.getText().toString().isEmpty()) {
            viewBinding.registroEtNombre.setError("Ingrese el nombre");
            viewBinding.registroEtNombre.requestFocus();
            return false;
        }
        if (viewBinding.registroEtApellido.getText().toString().isEmpty()) {
            viewBinding.registroEtApellido.setError("Ingrese el apellido");
            viewBinding.registroEtApellido.requestFocus();
            return false;
        }
        if (viewBinding.registroEtCorreo.getText().toString().isEmpty()) {
            viewBinding.registroEtCorreo.setError("Ingrese el correo electrónico");
            viewBinding.registroEtCorreo.requestFocus();
            return false;
        }
        if (viewBinding.registroEtContrasenia.getText().toString().isEmpty()) {
            viewBinding.registroEtContrasenia.setError("Ingrese una contraseña");
            viewBinding.registroEtContrasenia.requestFocus();
            return false;
        }
        if (viewBinding.registroEtFechaNacimiento.getText().toString().isEmpty()) {
            viewBinding.registroEtFechaNacimiento.setError("Ingrese una fecha de nacimiento");
            viewBinding.registroEtFechaNacimiento.requestFocus();
            return false;
        }
        if (viewBinding.registroEtSexo.getText().toString().isEmpty()) {
            viewBinding.registroEtSexo.setError("Ingrese una género");
            viewBinding.registroEtSexo.requestFocus();
            return false;
        }

        return true;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        viewBinding = null;
    }
    private void validarSesion(){
        viewBinding.registroTvMensaje.setText("Sesión iniciada");
        viewBinding.registroEtCorreo.setEnabled(false);
        viewBinding.registroEtContrasenia.setEnabled(false);
        viewBinding.registroBtnRegistrarme.setEnabled(false);
        viewBinding.registroBtnIniciarSesion.setEnabled(false);
        viewBinding.registroEtSexo.setEnabled(false);
        viewBinding.registroEtFechaNacimiento.setEnabled(false);
        viewBinding.registroEtNombre.setEnabled(false);
    }


    final class RegistrarCallBack implements Callback<AddRegistroResult> {

        @Override
        public void onResponse(Call<AddRegistroResult> call, Response<AddRegistroResult> r) {
            if (r.isSuccessful()) {
                String error=r.body().getError();
                Log.i(TAG, "La respuesta de registro es:"+error);
                if(error.compareTo("Ninguno")==0){
                    credencialViewModel.setCredencialGlobal(r.body().getCredenciales());
                    validarSesion();
                }else {
                    viewBinding.registroTvMensaje.setText("Credenciales incorrectas");
                }
            }
            else {
                Log.w(TAG, "onResponse: Regresó HttpError " + r.code());
                viewBinding.registroTvMensaje.setText("Error de http:"+ r.code());
            }
        }

        @Override
        public void onFailure(Call<AddRegistroResult> call, Throwable t) {
            Log.e(TAG, "onFailure: Error al registrar", t);
            viewBinding.registroTvMensaje.setText("Error de http:"+ t.getMessage());
        }

    }

}