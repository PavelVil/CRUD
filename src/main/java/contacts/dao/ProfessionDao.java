package contacts.dao;

import contacts.model.Profession;
import contacts.utils.DaoUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfessionDao implements IDao<Profession> {

    private final static String PROF_ID = "prof_id";
    private final static String PROFESSION = "profession";
    private final static String GET_ALL = "select prof_id, profession from professions;";
    private final static String ADD = "insert into contacts.professions(profession) VALUES (?);";
    private final static String GET_BY_ID = "select prof_id, profession from professions where prof_id=?;";
    private final static String GET_BY_TITLE = "select prof_id, profession from professions where profession=?;";
    private final static String DELETE = "delete from contacts.professions where prof_id=?;";
    private final static String EDIT = "update contacts.professions SET profession=? WHERE professions.prof_id=?;";
    @Override
    public List<Profession> getAll() {
        List<Profession> professions = null;
        try (Connection connection = DaoUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(GET_ALL);
        ResultSet rs = statement.executeQuery()){
            professions = new ArrayList<>();
            while (rs.next()){
                Profession profession = new Profession(rs.getInt(PROF_ID),rs.getString(PROFESSION));
                professions.add(profession);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return professions;
    }

    @Override
    public void add(Profession profession) {
        try (Connection connection = DaoUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(ADD)){
            statement.setString(1,profession.getProfession());
            statement.execute();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public Profession getById(int id) {
        Connection connection = DaoUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        Profession profession = null;
        try {
            statement = connection.prepareStatement(GET_BY_ID);
            statement.setInt(1,id);
            rs = statement.executeQuery();
            if (rs.next()){
                profession = new Profession(rs.getInt(PROF_ID),rs.getString(PROFESSION));
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        } finally {
            DaoUtil.close(statement,rs,connection);
        }
        return profession;
    }

    @Override
    public Profession getByTitle(String title){
        Connection connection = DaoUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        Profession profession = null;

        try {
            statement = connection.prepareStatement(GET_BY_TITLE);
            statement.setString(1,title);
            rs = statement.executeQuery();
            if (rs.next()){
                profession = new Profession(rs.getInt(PROF_ID),rs.getString(PROFESSION));
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        finally {
            DaoUtil.close(statement,rs,connection);
        }
        return profession;
    }

    @Override
    public List<Profession> getByTitle(String title, String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Profession profession) {
        try (Connection connection = DaoUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE)){
            statement.setInt(1,profession.getId());
            statement.execute();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void edit(Profession profession) {
        try (Connection connection = DaoUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(EDIT)){
            statement.setString(1,profession.getProfession());
            statement.setInt(2,profession.getId());
            statement.execute();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}
