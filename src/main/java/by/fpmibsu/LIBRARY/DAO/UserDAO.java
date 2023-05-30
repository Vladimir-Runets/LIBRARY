package by.fpmibsu.LIBRARY.DAO;

import java.sql.*;
import java.util.Optional;

import by.fpmibsu.LIBRARY.entity.User;
import by.fpmibsu.LIBRARY.util.ConnectionManager;
import lombok.SneakyThrows;

public class UserDAO implements GenericDAO<Integer, User> {

    private static final UserDAO INSTANCE = new UserDAO();

    private static final String SAVE_SQL = "INSERT INTO users (login, password, mail) VALUES (?,?,?)";

    public static UserDAO getInstance(){
        return INSTANCE;
    }

    @Override
    public Optional<User> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Integer id){
        return false;
    }

    @Override
    public void update(User object){

    }

    @Override
    @SneakyThrows
    public User save(User entity){
        try(var connection = ConnectionManager.get();
        var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setObject(1, entity.getLogin());
            preparedStatement.setObject(2, entity.getPassword());
            preparedStatement.setObject(3, entity.getMail());

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            entity.setUserID(generatedKeys.getObject("user_id", Integer.class));

            return entity;
        }
    }
}
