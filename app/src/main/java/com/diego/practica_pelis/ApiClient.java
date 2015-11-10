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

interface MoviesServiceInterface{
    @GET("")
    Call<Movies> getMostPopularMovies(
         @Query("") String country;

    );

}


public class ApiClient {
    private final MoviesServiceInterface service;
    private final String BASE_URL = "http://api.rottentomatoes.com/api/public/v1.0/";
    private final String API_KEY = "392610fb7cf880a09d912568ce434ef2";
    private final String FORMAT = "json";


    public ApiClient() {
        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(BASE_URL)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
        service = retrofit.create(MoviesServiceInterface.class);
    }

    public void getMostPopularMovies(ArrayAdapter<String> adapter, String country){
//        Call<Movies> call = service.getMostPopularMovies()
    }


}
