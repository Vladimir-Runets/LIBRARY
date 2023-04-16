package by.fpmibsu.LIBRARY.DAO;

import by.fpmibsu.LIBRARY.entity.Tag;
import by.fpmibsu.LIBRARY.exception.DAOException;

import java.sql.*;

public class TagDAO extends AbstractDAO<Tag, Integer> {

    @Override
    protected String getCreateQuery(Tag tag) {
        return "INSERT INTO users (tag_name) VALUES (?);";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE tags SET tag_name = ? WHERE tag_id = ?;";
    }

    @Override
    protected String getSelectByIdQuery() {
        return "SELECT * FROM tags WHERE tag_id = ?;";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM tags WHERE tag_id = ?";
    }

    @Override
    protected String getCountRowsQuery() {
        return "SELECT count(*) FROM tags;";
    }

    @Override
    protected void setIdStatement(PreparedStatement statement, Integer tagID) throws DAOException {
        try {
            statement.setInt(1, tagID);
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    @Override
    protected void setObjectStatement(PreparedStatement statement, Tag tag) throws DAOException {
        try {
            if (tag.getTagID() != 0) {
                statement.setInt(1, tag.getTagID());
                statement.setString(2, tag.getCategory());
            } else {
                statement.setString(1, tag.getCategory());
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    @Override
    protected Tag readObject(ResultSet resultSet) throws DAOException {
        Tag tag = new Tag();
        try {
            tag.setTagID(resultSet.getInt("tag_id"));
            tag.setCategory(resultSet.getString("category"));
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
        return tag;
    }

    @Override
    public void delete(Integer tagId) throws DAOException {
        Tag tag = read(tagId);
        String query = "DELETE FROM tags WHERE tag_id = " + tag.getTagID() + ";";
        executeQuery(query);
        super.delete(tagId);
    }

    private void executeQuery(String query) throws DAOException {
        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(query);
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    public Tag getRandomTag() throws DAOException {
        Tag tag = new Tag();

        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement()) {

            String query = "SELECT * FROM tags LIMIT 1;";
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                tag = readObject(resultSet);
            } else {
                throw new DAOException("No tag entry was found!");
            }

        } catch (Exception e) {
            throw new DAOException(e.getMessage(), e);
        }
        return tag;
    }
}
