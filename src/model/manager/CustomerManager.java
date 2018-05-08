package model.manager;

import model.Db;
import model.entity.Customer;
import model.factory.UserFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerManager extends Manager
{

   public Customer find(int id) throws SQLException
   {
        query = "SELECT * from Customer NATURAL JOIN User";
        query += " WHERE id = ?";

        prepare = Db.getInstance().prepareStatement(query);
        prepare.setInt(1, id);

        ResultSet result = prepare.executeQuery();

        UserFactory uf = new UserFactory();

        Customer c = (Customer) uf.getUser("CUSTOMER");

        if(result.first())
        {
            c.setId(result.getInt("id"));
            c.setFirstName(result.getString("firstName"));
            c.setLastName(result.getString("lastName"));
            c.setEmail(result.getString("email"));
            c.setPassword(result.getString("password"));
            c.setAddress(result.getString("address"));
            c.setPhone(result.getString("phone"));
        }


        prepare.close();

        return c;
   }

   public boolean exists(int id) throws SQLException
   {
       query = "SELECT 1 from Customer";
       query += " WHERE id = ?";

       prepare = Db.getInstance().prepareStatement(query);
       prepare.setInt(1, id);

       ResultSet rs = prepare.executeQuery();
       return rs.first();
   }
}
