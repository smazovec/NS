package ru.gb.storage.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserDto {

  Integer id;
  String login;
  String name;

}
