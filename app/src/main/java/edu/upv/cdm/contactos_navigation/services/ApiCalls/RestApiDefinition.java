package edu.upv.cdm.contactos_navigation.services.ApiCalls;

import java.util.Date;
import java.util.List;

import edu.upv.cdm.contactos_navigation.models.CredencialesModel;
import edu.upv.cdm.contactos_navigation.models.Results.AddRegistroResult;
import edu.upv.cdm.contactos_navigation.models.Results.AddSecretoResult;
import edu.upv.cdm.contactos_navigation.models.Results.TryLoginResult;
import edu.upv.cdm.contactos_navigation.models.Score;
import edu.upv.cdm.contactos_navigation.models.SecretoModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Definición del endpoint correspondiente al REST API de Primosoft Games.
 */
public interface RestApiDefinition {

    @GET("api/recetas")
    Call<List<String>> getGames();

    @GET("getscores.php")
    Call<List<Score>> getScores(@Query("game") String game);

    @FormUrlEncoded()
    @POST("addscore.php")
    Call<AddScoreResult> addScore(
            @Field("score") int score, @Field("player") String player, @Field("game") String game);



    //Se definen los metodos de la app de Secretos
    @FormUrlEncoded()
    @POST("/api/login")
    Call<TryLoginResult> tryLogin(
            @Field("correo") String correo,@Field("contrasenia") String contrasenia);

    @FormUrlEncoded()
    @POST("/api/registro")
    Call<AddRegistroResult> addRegistro(
        @Field("nombre") String nombre, @Field("apellidos") String apellido, @Field("correo") String correo,
        @Field("contrasenia") String contrasenia,    @Field("sexo") String sexo, @Field("fechaNacimiento") String fechaNacimiento
    );

    @FormUrlEncoded()
    @POST("/api/secretos")
    Call<List<SecretoModel>> getSecretos(
            @Field("correo") String correo,@Field("contrasenia") String contrasenia,@Field("usuarioId") String identificador
    );

    @FormUrlEncoded()
    @POST("/api/addSecreto")
    Call<AddSecretoResult> addSecretos(
            @Field("correo") String correo,@Field("contrasenia") String contrasenia,@Field("usuarioId") String identificador,
            @Field("nombre") String nombre,@Field("descripcion") String descripcion, @Field("cadenaSecreta") String cadenaSecreta
    );


}
