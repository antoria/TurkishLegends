package model.entity;

import builder.KebabBuilder;

public class Kebab
{
    private int id;
    private String name;
    private Recipe recipe;

    public Kebab(){}

    public Kebab(KebabBuilder kb) {
        this.id = kb.id;
        this.name = kb.name;
        this.recipe = kb.recipe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public String toString() {
        return name;
    }
}
