package com.example.ntremotesocketapp;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import it.naturtalent.common.logger.Log;

public class InitializeDialogFragment extends DialogFragment
{

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
                                Log.i("FragmentAlertDialog", "Negative click!");
                                //dialog.dismiss();
                            }
                        })
                .create();
    }
}
