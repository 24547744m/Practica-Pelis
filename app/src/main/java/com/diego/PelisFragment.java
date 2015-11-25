package com.diego;


import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.diego.MovieObjAdapter;
import com.diego.diego.ApiClient;
import com.diego.diego.R;
import com.pojos.MovieObj;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PelisFragment#} factory method to
 * create an instance of this fragment.
 */
public class PelisFragment extends Fragment {

    private ArrayList<MovieObj> moviesArr;
    private MovieObjAdapter adapter;

    public PelisFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true); //Seteamos como opciones de menú para llamarlo desde onCreateOptionsMenu
    }

    @Override
    public void onStart() {
        super.onStart();
//        refresh();//Al arrancar la app se actualiza los datos
    }

    @TargetApi(Build.VERSION_CODES.M)// Debido a que esta funcionalidad esta disponible a partir de la API 23, nos recomienda poner esta anotación
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_pelis, container, false); //Primero se crea un objeto del tipo View para extraer

        String[] data = {
                "Lun 23/6 - Soleado - 31/17",
                "Mar 24/6 - Niebla - 21/8",
                "Mier 25/6 - Nublado - 22/17",
                "Jue 26/6 - Lluvioso - 18/11",
                "Vie 27/6 - Niebla - 21/10",
                "Sab 28/6 - Soleado - 23/18",
                "Dom 29/6 - Soleado - 20/7"
        };

        //datos del fragment y luego se extrae el contenido del atributo que deseemos, en este caso una ListView, buscandolo por su id.
        ListView lvPelis = (ListView) rootView.findViewById(R.id.lvPelis);
        lvPelis.setAdapter(adapter);

        moviesArr = new ArrayList<MovieObj>();
        adapter = new MovieObjAdapter(getContext(), R.layout.pelis_row, moviesArr);

        lvPelis.setOnClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

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

        if (menuId == R.id.menuPopular){//Con el id obtenido comprobamos si es el el menú 'menuRefresh'
//            refresh(); //llamamos a nuestro método creado para refrescar nuestra activity
            popular(this.adapter, "es");
        }else if (menuId == R.id.menuRated){
            rated(this.adapter, "es");
        }



        return super.onOptionsItemSelected(item);
    }

    private void rated(ArrayAdapter<MovieObj> adapter, String country) {
        ApiClient apiClient = new ApiClient();
        apiClient.getMostRatedMovies(adapter, country);
    }

    private void popular(ArrayAdapter<MovieObj> adapter, String country) {
        ApiClient apiClient = new ApiClient();
        apiClient.getMostPopularMovies(adapter, country);
    }

//
//    private void refresh(){
//        String country = "es";
//        ApiClient apiClient = new ApiClient();
//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
//        String queryType = preferences.getString("query_type", "popular");
//
//        if (queryType.equals("popular")){
//            apiClient.getMostPopularMovies(adapter, country);
//        }else if (queryType.equals("most_rated")){
//            apiClient.getMostRatedMovies(adapter, country);
//        }
//
//
//    }


}
