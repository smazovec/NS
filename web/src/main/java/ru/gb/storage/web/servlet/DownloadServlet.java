package ru.gb.storage.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import lombok.SneakyThrows;
import ru.gb.storage.service.DirectoryService;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {

  private final DirectoryService directoryService = DirectoryService.getInstance();

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    var path = Paths.get(req.getParameter("path"));

    resp.setHeader("Content-Disposition", "attachment; filename=\""
        .concat(path.getFileName().toString())
        .concat("\""));
    resp.setCharacterEncoding("UTF-8");
    resp.setContentLength(Math.toIntExact((Files.size(path))));
    resp.setContentType("application/octet-stream");

    directoryService.downloadFile(path).ifPresentOrElse(
        file -> downloadFile(file, resp),
        () -> resp.setStatus(404));
  }

  @SneakyThrows
  private void downloadFile(InputStream file, HttpServletResponse resp) {
    try (file; var outputStream = resp.getOutputStream()) {
      int currentByte;
      while ((currentByte = file.read()) != -1) {
        outputStream.write(currentByte);
      }
    }

  }

}
