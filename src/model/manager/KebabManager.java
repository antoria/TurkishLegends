package model.manager;

import controller.Login;
import model.Db;
import model.entity.Kebab;
import model.entity.Recipe;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class KebabManager extends Manager
{
    public Kebab find(int id) throws SQLException
    {
        attributesList.clear();
        attributesList.add("*");

        conditionsList.clear();
        conditionsList.add("id = ?");

        query = queryGenerator.find(attributesList, "Kebab", conditionsList);

        prepare = Db.getInstance().prepareStatement(query);
        prepare.setInt(1, id);

        ResultSet result = prepare.executeQuery();

        Kebab k = new Kebab();

        if(result.first())
        {
            k.setId(result.getInt("id"));
            k.setName(result.getString("name"));

            RecipeManager rm = new RecipeManager();
            k.setRecipe(rm.find(result.getInt("recipe_id")));
        }


        prepare.close();

        return k;
    }

    public ArrayList<Kebab> findAllByCurrentUser() throws SQLException
    {
        attributesList.clear();
        attributesList.add("recipe_id");

        conditionsList.clear();
        conditionsList.add("customer_id = ?");

        query = queryGenerator.find(attributesList, "Customer_created_recipe", conditionsList);

        prepare = Db.getInstance().prepareStatement(query);
        prepare.setInt(1, Login.CURRENT_USER.getId());

        ResultSet result = prepare.executeQuery();
        ArrayList<Kebab> list = new ArrayList<>();
        RecipeManager rm = new RecipeManager();

        if(result.first())
        {
            do
            {
                attributesList.clear();
                attributesList.add("*");

                conditionsList.clear();
                conditionsList.add("recipe_id = ?");

                query = queryGenerator.find(attributesList, "Kebab", conditionsList);

                prepare = Db.getInstance().prepareStatement(query);
                prepare.setInt(1, result.getInt("recipe_id"));

                ResultSet result2 = prepare.executeQuery();

                if(result2.first())
                {
                    Recipe r = rm.find(result.getInt("recipe_id"));

                    Kebab k = new Kebab();
                    k.setRecipe(r);
                    k.setId(result2.getInt("id"));
                    k.setName(result2.getString("name"));

                    list.add(k);
                }
            }while(result.next());
        }

        prepare.close();

        return list;
    }

    public boolean add(Kebab k) throws SQLException
    {
        keysList.clear();
        keysList.add("name");
        keysList.add("recipe_id");

        query = queryGenerator.add("Kebab", keysList);

        prepare = Db.getInstance().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        prepare.setString(1, k.getName());
        prepare.setInt(2, k.getRecipe().getId());

        int rowsUpdated = prepare.executeUpdate();
        int generatedId = 0;

        try (ResultSet generatedKeys = prepare.getGeneratedKeys())
        {
            if (generatedKeys.next())
            {
                generatedId = generatedKeys.getInt(1);
            }
            else {
                throw new SQLException("Failed to retrieve auto-generated ID.");
            }
        }

        k.setId(generatedId);
        prepare.close();
        return (rowsUpdated > 0);
    }

    public boolean delete(Kebab k) throws SQLException
    {
        RecipeManager rm = new RecipeManager();
        if(rm.deleteCustomerRecipeLink(k.getRecipe()))
        {
            conditionsList.clear();
            conditionsList.add("id = ?");

            query = queryGenerator.delete("Kebab", conditionsList);

            prepare = Db.getInstance().prepareStatement(query);
            prepare.setInt(1, k.getId());

            if(prepare.executeUpdate() > 0)
            {
                prepare.close();
                DemandManager dm = new DemandManager();
                dm.updateNullKebab(k.getId());
                return (rm.delete(k.getRecipe()));
            }
            prepare.close();
        }

        return false;
    }
}
