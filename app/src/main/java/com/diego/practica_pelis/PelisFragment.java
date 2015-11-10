package com.diego.practica_pelis;


import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.pojos.Movies;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Query;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PelisFragment#} factory method to
 * create an instance of this fragment.
 */
public class PelisFragment extends Fragment {

    private ArrayList<String> arrList;
    private ArrayAdapter<String> adapter;

    public PelisFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true); //Seteamos como opciones de menú para llamarlo desde onCreateOptionsMenu
    }

    @TargetApi(Build.VERSION_CODES.M)// Debido a que esta funcionalidad esta disponible a partir de la API 23, nos recomienda poner esta anotación
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String[] datos = {"The Ring","The Lord of Rings","Harry Potter","Resident Evil","World War Z"};

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_pelis, container, false); //Primero se crea un objeto del tipo View para extraer
        //datos del fragment y luego se extrae el contenido del atributo que deseemos, en este caso una ListView, buscandolo por su id.
        ListView lvPelis = (ListView) rootView.findViewById(R.id.lvPelis);

        arrList = new ArrayList<>(Arrays.asList(datos));// Se setea al parámetro del constructor del ArrayList el array de String de películas de ejemplo creado anteriormente.
                                                        // Transformandolo en un objeto del tipo List, usando la utilidad de la clase Arrays.
        adapter = new ArrayAdapter<String>(getContext(), R.layout.pelis_row, R.id.tvPeli, arrList);// para getContext(); es necesaria la anotación en la parte superior del método
        lvPelis.setAdapter(adapter);

        return rootView; //retornamos el objeto antes creado y modificado

    }

/**
 * Al crearse las opciones del menú, referenciamos el menú creado en el XML 'menu_fragment_pelis'
 * */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_fragment_pelis, menu);//Referencia al menú del XML
    }
/**
 * Aquí capturamos los eventos del menú, al ser seleccionados
 * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int menuId = item.getItemId(); //Obtenemos el id del menú clickado

        if (menuId == R.id.menuRefresh){//Con el id obtenido comprobamos si es el el menú 'menuRefresh'
            refresh(); //llamamos a nuestro método creado para refrescar nuestra activity
            
        }


        return super.onOptionsItemSelected(item);
    }


    private void refresh(){
        String country = "es";
        ApiClient apiClient = new ApiClient();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String queryType = preferences.getString("query_type", "popular");

        if (queryType.equals("popular")){
            apiClient.getMostPopularMovies(adapter, country);
        }else if (queryType.equals("most_rated")){
            apiClient.getMostRatedMovies(adapter, country);
        }


    }


}
