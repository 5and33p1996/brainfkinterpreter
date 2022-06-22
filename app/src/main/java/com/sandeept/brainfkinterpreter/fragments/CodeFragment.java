package com.sandeept.brainfkinterpreter.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.sandeept.brainfkinterpreter.R;

public class CodeFragment extends Fragment implements View.OnClickListener {

    TextInputEditText editText;
    StringBuilder stringBuilder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_code, container, false);

        editText = view.findViewById(R.id.code_field);

        view.findViewById(R.id.plus_button).setOnClickListener(this);
        view.findViewById(R.id.minus_button).setOnClickListener(this);
        view.findViewById(R.id.left_shift_button).setOnClickListener(this);
        view.findViewById(R.id.right_shift_button).setOnClickListener(this);
        view.findViewById(R.id.start_loop_button).setOnClickListener(this);
        view.findViewById(R.id.end_loop_button).setOnClickListener(this);
        view.findViewById(R.id.dot_button).setOnClickListener(this);
        view.findViewById(R.id.comma_button).setOnClickListener(this);

        stringBuilder = new StringBuilder();

        return view;
    }

    @Override
    public void onClick(View view){

        editText.requestFocus();

        if(editText.getText() != null){

            stringBuilder.append(editText.getText().toString());
        }

        switch (view.getId()){

            case R.id.plus_button:
                editText.setText(stringBuilder.append("+"));
                break;

            case R.id.minus_button:
                editText.setText(stringBuilder.append("-"));
                break;

            case R.id.left_shift_button:
                editText.setText(stringBuilder.append("<"));
                break;

            case R.id.right_shift_button:
                editText.setText(stringBuilder.append(">"));
                break;

            case R.id.start_loop_button:
                editText.setText(stringBuilder.append("["));
                break;

            case R.id.end_loop_button:
                editText.setText(stringBuilder.append("]"));
                break;

            case R.id.dot_button:
                editText.setText(stringBuilder.append("."));
                break;

            case R.id.comma_button:
                editText.setText(stringBuilder.append(","));
                break;
        }

        editText.setSelection(stringBuilder.length());
    }
}
