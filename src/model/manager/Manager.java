package model.manager;

import model.factory.QueryGeneratorFactory;
import model.query.QueryGenerator;

import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 * Managers will connect to the database and execute the queries regarding the entities.
 */
public abstract class Manager
{
    protected QueryGenerator queryGenerator = new QueryGeneratorFactory().getQueryGenerator("MYSQL");
    protected String query;
    protected PreparedStatement prepare;
    protected ArrayList<String> attributesList = new ArrayList<>();
    protected ArrayList<String> conditionsList = new ArrayList<>();
    protected ArrayList<String> updatesList = new ArrayList<>();
    protected ArrayList<String> keysList = new ArrayList<>();
}
