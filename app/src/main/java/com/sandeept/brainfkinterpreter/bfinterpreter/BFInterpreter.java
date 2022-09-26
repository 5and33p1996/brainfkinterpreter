package com.sandeept.brainfkinterpreter.bfinterpreter;

import java.util.Stack;

public class BFInterpreter {
    private int numCells = 30000;
    private int cellSize = 8;
    private long minValue, maxValue;

    public boolean setNumCells(int numCells){

        if(numCells <= 10000000){
            this.numCells = numCells;
            return true;
        }

        return false;
    }

    public int setCellSize(int cellSize){

        this.cellSize = cellSize;

        if(cellSize == 8){

            minValue = -128;
            maxValue = 127;
        }
        else if(cellSize == 16){

            minValue = -32768;
            maxValue = 32767;
        }
        else if(cellSize == 32){

            minValue = -2147483648;
            maxValue = 2147483647;
        }
        else if(cellSize == 64){

            minValue = Long.MIN_VALUE;
            maxValue = Long.MAX_VALUE;
        }
        else{
            return -1;
        }


        return 1;
    }

    private long wrappedIncrement(long a){

        if(a == maxValue){
            a = minValue;
        }
        else{
            a++;
        }

        return a;
    }

    private long wrappedDecrement(long a){

        if(a == minValue){
            a = maxValue;
        }
        else{
            a--;
        }

        return a;
    }

    public BFResult run(char[] inputs, char[] code){

        long[] cells = new long[numCells];

        StringBuilder result = new StringBuilder();
        int cellPointer = 0, codePointer = 0, inputPointer = 0, numOpenLoops = 0;
        Stack<Integer> loopStack = new Stack<>();
        boolean skipToCloseLoop = false;

        while(codePointer < code.length){

            if(code[codePointer] == '+' && !skipToCloseLoop){

                cells[cellPointer] = wrappedIncrement(cells[cellPointer]);
            }

            else if(code[codePointer] == '-' && !skipToCloseLoop){

                cells[cellPointer] = wrappedDecrement(cells[cellPointer]);
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

        if(!loopStack.isEmpty() || skipToCloseLoop){

            return new BFResult(null, BFErrorCodes.NoCloseOfLoop, codePointer);
        }

        return new BFResult(result.toString(), BFErrorCodes.Success, codePointer);
    }
}
