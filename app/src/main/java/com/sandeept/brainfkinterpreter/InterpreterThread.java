package com.sandeept.brainfkinterpreter;

import android.os.Handler;
import android.os.Message;

import com.sandeept.brainfkinterpreter.bfinterpreter.BFInterpreter;
import com.sandeept.brainfkinterpreter.bfinterpreter.BFResult;

public class InterpreterThread extends Thread {

    Handler uiHandler;
    char[] inputs, code;
    int numCells, cellSize;

    public InterpreterThread(Handler handler){

        uiHandler = handler;
    }

    public void setData(char[] inputs, char[] code, int numCells, int cellSize){

        this.inputs = inputs;
        this.code = code;
        this.numCells = numCells;
        this.cellSize = cellSize;
    }

    @Override
    public void run() {
        super.run();

        BFInterpreter interpreter = new BFInterpreter();

        interpreter.setCellSize(cellSize);
        interpreter.setNumCells(numCells);

        BFResult result = interpreter.run(inputs, code);

        Message message = Message.obtain(uiHandler, 0, result);
        uiHandler.sendMessage(message);
    }
}
