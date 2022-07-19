package com.sandeept.brainfkinterpreter.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.textfield.TextInputEditText;
import com.sandeept.brainfkinterpreter.R;
import com.sandeept.brainfkinterpreter.bfinterpreter.BFInterpreter;
import com.sandeept.brainfkinterpreter.bfinterpreter.BFResult;
import com.sandeept.brainfkinterpreter.viewmodel.CodeDataViewModel;

public class CodeFragment extends Fragment implements View.OnClickListener {

    private TextInputEditText codeField;
    private TextInputEditText inputField;
    private CodeDataViewModel codeDataViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_code, container, false);

        codeField = view.findViewById(R.id.code_field);
        inputField = view.findViewById(R.id.input_field);

        codeField.setShowSoftInputOnFocus(false);

        view.findViewById(R.id.plus_button).setOnClickListener(this);
        view.findViewById(R.id.minus_button).setOnClickListener(this);
        view.findViewById(R.id.left_shift_button).setOnClickListener(this);
        view.findViewById(R.id.right_shift_button).setOnClickListener(this);
        view.findViewById(R.id.start_loop_button).setOnClickListener(this);
        view.findViewById(R.id.end_loop_button).setOnClickListener(this);
        view.findViewById(R.id.dot_button).setOnClickListener(this);
        view.findViewById(R.id.comma_button).setOnClickListener(this);
        view.findViewById(R.id.run_button).setOnClickListener(this);
        view.findViewById(R.id.backspace_button).setOnClickListener(this);
        view.findViewById(R.id.space_button).setOnClickListener(this);
        view.findViewById(R.id.enter_button).setOnClickListener(this);

        codeDataViewModel = new ViewModelProvider(requireActivity()).get(CodeDataViewModel.class);


        if(codeDataViewModel.getCode() != null){

            codeField.setText(codeDataViewModel.getCode());
        }

        if(codeDataViewModel.getInput() != null){

            inputField.setText(codeDataViewModel.getInput());
        }

        return view;
    }

    @Override
    public void onStop(){

        super.onStop();

        if(codeField.getText() != null) {
            codeDataViewModel.setCode(codeField.getText().toString());
        }

        if(inputField.getText() != null){
            codeDataViewModel.setInput(inputField.getText().toString());
        }
    }

    private String updateCodeString(String s){

        int cursorPosition = codeField.getSelectionEnd();
        String code;

        if(codeField.getText() == null || codeField.getText().length() == 0){

            return s;
        }

        code = codeField.getText().toString();

        return code.substring(0, cursorPosition) + s + code.substring(cursorPosition);
    }

    @Override
    public void onClick(View view){

        codeField.requestFocus();

        String ch = null;

        switch (view.getId()){

            case R.id.plus_button:
                ch = "+";
                break;

            case R.id.minus_button:
                ch = "-";
                break;

            case R.id.left_shift_button:
                ch = "<";
                break;

            case R.id.right_shift_button:
                ch = ">";
                break;

            case R.id.start_loop_button:
                ch = "[";
                break;

            case R.id.end_loop_button:
                ch = "]";
                break;

            case R.id.dot_button:
                ch = ".";
                break;

            case R.id.comma_button:
                ch = ",";
                break;

            case R.id.enter_button:
                ch = "\n";
                break;

            case R.id.space_button:
                ch = " ";
                break;

            case R.id.backspace_button:

                if(codeField.getText() == null || codeField.getText().length() == 0){
                    break;
                }

                int cursorPosition = codeField.getSelectionEnd();
                String curCode = codeField.getText().toString();

                if(cursorPosition == 0){
                    break;
                }

                curCode = curCode.substring(0, cursorPosition - 1) + curCode.substring(cursorPosition);

                codeField.setText(curCode);
                codeField.setSelection(cursorPosition - 1);
                break;

            case R.id.run_button:

                BFInterpreter interpreter = new BFInterpreter();

                char[] inputs = null, code;

                if(inputField.getText() != null){

                    inputs = inputField.getText().toString().toCharArray();
                }

                if(codeField.getText() == null || codeField.getText().length() == 0){

                    Toast.makeText(getContext(), "No Code!", Toast.LENGTH_SHORT).show();
                    return;
                }

                code = codeField.getText().toString().toCharArray();

                BFResult result = interpreter.run(inputs, code);

                codeDataViewModel.setOutput(result);

                ViewPager2 viewPager2 = getActivity().findViewById(R.id.view_pager);
                viewPager2.setCurrentItem(1);
        }

        if(ch != null) {
            String result = updateCodeString(ch);
            codeField.setText(result);
            codeField.setSelection(result.length());
        }
    }
}
