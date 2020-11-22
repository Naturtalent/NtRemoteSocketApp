package com.example.ntremotesocketapp;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import it.naturtalent.common.logger.Log;
import it.naturtalent.common.threadpool.FetchDataUseCase;
import it.naturtalent.common.threadpool.ThreadPool;
import it.naturtalent.databinding.RemoteData;

public class SecondFragment extends Fragment implements FetchDataUseCase.Listener
{
    private static final String TAG = "SecondFragment";
    private static final int SPAN_COUNT = 2;

    private FetchDataUseCase mFetchDataUseCase;

    private InitializeDialogFragment dialogFragment;

    private View rootView;
    protected RecyclerView mRecyclerView;
    protected RecyclerView.LayoutManager mLayoutManager;
    //protected LayoutManagerType mCurrentLayoutManagerType;

    private List<RemoteData> socketData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
       // rootView =  inflater.inflate(R.layout.fragment_second, container, false);
        rootView =  inflater.inflate(R.layout.recycler_view_frag, container, false);
        rootView.setTag(TAG);

        //rootView = inflater.inflate(R.layout.recycler_view_activity, container, false);

        RemoteDataUtils remoteDataUtils = new RemoteDataUtils(this, this );
        remoteDataUtils.getSocketData();

        /*
        SocketViewAdapter mAdapter = new SocketViewAdapter(socketData);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        mRecyclerView.setAdapter(mAdapter);

         */




/*
        if(socketData == null)
        {
            mFetchDataUseCase = new ThreadPool().getFetchDataUseCase();
            mFetchDataUseCase.registerListener(this);
            mFetchDataUseCase.fetchData();
            showDialog();
        }
        */

        return rootView;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

/*
        view.findViewById(R.id.button_second).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                showDialog();


            }
        });

 */


        /*
        view.findViewById(R.id.button_second).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_recyclerViewFragment);




            }
        });

         */
    }

    /*
    private void showDialog()
    {
        dialogFragment = InitializeDialogFragment.newInstance(R.string.alert_dialog_two_buttons_title);

        dialogFragment.show(getActivity().getSupportFragmentManager(), "dialog");
    }

     */

    @Override
    public void onDataFetched(List<RemoteData> data)
    {
        socketData = data;

        android.util.Log.d("ThreadPoster", "Load Data OK data: " + data.get(0).getName());


        SocketViewAdapter mAdapter = new SocketViewAdapter(socketData);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        mRecyclerView.setAdapter(mAdapter);

        //mLayoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT);

        mLayoutManager = new LinearLayoutManager(getActivity());
       // mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    @Override
    public void onDataFetchFailed()
    {
        android.util.Log.d("ThreadPoster", "Load Data Failed");
    }

    /*
    void showDialog()
    {
        DialogFragment newFragment = MyAlertDialogFragment
                .newInstance(R.string.alert_dialog_two_buttons_title);

        newFragment.show(getActivity().getSupportFragmentManager(), "dialog");
    }

     */

    public static class MyAlertDialogFragment extends DialogFragment
    {

        public static MyAlertDialogFragment newInstance(int title)
        {
            MyAlertDialogFragment frag = new MyAlertDialogFragment();
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
                    .setPositiveButton(R.string.alert_dialog_ok,
                            new DialogInterface.OnClickListener()
                            {
                                public void onClick(DialogInterface dialog,
                                                    int whichButton)
                                {
                                    Log.i("FragmentAlertDialog", "Positive click!");
                                }
                            })
                    .setNegativeButton(R.string.alert_dialog_cancel,
                            new DialogInterface.OnClickListener()
                            {
                                public void onClick(DialogInterface dialog,
                                                    int whichButton)
                                {
                                    Log.i("FragmentAlertDialog", "Negative click!");
                                }
                            }).create();
        }
    }


}