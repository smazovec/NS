package ru.gb.storage.exception;

import java.util.List;
import lombok.Getter;
import ru.gb.storage.validator.Error;

public class ValidationException extends RuntimeException {

  @Getter
  private final List<Error> errors;

  public ValidationException(List<Error> errors) {
    this.errors = errors;
  }

}
