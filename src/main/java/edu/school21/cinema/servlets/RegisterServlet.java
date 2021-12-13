package edu.school21.cinema.servlets;

import edu.school21.cinema.services.UsersService;
import lombok.SneakyThrows;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/signUp")
public class RegisterServlet extends HttpServlet {
    private UsersService usersService;

    public void init(ServletConfig config) {
        ServletContext context  = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) context
                .getAttribute("springContext");
        this.usersService = springContext.getBean(UsersService.class);
    }

    @SneakyThrows
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        if (usersService.signUp(email, firstname, lastname, phone, password)) {
            request.getSession().setAttribute("auth", 1);
            response.sendRedirect("/signIn");
        } else {
            request.getSession().setAttribute("retry", 1);
            request.getSession().setAttribute("email", email);
            request.getSession().setAttribute("firstname", firstname);
            request.getSession().setAttribute("lastname", lastname);
            request.getSession().setAttribute("phone", phone);
            response.sendRedirect("/signUp");
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/signUp.jsp").forward(request,response);
    }
}