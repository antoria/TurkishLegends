package model.query;

import java.sql.SQLException;
import java.util.HashMap;

public interface QueryGenerator {
    // return the auto-generated id
    public int add(Object o, String table, HashMap<String,Object> args) throws SQLException;
    public boolean remove();
    public boolean update();
    public boolean find();
}
