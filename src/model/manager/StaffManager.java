package model.manager;

import model.Db;
import model.entity.Staff;
import model.factory.UserFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StaffManager extends Manager
{

    public Staff find(int id) throws SQLException
    {
        query = "SELECT * from Staff NATURAL JOIN User";
        query += " WHERE id = ?";

        prepare = Db.getInstance().prepareStatement(query);
        prepare.setInt(1, id);

        ResultSet result = prepare.executeQuery();

        UserFactory uf = new UserFactory();

        Staff s = (Staff) uf.getUser("STAFF");

        if(result.first())
        {
            s.setId(result.getInt("id"));
            s.setFirstName(result.getString("firstName"));
            s.setLastName(result.getString("lastName"));
            s.setEmail(result.getString("email"));
            s.setPassword(result.getString("password"));
            s.setSalary(result.getInt("salary"));
        }

        prepare.close();

        return s;
    }

    public boolean exists(int id) throws SQLException
    {
        query = "SELECT 1 from Staff";
        query += " WHERE id = ?";

        prepare = Db.getInstance().prepareStatement(query);
        prepare.setInt(1, id);

        ResultSet rs = prepare.executeQuery();
        return rs.first();
    }
}
