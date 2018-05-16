package model.query;

import model.Db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MysqlQuery implements QueryGenerator
{
    // returns the ID of the object inserted in the database
    @Override
    public int add(Object o, String table, HashMap<String,Object> args) throws SQLException {
        String query = "insert into ";
        query += table;
        query += " (";
        Iterator it = args.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry pair = (Map.Entry) it.next();
            query += pair.getKey();
            if(it.hasNext()){
                query+= ",";
            }
        }
        query+= ") values";


        query += " (";
        it = args.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry pair = (Map.Entry) it.next();
            query += "?";
            if(it.hasNext()){
                query+= ",";
            }
        }
        query+= ")";


        PreparedStatement prepare = Db.getInstance().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        int index = 1;
        it = args.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry pair = (Map.Entry) it.next();
            switch (pair.getValue().getClass().getSimpleName())
            {
                case "String":
                    prepare.setString(index, (String)pair.getValue());
                    break;
                case "int":
                    prepare.setInt(index, (int)pair.getValue());
                    break;
                case "Double":
                    prepare.setDouble(index, (Double)pair.getValue());
                    break;
                default: break;
            }
            index++;
        }

        int rowsUpdated = prepare.executeUpdate();
        int generatedId = 0;

        try(ResultSet generatedKeys = prepare.getGeneratedKeys())
        {
            if(generatedKeys.next())
            {
                generatedId = generatedKeys.getInt(1);
            }else
            {
                throw new SQLException("Failed to retrieve auto-generated ID.");
            }
        }

        prepare.close();
        return generatedId;
    };

    @Override
    public boolean remove()
    {
        return true;
    };

    @Override
    public boolean update()
    {
        return true;
    };

    @Override
    public boolean find()
    {
        return true;
    };
}
