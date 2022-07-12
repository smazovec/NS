package ru.gb.storage.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import ru.gb.storage.dto.CreateUserDto;
import ru.gb.storage.exception.ValidationException;
import ru.gb.storage.service.UserService;
import ru.gb.storage.web.util.JspHelper;

@WebServlet(value = "/registration", name = "RegistrationServlet")
public class RegistrationServlet extends HttpServlet {

  private final UserService userService = UserService.getInstance();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.getRequestDispatcher(JspHelper.getPath("registration")).forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    var userDto = CreateUserDto.builder()
        .login(req.getParameter("login"))
        .password(req.getParameter("password"))
        .name(req.getParameter("name"))
        .build();

    try {
      userService.create(userDto);
      resp.sendRedirect("/login?login=" + req.getParameter("login"));
    } catch (ValidationException exception) {
      req.setAttribute("errors", exception.getErrors());
      doGet(req, resp);
    }
  }

}
