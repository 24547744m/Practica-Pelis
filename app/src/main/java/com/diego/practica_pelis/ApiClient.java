package com.diego.practica_pelis;

import android.util.Log;
import android.widget.ArrayAdapter;

import com.pojos.MovieObj;
import com.pojos.Movies;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
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

    public void getMostPopularMovies(ArrayAdapter<MovieObj> adapter, String country){
        Call<Movies> call = serviceInterface.getMostPopularMovies(country, API_KEY);//Ejecuta el método de la interfaz MoviesServiceInterface enviando parámetros para ejecutarse en una Query
        retrofitProcessCall(adapter, call);
    }
    public void getMostRatedMovies(ArrayAdapter<MovieObj> adapter, String country) {
        Call<Movies> call = serviceInterface.getMostRatedMovies(country, API_KEY);
        retrofitProcessCall(adapter,call);
    }

    public void retrofitProcessCall(final ArrayAdapter<MovieObj> adapter, Call<Movies> call){//Proceso de llamado de Retrofit
        Log.e("HOLAAAAAAAAAAAAAAAAAa", "HOLAAAAAAAAAAAAAAAAa");
        call.enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Response<Movies> response, Retrofit retrofit) {
                if (response.isSuccess()){
                    Log.e("Success: ", "HAY RESPUESTA");
                    Movies movies = response.body();
                    for (MovieObj movie: movies.getMovieObjs()){
                        adapter.add(movie);
                    }
                    adapter.clear();

                    Log.d("Resultados: ", movies.getTotalPages().toString());

                }else{
                    Log.e("Error: ", response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });
    }

}
