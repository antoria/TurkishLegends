package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Db
{
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/turkishlegends?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String DB_USER = "test";
    private static final String DB_PASSWORD = "test";

    private static Connection CONNECTION = null;

    /**
     *
     * @return Connection to the database
     * @throws SQLException
     */
    public static Connection getInstance() throws SQLException
    {
        if(CONNECTION == null)
        {
            try
            {
                Class.forName(JDBC_DRIVER);
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }

            CONNECTION = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        }

        return CONNECTION;
    }

    public static void closeInstance() throws SQLException
    {
        CONNECTION.close();
    }
}