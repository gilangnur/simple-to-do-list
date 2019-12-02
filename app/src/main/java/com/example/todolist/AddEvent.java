package com.example.todolist;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
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
    private NotificationManagerCompat notificationManagerCompat;
    private NotificationManager mNotifyManager;

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
        mNotifyManager = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);

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
                sendNotification(mYear, mMonth, mDay);
                break;
            case R.id.btnAddImage:
                Intent intent = new Intent();
                intent.setAction(android.content.Intent.ACTION_VIEW);
                intent.setType("image/*");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
        }
    }

    public void sendNotification(int mYear, int mMonth, int mDay){
        String tahun = String.valueOf(mYear);
        String bulan = String.valueOf(mMonth);
        String hari = String.valueOf(mDay);
        notificationManagerCompat = NotificationManagerCompat.from(getContext());
        Intent intent = new Intent(getContext(), MainActivity.class);
        PendingIntent contenIntent = PendingIntent.getActivity(getContext(), 0, intent, 0);
        Intent broadcastIntent = new Intent(getContext(), NotificationReceiver.class);
        PendingIntent actionIntent = PendingIntent.getActivity(getContext(),
                0,
                broadcastIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new NotificationCompat.Builder(getContext(), app.CHANNEL_2_ID)
//                .setSmallIcon(R.drawable.ic_android)
                .setContentTitle("Deadline Set!")
                .setContentText("Deadline Date : " + hari + "/" + bulan+ "/" + tahun)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setContentIntent(contenIntent)
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setOnlyAlertOnce(true)
                .addAction(R.mipmap.ic_launcher, "Toast", actionIntent)
                .build();
        notificationManagerCompat.notify(1, notification);

    }

}
