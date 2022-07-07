package ru.gb.storage.database;

import java.sql.Connection;
import java.sql.DriverManager;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import ru.gb.storage.util.PropertiesUtil;

@UtilityClass
public class ConnectionManager {

  private static final String URL_KEY = "db.url";

  static {
    LoadDriver();
  }

  @SneakyThrows
  private static void LoadDriver() {
    Class.forName("org.sqlite.JDBC");
  }

  @SneakyThrows
  public static Connection get() {
    return DriverManager.getConnection(PropertiesUtil.get(URL_KEY));
  }

}
