package model.entity;

public class Recipe
{
    private int id;
    private Ingredient bread, meat, sauce, vegetable1, vegetable2, vegetable3;

    public Recipe(){}

    public Recipe(int id, Ingredient bread, Ingredient meat, Ingredient sauce, Ingredient vegetable1, Ingredient vegetable2, Ingredient vegetable3) {
        this.id = id;
        this.bread = bread;
        this.meat = meat;
        this.sauce = sauce;
        this.vegetable1 = vegetable1;
        this.vegetable2 = vegetable2;
        this.vegetable3 = vegetable3;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Ingredient getBread() {
        return bread;
    }

    public void setBread(Ingredient bread) {
        this.bread = bread;
    }

    public Ingredient getMeat() {
        return meat;
    }

    public void setMeat(Ingredient meat) {
        this.meat = meat;
    }

    public Ingredient getSauce() {
        return sauce;
    }

    public void setSauce(Ingredient sauce) {
        this.sauce = sauce;
    }

    public Ingredient getVegetable1() {
        return vegetable1;
    }

    public void setVegetable1(Ingredient vegetable1) {
        this.vegetable1 = vegetable1;
    }

    public Ingredient getVegetable2() {
        return vegetable2;
    }

    public void setVegetable2(Ingredient vegetable2) {
        this.vegetable2 = vegetable2;
    }

    public Ingredient getVegetable3() {
        return vegetable3;
    }

    public void setVegetable3(Ingredient vegetable3) {
        this.vegetable3 = vegetable3;
    }
}
