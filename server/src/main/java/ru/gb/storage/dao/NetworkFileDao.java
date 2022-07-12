package ru.gb.storage.dao;

import static java.nio.file.Files.isDirectory;
import static java.nio.file.Files.walk;
import static java.util.stream.Collectors.toList;
import static lombok.AccessLevel.PRIVATE;

import java.nio.file.Path;
import java.util.List;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import ru.gb.storage.entity.NetworkFile;
import ru.gb.storage.util.PropertiesUtil;

@NoArgsConstructor(access = PRIVATE)
public class NetworkFileDao {

  private static final NetworkFileDao INSTANCE = new NetworkFileDao();

  @SneakyThrows
  public List<NetworkFile> lsDir(Path path) {

    var rootPath = Path.of(PropertiesUtil.get("root.dir"));

    try (var walk = walk(path, 1)) {
      return walk.filter(f -> !f.getParent().equals(rootPath))
          .map(p -> p != path ?
              NetworkFile.builder()
                  .isParent(false)
                  .isDir(isDirectory(p))
                  .file(p.toFile())
                  .path(p.toFile().getAbsolutePath())
                  .name(p.toFile().getName())
                  .build()
              : NetworkFile.builder() // Parent dir ..
                  .isParent(true)
                  .isDir(isDirectory(p.getParent()))
                  .file(p.getParent().toFile())
                  .path(p.getParent().toFile().getAbsolutePath())
                  .name("..")
                  .build())
          .sorted()
          .collect(toList());
    }
  }

  public static NetworkFileDao getInstance() {
    return INSTANCE;
  }

}
