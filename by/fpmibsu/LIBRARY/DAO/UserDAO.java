package by.fpmibsu.LIBRARY.DAO;

import java.sql.*;
import by.fpmibsu.LIBRARY.entity.User;
import by.fpmibsu.LIBRARY.exception.DAOException;
public class UserDAO extends AbstractDAO<User, Integer> {

    @Override
    protected String getCreateQuery(User user) {
        return "INSERT INTO users (login, password, mail) VALUES (?,?,?);";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE users SET login = ?, password = ?, mail = ? WHERE user_id = ?;";
    }

    @Override
    protected String getSelectByIdQuery() {
        return "SELECT * FROM users WHERE user_id = ?;";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM users WHERE user_id = ?";
    }

    @Override
    protected String getCountRowsQuery() {
        return "SELECT count(*) FROM users;";
    }

    @Override
    protected void setIdStatement(PreparedStatement statement, Integer userID) throws DAOException {
        try {
            statement.setInt(1, userID);
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    @Override
    protected void setObjectStatement(PreparedStatement statement, User user) throws DAOException {
        try {
            if (user.getUserID() != 0) {
                statement.setInt(1, user.getUserID());
                statement.setString(2, user.getLogin());
                statement.setString(3, user.getPassword());
                statement.setString(4, user.getMail());
            } else {
                statement.setString(1, user.getLogin());
                statement.setString(2, user.getPassword());
                statement.setString(3, user.getMail());
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    @Override
    protected User readObject(ResultSet resultSet) throws DAOException {
        User user = new User();
        try {
            user.setUserID(resultSet.getInt("user_id"));
            user.setLogin(resultSet.getString("login"));
            user.setPassword(resultSet.getString("password"));
            user.setMail(resultSet.getString("mail"));
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
        return user;
    }

    @Override
    public void delete(Integer userId) throws DAOException {
        User user = read(userId);
        new UserLiteratureDAO().delete(user);
        super.delete(userId);
    }

    public User getRandomUser() throws DAOException {
        User user = new User();

        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement()) {

            String query = "SELECT * FROM users LIMIT 1;";
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                user = readObject(resultSet);
            } else {
                throw new DAOException("No user entry was found!");
            }

        } catch (Exception e) {
            throw new DAOException(e.getMessage(), e);
        }
        return user;
    }
}
