package com.example.collegeapp_amenahussain;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.service.autofill.SaveInfo;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    TextView recipefindertitle, tminCal, tmaxCal, tminFat, tmaxFat, tminProtein, tmaxProtein, tingredients;
    TextView name1, cal1, protein1, fat1, recipelink;
    Button previous, next, findRecipes;
    ListView listview;
    String urlstring, data;
    EditText eminCal, emaxCal, eminFat, emaxFat, eminProtein, emaxProtein, eIngredients;
    String minCal, maxCal, minFat, maxFat, minProtein, maxProtein, ingredients;
    ArrayList<findRecipes> recipes;
    private ArrayAdapter<findRecipes> adapter;
    private FirebaseAuth mAuth;
    ArrayList<saveInfo> savedInfo;
    DatabaseReference ref;
    saveInfo savedinformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        recipefindertitle = findViewById(R.id.recipefinder_txt);
        previous = findViewById(R.id.button_previous);
        next = findViewById(R.id.button_next);
        tminCal = findViewById(R.id.mincal_txt);
        eminCal = findViewById(R.id.mincal_edittxt);
        tmaxCal = findViewById(R.id.maxcal_txt);
        emaxCal = findViewById(R.id.maxcal_edittxt);
        tminFat = findViewById(R.id.minfat_txt);
        eminFat = findViewById(R.id.minfat_edittxt);
        tmaxFat = findViewById(R.id.maxfat_txt);
        emaxFat = findViewById(R.id.maxfat_edittxt);
        tminProtein = findViewById(R.id.minprotein_txt);
        eminProtein = findViewById(R.id.minprotein_edittxt);
        tmaxProtein = findViewById(R.id.maxprotein_txt);
        emaxProtein = findViewById(R.id.maxprotein_edittxt);
        tingredients = findViewById(R.id.ingredients_txt);
        eIngredients = findViewById(R.id.ingredients_edittxt);
        listview = findViewById(R.id.id_listview);
        findRecipes = findViewById(R.id.submit_button);




        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                startActivity(intent);
            }
        });

        eminCal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                minCal = eminCal.getText().toString();
            }
        });

        emaxCal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
               maxCal = emaxCal.getText().toString();

            }
        });

        eminFat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                minFat = eminFat.getText().toString();
            }
        });
        
        emaxFat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
               maxFat = emaxFat.getText().toString();
            }
        });

        eminProtein.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                minProtein = eminProtein.getText().toString();
            }
        });


        emaxProtein.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                maxProtein = emaxProtein.getText().toString();
            }
        });

        eIngredients.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                ingredients = eIngredients.getText().toString();
            }
        });



        findRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               urlstring = "https://api.spoonacular.com/recipes/complexSearch?apiKey=bfa0441fabe847f4a0222f25dc747542&query="+ingredients+"&addRecipeNutrition=true&addRecipeInformation=true&minCalories="+minCal+"&maxCalories="+maxCal+"&minFat="+minFat+"&maxFat="+maxFat+"&minProtein="+minProtein+"&maxProtein="+maxProtein;
               // urlstring = "https://api.spoonacular.com/recipes/complexSearch?apiKey=bfa0441fabe847f4a0222f25dc747542&query=cheese&addRecipeNutrition=true&addRecipeInformation=true&minCalories=100&maxCalories=800&minFat=2&maxFat=10&minProtein=2&maxProtein=10";
                MainActivity2.Task myTask = new MainActivity2.Task();
                myTask.execute();
            }
        });
    }

    private class Task extends AsyncTask<String, Void, String> {
        String nameone, nametwo, namethree;
        String fone, ftwo, fthree;
        Double fdone, fdtwo, fdthree;
        Double cdone, cdtwo, cdthree;
        Double pdone, pdtwo, pdthree;
        String cone, ctwo, cthree;
        String pone, ptwo, pthree;
        String uone, utwo, uthree;
        String uione, uitwo, uithree;
        int inttotal;
        String total;


        @Override
        protected String doInBackground(String... strings) {
            try{
                URL recipeURL = new URL(urlstring);
                Log.d("TAG1", urlstring);
                URLConnection connection = recipeURL.openConnection();
                Log.d("TAG1.5", "after connection");
                InputStream inputStream = connection.getInputStream();
                Log.d("TAG2", "before buffered");
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                data = br.readLine();

            } catch (MalformedURLException malformedURLException) {
                malformedURLException.printStackTrace();
                Log.d("TAGE1", "malformedURLException");
            } catch (IOException e) {
                e.printStackTrace();
                Log.d("TAGE2", e.toString());
            }
            return data;
        }//doInBackground

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONObject rootObject = new JSONObject(s);
                JSONArray mainArray = rootObject.getJSONArray("results");
                total = rootObject.getString("totalResults");
                inttotal = Integer.valueOf(total);

                //recipe one
                if(inttotal>=1) {
                    nameone = mainArray.getJSONObject(0).getString("title");
                    Log.d("TAG1N", nameone);
                    cdone = mainArray.getJSONObject(0).getJSONObject("nutrition").getJSONArray("nutrients").getJSONObject(0).getDouble("amount");
                    cone = Double.toString(cdone);
                    Log.d("TAGCONE", cone);
                    fdone = mainArray.getJSONObject(0).getJSONObject("nutrition").getJSONArray("nutrients").getJSONObject(1).getDouble("amount");
                    fone = Double.toString(fdone);
                    Log.d("TAGFONE", fone);
                    pdone = mainArray.getJSONObject(0).getJSONObject("nutrition").getJSONArray("nutrients").getJSONObject(9).getDouble("amount");
                    pone = Double.toString(pdone);
                    Log.d("TAGFONE", pone);

                    uione = mainArray.getJSONObject(0).getString("image");
                    Log.d("TAGUIONE", uione);

                    uone = mainArray.getJSONObject(0).getString("sourceUrl");
                    Log.d("TAGUONE", uone);
                }



                //recipe two
                if(inttotal>=2) {
                    nametwo = mainArray.getJSONObject(1).getString("title");
                    Log.d("TAG2N", nametwo);
                    cdtwo = mainArray.getJSONObject(1).getJSONObject("nutrition").getJSONArray("nutrients").getJSONObject(0).getDouble("amount");
                    ctwo = Double.toString(cdtwo);
                    Log.d("TAGCTWO", ctwo);
                    fdtwo = mainArray.getJSONObject(1).getJSONObject("nutrition").getJSONArray("nutrients").getJSONObject(1).getDouble("amount");
                    ftwo = Double.toString(fdtwo);
                    Log.d("TAGFTWO", ftwo);
                    pdtwo = mainArray.getJSONObject(1).getJSONObject("nutrition").getJSONArray("nutrients").getJSONObject(9).getDouble("amount");
                    ptwo = Double.toString(pdtwo);
                    Log.d("TAGPTWO", ptwo);


                    uitwo = mainArray.getJSONObject(1).getString("image");
                    Log.d("TAGUITWO", uitwo);

                    utwo = mainArray.getJSONObject(1).getString("sourceUrl");
                    Log.d("TAGUTWO", utwo);


                }

                //recipe three
                if(inttotal>=3) {
                    namethree = mainArray.getJSONObject(2).getString("title");
                    Log.d("TAG3N", namethree);
                    cdthree = mainArray.getJSONObject(2).getJSONObject("nutrition").getJSONArray("nutrients").getJSONObject(0).getDouble("amount");
                    cthree = Double.toString(cdthree);
                    Log.d("TAGCTHREE", cthree);
                    fdthree = mainArray.getJSONObject(2).getJSONObject("nutrition").getJSONArray("nutrients").getJSONObject(1).getDouble("amount");
                    fthree = Double.toString(fdthree);
                    Log.d("TAGFTHREE", fthree);
                    pdthree = mainArray.getJSONObject(2).getJSONObject("nutrition").getJSONArray("nutrients").getJSONObject(9).getDouble("amount");
                    pthree = Double.toString(pdthree);
                    Log.d("TAGPTHREE", pthree);

                    uithree = mainArray.getJSONObject(2).getString("image");
                    Log.d("TAGUITHREE", uithree);

                    uthree = mainArray.getJSONObject(2).getString("sourceUrl");
                    Log.d("TAGUTHREE", uthree);
                }

            } catch (JSONException e) {
                e.printStackTrace();
                Log.d("TAGE3", e.toString());
            }

            recipes = new ArrayList<findRecipes>();

            findRecipes firstrecipe = new findRecipes(nameone, cone, fone, pone, uone, uione);
            findRecipes secondrecipe = new findRecipes(nametwo, ctwo, ftwo, ptwo, utwo, uitwo);
            findRecipes threerecipe = new findRecipes(namethree, cthree, fthree, pthree, uthree, uithree);
            recipes.add(firstrecipe);
            recipes.add(secondrecipe);
            recipes.add(threerecipe);


            adapter = new ListViewAdapter(MainActivity2.this, R.layout.activity_adapterview, recipes);
            listview.setAdapter(adapter);


        }
    }//AsyncTask

    public class ListViewAdapter extends ArrayAdapter<findRecipes> {
        Context myContext;
        int xml;
        List<findRecipes> list;
        String savedname, savedcals, savedprotein, savedfats, savedlink, savedimagelink;

        public ListViewAdapter(@NonNull Context context, int resource, @NonNull List<findRecipes> objects) {
            super(context, resource, objects);
            myContext = context;
            xml = resource;
            list = objects;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) myContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            View adapterView = inflater.inflate(xml, null);
            name1 = adapterView.findViewById(R.id.adapter_recipename);
            cal1 = adapterView.findViewById(R.id.adapter_cal);
            protein1 = adapterView.findViewById(R.id.adapter_prot);
            fat1 = adapterView.findViewById(R.id.adapter_fat);
            recipelink = adapterView.findViewById(R.id.adapter_recipelink);
            ImageView image = adapterView.findViewById(R.id.adapter_image);
            Button fav = adapterView.findViewById(R.id.adapter_favbutton);
            mAuth = FirebaseAuth.getInstance();
            //savedInfo = new ArrayList<saveInfo>();

            name1.setText(list.get(position).getRecipeName());
            cal1.setText("Calories: " + list.get(position).getCalories());
            protein1.setText("Grams of Protein: " + list.get(position).getProtein());
            fat1.setText("Grams of Fat: " + list.get(position).getFat());
            recipelink.setText("Recipe Link: " + list.get(position).getUrl());

            Picasso.get().load(list.get(position).getImageurl()).into(image);

           fav.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   ref = FirebaseDatabase.getInstance().getReference("Users")
                           .child(FirebaseAuth.getInstance().getCurrentUser().getUid());


                   savedname = list.get(position).getRecipeName();
                   savedcals = list.get(position).getCalories();
                   savedfats = list.get(position).getFat();
                   savedprotein = list.get(position).getProtein();
                   savedlink = list.get(position).getUrl();
                   savedimagelink = list.get(position).getImageurl();

                  savedinformation = new saveInfo(savedname, savedfats, savedcals, savedprotein, savedlink, savedimagelink);
                  ref.push().setValue(savedinformation);

                  }
           });
            return adapterView;
        }

    }
}//main

