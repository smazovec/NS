package ru.gb.storage.service;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;
import static lombok.AccessLevel.PRIVATE;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import ru.gb.storage.dao.NetworkFileDao;
import ru.gb.storage.dto.NetworkFileDto;
import ru.gb.storage.mapper.NetworkFileMapper;

@NoArgsConstructor(access = PRIVATE)
public class DirectoryService {

  private static final DirectoryService INSTANCE = new DirectoryService();
  private final UserService userService = UserService.getInstance();
  private final NetworkFileDao networkFileDao = NetworkFileDao.getInstance();
  private final NetworkFileMapper networkFileMapper = NetworkFileMapper.getInstance();

  public List<NetworkFileDto> lsDir(Path path) {
    return networkFileDao.lsDir(path).stream().map(networkFileMapper::mapFrom)
        .collect(Collectors.toList());
  }

  @SneakyThrows
  public void createFolder(Path path) {
    Files.createDirectory(path);
  }

  @SneakyThrows
  public void uploadFile(Path path, InputStream fileContent) {
    try (fileContent) {
      Files.write(path, fileContent.readAllBytes(),
          CREATE,
          TRUNCATE_EXISTING);
    }
  }

  @SneakyThrows
  public Optional<InputStream> downloadFile(Path path) {
    return Files.exists(path)
        ? Optional.of(Files.newInputStream(path))
        : Optional.empty();
  }

  public static DirectoryService getInstance() {
    return INSTANCE;
  }

}
