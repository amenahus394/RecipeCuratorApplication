package com.example.collegeapp_amenahussain;

public class saveInfo {

    String name1, fats, cals, protein, recipelink, imagelink, extra;

    public saveInfo(){

    }

    public saveInfo(String name1, String fats, String cals, String protein, String recipelink, String imagelink){
        this.name1 = name1;
        this.fats = fats;
        this.cals = cals;
        this.protein = protein;
        this.recipelink = recipelink;
        this.imagelink = imagelink;
        this.extra = "1111";
    }

    public String getCals() {
        return cals;
    }

    public String setCals(String s) { return cals;}

    public String getFats() {
        return fats;
    }

    public String setFats(String s){ return fats; }

    public String getSavedRecipeName() {
        return name1;
    }

    public String setSavedRecipeName(String s) { return name1; }

    public String getProtein() {
        return protein;
    }

    public String setProtein(String s){ return protein; }

    public String getRecipelink() {
        return recipelink;
    }

    public String setRecipelink(String s){ return recipelink; }

}
