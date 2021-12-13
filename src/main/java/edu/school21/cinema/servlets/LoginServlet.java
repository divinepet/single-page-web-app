package edu.school21.cinema.servlets;

import edu.school21.cinema.models.User;
import edu.school21.cinema.services.UsersService;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.InetAddress;

@WebServlet("/signIn")
public class LoginServlet extends HttpServlet {
    private UsersService usersService;

    public void init(ServletConfig config) {
        ServletContext context  = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
        this.usersService = springContext.getBean(UsersService.class);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        if (usersService.signIn(request.getParameter("email"), request.getParameter("password"))) {
            session.setAttribute("authenticated", 1);
            usersService.updateLogInfo(request.getParameter("email"), request.getRemoteAddr());
            User user = usersService.getUser(request.getParameter("email"));
            session.setAttribute("email", user.getEmail());
            session.setAttribute("firstname", user.getFirstName());
            session.setAttribute("lastname", user.getLastName());
            response.sendRedirect("/profile");
        } else {
            session.setAttribute("auth", -1);
            response.sendRedirect("/signIn");
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/signIn.jsp").forward(request,response);
    }

}