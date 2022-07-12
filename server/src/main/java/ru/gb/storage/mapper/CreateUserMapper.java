package ru.gb.storage.mapper;

import static lombok.AccessLevel.PRIVATE;

import lombok.NoArgsConstructor;
import ru.gb.storage.dto.CreateUserDto;
import ru.gb.storage.entity.User;

@NoArgsConstructor(access = PRIVATE)
public class CreateUserMapper implements Mapper<CreateUserDto, User> {

  private static final CreateUserMapper INSTANCE = new CreateUserMapper();

  @Override
  public User mapFrom(CreateUserDto object) {
    return User.builder()
        .login(object.getLogin())
        .password(object.getPassword())
        .name(object.getName())
        .build();
  }

  public static CreateUserMapper getInstance() {
    return INSTANCE;
  }

}
