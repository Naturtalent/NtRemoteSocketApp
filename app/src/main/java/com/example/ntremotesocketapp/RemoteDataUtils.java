package com.example.ntremotesocketapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import java.util.ArrayList;
import java.util.List;

import it.naturtalent.databinding.RemoteData;

public class RemoteDataUtils
{

    private Context context;
    private SocketAdapter socketAdapter;
    private ListFragment listFragment;

    private ProgressDialog mProgressBar;

    private Fragment fragment;

    /*
    public RemoteDataUtils(Context context, SocketAdapter socketAdapter)
    {
        this.context = context;
        this.socketAdapter = socketAdapter;
        mProgressBar = new ProgressDialog(context);
    }

     */

     /*
        Konstruktion
     */
    public RemoteDataUtils(Context context)
    {
        this.context = context;
        mProgressBar = new ProgressDialog(context);
    }

    /*
        Konstruktion
     */
    public RemoteDataUtils(Fragment fragment)
    {
        this.fragment = fragment;
    }

    public void setListFragment(ListFragment listFragment)
    {
        this.listFragment = listFragment;
    }

    public void startLoadData()
    {

        mProgressBar.setCancelable(false);
        mProgressBar.setMessage("gespeicherte Schalter werden geladen..");
        mProgressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressBar.show();
        new RemoteDataUtils.LoadDataTask().execute(0);

    }


    

    //////////////////////////////////////////////////////////////////////////////

    //
    // The params are dummy and not used
    //
    class LoadDataTask extends AsyncTask<Integer, Integer, String>
    {
        @Override
        protected String doInBackground(Integer... params)
        {
            try
            {
                Log.d("check", "start Daten Ladevorgang");
                Thread.sleep(2000);

                // Remotedaten laden
                List<RemoteData> dataList = loadDataList();

                // Socketadapter erzeugen und diesem die Daten zuordnen
                socketAdapter = new SocketAdapter(context, new String[dataList.size()]);
                socketAdapter.setRemoteDataList(dataList);

                Log.d("check", "ende Daten Ladevorgang");

            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }


            return "Task Completed.";
        }

        @Override
        protected void onPostExecute(String result)
        {
            Log.d("check", "der gesamte Ladevorgang beendet");

            mProgressBar.hide();

            // Socketadapter dem aufrufenden Fragment zuordnen
            if(listFragment != null)
                listFragment.setListAdapter(socketAdapter);
        }

        @Override
        protected void onPreExecute()
        {
        }

        @Override
        protected void onProgressUpdate(Integer... values)
        {

        }
    }

    // RemmoteSeocketDaten generieren
    public List<RemoteData> loadDataList ()
    {
        List<RemoteData>remoteDateList = new ArrayList<>();

        RemoteData socket = new RemoteData("Schalter1", "type", "Code", "RemoteCode");
        remoteDateList.add(socket);

        socket = new RemoteData("Schalter2", "type", "Code", "RemoteCode");
        remoteDateList.add(socket);

        return remoteDateList;
    }

    /*
    public List<RemoteData> getRemoteDateList()
    {
        return remoteDateList;
    }

     */


}
