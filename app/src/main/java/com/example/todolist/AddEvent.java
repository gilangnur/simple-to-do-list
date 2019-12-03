package com.example.todolist;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
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
import android.widget.Toast;

import com.example.todolist.model.CategoriesModel;
import com.example.todolist.model.TaskModel;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

public class AddEvent extends Fragment implements View.OnClickListener {

    private SharedPreferences sharedPreferences;
    private ArrayList<CategoriesModel> categoriesModels;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private TextInputLayout inputJudul, inputDeskripsi;
    private Button btnDeadlineData, btnAddImage, addTask;
    private TextView txtDeadlineDate;
    private Calendar calendar;
    private DatePickerDialog datePickerDialog;

    private int mYear, mMonth, mDay;
    private ImageView imgNote;
    private Uri image;

    public static final int PICK_IMAGE = 1000;
    public static final int NOTIFICATION_ID = 0;

    private NotificationManager mNotifyManager;
    private NotificationCompat.Builder mNotifyBuilder;
    private Notification myNotification;

    private int postion;

    public void setPostion(int postion) {
        this.postion = postion;
    }

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

        loadData();

        calendar = Calendar.getInstance();
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);

        inputJudul = view.findViewById(R.id.inputJudul);
        inputDeskripsi = view.findViewById(R.id.inputDeskripsi);
        btnAddImage = (Button) view.findViewById(R.id.btnAddImage);
        addTask = view.findViewById(R.id.btnAddNewTask);
        imgNote = view.findViewById(R.id.imageView4);

        txtDeadlineDate = (TextView) view.findViewById(R.id.txtDeadlineDate);
        btnDeadlineData = (Button) view.findViewById(R.id.btnDeadlineDate);

        mNotifyManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);

        btnDeadlineData.setOnClickListener(this);
        btnAddImage.setOnClickListener(this);
        addTask.setOnClickListener(this);

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
                                sendNotification(year,monthOfYear,dayOfMonth);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
                break;
            case R.id.btnAddImage:
                startGallery();
                break;
            case R.id.btnAddNewTask:
                TaskModel taskModel = new TaskModel();
                taskModel.setTitle(String.valueOf(inputJudul.getEditText().getText()));
                taskModel.setDescription(String.valueOf(inputDeskripsi.getEditText().getText()));
                taskModel.setDeadLine(txtDeadlineDate.getText().toString());
//                taskModel.setImage(image);
                if (taskModel.getTitle() != null &&
                        taskModel.getDeadLine() != null &&
                        taskModel.getDescription() != null
//                  && taskModel.getImage() != null
                ) {
                    categoriesModels.get(postion).getTasks().add(taskModel);
                    saveList();

                    fragmentManager = getActivity().getFragmentManager();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    FragmentList fragmentList = new FragmentList();
                    fragmentList.setPosition(postion);
                    fragmentTransaction.replace(R.id.list_work, fragmentList);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();

                } else {
                    Toast.makeText(getActivity().getApplicationContext()
                            , "Saved"
                            , Toast.LENGTH_LONG).show();
                }
        }
    }

    private void sendNotification(int mYearear, int mMonth, int mDay) {
        String tahun = String.valueOf(mYear);
        String bulan = String.valueOf(mMonth);
        String hari = String.valueOf(mDay);
        mNotifyBuilder = new NotificationCompat.Builder(getActivity())
                .setContentTitle("Deadline Set!")
                .setContentText("Deadline Date : " + hari + "/" + bulan+ "/" + tahun)
                .setSmallIcon(R.drawable.ic_event_note_black_24dp);
        myNotification = mNotifyBuilder.build();
        mNotifyManager.notify(NOTIFICATION_ID, myNotification);
    }

    private void startGallery() {
        Intent cameraIntent = new Intent(Intent.ACTION_GET_CONTENT);
        cameraIntent.setType("image/*");
        if (cameraIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(cameraIntent, PICK_IMAGE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE) {
            imgNote.setImageURI(data.getData());
        }
    }


    private void saveList() {
        sharedPreferences = Objects.requireNonNull(getActivity())
                .getSharedPreferences(getActivity().getApplication().toString(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(categoriesModels);
        editor.putString("LIST", json);
        editor.apply();
    }

    private void loadData() {
        sharedPreferences = Objects.requireNonNull(getActivity())
                .getSharedPreferences(getActivity().getApplication().toString(), Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("LIST", null);
        Type type = new TypeToken<ArrayList<CategoriesModel>>() {
        }.getType();
        categoriesModels = gson.fromJson(json, type);
        if (categoriesModels == null) {
            categoriesModels = new ArrayList<>();
        }
    }


}
