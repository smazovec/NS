package ru.gb.storage.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import ru.gb.storage.web.util.JspHelper;

@WebServlet("/prepare-crete")
public class PrepareCreateFolderServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.getRequestDispatcher(JspHelper.getPath("prepare-create")).forward(req, resp);
  }

}
