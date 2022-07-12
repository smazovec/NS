package ru.gb.storage.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Paths;
import ru.gb.storage.service.DirectoryService;
import ru.gb.storage.web.util.JspHelper;

@WebServlet("/create")
public class CreateFolderServlet extends HttpServlet {

  private final DirectoryService directoryService = DirectoryService.getInstance();

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    var path = Paths.get(req.getParameter("path")
        .concat("\\")
        .concat(req.getParameter("folderName")));

    directoryService.createFolder(path);

    req.setAttribute("path", path.getParent());
    req.setAttribute("files", directoryService.lsDir(path.getParent()));
    req.getRequestDispatcher(JspHelper.getPath("storage")).forward(req, resp);
  }

}
