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
        query = "SELECT * from Kebab";
        query += " WHERE id = ?";

        prepare = Db.getInstance().prepareStatement(query);
        prepare.setInt(1, id);

        ResultSet result = prepare.executeQuery();

        Kebab k = new Kebab();

        if(result.first())
        {
            k.setId(result.getInt("id"));
            k.setName(result.getString("name"));
            //k.setRecipe_id(result.getInt("recipe_id"));

            RecipeManager rm = new RecipeManager();
            k.setRecipe(rm.find(result.getInt("recipe_id")));
        }


        prepare.close();

        return k;
    }

    public ArrayList<Kebab> findAllByCurrentUser() throws SQLException
    {
        query = "SELECT recipe_id from Customer_created_recipe";
        query += " WHERE customer_id = ?";

        prepare = Db.getInstance().prepareStatement(query);
        prepare.setInt(1, Login.CURRENT_USER.getId());

        ResultSet result = prepare.executeQuery();
        ArrayList<Kebab> list = new ArrayList<>();
        RecipeManager rm = new RecipeManager();

        if(result.first())
        {
            do
            {
                query = "SELECT * from Kebab";
                query += " WHERE recipe_id = ?";

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
        query = "INSERT INTO Kebab";
        query += " (name, recipe_id) VALUES";
        query += " (?,?)";

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
            query = "DELETE FROM Kebab";
            query += " WHERE id = ?";

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
