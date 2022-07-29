package com.sandeept.brainfkinterpreter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SettingsActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    EditText numCellsField;
    RadioGroup cellSizeGroup;
    RadioButton eightBitButton;
    RadioButton sixteenBitButton;
    RadioButton thirtytwoBitButton;
    RadioButton sixtyfourBitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        numCellsField = findViewById(R.id.num_cells_field);
        cellSizeGroup = findViewById(R.id.radio_group);
        eightBitButton = findViewById(R.id.eight_bit_radio);
        sixteenBitButton = findViewById(R.id.sixteen_bit_radio);
        thirtytwoBitButton = findViewById(R.id.thirtytwo_bit_radio);
        sixtyfourBitButton = findViewById(R.id.sixtyfour_bit_radio);

        sharedPreferences = getSharedPreferences(getString(R.string.settings_pref), Context.MODE_PRIVATE);
        numCellsField.setText(Integer.toString(sharedPreferences.getInt(getString(R.string.num_cells_key), 30000)));

        checkCellSizeRadioButton();
    }

    @Override
    protected void onStop(){
        super.onStop();

        int numCells = numCellsField.getText().toString().length() == 0 ? 30000 :
                Integer.parseInt(numCellsField.getText().toString());

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(getString(R.string.num_cells_key), numCells);
        editor.putInt(getString(R.string.cell_size_key), getCellSizeSelection());

        editor.apply();
    }

    private int getCellSizeSelection(){

        switch(cellSizeGroup.getCheckedRadioButtonId()){

            case R.id.eight_bit_radio:
                return 8;

            case R.id.sixteen_bit_radio:
                return 16;

            case R.id.thirtytwo_bit_radio:
                return 32;

            default:
                return 64;
        }
    }

    private void checkCellSizeRadioButton(){

        int cellSize = sharedPreferences.getInt(getString(R.string.cell_size_key), 8);

        switch(cellSize){

            case 8:
                eightBitButton.setChecked(true);
                break;

            case 16:
                sixteenBitButton.setChecked(true);
                break;

            case 32:
                thirtytwoBitButton.setChecked(true);
                break;

            case 64:
                sixtyfourBitButton.setChecked(true);
                break;
        }
    }
}