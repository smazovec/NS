package ru.gb.storage.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.SneakyThrows;
import ru.gb.storage.dto.UserDto;
import ru.gb.storage.service.UserService;
import ru.gb.storage.web.util.JspHelper;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

  private final UserService userService = UserService.getInstance();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.getRequestDispatcher(JspHelper.getPath("login")).forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    userService.login(req.getParameter("login"), req.getParameter("password"))
        .ifPresentOrElse(user -> onLoginSuccess(user, req, resp), () -> onLoginFail(req, resp));
  }

  @SneakyThrows
  private void onLoginSuccess(UserDto user, HttpServletRequest req, HttpServletResponse resp) {
    req.getSession().setAttribute("user", user);
    req.getSession().setAttribute("userRootPath", userService.getUserRootPath(user));
    resp.sendRedirect("/storage");
  }

  @SneakyThrows
  private void onLoginFail(HttpServletRequest req, HttpServletResponse resp) {
    resp.sendRedirect("/login?fail&login=" + req.getParameter("login"));
  }

}
