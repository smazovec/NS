package ru.gb.storage.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Paths;
import ru.gb.storage.service.DirectoryService;
import ru.gb.storage.web.util.JspHelper;

@MultipartConfig(fileSizeThreshold = 1024 * 1024)
@WebServlet("/upload")
public class UploadServlet extends HttpServlet {

  private final DirectoryService directoryService = DirectoryService.getInstance();

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    var file = req.getPart("fileName");
    var path = Paths.get(req.getParameter("path")
        .concat("\\")
        .concat(file.getSubmittedFileName()));

    directoryService.uploadFile(path, file.getInputStream());
    req.setAttribute("path", path.getParent());
    req.setAttribute("files", directoryService.lsDir(path.getParent()));
    req.getRequestDispatcher(JspHelper.getPath("storage")).forward(req, resp);

  }

}
