package com.example.ntremotesocketapp;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.ListFragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import java.util.List;

import it.naturtalent.databinding.RemoteData;

/*
    Liste aller definierten Schalter in einem ListFragmetn anzeigen
    Definition des Fragments durch Erweiterung einer Unterklasse (Listfragment) von Fragment
 */
public class SocketListFragment extends ListFragment
{
    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        /*
        String[] values = new String[]{"Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2"};

         */

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

        if (remoteData != null)
        {
            Log.d("Select", "Position: " + position);
            String name = remoteData.getName();
            String type = remoteData.getType();

            NavController navController =  NavHostFragment.findNavController(SocketListFragment.this);
            navController.navigate(R.id.action_FirstFragment_to_SecondFragment);
            //navController.navigate(R.id.action_SecondFragment_to_FirstFragment);

            Log.d("Navigator", "Nav:" + navController);

            /*
            NavHostFragment.findNavController(SocketListFragment.this)
                    .navigate(R.id.action_FirstFragment_to_SecondFragment);

             */

            /*
            v.findViewById(R.id.button_second).setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    NavHostFragment.findNavController(SocketListFragment.this)
                            .navigate(R.id.action_FirstFragment_to_SecondFragment);
                }
            });

             */
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