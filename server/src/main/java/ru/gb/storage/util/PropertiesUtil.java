package ru.gb.storage.util;

import java.util.Properties;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PropertiesUtil {

  public static final Properties PROPERTIES = new Properties();

  static {
    LoadProperties();
  }

  @SneakyThrows
  private static void LoadProperties() {
    var inputStream = PropertiesUtil.class.getClassLoader()
        .getResourceAsStream("application.properties");
    PROPERTIES.load(inputStream);
  }

  public String get(String key) {
    return PROPERTIES.getProperty(key);
  }

}
