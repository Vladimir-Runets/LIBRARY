package by.fpmibsu.LIBRARY.Service;

import by.fpmibsu.LIBRARY.DAO.UserDAO;
import by.fpmibsu.LIBRARY.DTO.CreateUserDto;
import by.fpmibsu.LIBRARY.mapper.CreateUserMapper;

public class UserService {

    private static final UserService INSTANCE = new UserService();
    private static final UserDAO userDAO = UserDAO.getInstance();
    private static final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();

    public static Integer create(CreateUserDto userDto){
        var userEntity = createUserMapper.mapFrom(userDto);
        userDAO.save(userEntity);
        return userEntity.getUserID();
    }

    public static UserService getInstance(){
        return INSTANCE;
    }

}
