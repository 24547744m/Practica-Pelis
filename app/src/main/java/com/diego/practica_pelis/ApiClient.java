package com.diego.practica_pelis;

import android.widget.ArrayAdapter;

import com.pojos.Movies;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by dam on 9/11/15.
 */
/**
 * Interfaz para realizar las consultas
 * */
interface MoviesServiceInterface{
    @GET("3/movie/popular")
    Call<Movies> getMostPopularMovies(@Query("country") String country, @Query("api_key") String api_key);
    @GET("3/movie/top_rated")
    Call<Movies> getMostRatedMovies(@Query("country") String country, @Query("api_key") String api_key);
}


public class ApiClient {
    private final MoviesServiceInterface serviceInterface;
    private final String BASE_URL = "http://api.rottentomatoes.com/api/public/v1.0/";
    private final String API_KEY = "392610fb7cf880a09d912568ce434ef2";
    private final String FORMAT = "json";


    public ApiClient() {
        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(BASE_URL)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
        serviceInterface = retrofit.create(MoviesServiceInterface.class);
    }

    public void getMostPopularMovies(ArrayAdapter<String> adapter, String country){
        Call<Movies> call = serviceInterface.getMostPopularMovies(country, API_KEY);//Ejecuta el método de la interfaz MoviesServiceInterface enviando parámetros para ejecutarse en una Query

    }

    public void retrofitProcessCall(ArrayAdapter<Movies> adapter, Call<>){

    }

    public void getMostRatedMovies(ArrayAdapter<String> adapter, String country) {
        Call<Movies> call = serviceInterface.getMostRatedMovies(country, API_KEY);


    }
}
