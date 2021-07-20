package edu.upv.cdm.contactos_navigation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import edu.upv.cdm.contactos_navigation.models.CredencialesModel;

public class CredencialViewModel extends ViewModel {
    private final MutableLiveData<CredencialesModel> credencialActual= new MutableLiveData<CredencialesModel>();
    public void setCredencialGlobal(CredencialesModel credencial){
        credencialActual.setValue(credencial);
    }
    public LiveData<CredencialesModel> getCredencialGlobal(){
        return credencialActual;
    }

}
