package by.fpmibsu.LIBRARY.Servlet;

import by.fpmibsu.LIBRARY.DTO.UserDto;
import by.fpmibsu.LIBRARY.Service.UserService;
import by.fpmibsu.LIBRARY.util.JspHelper;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final UserService userService = UserService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath("login"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userService.login(req.getParameter("username"), req.getParameter("password"))
                        .ifPresentOrElse(
                                user -> onLoginSuccess(user, req, resp),
                                () -> onLoginFail(req, resp)
                        );
    }

    @SneakyThrows
    private void onLoginSuccess(UserDto user, HttpServletRequest req, HttpServletResponse resp){
        req.getSession().setAttribute("user", user);
        resp.sendRedirect("/JSP/UserPage.jsp");
    }

    @SneakyThrows
    private void onLoginFail(HttpServletRequest req, HttpServletResponse resp) {
        resp.sendRedirect("/JSP/Entrance.jsp?error");
    }
}
