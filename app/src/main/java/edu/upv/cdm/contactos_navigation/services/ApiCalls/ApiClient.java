package edu.upv.cdm.contactos_navigation.services.ApiCalls;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;
import java.util.List;

import edu.upv.cdm.contactos_navigation.models.CredencialesModel;
import edu.upv.cdm.contactos_navigation.models.Results.AddRegistroResult;
import edu.upv.cdm.contactos_navigation.models.Results.AddSecretoResult;
import edu.upv.cdm.contactos_navigation.models.Results.TryLoginResult;
import edu.upv.cdm.contactos_navigation.models.Score;
import edu.upv.cdm.contactos_navigation.models.SecretoModel;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Clase client correspondiente a la REST API de Primosoft Games.
 */
public class ApiClient {
    /**
     * URL Base de la REST API.
     */
    public static final String BASE_URL = "https://comercioagricultura.me/";
    /**
     * Inicializa una nueva instancia de ApiClient.
     */
    public ApiClient(OkHttpClient https) {
        initGson();
        initRetrofit(https);
        api = retrofit.create(RestApiDefinition.class);
    }
    private Gson gson;
    private Retrofit retrofit;
    private RestApiDefinition api;
    private void initGson() {
        gson = new GsonBuilder()
                .setDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss")
                .create();
    }
    private void initRetrofit(OkHttpClient https) {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(https)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }


    public Call<AddRegistroResult> registrar(String nombre, String apellido, String correo, String contrasenia,
                                             String sexo, String fechaNacimiento){
        return api.addRegistro(nombre,apellido,correo,contrasenia,sexo,fechaNacimiento);
    }

    public void registrar(Callback<AddRegistroResult> callback ,String nombre, String apellido, String correo, String contrasenia,
                          String sexo, String fechaNacimiento){
        registrar(nombre,apellido,correo,contrasenia,sexo,fechaNacimiento).enqueue(callback);
    }

    public Call<TryLoginResult> login(String correo, String contrasenia){
        return api.tryLogin(correo,contrasenia);
    }

    public void login(Callback<TryLoginResult> callback,String correo,String contrasenia){
        login(correo,contrasenia).enqueue(callback);
    }

    public Call<List<SecretoModel>> getSecretos(String correo, String contrasenia, String identificador){
        return api.getSecretos(correo,contrasenia,identificador);
    }

    public void getSecretos(Callback<List<SecretoModel>> callback,String correo, String contrasenia, String identificador){
        getSecretos(correo,contrasenia,identificador).enqueue(callback);
    }

    public Call<AddSecretoResult> addSecreto(String correo, String contrasenia, String identificador,String nombre,String descripcion, String cadenaSecreta){
        return api.addSecretos(correo,contrasenia,identificador,nombre,descripcion,cadenaSecreta);
    }

    public void addSecreto(Callback<AddSecretoResult> callback,String correo, String contrasenia, String identificador,String nombre,String descripcion, String cadenaSecreta){
        addSecreto(correo,contrasenia,identificador,nombre,descripcion,cadenaSecreta).enqueue(callback);
    }


    //Sin usar
    /**
     * Ejecuta la llamada para obtener el listado de los juegos.
     * @return La instancia correspondiente a la llamada a la REST API.
     */
    public Call<List<String>> getGames() {
        return api.getGames();
    }

    /**
     * Ejecuta la llamada para obtener el listado de los juegos de forma asíncrona.
     * @param callback La implementación del callback que se ejecutará al terminar de hacer la
     *                 llamada a la REST API.
     */
    public void getGames(Callback<List<String>> callback) {
        getGames().enqueue(callback);
    }

    /**
     * Ejecuta la llamada para obtener los Scores de un juego.
     * @param game Nombre del juego del cual se quiere obtener los scores.
     * @return La instancia correspondiente a la llamda a la REST API.
     */
    public Call<List<Score>> getScores(String game) {
        return api.getScores(game);
    }


    public void addScore(int score, String player, String game, Callback<AddScoreResult> callback){
        addScore(score,player,game).enqueue(callback);
    }

    public Call<AddScoreResult> addScore(int score, String player, String game){
        return api.addScore(score,player,game);
    }
    /**
     * Ejecuta la llamada para obtener el listado de los juegos de forma asíncrona.
     * @param game Nombre del juego del cual se quiere obtener los scores.
     * @param callback La implementación del callback que se ejecutará al terminar de hacer la
     *                 llamada a la REST API.
     */
    public void getScores(String game, Callback<List<Score>> callback) {
        getScores(game).enqueue(callback);
    }
}
