package model.entity;

public class Ingredient
{
    // Corresponding ID in the database
    public static final int NO_MEAT_ID = 27;
    public static final int NO_SAUCE_ID = 29;
    public static final int NO_VEGETABLE_ID = 28;

    private int id;
    private String name;
    private double price;
    private String image_path;
    // 0 bread, 1 meat, 2 vegetable, 3 sauce
    private int type;

    public static final int BREAD = 0;
    public static final int MEAT = 1;
    public static final int VEGETABLE = 2;
    public static final int SAUCE = 3;

    public Ingredient(){}

    public Ingredient(int id, String name, double price, String image_path, int type) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image_path = image_path;
        this.type = type;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return name;
    }
}
