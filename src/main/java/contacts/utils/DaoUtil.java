package contacts.utils;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.Properties;

public class DaoUtil {

    public static Connection getConnection(){
        Connection connection = null;
        try {
            Context context = new InitialContext();
            Context initContext  = (Context )context.lookup("java:/comp/env");
            DataSource ds = (DataSource) initContext.lookup("jdbc/contacts");
            connection = ds.getConnection();
        }catch (SQLException | NamingException ex){
            ex.printStackTrace();
        }
        return connection;
    }

    public static void close(Statement statement, ResultSet rs,Connection connection){
        try {

            if (statement != null) {
                statement.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }

}
