package com.allanguan.distanceconverter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText inputView;
    private TextView outputView;
    private TextView historyView;
    private TextView inputText;
    private TextView outputText;
    private boolean mToKm;
    private StringBuilder sb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputText = findViewById(R.id.textView2);
        outputText = findViewById(R.id.textView3);
        inputView = findViewById(R.id.inputText);
        historyView = findViewById(R.id.historyText);
        outputView = findViewById(R.id.outputText);
        mToKm = true;
        sb = new StringBuilder();
        historyView.setMovementMethod(new ScrollingMovementMethod());
    }

    public void clearClicked(View v){
        historyView.setText("");
        sb = new StringBuilder();
    }

    public void radioClicked(View v){
        switch (v.getId()) {
            case R.id.mtokButton:
                mToKm = true;
                outputText.setText("Kilometers Value:");
                inputText.setText("Miles Value:");
                break;
            case R.id.ktomButton:
                mToKm = false;
                inputText.setText("Kilometers Value:");
                outputText.setText("Miles Value:");
                break;
        }
    }

    public void convertClicked(View v){
        String inputText = inputView.getText().toString();
        double inputDouble = Double.parseDouble(inputText.toString());
        double result = Math.round(calculation(inputDouble) * 10.0)/10.0;

        outputView.setText(String.valueOf(result));
        inputView.setText("");

        if(mToKm){
            sb.insert(0, inputDouble + " Mi ==> " + result + " Km\n");
        }
        else{
            sb.insert(0,inputDouble + " Km ==> " + result + " Mi\n");
        }
        historyView.setText(sb.toString());
    }

    private double calculation(double in){
        if(mToKm){
            return in * 1.60934;
        }
        else{
            return in * 0.621371;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        outState.putString("HISTORY", historyView.getText().toString());
        outState.putString("OUTPUT", outputView.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        historyView.setText(savedInstanceState.getString("HISTORY"));
        sb.append(savedInstanceState.getString("HISTORY"));
        outputView.setText(savedInstanceState.getString("OUTPUT"));
//        Log.d(TAG, "onRestoreInstanceState: ");
    }
}