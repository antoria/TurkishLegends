package model.factory;

import model.query.MysqlQuery;
import model.query.QueryGenerator;

public class QueryGeneratorFactory
{
    //DESIGN PATTERN FACTORY
    public QueryGenerator getQueryGenerator(String type)
    {
        if(type.equalsIgnoreCase("MYSQL"))
        {
            return new MysqlQuery();
        }

        return null;
    }
}
