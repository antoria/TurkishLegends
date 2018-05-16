package model.query;

import java.sql.SQLException;
import java.util.HashMap;

public interface QueryGenerator {
    // return the auto-generated id
    public String add(Object o, String table, HashMap<String,Object> args) throws SQLException;
    public String remove();
    public String update();
    public String find();
}
