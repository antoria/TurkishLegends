package model.manager;

import controller.Login;
import model.Db;
import model.entity.Ingredient;
import model.entity.Recipe;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RecipeManager extends Manager
{

    public Recipe find(int id) throws SQLException
    {
        query = "SELECT * from Recipe";
        query += " WHERE id = ?";

        prepare = Db.getInstance().prepareStatement(query);
        prepare.setInt(1, id);

        ResultSet result = prepare.executeQuery();

        Recipe r = new Recipe();

        if(result.first())
        {
            r.setId(result.getInt("id"));

            IngredientManager im = new IngredientManager();
            r.setBread(im.find(result.getInt("bread_id")));
            r.setMeat(im.find(result.getInt("meat_id")));
            r.setSauce(im.find(result.getInt("sauce_id")));
            r.setVegetable1(im.find(result.getInt("vegetable1_id")));
            r.setVegetable2(im.find(result.getInt("vegetable2_id")));
            r.setVegetable3(im.find(result.getInt("vegetable3_id")));
        }


        prepare.close();

        return r;
    }

    public boolean add(Recipe r) throws SQLException
    {
        query = "INSERT INTO Recipe";
        query += " (bread_id, meat_id, sauce_id, vegetable1_id, vegetable2_id, vegetable3_id) VALUES";
        query += " (?,?,?,?,?,?)";

        prepare = Db.getInstance().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        /*
        If the user didn't select anything, set the value to no_meat, no_sauce...
        Except for the bread. It will be the first bread per default (pita bread).
         */
        if(r.getBread() == null)
        {
            prepare.setInt(1, 1);
        }else
        {
            prepare.setInt(1, r.getBread().getId());
        }

        if(r.getMeat() == null)
        {
            prepare.setInt(2, Ingredient.NO_MEAT_ID);
        }else
        {
            prepare.setInt(2, r.getMeat().getId());
        }

        if(r.getSauce() == null)
        {
            prepare.setInt(3, Ingredient.NO_SAUCE_ID);
        }else
        {
            prepare.setInt(3, r.getSauce().getId());
        }

        if(r.getVegetable1() == null)
        {
            prepare.setInt(4, Ingredient.NO_VEGETABLE_ID);
        }else
        {
            prepare.setInt(4, r.getVegetable1().getId());
        }

        if(r.getVegetable2() == null)
        {
            prepare.setInt(5, Ingredient.NO_VEGETABLE_ID);
        }else
        {
            prepare.setInt(5, r.getVegetable2().getId());
        }

        if(r.getVegetable3() == null)
        {
            prepare.setInt(6, Ingredient.NO_VEGETABLE_ID);
        }else
        {
            prepare.setInt(6, r.getVegetable3().getId());
        }


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

        prepare.close();

        if(generatedId != 0)
        {
            query = "INSERT INTO Customer_created_recipe";
            query += " (customer_id, recipe_id) VALUES";
            query += " (?,?)";

            prepare = Db.getInstance().prepareStatement(query);
            prepare.setInt(1, Login.CURRENT_USER.getId());
            prepare.setInt(2, generatedId);

            rowsUpdated = prepare.executeUpdate();
        }

        r.setId(generatedId);
        prepare.close();
        return (rowsUpdated > 0);
    }


    public boolean delete(Recipe r) throws SQLException
    {
        query = "DELETE FROM Recipe";
        query += " WHERE id = ?";

        prepare = Db.getInstance().prepareStatement(query);
        prepare.setInt(1, r.getId());

        int rowsUpdated = prepare.executeUpdate();
        prepare.close();
        return (rowsUpdated > 0);
    }

    public boolean deleteCustomerRecipeLink(Recipe r) throws SQLException
    {
        query = "DELETE FROM Customer_created_recipe";
        query += " WHERE customer_id = ?";
        query += " AND recipe_id = ?";

        prepare = Db.getInstance().prepareStatement(query);
        prepare.setInt(1, Login.CURRENT_USER.getId());
        prepare.setInt(2, r.getId());

        int rowsUpdated = prepare.executeUpdate();
        prepare.close();
        return (rowsUpdated > 0);
    }
}
