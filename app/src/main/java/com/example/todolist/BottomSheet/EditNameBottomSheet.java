package com.example.todolist.BottomSheet;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.todolist.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class EditNameBottomSheet extends BottomSheetDialogFragment
    implements View.OnClickListener {

    private SharedPreferences sharedPreferences;
    final String PERSON_NAME = "PERSON_NAME";
    private String DEFAULT_NAME = "Buddy";
    private TextInputLayout newName;
    private Button save;

    private ItemClickListener mListener;

    public static EditNameBottomSheet newInstance() {
        return new EditNameBottomSheet();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.edit_name_bottom_sheet,
                container, false);

        newName = view.findViewById(R.id.textInputLayout2);
        save = view.findViewById(R.id.btn_saveChangeName);

        save.setOnClickListener(this);

        sharedPreferences = Objects.requireNonNull(getActivity())
                .getSharedPreferences(getActivity().getApplication().toString(), Context.MODE_PRIVATE);
        String lastName = sharedPreferences.getString(PERSON_NAME, DEFAULT_NAME);
        newName.getEditText().setText(String.valueOf(lastName));

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_saveChangeName) {
            Log.d("CHECK", "onClick: ");
            SharedPreferences.Editor preferenceEdit = sharedPreferences.edit();
            String givenString = String.valueOf(newName.getEditText().getText());
            preferenceEdit.putString(PERSON_NAME, givenString);
            preferenceEdit.apply();
            this.dismiss();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof ItemClickListener) {
            mListener = (ItemClickListener) context;
        } else {
            throw new RuntimeException(context.toString());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface ItemClickListener {
        void onItemClick(String item);
    }
}
