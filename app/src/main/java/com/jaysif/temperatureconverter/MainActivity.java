package com.jaysif.temperatureconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView appTitleText = findViewById(R.id.appTitleLabel);
        appTitleText.setText("Temperature Converter");
        TextView degreeLabel = findViewById(R.id.degreeLbl);
        degreeLabel.setText("Degrees:");
        TextView tempTypeFromLabel = findViewById(R.id.tempTypeFromLbl);
        tempTypeFromLabel.setText("Convert From");
        TextView tempTypetoLabel = findViewById(R.id.tempTypeToLbl);
        tempTypetoLabel.setText("Convert To");
        TextView resultTextLabel = findViewById(R.id.resultTextLbl);
        resultTextLabel.setText("Result");
        Button convertButton = findViewById(R.id.convButton);
        convertButton.setText("Convert");
    }

    public void onClrBtnClick(View view) {
        TextView degreesInput = findViewById(R.id.editDegreeText);
        TextView result = findViewById(R.id.finalResultTxtLbl);
        degreesInput.setText("");
        result.setText("");
    }

    public void onBtnClick(View view) {

        TextView degreesInput = findViewById(R.id.editDegreeText);
        Spinner tempfromS = findViewById(R.id.tempTypeFromSpinner);
        Spinner tempToS = findViewById(R.id.tempTypeToSpinner);

        TextView result = findViewById(R.id.finalResultTxtLbl);
        String tempF = tempfromS.getSelectedItem().toString();
        String tempT = tempToS.getSelectedItem().toString();

        result.setText(convertTemp(Float.valueOf(degreesInput.getText().toString()), tempF, tempT));
    }

    public String convertTemp(float degrees, String tempfromType, String tempToType) {

        String C = " °C", F = " °F", K = "K";
        DecimalFormat df = new DecimalFormat("#.###");
        float resultTemp = 0, resultTempFinal;
        StringBuilder formattedTemp = new StringBuilder();
        if (tempfromType == tempToType) { return String.valueOf(degrees);}

        if (tempfromType.equals("Celsius")) {

            switch (tempToType) {
                case "Kelvin":
                    resultTemp = (degrees + 273.15f);
                    formattedTemp.append(resultTemp).append(K);
                    break;
                case "Fahrenheit":
                    resultTemp = (32 + (degrees * (float)9/5));
                    resultTempFinal = Float.parseFloat(df.format(resultTemp - (int)resultTemp)) + (int)resultTemp;
                    formattedTemp.append(resultTempFinal).append(F);
                    break;
                default:
                    break;
            }
        }
        else if (tempfromType.equals("Kelvin")) {

            switch (tempToType) {
                case "Celsius":
                    resultTemp = degrees - 273.15f;
                    formattedTemp.append(resultTemp).append(C);
                    break;
                case "Fahrenheit":
                    resultTemp = (((float)9/5 * (degrees - 273.15f)) + 32);
                    resultTempFinal = Float.parseFloat(df.format(resultTemp - (int)resultTemp)) + (int)resultTemp;
                    formattedTemp.append(resultTempFinal).append(F);
                    break;
                default:
                    break;
            }
        }
        else {
            switch (tempToType) {
                case "Celsius":
                    resultTemp = ((float)5/9 * (degrees - 32));
                    resultTempFinal = Float.parseFloat(df.format(resultTemp - (int)resultTemp)) + (int)resultTemp;
                    formattedTemp.append(resultTempFinal).append(C);
                    break;
                case "Kelvin":
                    resultTemp = (((float)5/9 * (degrees - 32)) + 273.15f);
                    resultTempFinal = Float.parseFloat(df.format(resultTemp - (int)resultTemp)) + (int)resultTemp;
                    formattedTemp.append(resultTempFinal).append(K);
                    break;
                default:
                    break;
            }

        }

        return formattedTemp.toString();
    }

}