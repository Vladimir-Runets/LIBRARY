package by.fpmibsu.LIBRARY.DAO;

import java.sql.*;
import java.util.*;
import by.fpmibsu.LIBRARY.entity.*;
import by.fpmibsu.LIBRARY.exception.DAOException;

public class UserLiteratureDAO extends AbstractDAO<UserLiterature, Integer> {

    @Override
    protected String getCreateQuery(UserLiterature userLiterature) {
        return "INSERT INTO students_courses (user_id, literature_id) VALUES (?,?);";
    }

    @Override
    protected String getSelectByIdQuery() {
        return "SELECT * FROM user_literature WHERE user_literature_id = ?;";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE user_literature SET user_id = ?, literature_id = ? WHERE user_literature_id = ?;";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM user_literature WHERE user_literature_id = ?;";
    }

    @Override
    protected String getCountRowsQuery() {
        return "SELECT count(*) FROM user_literature;";
    }

    @Override
    protected void setIdStatement(PreparedStatement statement, Integer userLiteratureId) throws DAOException {
        try {
            statement.setInt(1, userLiteratureId);
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    @Override
    protected void setObjectStatement(PreparedStatement statement, UserLiterature userLiterature) throws DAOException {
        try {
            if (userLiterature.getUserLiteratureId() != 0) {
                statement.setInt(1, userLiterature.getUserLiteratureId());
                statement.setInt(2, userLiterature.getUserId());
                statement.setInt(3, userLiterature.getLiteratureId());
            } else {
                statement.setInt(1, userLiterature.getUserId());
                statement.setInt(2, userLiterature.getLiteratureId());
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    @Override
    protected UserLiterature readObject(ResultSet resultSet) throws DAOException {
        UserLiterature userLiterature = new UserLiterature();
        try {
            userLiterature.setUserLiteratureId(resultSet.getInt("user_literature_id"));
            userLiterature.setUserId(resultSet.getInt("user_id"));
            userLiterature.setLiteratureId(resultSet.getInt("literature_id"));
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
        return userLiterature;
    }

    public UserLiterature read(int userID, int literatureID) throws DAOException {
        String query = "SELECT * FROM user_literature WHERE user_id = " + userID +
                " AND literature_id = " + literatureID + ";";

        UserLiterature userLiterature = new UserLiterature();
        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                userLiterature = readObject(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
        return userLiterature;
    }

    public List<UserLiterature> findByUserId(Integer user_id) throws DAOException {
        List<UserLiterature> userLiteratures = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement()) {

            String query = "SELECT * FROM user_literature WHERE user_id = " + user_id + ";";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int literature_id = resultSet.getInt("literature_id");
                int user_literature_id = resultSet.getInt("user_literature_id");
                userLiteratures.add(new UserLiterature(user_literature_id, user_id, literature_id));
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
        return userLiteratures;
    }

    public void delete(User user) throws DAOException {
        String query = "DELETE FROM user_literature WHERE user_id = " + user.getUserID() + ";";
        executeQuery(query);
    }

    public void delete(Literature literature) throws DAOException {
        String query = "DELETE FROM user_literature WHERE literature_id = " + literature.getLiteratureID() + ";";
        executeQuery(query);
    }

    private void executeQuery(String query) throws DAOException {
        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement()) {
             statement.execute(query);
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }
}