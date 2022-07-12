package ru.gb.storage.web.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;
import ru.gb.storage.dto.UserDto;

@WebFilter("/*")
public class AuthorizationFilter implements Filter {

  private static final Set<String> PUBLIC_PATH = Set.of("/login", "/logout", "/registration");

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
      FilterChain filterChain) throws IOException, ServletException {

    var uri = ((HttpServletRequest) servletRequest).getRequestURI();
    if (isPublicPath(uri) || isUserLoggedIn(servletRequest)) {
      filterChain.doFilter(servletRequest, servletResponse);
    } else {
      ((HttpServletResponse) servletResponse).sendRedirect("/login");
    }
  }

  private boolean isUserLoggedIn(ServletRequest servletRequest) {
    var user = (UserDto) ((HttpServletRequest) servletRequest).getSession().getAttribute("user");
    return user != null;
  }

  private boolean isPublicPath(String uri) {
    return PUBLIC_PATH.stream().anyMatch(uri::startsWith);
  }

}
