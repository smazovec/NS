package ru.gb.storage.mapper;

public interface Mapper<F, T> {

  T mapFrom(F object);

}
