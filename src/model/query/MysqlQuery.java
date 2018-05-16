package model.query;

import java.util.ArrayList;

public class MysqlQuery extends AbstractQueryGenerator
{
    // returns the ID of the object inserted in the database
    @Override
    public String add(String table, ArrayList<String> keys)
    {
        // clear the object
        query = new StringBuilder();

        // INSERT INTO Table (a,b,c,d) VALUES (?,?,?,?)
        query.append("INSERT INTO ");
        query.append(table);

        query.append(" (");
        controlClause(query, keys, ",");

        query.append(") VALUES (");

        int i = 0;
        int numberOfKeys = keys.size();
        while(i < numberOfKeys)
        {
            query.append("?");
            i++;
            if(i != numberOfKeys) query.append(",");
        }

        query.append(")");

        return query.toString();
    };

    @Override
    public String delete(String table, ArrayList<String> conditions)
    {
        query = new StringBuilder();

        // DELETE FROM Table WHERE x = y AND w = z
        query.append("DELETE FROM ");
        query.append(table);

        if(conditions.size() > 0)
        {
            query.append(" WHERE ");
            controlClause(query, conditions, " AND ");
        }

        return query.toString();
    };

    @Override
    public String update(String table, ArrayList<String> updates, ArrayList<String> conditions)
    {
        query = new StringBuilder();

        // UPDATE Table SET field1 = value1, field2 = value2 WHERE x = y and w = z;
        query.append("UPDATE ");
        query.append(table);

        query.append(" SET ");
        controlClause(query, updates, ", ");

        if(conditions.size() > 0)
        {
            query.append(" WHERE ");
            controlClause(query, conditions, " AND ");
        }

        return query.toString();
    };

    @Override
    public String find(ArrayList<String> attributes, String table, ArrayList<String> conditions)
    {
        query = new StringBuilder();

        // SELECT a FROM Table WHERE x = y and w = z
        query.append("SELECT ");
        controlClause(query, attributes, ", ");

        query.append(" FROM ");
        query.append(table);

        if(conditions.size() > 0)
        {
            query.append(" WHERE ");
            controlClause(query, conditions, " AND ");
        }

        return query.toString();
    };
}
