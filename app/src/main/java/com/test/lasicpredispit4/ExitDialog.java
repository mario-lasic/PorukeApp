package com.test.lasicpredispit4;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class ExitDialog extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setIconAttribute(android.R.attr.alertDialogIcon);
        builder.setTitle(R.string.sto_zelite);
        builder.setMessage(R.string.odabir_izlaska);
        builder.setPositiveButton("EXIT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getActivity().finish();
            }
        });
        builder.setNegativeButton("Odustani", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        return  alertDialog;

    }
}
