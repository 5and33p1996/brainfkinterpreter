package com.sandeept.brainfkinterpreter.bfinterpreter;

public class BFResult {

    public String result;
    public BFErrorCodes errorCode;
    public int position;

    BFResult(String result, BFErrorCodes errorCode, int position){

        this.result = result;
        this.errorCode = errorCode;
        this.position = position;
    }
}
