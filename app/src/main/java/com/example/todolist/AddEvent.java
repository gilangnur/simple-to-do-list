package com.example.todolist;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;

public class AddEvent extends Fragment implements View.OnClickListener {

    private EditText inputJudul, inputDeskripsi;
    private Button btnDeadlineData, btnAddImage;
    private TextView txtDeadlineDate;
    private Calendar c;
    private DatePickerDialog dpd;
    private int mYear,mMonth,mDay;
    private ImageView img;
    public static final int PICK_IMAGE = 1000;

    public AddEvent() {

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        inputJudul = (EditText) getActivity().findViewById(R.id.inputJudul);
        inputDeskripsi = (EditText) getActivity().findViewById(R.id.inputDeskripsi);
        btnDeadlineData = (Button) getActivity().findViewById(R.id.btnDeadlineDate);
        btnAddImage = (Button) getActivity().findViewById(R.id.btnAddImage);
        txtDeadlineDate = (TextView) getActivity().findViewById(R.id.txtDeadlineDate);

        btnDeadlineData.setOnClickListener(this);
        btnAddImage.setOnClickListener(this);

        return inflater.inflate(R.layout.fragment_add_event, container, false);
    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnDeadlineDate:
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                txtDeadlineDate.setText(dayOfMonth + "/" + (monthOfYear) + "/" + year);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
                break;
            case R.id.btnAddImage:
                Intent intentImage = new Intent(Intent.ACTION_PICK);
                intentImage.setType("image/*");
                startActivityForResult(PICK_IMAGE, intentImage);
                break;
        }
    }

    private void startActivityForResult(int pickImage, Intent data) {
        switch(pickImage) {
            case PICK_IMAGE:
                img.setImageURI(data.getData());
                break;
        }
    }

}
