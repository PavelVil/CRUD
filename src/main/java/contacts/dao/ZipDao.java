package contacts.dao;

import contacts.model.Zip;
import contacts.utils.DaoUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pavel on 27.06.2017.
 */
public class ZipDao implements AbstractDao<Zip> {

    private final static String ZIP_CODE = "zip_code";
    private final static String CITY = "city";
    private final static String STATE = "state";
    private final static String GET_ALL = "select zip_code, city, state from zip_code;";
    private final static String ADD = "insert into zip_code(city,state) VALUES (?,?);";
    private final static String GET_BY_ID = "select zip_code, city, state from zip_code where zip_code.zip_code=?;";
    private final static String GET_BY_TITLE = "select zip_code, city, state from zip_code where zip_code.city=?;";
    private final static String DELETE = "delete from contacts.zip_code where zip_code=?;";
    private final static String EDIT = "update contacts.zip_code set city=?, state=? where contacts.zip_code.zip_code=?;";

    @Override
    public List<Zip> getAll() {
        List<Zip> zips = null;
        try (Connection connection = DaoUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(GET_ALL);
        ResultSet rs = statement.executeQuery()){
            zips = new ArrayList<>();
            while (rs.next()){
                Zip zip = new Zip(rs.getInt(ZIP_CODE),rs.getString(CITY),rs.getString(STATE));
                zips.add(zip);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return zips;
    }

    @Override
    public void add(Zip zip) {
        try(Connection connection = DaoUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(ADD)){
            statement.setString(1,zip.getCity());
            statement.setString(2,zip.getState());
            statement.execute();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public Zip getById(int id) {
        Connection connection = DaoUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        Zip zip = null;
        try {
            statement = connection.prepareStatement(GET_BY_ID);
            statement.setInt(1,id);
            rs = statement.executeQuery();
            if (rs.next()){
                zip = new Zip(rs.getInt(ZIP_CODE),rs.getString(CITY),rs.getString(STATE));
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            DaoUtil.close(statement,rs,connection);
        }
        return zip;
    }

    @Override
    public Zip getByTitle(String title){
        Connection connection = DaoUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        Zip zip = null;
        try {
            statement = connection.prepareStatement(GET_BY_TITLE);
            statement.setString(1,title);
            rs = statement.executeQuery();
            if (rs.next()){
                zip = new Zip(rs.getInt(ZIP_CODE),rs.getString(CITY),rs.getString(STATE));
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            DaoUtil.close(statement,rs,connection);
        }
        return zip;
    }

    @Override
    public List<Zip> getByTitle(String title, String name) {
     throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Zip zip) {
        try (Connection connection = DaoUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE)){
            statement.setInt(1,zip.getId());
            statement.execute();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void edit(Zip zip) {
        try (Connection connection = DaoUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(EDIT)){
            statement.setString(1,zip.getCity());
            statement.setString(2,zip.getState());
            statement.setInt(3,zip.getId());
            statement.execute();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}
