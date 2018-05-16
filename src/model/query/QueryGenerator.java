package model.query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public interface QueryGenerator {
    // return the auto-generated id
    public String add(String table, ArrayList<String> keys);
    public String delete(String table, ArrayList<String> conditions);
    public String update(String table, ArrayList<String> updates, ArrayList<String> conditions);
    public String find(ArrayList<String> attributes, String table, ArrayList<String> conditions);
}
