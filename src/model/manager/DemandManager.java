package model.manager;

import controller.Login;
import model.Db;
import model.entity.Demand;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DemandManager extends Manager
{
    public Demand find(int id) throws SQLException
    {
        query = "SELECT * from Demand";
        query += " WHERE id = ?";

        prepare = Db.getInstance().prepareStatement(query);
        prepare.setInt(1, id);

        ResultSet result = prepare.executeQuery();

        Demand d = new Demand();

        if(result.first())
        {
            d.setId(result.getInt("id"));
            d.setDate(result.getDate("date"));
            d.setStatus(result.getInt("status"));

            CustomerManager cm = new CustomerManager();
            d.setUser(cm.find(result.getInt("user_id")));

            KebabManager km = new KebabManager();
            d.setKebab(km.find(result.getInt("kebab_id")));
        }


        prepare.close();

        return d;
    }

    public ArrayList<Demand> findAll() throws SQLException
    {
        query = "SELECT * from Demand";

        prepare = Db.getInstance().prepareStatement(query);

        ResultSet result = prepare.executeQuery();

        ArrayList<Demand> list = new ArrayList<>();
        CustomerManager cm = new CustomerManager();
        KebabManager km = new KebabManager();

        if(result.first())
        {
            do
            {
                Demand d = new Demand();

                d.setId(result.getInt("id"));
                d.setDate(result.getDate("date"));
                d.setStatus(result.getInt("status"));

                d.setUser(cm.find(result.getInt("user_id")));
                d.setKebab(km.find(result.getInt("kebab_id")));

                list.add(d);

            }while(result.next());
        }

        prepare.close();

        return list;
    }

    public boolean add(Demand d) throws SQLException
    {
        query = "INSERT INTO Demand";
        query += " (user_id, kebab_id, date, status) VALUES";
        query += " (?,?,?,?)";

        prepare = Db.getInstance().prepareStatement(query);
        prepare.setInt(1, Login.CURRENT_USER.getId());
        prepare.setInt(2, d.getKebab().getId());
        prepare.setDate(3, java.sql.Date.valueOf(java.time.LocalDate.now()));
        prepare.setInt(4, 0); // waiting

        int rowsUpdated = prepare.executeUpdate();
        prepare.close();

        return (rowsUpdated > 0);
    }

    public boolean updateStatus(Demand d) throws SQLException
    {
        query = "UPDATE Demand";
        query += " SET status = ?";
        query += " WHERE id = ?";

        prepare = Db.getInstance().prepareStatement(query);
        prepare.setInt(1, d.getStatus());
        prepare.setInt(2, d.getId());

        int rowsUpdated = prepare.executeUpdate();
        prepare.close();

        return (rowsUpdated > 0);
    }

    public boolean delete(Demand d) throws SQLException
    {
        query = "DELETE FROM Demand";
        query += " WHERE id = ?";

        prepare = Db.getInstance().prepareStatement(query);
        prepare.setInt(1, d.getId());

        int rowsUpdated = prepare.executeUpdate();
        prepare.close();

        return (rowsUpdated > 0);
    }

    public void updateNullKebab(int id) throws SQLException
    {
        query = "UPDATE Demand";
        query += " SET kebab_id = NULL";
        query += " WHERE kebab_id = ?";

        prepare = Db.getInstance().prepareStatement(query);
        prepare.setInt(1, id);

        prepare.executeUpdate();
        prepare.close();
    }
}
