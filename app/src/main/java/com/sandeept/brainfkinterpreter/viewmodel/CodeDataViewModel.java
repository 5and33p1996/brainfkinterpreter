package com.sandeept.brainfkinterpreter.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sandeept.brainfkinterpreter.bfinterpreter.BFResult;

public class CodeDataViewModel extends ViewModel {

    MutableLiveData<String> code = new MutableLiveData<>();
    MutableLiveData<String> input = new MutableLiveData<>();
    MutableLiveData<BFResult> output = new MutableLiveData<>();

    public String getCode() {
        return code.getValue();
    }

    public void setCode(String userCode) {
        code.setValue(userCode);
    }

    public String getInput() {
        return input.getValue();
    }

    public void setInput(String inputArgs) {
        input.setValue(inputArgs);
    }

    public BFResult getOutput() {
        return output.getValue();
    }

    public void setOutput(BFResult codeOutput) {
        output.setValue(codeOutput);
    }
}
