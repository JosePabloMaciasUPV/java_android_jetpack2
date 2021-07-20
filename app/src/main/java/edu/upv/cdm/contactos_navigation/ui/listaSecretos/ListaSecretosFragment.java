package edu.upv.cdm.contactos_navigation.ui.listaSecretos;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import edu.upv.cdm.contactos_navigation.CredencialViewModel;
import edu.upv.cdm.contactos_navigation.R;
import edu.upv.cdm.contactos_navigation.databinding.ListaContactosFragmentBinding;
import edu.upv.cdm.contactos_navigation.models.SecretoModel;
import edu.upv.cdm.contactos_navigation.services.ApiCalls.ApiClient;
import edu.upv.cdm.contactos_navigation.services.common.HttpsService;
import edu.upv.cdm.contactos_navigation.services.secretos.SecretoAdapter;
import edu.upv.cdm.contactos_navigation.services.secretos.SecretoListener;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class ListaSecretosFragment extends Fragment {

    private ListaSecretosViewModel mViewModel;
    private ApiClient apiClient = null;
    private ListaContactosFragmentBinding viewBinding;
    private CredencialViewModel credencialViewModel;
    private SecretoAdapter secretoAdapter;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        viewBinding= ListaContactosFragmentBinding.inflate(inflater,container,false);
        View view=viewBinding.getRoot();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ListaSecretosViewModel.class);
        // TODO: Use the ViewModel

        secretoAdapter  = new SecretoAdapter(new SecretosEvents());
        secretoAdapter.setSecretos(mViewModel.getListaSecretos());
        viewBinding.mainRvContactos.setAdapter(secretoAdapter);

        if(credencialViewModel.getCredencialGlobal().getValue()!=null){
            obtenerListaSecretos();
        }
    }
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        credencialViewModel=new ViewModelProvider(requireActivity()).get(CredencialViewModel.class);

    }

    public void obtenerListaSecretos(){
        Context context=getActivity().getApplicationContext();
        HttpsService httpsService=new HttpsService(context);
        OkHttpClient https=httpsService.GetHttpsClient();
        apiClient=new ApiClient(https);

        String correo=credencialViewModel.getCredencialGlobal().getValue().getCorreo();
        String contrasenia=credencialViewModel.getCredencialGlobal().getValue().getContrasenia();
        String identificador=credencialViewModel.getCredencialGlobal().getValue().getIdentificador();

        apiClient.getSecretos(new GetSecretsCallBack(),correo,contrasenia,identificador);
    }


    final class GetSecretsCallBack implements Callback<List<SecretoModel>> {

        @Override
        public void onResponse(Call<List<SecretoModel>> call, Response<List<SecretoModel>> r) {
            if (r.isSuccessful()) {

                Log.i(TAG, "La respuesta la lista de secretos es:");
                List<SecretoModel> lista=new ArrayList<SecretoModel>();
                for (SecretoModel i : r.body()) {
                    lista.add(i);
                    Log.i(TAG, i.getNombre());
                }
                mViewModel.setListaSecretos(lista);
                 // Enviamos la list de los juegos al adapter para mostrarlos en el RV.

                secretoAdapter.setSecretos(lista);
                viewBinding.mainRvContactos.setAdapter(secretoAdapter);

            }
            else {
                Log.w(TAG, "onResponse: Regresó HttpError " + r.code());
            }
        }

        @Override
        public void onFailure(Call<List<SecretoModel>> call, Throwable t) {
            Log.e(TAG, "onFailure: Error al obtener juegos", t);
        }

    }
    final class SecretosEvents implements SecretoListener {
        public static final String TAG = "MainActivity";
        @Override
        public void onSecretoSeleccionado(SecretoModel secreto, View view) {
            //Log.d(TAG, "onContactoSeleccionado: Seleccionado el contacto" + contacto.getNombre());
            //Log.d(TAG, "onContactoSeleccionado: Seleccionado el contacto con id" + contacto.getId());

            //ListaContactosFragmentDirections.ActionNavListaContactosToNavEditarContacto2
            //        action=ListaContactosFragmentDirections.actionNavListaContactosToNavEditarContacto2(secreto.getNombre());
            //Navigation.findNavController(view).navigate(action);
        }
    }


}