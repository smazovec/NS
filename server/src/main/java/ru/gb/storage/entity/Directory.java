package ru.gb.storage.entity;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Directory {

  private String path;
  private boolean isRoot;
  private List<NetworkFile> networkFiles;

}
