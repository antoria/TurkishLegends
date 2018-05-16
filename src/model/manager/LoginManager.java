package model.manager;

import model.Db;
import model.entity.User;
import model.factory.UserFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginManager extends Manager
{

    public User login(String email, String password) throws SQLException
    {
        query = "SELECT * from User";
        query += " WHERE email = ?";
        query += " AND password = ?";


        prepare = Db.getInstance().prepareStatement(query);
        prepare.setString(1, email);
        prepare.setString(2, password);

        ResultSet result = prepare.executeQuery();

        if(!result.first()) return null; // incorrect credentials

        int id = result.getInt("id");
        String firstName = result.getString("firstName");
        String lastName = result.getString("lastName");

        prepare.close();

        StaffManager sm = new StaffManager();

        UserFactory uf = new UserFactory();

        if(sm.exists(id))
        {
            return sm.find(id);
        }else
        {
            CustomerManager cm = new CustomerManager();
            return cm.find(id);
        }
    }

    public boolean register(String firstName, String lastName, String email, String password, String phone, String address) throws SQLException
    {
        query = "INSERT INTO User";
        query += " (firstName, lastName, email, password) VALUES";
        query += " (?,?,?,?)";

        prepare = Db.getInstance().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        prepare.setString(1, firstName);
        prepare.setString(2, lastName);
        prepare.setString(3, email);
        prepare.setString(4, password);

        int rowsUpdated = prepare.executeUpdate();
        int generatedId = 0;

        /*
        retrieving the automatically generated ID for the newly inserted User from the database
         */
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
            query = "INSERT INTO Customer";
            query += " (id, phone, address) VALUES";
            query += " (?,?,?)";

            prepare = Db.getInstance().prepareStatement(query);
            prepare.setInt(1, generatedId);
            prepare.setString(2, phone);
            prepare.setString(3, address);

            rowsUpdated = prepare.executeUpdate();
        }

        prepare.close();
        return (rowsUpdated > 0);
    }
}
