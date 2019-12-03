package com.example.todolist.BottomSheet;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.todolist.R;
import com.example.todolist.model.CategoriesModel;
import com.example.todolist.model.TaskModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AddCategoriesBottomSheet extends BottomSheetDialogFragment
        implements View.OnClickListener{

    private EditNameBottomSheet.ItemClickListener itemClickListener;
    private ArrayList<CategoriesModel> categoriesModels;
    private SharedPreferences sharedPreferences;

    private TextInputLayout addCategories;
    private Button saveAddCategories;
    private final String LIST = "LIST";


    public static AddCategoriesBottomSheet newInstance() {
        return new AddCategoriesBottomSheet();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.add_categories_botttom_sheet,
                container, false);

        addCategories = view.findViewById(R.id.textInputLayout);

        saveAddCategories = view.findViewById(R.id.btn_saveAddCatories);
        saveAddCategories.setOnClickListener(this);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof EditNameBottomSheet.ItemClickListener) {
            itemClickListener = (EditNameBottomSheet.ItemClickListener) context;
        } else {
            throw new RuntimeException(context.toString());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        itemClickListener = null;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_saveAddCatories) {
            if (addCategories.getEditText().getText() != null) {
                loadData();
                categoriesModels.add(
                        new CategoriesModel(
                                String.valueOf(addCategories.getEditText().getText())
                                , new ArrayList<TaskModel>()));
                saveList();
                this.dismiss();
            } else {
                Toast.makeText(getActivity().getApplicationContext()
                        , "Kogong"
                        ,Toast.LENGTH_LONG).show();
            }
        }
    }

    private void saveList() {
        sharedPreferences = Objects.requireNonNull(getActivity())
                .getSharedPreferences(getActivity().getApplication().toString(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(categoriesModels);
        editor.putString(LIST, json);
        editor.apply();
    }

    private void loadData() {
        sharedPreferences = Objects.requireNonNull(getActivity())
                .getSharedPreferences(getActivity().getApplication().toString(), Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(LIST, null);
        Type type = new TypeToken<ArrayList<CategoriesModel>>() {}.getType();
        categoriesModels = gson.fromJson(json, type);
        if (categoriesModels == null) {
            categoriesModels = new ArrayList<>();
        }
    }


}
