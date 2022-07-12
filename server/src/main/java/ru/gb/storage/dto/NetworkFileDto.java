package ru.gb.storage.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class NetworkFileDto {

  boolean isParent;
  boolean isDir;
  String path;
  String name;

}
