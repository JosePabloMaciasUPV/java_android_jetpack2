package edu.upv.cdm.contactos_navigation.ui.agregarSecreto;

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

import edu.upv.cdm.contactos_navigation.CredencialViewModel;
import edu.upv.cdm.contactos_navigation.R;
import edu.upv.cdm.contactos_navigation.databinding.AgregarSecretoFragmentBinding;
import edu.upv.cdm.contactos_navigation.databinding.LoginFragmentBinding;
import edu.upv.cdm.contactos_navigation.models.Results.AddRegistroResult;
import edu.upv.cdm.contactos_navigation.models.Results.AddSecretoResult;
import edu.upv.cdm.contactos_navigation.models.Results.TryLoginResult;
import edu.upv.cdm.contactos_navigation.services.ApiCalls.ApiClient;
import edu.upv.cdm.contactos_navigation.services.common.HttpsService;
import edu.upv.cdm.contactos_navigation.ui.crearContacto.CrearContactoFragment;
import edu.upv.cdm.contactos_navigation.ui.login.LoginFragment;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class AgregarSecretoFragment extends Fragment {

    private AgregarSecretoViewModel mViewModel;
    private AgregarSecretoFragmentBinding viewBinding;
    private ApiClient apiClient = null;
    private CredencialViewModel credencialViewModel;
    public static AgregarSecretoFragment newInstance() {
        return new AgregarSecretoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        viewBinding= AgregarSecretoFragmentBinding.inflate(inflater,container,false);
        View view=viewBinding.getRoot();
        viewBinding.agregarSecretoBtnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AgregarSecretoFragment.this.agregarSecreto();
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AgregarSecretoViewModel.class);
        // TODO: Use the ViewModel
    }
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        credencialViewModel=new ViewModelProvider(requireActivity()).get(CredencialViewModel.class);
        if(credencialViewModel.getCredencialGlobal().getValue()==null){
            sesionInvalida("Inicia sesión para continuar.");
        }
    }
    private void sesionInvalida(String mensaje){
        viewBinding.agregarSecretoTvMensaje.setText(mensaje);
        viewBinding.agregarSecretoEtNombre.setEnabled(false);
        viewBinding.agregarSecretoEtDescripcion.setEnabled(false);
        viewBinding.agregarSecretoEtCadenaSecreta.setEnabled(false);
        viewBinding.agregarSecretoBtnAgregar.setEnabled(false);

    }

    private void agregarSecreto(){
        if(validarAgregar()){
            Context context=getActivity().getApplicationContext();
            HttpsService httpsService=new HttpsService(context);
            OkHttpClient https=httpsService.GetHttpsClient();
            apiClient=new ApiClient(https);

            //Obtener los datos del formulario
            String nombre=viewBinding.agregarSecretoEtNombre.getText().toString();
            String descripcion=viewBinding.agregarSecretoEtDescripcion.getText().toString();
            String cadenaSecreta=viewBinding.agregarSecretoEtDescripcion.getText().toString();
            String correo=credencialViewModel.getCredencialGlobal().getValue().getCorreo();
            String contrasenia=credencialViewModel.getCredencialGlobal().getValue().getContrasenia();
            String identificador=credencialViewModel.getCredencialGlobal().getValue().getIdentificador();
            apiClient.addSecreto(new AgregarSecretoCallBack(),correo,contrasenia,identificador,nombre,descripcion,cadenaSecreta);
        }
    }

    private boolean validarAgregar() {

        if (viewBinding.agregarSecretoEtNombre.getText().toString().isEmpty()) {
            viewBinding.agregarSecretoEtNombre.setError("Ingrese un nombre ");
            viewBinding.agregarSecretoEtNombre.requestFocus();
            return false;
        }
        if (viewBinding.agregarSecretoEtDescripcion.getText().toString().isEmpty()) {
            viewBinding.agregarSecretoEtDescripcion.setError("Ingrese una descripción");
            viewBinding.agregarSecretoEtDescripcion.requestFocus();
            return false;
        }
        if (viewBinding.agregarSecretoEtCadenaSecreta.getText().toString().isEmpty()) {
            viewBinding.agregarSecretoEtCadenaSecreta.setError("Ingrese la cadena secreta");
            viewBinding.agregarSecretoEtCadenaSecreta.requestFocus();
            return false;
        }

        return true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        viewBinding = null;
    }


    final class AgregarSecretoCallBack implements Callback<AddSecretoResult> {

        @Override
        public void onResponse(Call<AddSecretoResult> call, Response<AddSecretoResult> r) {
            if (r.isSuccessful()) {
                String error=r.body().getError();
                Log.i(TAG, "La respuesta de agregar secreto es:"+error);
                if(error.compareTo("Se agregó correctamente")==0){
                    sesionInvalida("Se agregó correctamente.");

                }else {
                    viewBinding.agregarSecretoTvMensaje.setText("Ocurio una error con los datos ingresados.");
                }


            }
            else {
                Log.w(TAG, "onResponse: Regresó HttpError " + r.code());
                viewBinding.agregarSecretoTvMensaje.setText("Error de http:"+ r.code());
            }
        }

        @Override
        public void onFailure(Call<AddSecretoResult> call, Throwable t) {
            Log.e(TAG, "onFailure: Error al registrar", t);
            viewBinding.agregarSecretoTvMensaje.setText("Error de http:"+ t.getMessage());
        }

    }
}