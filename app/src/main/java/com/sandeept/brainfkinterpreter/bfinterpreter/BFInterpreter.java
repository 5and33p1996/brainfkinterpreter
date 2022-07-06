package com.sandeept.brainfkinterpreter.bfinterpreter;

import java.util.Stack;

public class BFInterpreter {
    private int numCells = 30000;

    public boolean setNumCells(int numCells){

        if(numCells <= 10000000){
            this.numCells = numCells;
            return true;
        }

        return false;
    }

    public BFResult run(char[] inputs, char[] code){

        byte[] cells = new byte[numCells];
        StringBuilder result = new StringBuilder();

        int cellPointer = 0, codePointer = 0, inputPointer = 0, numOpenLoops = 0;
        Stack<Integer> loopStack = new Stack<>();
        boolean skipToCloseLoop = false;

        while(codePointer < code.length){

            if(code[codePointer] == '+' && !skipToCloseLoop){

                cells[cellPointer]++;
            }

            else if(code[codePointer] == '-' && !skipToCloseLoop){

                cells[cellPointer]--;
            }

            else if(code[codePointer] == '>' && !skipToCloseLoop){

                cellPointer = (cellPointer + 1) % (numCells);
            }

            else if(code[codePointer] == '<' && !skipToCloseLoop){

                if(cellPointer == 0){

                    cellPointer = numCells - 1;
                }

                cellPointer--;
            }

            else if(code[codePointer] == '.' && !skipToCloseLoop){

                result.append((char) cells[cellPointer]);
            }

            else if(code[codePointer] == ',' && !skipToCloseLoop){

                if(inputPointer != inputs.length){

                    cells[cellPointer] = (byte)inputs[inputPointer];
                    inputPointer++;
                }
            }

            else if(code[codePointer] == '['){

                codePointer++;

                if(skipToCloseLoop){
                    numOpenLoops++;
                    continue;
                }

                if(cells[cellPointer] == 0){
                    skipToCloseLoop = true;
                    continue;
                }

                loopStack.push(codePointer);
                continue;
            }

            else if(code[codePointer] == ']'){

                if(skipToCloseLoop){

                    if(numOpenLoops != 0){
                        numOpenLoops--;
                        codePointer++;
                    }
                    else{
                        codePointer++;
                        skipToCloseLoop = false;
                    }
                    continue;
                }

                if(loopStack.isEmpty()){

                    return new BFResult(null, BFErrorCodes.NoStartOfLoop, codePointer);
                }

                if(cells[cellPointer] != 0){

                    codePointer = loopStack.peek();
                    continue;
                }

                loopStack.pop();
            }

            codePointer++;
        }

        if(!loopStack.isEmpty()){

            return new BFResult(null, BFErrorCodes.NoCloseOfLoop, codePointer);
        }

        return new BFResult(result.toString(), BFErrorCodes.Success, codePointer);
    }
}
