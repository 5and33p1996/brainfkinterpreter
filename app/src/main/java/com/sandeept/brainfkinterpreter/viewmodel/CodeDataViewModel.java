package com.sandeept.brainfkinterpreter.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CodeDataViewModel extends ViewModel {

    MutableLiveData<String> code = new MutableLiveData<>();
    MutableLiveData<String> input = new MutableLiveData<>();
    MutableLiveData<String> output = new MutableLiveData<>();

    public boolean hasUserData(){

        return code.getValue() != null || input.getValue() != null;
    }

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

    public String getOutput() {
        return output.getValue();
    }

    public void setOutput(String codeOutput) {
        output.setValue(codeOutput);
    }
}
