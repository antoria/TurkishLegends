package model.manager;

import java.sql.PreparedStatement;

/**
 * Managers will connect to the database and execute the queries regarding the entities.
 */
public abstract class Manager
{
    protected String query;
    protected PreparedStatement prepare;
}
