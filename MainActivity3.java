package com.example.collegeapp_amenahussain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.AsynchronousChannel;
import java.util.concurrent.CompletableFuture;

public class MainActivity3 extends AppCompatActivity {

    TextView bmr, bmi, bmrdes, bmides;
    EditText gender, weight, height, age;
    String g, w, h, a;
    Double wd, hd, ad, finalbmi, finalbmr, cm, kg, x, y;
    Button previous, next, calc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        previous = findViewById(R.id.button_previous3);
        next = findViewById(R.id.button_next3);
        calc = findViewById(R.id.calculate_button);
        bmr = findViewById(R.id.bmr_txt);
        bmi = findViewById(R.id.bmi_txt);
        bmrdes = findViewById(R.id.bmrdes_txt);
        bmides = findViewById(R.id.bmides_txt);
        gender = findViewById(R.id.gender_edittext);
        weight = findViewById(R.id.weight_edittext);
        height = findViewById(R.id.height_edittext);
        age = findViewById(R.id.age_edittext);


        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity3.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity3.this, MainActivity4.class);
                startActivity(intent);
            }
        });

        gender.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                g = gender.getText().toString();
            }
        });

        weight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                w = weight.getText().toString();
            }
        });

        height.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                h = height.getText().toString();
            }
        });

        age.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                a = age.getText().toString();
            }
        });


        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hd = Double.valueOf(h);
                cm = hd*2.54;
                wd = Double.valueOf(w);
                kg = wd*.453592;
                ad = Double.valueOf(a);


                if(g.equalsIgnoreCase("M")){
                    y = (10*kg)+(6.25*cm)-(5*ad)+5;

                } else if(g.equalsIgnoreCase("F")){
                    y = (10*kg)+(6.25*cm)-(5*ad)-161;
                }

                x = ((wd)/(hd*hd))*703.0;
                finalbmi = Double.valueOf(Math.round(x*100)/100);

                finalbmr = Double.valueOf(Math.round(y*100)/100);



                bmr.setText("BMR: " + finalbmr);
                bmi.setText("BMI: "+ finalbmi);


                bmides.setText("A person's Body Mass Index (BMI) is a measure of body fat based on height and weight. A healthy range for BMI is 18.5 to 24.9!");
                bmrdes.setText("A person's Basal Metabolic Rate (BMR) is the total number of calories their body needs to perform fundamental functions, such as blood circulation, breathing, and cell production. ");






            }
        });

    }//onCreate

    }//main activity