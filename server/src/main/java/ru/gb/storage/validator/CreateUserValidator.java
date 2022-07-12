package ru.gb.storage.validator;

import static lombok.AccessLevel.PRIVATE;

import lombok.NoArgsConstructor;
import ru.gb.storage.dao.UserDao;
import ru.gb.storage.dto.CreateUserDto;

@NoArgsConstructor(access = PRIVATE)
public class CreateUserValidator implements Validator<CreateUserDto> {

  private static final CreateUserValidator INSTANCE = new CreateUserValidator();
  private final UserDao userDao = UserDao.getInstance();

  @Override
  public ValidationResult isValid(CreateUserDto object) {
    var validationResult = new ValidationResult();

    if (object.getLogin().isEmpty()) {
      validationResult.add(Error.of("invalid.login", "Login is empty"));
    }
    if (object.getPassword().isEmpty()) {
      validationResult.add(Error.of("invalid.password", "Password is empty"));
    }
    if (object.getName().isEmpty()) {
      validationResult.add(Error.of("invalid.name", "Name is empty"));
    }
    if (userDao.findByLogin(object.getLogin()).isPresent()) {
      validationResult.add(Error.of("duplicate.login", "Login is busy"));
    }
    return validationResult;
  }

  public static CreateUserValidator getInstance() {
    return INSTANCE;
  }

}
