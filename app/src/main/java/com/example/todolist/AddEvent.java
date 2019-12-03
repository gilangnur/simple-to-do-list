package com.example.todolist;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
//import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class AddEvent extends Fragment implements View.OnClickListener {

    private TextInputLayout inputJudul, inputDeskripsi;
    private Button btnDeadlineData, btnAddImage;
    private TextView txtDeadlineDate;
    private Calendar calendar;
    private DatePickerDialog datePickerDialog;
    private int mYear, mMonth, mDay;
    private ImageView img;
    public static final int PICK_IMAGE = 1000;

    public AddEvent() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        ;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_event, container, false);

        calendar = Calendar.getInstance();
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);

        inputJudul =  view.findViewById(R.id.inputJudul);
        inputDeskripsi = view.findViewById(R.id.inputDeskripsi);
        btnAddImage = (Button) view.findViewById(R.id.btnAddImage);

        txtDeadlineDate = (TextView) view.findViewById(R.id.txtDeadlineDate);
        btnDeadlineData = (Button) view.findViewById(R.id.btnDeadlineDate);

        btnDeadlineData.setOnClickListener(this);
        btnAddImage.setOnClickListener(this);

        return view;

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnDeadlineDate:
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
        switch (pickImage) {
            case PICK_IMAGE:
                img.setImageURI(data.getData());
                break;
        }
    }


}
