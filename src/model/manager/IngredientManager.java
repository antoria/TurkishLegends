package model.manager;

import model.Db;
import model.entity.Ingredient;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class IngredientManager extends Manager
{
    public Ingredient find(int id) throws SQLException
    {
        query = "SELECT * from Ingredient";
        query += " WHERE id = ?";

        prepare = Db.getInstance().prepareStatement(query);
        prepare.setInt(1, id);

        ResultSet result = prepare.executeQuery();

        Ingredient i = new Ingredient();

        if(result.first())
        {
            i.setId(result.getInt("id"));
            i.setName(result.getString("name"));
            i.setPrice(result.getDouble("price"));
            i.setImage_path(result.getString("image_path"));
            i.setType(result.getInt("type"));
        }


        prepare.close();

        return i;
    }

    public HashMap<String, ArrayList<Ingredient>> findAll() throws SQLException
    {
        query = "SELECT * FROM Ingredient";

        prepare = Db.getInstance().prepareStatement(query);

        ResultSet result = prepare.executeQuery();

        HashMap<String, ArrayList<Ingredient>> map = new HashMap<>();
        ArrayList<Ingredient> bread_list = new ArrayList<>();
        ArrayList<Ingredient> meat_list = new ArrayList<>();
        ArrayList<Ingredient> vegetable_list = new ArrayList<>();
        ArrayList<Ingredient> sauce_list = new ArrayList<>();

        if(result.first())
        {
            do
            {
                Ingredient i = new Ingredient();
                i.setId(result.getInt("id"));
                i.setName(result.getString("name"));
                i.setPrice(result.getDouble("price"));
                System.out.println(result.getDouble("price"));
                System.out.println(i.getPrice());
                System.out.println("---------------------");
                i.setImage_path(result.getString("image_path"));
                i.setType(result.getInt("type"));

                switch(i.getType())
                {
                    case Ingredient.BREAD:
                        bread_list.add(i);
                        break;
                    case Ingredient.MEAT:
                        meat_list.add(i);
                        break;
                    case Ingredient.VEGETABLE:
                        vegetable_list.add(i);
                        break;
                    case Ingredient.SAUCE:
                        sauce_list.add(i);
                        break;
                    default:
                        break;

                }
            }while(result.next());
        }

        prepare.close();

        map.put("BREAD", bread_list);
        map.put("MEAT", meat_list);
        map.put("VEGETABLE", vegetable_list);
        map.put("SAUCE", sauce_list);

        return map;
    }
}
