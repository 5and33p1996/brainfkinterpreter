package com.sandeept.brainfkinterpreter.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.sandeept.brainfkinterpreter.R;
import com.sandeept.brainfkinterpreter.viewmodel.CodeDataViewModel;

public class OutputFragment extends Fragment {

    private TextView outputField;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_output, container, false);

        outputField = view.findViewById(R.id.output_field);

        return view;
    }

    @Override
    public void onResume(){

        super.onResume();

        CodeDataViewModel codeDataViewModel = new ViewModelProvider(requireActivity()).get(CodeDataViewModel.class);

        if(codeDataViewModel.getOutput() != null){

            outputField.setText(codeDataViewModel.getOutput());
        }
    }
}
