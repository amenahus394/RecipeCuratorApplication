package com.example.collegeapp_amenahussain;

public class trial {

        public String cals, fats, protein, recipelink, savedRecipeName, random, imagelink;

    public trial(){

    }

    public trial(String cals, String fats, String protein, String recipelink, String savedRecipeName, String imagelink){
        this.cals = cals;
        this.fats = fats;
        this.protein = protein;
        this.recipelink = recipelink;
        this.savedRecipeName = savedRecipeName;
       this.imagelink = imagelink;
    }

    public String getCals(){
        return cals;
    }

    public String getFats(){
        return fats;
    }

    public String getProtein(){
        return protein;
    }

    public String getRecipelink(){
        return recipelink;
    }

    public String getSavedRecipeName(){
        return savedRecipeName;
    }

    public String getImagelinknew(){
        return imagelink;
    }

}
