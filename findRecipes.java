package com.example.collegeapp_amenahussain;

import java.io.Serializable;

public class findRecipes implements Serializable {

    String fat;
    String protein;
    String calories;
    String recipeName;
    String summary;
    String prepTime;
    String carbs;
    String url;
    String imageurl;

    public findRecipes(String r, String c, String f, String p, String u, String i){

        recipeName = r;
       // carbs = ca;
        protein = p;
        fat = f;
        calories = c;
        url = u;
        imageurl = i;



    }

    public String getRecipeName(){
        return recipeName;
    }

    public String getCarbs(){
        return carbs;
    }

    public String getFat(){
        return fat;
    }

    public String getProtein(){
        return protein;
    }

    public String getCalories(){
        return calories;
    }

    public String getUrl(){
        return url;
    }

    public String getImageurl(){
        return imageurl;
    }


}


