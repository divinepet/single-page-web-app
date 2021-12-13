package edu.school21.cinema.listeners;

import edu.school21.cinema.config.AppServletConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        ServletContext servletContext = arg0.getServletContext();
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(AppServletConfig.class);
        servletContext.setAttribute("springContext", applicationContext);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {}
}
