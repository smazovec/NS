package ru.gb.storage.validator;

import lombok.Value;

@Value(staticConstructor = "of")
public class Error {

  String code;
  String message;

}