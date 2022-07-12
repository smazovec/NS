package ru.gb.storage.dto;

import java.util.List;
import lombok.Builder;
import lombok.Value;
import ru.gb.storage.entity.NetworkFile;

@Value
@Builder
public class DirectoryDto {

  boolean isRoot;
  List<NetworkFile> networkFile;

}
