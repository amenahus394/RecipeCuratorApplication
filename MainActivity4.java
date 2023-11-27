package com.example.collegeapp_amenahussain;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity4 extends AppCompatActivity {


    Button previous;
    TextView name2, cal2, protein2, fat2, recipelink2;
    int i = 0;

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    ListView listview;
    RecyclerView recyclerView;
    //ArrayList<findRecipes> savedrecipes;
    ArrayList<String> savedrecipes;
    private ArrayAdapter<findRecipes> savedadapter;
    String snameone, scone, sfone, spone, suone, suione;
    DatabaseReference dbrecipes;
    ValueEventListener valueEventListener;
    ValueEventListener queryValueListener;
    ArrayList<trial> finalarray;
    private ArrayAdapter<trial> adapter;
    trial t;
    String cals, savedRecipeName, fats, protein, recipelink, savedImageLink;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        final TextView profiletitle = findViewById(R.id.textView4);
        final TextView fullNameTextView = findViewById(R.id.fullName);
        final TextView emailTextView = findViewById(R.id.emailAddy);
        final TextView ageTextView = findViewById(R.id.Age);
        final TextView greeting = findViewById(R.id.greeting);
        listview = findViewById(R.id.mafour_listview);
        previous = findViewById(R.id.button_previous4);




        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity4.this, MainActivity3.class);
                startActivity(intent);
            }
        });

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();



        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
       //valueEventListener = new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if (userProfile != null) {
                    String fullName = userProfile.fullName;
                    String email = userProfile.email;
                    String age = userProfile.age;


                    greeting.setText("Welcome, " + fullName + "!");
                    fullNameTextView.setText("Full Name: " + fullName);
                    emailTextView.setText("Email: " + email);
                    ageTextView.setText("Age: " + age);

                }
            }//onDataChange

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity4.this, "Something went wrong!", Toast.LENGTH_LONG).show();
            }

       });

        finalarray = new ArrayList<trial>();


         queryValueListener = new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Iterable<DataSnapshot> snapshotIterator = dataSnapshot.getChildren();

                Iterator<DataSnapshot> iterator = snapshotIterator.iterator();


                while (iterator.hasNext()) {
                    DataSnapshot next = (DataSnapshot) iterator.next();

                    if(next.child("cals").getValue() != null) {
                         cals = next.child("cals").getValue().toString();
                        Log.d("TAGfinalcalories", cals);
                    }

                    if(next.child("savedRecipeName").getValue() != null) {
                         savedRecipeName = next.child("savedRecipeName").getValue().toString();
                        Log.d("TAGfinalsavedRecipeName", savedRecipeName);
                    }

                    if(next.child("protein").getValue() != null) {
                         protein = next.child("protein").getValue().toString();
                        Log.d("TAGfinalprotein", protein);
                    }


                    if(next.child("fats").getValue() != null) {
                            fats = next.child("fats").getValue().toString();
                            Log.d("TAGfinalfats", fats);
                    }

                    if(next.child("recipelink").getValue() != null) {
                        recipelink = next.child("recipelink").getValue().toString();
                        Log.d("TAGfinallink", recipelink);
                    }


                    if(next.child("savedImageLink").getValue() != null) {
                        savedImageLink = next.child("savedImageLink").getValue().toString();
                        Log.d("TAGfinalimagelink", savedImageLink);
                    }

                    finalarray.add(new trial(cals, fats, protein, recipelink, savedRecipeName, savedImageLink));
                }



                for(int i = 0; i<finalarray.size()-1; i++){
                    if(finalarray.get(i).getSavedRecipeName().equals(finalarray.get(i+1).getSavedRecipeName())){
                        finalarray.remove(i);
                        i--;
                    }
                }


                    adapter = new MainActivity4.ListViewAdapter(MainActivity4.this, R.layout.activity_adapterview, finalarray);
                    listview.setAdapter(adapter);



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        Query query = reference.child(userID).orderByKey();
        query.addListenerForSingleValueEvent(queryValueListener);

    }//onCreate


    public class ListViewAdapter extends ArrayAdapter<trial> {
        Context myContext;
        int xml;
        List<trial> list;
    public ListViewAdapter(@NonNull Context context, int resource, @NonNull List<trial> objects) {
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

        name2 = adapterView.findViewById(R.id.adapter_recipename);
        cal2 = adapterView.findViewById(R.id.adapter_cal);
        protein2 = adapterView.findViewById(R.id.adapter_prot);
        fat2 = adapterView.findViewById(R.id.adapter_fat);
        recipelink2 = adapterView.findViewById(R.id.adapter_recipelink);
        ImageView image = adapterView.findViewById(R.id.adapter_image);
        Button fav = adapterView.findViewById(R.id.adapter_favbutton);

       // image.setImageResource(R.drawable.favicon);

        fav.setText("REMOVE");

        name2.setText(list.get(position).getSavedRecipeName());
        cal2.setText("Calories: " + list.get(position).getCals());
        protein2.setText("Grams of Protein: " + list.get(position).getProtein());
        recipelink2.setText("Recipe Link: " + list.get(position).getRecipelink());
        fat2.setText("Grams of Fat: " + list.get(position).getFats());
        //String newlink = list.get(position).getImagelinknew();
        //Log.d("TAGNEWLINK", newlink);
        //Picasso.get().load(list.get(position).getImagelinknew()).into(image);

        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalarray.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
        return adapterView;
    }

}


}