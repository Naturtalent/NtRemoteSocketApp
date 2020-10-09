package com.example.ntremotesocketapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.ListFragment;

import java.util.List;

import it.naturtalent.databinding.RemoteData;

/*
    Benutzerdefinierter Adapter versorgt das ListFragment mit der Socketdaten
 */
//public class SocketAdapter extends ArrayAdapter<String>
public class SocketAdapter extends ArrayAdapter<String>
{
    private final Context context;
    private List<RemoteData> remoteDataList;


    static String [] values = new String []{"TEST"};

    /*
    public SocketAdapter(@NonNull Context context)
    {
        // param ist dummy
        this(context, values);
    }
     */

    /*
    public SocketAdapter(Context context, List<RemoteData> remoteDataList)
    {
        this(context, values);
        this.remoteDataList = remoteDataList;
    }

     */

    public SocketAdapter(Context context, String [] values)
    {
        super(context, R.layout.rowlayout, (String[]) values);
        this.context = context;
    }

    public void setRemoteDataList(List<RemoteData> remoteDataList)
    {
        this.remoteDataList = remoteDataList;
    }

    public List<RemoteData> getRemoteDataList()
    {
        return remoteDataList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View rowView = null;

        if((remoteDataList != null) && (!remoteDataList.isEmpty()))
        {
                LayoutInflater inflater = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                rowView = inflater.inflate(R.layout.rowlayout, parent, false);

                TextView textView = (TextView) rowView.findViewById(R.id.label);
                RemoteData remoteData = remoteDataList.get(position);
                textView.setText(remoteData.getName());



                ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);


            Log.d("check", "get view");

        }

        /*
        if((remoteDataList != null) && (!remoteDataList.isEmpty()))
        {
            RemoteData remoteData = remoteDataList.get(position);
            textView.setText(remoteData.getName());
        }

         */

        /*
        // Change the icon for Windows and iPhone
        String s = values[position];
        if (s.startsWith("Windows7") || s.startsWith("iPhone")
                || s.startsWith("Solaris"))
        {
            imageView.setImageResource(R.drawable.ic_launcher);
        } else
        {
            imageView.setImageResource(R.drawable.ic_launcher_round);
        }

         */

        return rowView;
    }



    /*
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.rowlayout, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.label);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        textView.setText(values[position]);


        // Change the icon for Windows and iPhone
        String s = values[position];
        if (s.startsWith("Windows7") || s.startsWith("iPhone")
                || s.startsWith("Solaris"))
        {
            imageView.setImageResource(R.drawable.ic_launcher);
        } else
        {
            imageView.setImageResource(R.drawable.ic_launcher_round);
        }

        return rowView;
    }

     */

}
