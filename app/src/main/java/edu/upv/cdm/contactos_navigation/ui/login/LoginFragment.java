package edu.upv.cdm.contactos_navigation.ui.login;

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
import edu.upv.cdm.contactos_navigation.databinding.LoginFragmentBinding;
import edu.upv.cdm.contactos_navigation.models.CredencialesModel;
import edu.upv.cdm.contactos_navigation.models.Results.AddRegistroResult;
import edu.upv.cdm.contactos_navigation.models.Results.TryLoginResult;
import edu.upv.cdm.contactos_navigation.services.ApiCalls.ApiClient;
import edu.upv.cdm.contactos_navigation.services.common.HttpsService;
import edu.upv.cdm.contactos_navigation.ui.crearContacto.CrearContactoFragment;
import edu.upv.cdm.contactos_navigation.ui.registro.RegistroFragment;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class LoginFragment extends Fragment {

    private LoginViewModel mViewModel;
    private LoginFragmentBinding viewBinding;
    private CredencialViewModel credencialViewModel;
    private ApiClient apiClient = null;
    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        viewBinding=LoginFragmentBinding.inflate(inflater,container,false);
        View view=viewBinding.getRoot();
        viewBinding.loginBtnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginFragment.this.iniciarSesion();
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
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


    private void iniciarSesion(){
        if(validarAgregar()){
            Context context=getActivity().getApplicationContext();
            HttpsService httpsService=new HttpsService(context);
            OkHttpClient https=httpsService.GetHttpsClient();
            apiClient=new ApiClient(https);

            //Obtener los datos del formulario
            String correo=viewBinding.loginEtCorreo.getText().toString();
            String contrasenia=viewBinding.loginEtContrasenia.getText().toString();
            apiClient.login(new LoginCallBack(),correo,contrasenia);
        }
    }

    private boolean validarAgregar() {

        if (viewBinding.loginEtCorreo.getText().toString().isEmpty()) {
            viewBinding.loginEtCorreo.setError("Ingrese el correo electrónico");
            viewBinding.loginEtCorreo.requestFocus();
            return false;
        }
        if (viewBinding.loginEtContrasenia.getText().toString().isEmpty()) {
            viewBinding.loginEtContrasenia.setError("Ingrese una contraseña");
            viewBinding.loginEtContrasenia.requestFocus();
            return false;
        }


        return true;
    }

    private void validarSesion(){

        viewBinding.loginTvMensaje.setText("Sesión iniciada");
        viewBinding.loginEtCorreo.setEnabled(false);
        viewBinding.loginEtContrasenia.setEnabled(false);
        viewBinding.loginBtnIngresar.setEnabled(false);
        viewBinding.loginBtnRegistrarse.setEnabled(false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        viewBinding = null;
    }

    final class LoginCallBack implements Callback<TryLoginResult> {

        @Override
        public void onResponse(Call<TryLoginResult> call, Response<TryLoginResult> r) {
            if (r.isSuccessful()) {
                String error=r.body().getError();
                Log.i(TAG, "La respuesta de login es:"+error);
                if(error.compareTo("Ninguno")==0){
                    credencialViewModel.setCredencialGlobal(r.body().getLogin());
                    validarSesion();
                }else{
                    viewBinding.loginTvMensaje.setText("Credenciales incorrectas");
                }



            }
            else {
                Log.w(TAG, "onResponse: Regresó HttpError " + r.code());
                viewBinding.loginTvMensaje.setText("Error de http:"+ r.code());
            }
        }

        @Override
        public void onFailure(Call<TryLoginResult> call, Throwable t) {
            Log.e(TAG, "onFailure: Error al registrar", t);
            viewBinding.loginTvMensaje.setText("Error de http:"+ t.getMessage());
        }

    }
}