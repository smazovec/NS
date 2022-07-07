package ru.gb.storage.entity;

import java.io.File;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NetworkFile implements Comparable<NetworkFile> {

  private boolean isParent;
  private boolean isDir;
  private File file;
  private String path;
  private String name;

  @Override
  public int compareTo(NetworkFile o) {
    return Boolean.compare(o.isDir, isDir);
  }

}
