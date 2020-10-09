package com.example.ntremotesocketapp;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.ListFragment;

import java.util.List;

import it.naturtalent.databinding.RemoteData;

/*
    Liste aller definierten Schalter in einem ListFragmetn anzeigen
 */
public class SecondFragment extends ListFragment
{

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2" };

        Context context = getView().getContext();

        // Adapter fuer das ListFragment erzeugen
        //SocketAdapter adapter = new SocketAdapter(context);


        // Die gespeicherten Socketdaten laden
        RemoteDataUtils remoteDataUtils = new RemoteDataUtils(context);
        remoteDataUtils.setListFragment(this);

        Log.d("SecondFragment", "start Load Data");
        remoteDataUtils.startLoadData();
        //List<RemoteData> remoteDataList = remoteDataUtils.getRemoteDateList();
        Log.d("SecondFragment", "Load Data beendet");

        /*
        Log.d("SecondFragment", "generate Adapter");

        // Adapter fuer das ListFragment erzeugen
        SocketAdapter adapter = new SocketAdapter(context, remoteDataList);
        setListAdapter(adapter);

         */



    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id)
    {

        SocketAdapter socketAdapter = (SocketAdapter) getListAdapter();
        List<RemoteData> getRemoteDataList = socketAdapter.getRemoteDataList();

        RemoteData remoteData = (RemoteData) getRemoteDataList.get(position);

        if(remoteData != null)
        {
            Log.d("Select", "Position: "+ position);
            String name = remoteData.getName();
            String type = remoteData.getType();
        }
    }

    /*
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2" };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                R.layout.rowlayout, R.id.label, values);

        setListAdapter(adapter);

    }

     */
    /*
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2" };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);
    }


     */



}