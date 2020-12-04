package com.example.ntremotesocketapp;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.ListFragment;

import java.util.ArrayList;
import java.util.List;

import it.naturtalent.common.threadpool.FetchDataUseCase;
import it.naturtalent.common.threadpool.ThreadPool;
import it.naturtalent.databinding.RemoteData;

public class RemoteDataUtils
{

    private Context context;
    private SocketAdapter socketAdapter;
    private ListFragment listFragment;

    private ProgressDialog mProgressBar;

    private Fragment fragment;

    // ThreadPool Parameter
    private static FetchDataUseCase mFetchDataUseCase;
    private InitializeDialogFragment dialogFragment;
    private FetchDataUseCase.Listener threadPoolListerner;

    // Listener dienst dazau den Dialog auszuschalten
    private FetchDataUseCase.Listener ctrlDialogListener = new FetchDataUseCase.Listener()
    {
        @Override
        public void onDataFetched(List<RemoteData> data)
        {
            dialogFragment.dismiss();
        }

        @Override
        public void onDataFetchFailed()
        {
            dialogFragment.dismiss();
        }
    };


    // die gespeicherten Daten
    private static List<RemoteData> socketData;


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

    /*************************************************************************

     ThreadPool Variante
     Laden der Daten in einer Multiprozess Variante

     *************************************************************************/

    /*
        Konstruktion
     */
    public RemoteDataUtils(Fragment fragment, FetchDataUseCase.Listener threadPoolListerner)
    {
        this.fragment = fragment;
        this.threadPoolListerner = threadPoolListerner;
    }

    public static class InitializeDialogFragment extends DialogFragment
    {

        /*
            Den Lade-Dialog instanziierren
         */
        public static InitializeDialogFragment newInstance(int title)
        {
            InitializeDialogFragment frag = new InitializeDialogFragment();
            Bundle args = new Bundle();
            args.putInt("title", title);
            frag.setArguments(args);
            return frag;
        }


        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState)
        {
            int title = getArguments().getInt("title");

            return new AlertDialog.Builder(getActivity())
                    .setIcon(R.drawable.alert_dialog_dart_icon)
                    .setTitle(title)
                    .setCancelable(true)
                    .setNegativeButton(R.string.alert_dialog_cancel,
                            new DialogInterface.OnClickListener()
                            {
                                public void onClick(DialogInterface dialog,
                                                    int whichButton)
                                {
                                    it.naturtalent.common.logger.Log.i("FragmentAlertDialog", "Negative click!");
                                    //dialog.dismiss();
                                }
                            })
                    .create();
        }


    }




    /*
        Laden der gespeicherten Daten mit dem ThradPool Tool

     */
    public List<RemoteData> getSocketData()
    {
        if(socketData == null)
        {
            mFetchDataUseCase = new ThreadPool().getFetchDataUseCase();
            mFetchDataUseCase.registerListener(ctrlDialogListener);
            mFetchDataUseCase.registerListener(threadPoolListerner);
            mFetchDataUseCase.fetchData();
            showDialog();
        }

        return socketData;
    }

    private void showDialog()
    {
        dialogFragment = InitializeDialogFragment.newInstance(R.string.alert_dialog_two_buttons_title);
        dialogFragment.show(fragment.getActivity().getSupportFragmentManager(), "dialog");
    }


    /*************************************************************************

     alte Variante

     *************************************************************************/

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

        /*
        RemoteData socket = new RemoteData("Schalter1", "type", "Code", "RemoteCode");
        remoteDateList.add(socket);

        socket = new RemoteData("Schalter2", "type", "Code", "RemoteCode");
        remoteDateList.add(socket);
        */


        int n = 20;
        for(int i = 1; i < n;i++)
        {
            RemoteData socket = new RemoteData("Schalter"+i, "type", "Code", "RemoteCode");
            remoteDateList.add(socket);
        }

        return remoteDateList;
    }

    /*
    public List<RemoteData> getRemoteDateList()
    {
        return remoteDateList;
    }

     */


}
