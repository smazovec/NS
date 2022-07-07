package ru.gb.storage.mapper;

import static lombok.AccessLevel.PRIVATE;

import lombok.NoArgsConstructor;
import ru.gb.storage.dto.UserDto;
import ru.gb.storage.entity.User;

@NoArgsConstructor(access = PRIVATE)
public class UserMapper implements Mapper<User, UserDto> {

  private static final UserMapper INSTANCE = new UserMapper();

  @Override
  public UserDto mapFrom(User object) {
    return UserDto.builder()
        .id(object.getId())
        .login(object.getLogin())
        .name(object.getName())
        .build();
  }

  public static UserMapper getInstance() {
    return INSTANCE;
  }

}
