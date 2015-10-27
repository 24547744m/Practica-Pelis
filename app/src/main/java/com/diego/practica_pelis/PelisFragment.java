package com.diego.practica_pelis;


import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


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

        arrList = new ArrayList<>(Arrays.asList(datos));
        adapter = new ArrayAdapter<String>(getContext(), R.layout.pelis_row, R.id.tvPeli, arrList);// para getContext(); es necesaria la anotacion en la parte superior del método
        lvPelis.setAdapter(adapter);

        return rootView; //retornamos el objeto antes creado y modificado

    }


}
