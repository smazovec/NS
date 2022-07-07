package ru.gb.storage.validator;

public interface Validator<T> {

  ValidationResult isValid(T object);

}
