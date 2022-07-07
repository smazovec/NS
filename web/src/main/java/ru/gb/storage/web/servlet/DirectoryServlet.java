package ru.gb.storage.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Path;
import ru.gb.storage.dto.UserDto;
import ru.gb.storage.service.DirectoryService;
import ru.gb.storage.service.UserService;
import ru.gb.storage.web.util.JspHelper;

@WebServlet("/storage")
public class DirectoryServlet extends HttpServlet {

  private final DirectoryService directoryService = DirectoryService.getInstance();
  private final UserService userService = UserService.getInstance();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    var path = userService.getUserRootPath((UserDto) req.getSession().getAttribute("user"));

    req.setAttribute("path", path);
    req.setAttribute("files", directoryService.lsDir(path));
    req.getRequestDispatcher(JspHelper.getPath("storage")).forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    var path = Path.of(req.getParameter("path"));

    req.setAttribute("path", path);
    req.setAttribute("files", directoryService.lsDir(path));
    req.getRequestDispatcher(JspHelper.getPath("storage")).forward(req, resp);
  }

}
