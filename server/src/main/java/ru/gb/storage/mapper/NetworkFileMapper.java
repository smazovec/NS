package ru.gb.storage.mapper;

import static lombok.AccessLevel.PRIVATE;

import lombok.NoArgsConstructor;
import ru.gb.storage.dto.NetworkFileDto;
import ru.gb.storage.entity.NetworkFile;

@NoArgsConstructor(access = PRIVATE)
public class NetworkFileMapper implements Mapper<NetworkFile, NetworkFileDto> {

  private static final NetworkFileMapper INSTANCE = new NetworkFileMapper();

  @Override
  public NetworkFileDto mapFrom(NetworkFile object) {
    return NetworkFileDto.builder()
        .isDir(object.isDir())
        .isParent(object.isParent())
        .path(object.getPath())
        .name(object.getName())
        .build();
  }

  public static NetworkFileMapper getInstance() {
    return INSTANCE;
  }

}


