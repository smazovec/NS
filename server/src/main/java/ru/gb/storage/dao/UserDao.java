package ru.gb.storage.dao;

import static java.sql.Statement.RETURN_GENERATED_KEYS;
import static lombok.AccessLevel.PRIVATE;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import ru.gb.storage.database.ConnectionManager;
import ru.gb.storage.entity.User;

@NoArgsConstructor(access = PRIVATE)
public class UserDao implements Dao<Integer, User> {

  private static final UserDao INSTANCE = new UserDao();
  private static final String FIND_ALL_SQL = "SELECT * FROM users";
  private static final String FIND_BY_LOGIN_SQL = "SELECT * FROM users WHERE login = ?";
  private static final String FIND_BY_LOGIN_AND_PASSWORD_SQL = "SELECT * FROM users WHERE login = ? AND password = ?";
  private static final String SAVE_SQL = "INSERT INTO users (login, password, name) VALUES (?, ?, ?)";

  @Override
  @SneakyThrows
  public List<User> findAll() {
    var connection = ConnectionManager.get();
    var prepareStatement = connection.prepareStatement(FIND_ALL_SQL);
    try (connection; prepareStatement) {
      var resultSet = prepareStatement.executeQuery();
      List<User> users = new ArrayList<>();
      while (resultSet.next()) {
        users.add(buildUser(resultSet));
      }
      return users;
    }
  }

  @Override
  public Optional<User> findById(Integer id) {
    return Optional.empty();
  }

  @Override
  public boolean delete(Integer id) {
    return false;
  }

  @Override
  public void update(User entity) {

  }

  @Override
  @SneakyThrows
  public User save(User entity) {
    var connection = ConnectionManager.get();
    var prepareStatement = connection.prepareStatement(SAVE_SQL, RETURN_GENERATED_KEYS);
    var statement = connection.createStatement();
    try (connection; prepareStatement; statement) {
      prepareStatement.setObject(1, entity.getLogin());
      prepareStatement.setObject(2, entity.getPassword());
      prepareStatement.setObject(3, entity.getName());
      prepareStatement.executeUpdate();

      var generatedKeys = statement.executeQuery("SELECT last_insert_rowid()");
      generatedKeys.next();
      entity.setId(generatedKeys.getInt(1));

      return entity;
    }
  }

  @SneakyThrows
  public Optional<User> findByLogin(String login) {
    var connection = ConnectionManager.get();
    var prepareStatement = connection.prepareStatement(FIND_BY_LOGIN_SQL);
    try (connection; prepareStatement) {
      prepareStatement.setObject(1, login);
      var resultSet = prepareStatement.executeQuery();
      if (resultSet.next()) {
        return Optional.of(buildUser(resultSet));
      } else {
        return Optional.empty();
      }
    }
  }

  @SneakyThrows
  public Optional<User> findByLoginAndPassword(String login, String password) {
    var connection = ConnectionManager.get();
    var prepareStatement = connection.prepareStatement(FIND_BY_LOGIN_AND_PASSWORD_SQL);
    try (connection; prepareStatement) {
      prepareStatement.setObject(1, login);
      prepareStatement.setObject(2, password);
      var resultSet = prepareStatement.executeQuery();
      if (resultSet.next()) {
        return Optional.of(buildUser(resultSet));
      } else {
        return Optional.empty();
      }
    }
  }

  public static UserDao getInstance() {
    return INSTANCE;
  }

  @SneakyThrows
  private User buildUser(ResultSet resultSet) {
    return User.builder()
        .id(resultSet.getInt(1))
        .login(resultSet.getString(2))
        .password(resultSet.getString(3))
        .name(resultSet.getString(4))
        .build();
  }

}