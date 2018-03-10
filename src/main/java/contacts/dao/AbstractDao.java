package contacts.dao;

import java.sql.*;
import java.util.*;

import contacts.model.*;
import contacts.utils.*;


public abstract class AbstractDao<T extends BaseEntity> implements IDao<T> {

    @Override
    public List<T> getAll() {
        try (Connection connection = DaoUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(getSelectedAllQuery());
             ResultSet rs = preparedStatement.executeQuery()) {
            List<T> entityList = new ArrayList<>();

            while (rs.next()) {
                entityList.add(getEntity(rs));
            }

            return CollectionsUtil.isEmpty(entityList) ? null : entityList;

        } catch (SQLException ignore) {

        }
        return null;
    }

    @Override
    public T create(T entity) {
        ResultSet rs = null;
        try (Connection connection = DaoUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(
                getCreateQuery(), Statement.RETURN_GENERATED_KEYS)) {
            fillCreateStatement(preparedStatement, entity);
            preparedStatement.executeUpdate();

            rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                int generatedId = rs.getInt(1);
                return getById(generatedId);
            }
        } catch (SQLException ignore) {

        } finally {
            DaoUtil.close(rs);
        }
        return null;
    }

    @Override
    public T getById(int id) {
        ResultSet rs = null;
        try (Connection connection = DaoUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(getSelectByIdQuery())) {
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            if (rs.next())
                return getEntity(rs);
        } catch (SQLException ignore) {

        } finally {
            DaoUtil.close(rs);
        }
        return null;
    }

    @Override
    public void delete(T entity) {
        try (Connection connection = DaoUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(getDeleteQuery())) {
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.execute();
        } catch (SQLException ignore) {

        }
    }

    @Override
    public void edit(T entity) {
        try (Connection connection = DaoUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(getEditQuery())) {
            fillEditStatement(preparedStatement, entity);
            preparedStatement.executeUpdate();
        } catch (SQLException ignore) {

        }
    }

    @Override
    public T getByTitle(String title) {
        return CollectionsUtil.getOneFrom(getByTitle(title, null));
    }

    protected abstract String getSelectedAllQuery();

    protected abstract String getSelectByIdQuery();

    protected abstract String getDeleteQuery();

    protected abstract String getCreateQuery();

    protected abstract String getEditQuery();

    protected abstract T getEntity(ResultSet rs) throws SQLException;

    protected abstract void fillCreateStatement(PreparedStatement preparedStatement, T entity) throws SQLException;

    protected abstract void fillEditStatement(PreparedStatement preparedStatement, T entity) throws SQLException;
}
