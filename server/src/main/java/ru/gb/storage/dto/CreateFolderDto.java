package ru.gb.storage.dto;

import java.nio.file.Path;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateFolderDto {

  Path path;

}
