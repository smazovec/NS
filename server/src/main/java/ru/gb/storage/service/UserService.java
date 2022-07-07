package ru.gb.storage.service;

import static lombok.AccessLevel.PRIVATE;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import ru.gb.storage.dao.UserDao;
import ru.gb.storage.dto.CreateUserDto;
import ru.gb.storage.dto.UserDto;
import ru.gb.storage.entity.User;
import ru.gb.storage.exception.ValidationException;
import ru.gb.storage.mapper.CreateUserMapper;
import ru.gb.storage.mapper.UserMapper;
import ru.gb.storage.util.PropertiesUtil;
import ru.gb.storage.validator.CreateUserValidator;

@NoArgsConstructor(access = PRIVATE)
public class UserService {

  private static final UserService INSTANCE = new UserService();
  private final CreateUserValidator userValidator = CreateUserValidator.getInstance();
  private final UserDao userDao = UserDao.getInstance();
  private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();
  private final UserMapper userMapper = UserMapper.getInstance();
  private Optional<User> user;

  public Optional<UserDto> login(String login, String password) {
    this.user = userDao.findByLoginAndPassword(login, password);
    return this.user.map(userMapper::mapFrom);
  }

  @SneakyThrows
  public User create(CreateUserDto userDto) {

    var validationResult = userValidator.isValid(userDto);
    if (!validationResult.isValid()) {
      throw new ValidationException(validationResult.getErrors());
    }

    var userEntity = createUserMapper.mapFrom(userDto);
    userDao.save(userEntity);

    return userEntity;
  }

  @SneakyThrows
  public Path getUserRootPath(UserDto user) {

    Path userRootPath = Paths.get(PropertiesUtil.get("root.dir") + user.getLogin());

    // Create root user dir
    if (!Files.exists(userRootPath)) {
      Files.createDirectory(userRootPath);
    }

    return userRootPath;

  }

  public static UserService getInstance() {
    return INSTANCE;
  }

}
