package builder;
import model.entity.Recipe;
import model.entity.Kebab;

//DESIGN PATTERN BUILDER
public class KebabBuilder {
    public int id;
    public String name;
    public Recipe recipe;

    public KebabBuilder(int id, String name, Recipe recipe){
        this.id = id;
        this.name = name;
        this.recipe= recipe;
    }

    public Kebab buildKebab(){
        return new Kebab(this);
    }
}
