package model.query;

import java.util.ArrayList;

public abstract class AbstractQueryGenerator implements QueryGenerator
{
    protected StringBuilder query;

    public void controlClause(StringBuilder sb, ArrayList<String> list, String separator)
    {
        int i = 0;
        int numberOfElements = list.size();
        while(i < numberOfElements)
        {
            sb.append(list.get(i));
            i++;
            if(i != numberOfElements) sb.append(separator);
        }
    }

}
