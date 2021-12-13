package edu.school21.cinema.servlets;

import edu.school21.cinema.services.UsersService;
import lombok.SneakyThrows;
import org.apache.commons.io.IOCase;
import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    public static final int PREFIX_LENGTH = 15;
    private UsersService usersService;

    public void init(ServletConfig config) {
        ServletContext context  = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
        this.usersService = springContext.getBean(UsersService.class);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("authenticated");
        request.getSession().removeAttribute("email");
        request.getSession().removeAttribute("firstname");
        request.getSession().removeAttribute("lastname");
        response.sendRedirect("/signIn");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<List<String>> fileInfoList = analyzeUploadFolder(request);
        String picName = usersService.getPicName((String)request.getSession().getAttribute("email"));

        if (picName == null)
            picName = "xxxxxxxxxxxxxx-default.png";
        request.setAttribute("fileInfo", fileInfoList);
        request.setAttribute("avatar", picName);
        request.setAttribute("logInfo", formationLogInfo((String) request.getSession().getAttribute("email")));
        request.getRequestDispatcher("/WEB-INF/jsp/profile.jsp").forward(request,response);
    }

    public List<List<String>> formationLogInfo(String email) {
        List<List<String>> logInfo = new ArrayList<>();
        List<String> bdLogInfo = usersService.getLogInfo(email);

        for (String line : bdLogInfo) {
            List<String> tmp = new ArrayList<>();
            String[] buf = line.split("/");
            for (String bufLine : buf)
                tmp.add(bufLine.replace(". ", ", "));
            logInfo.add(tmp);
        }

        return (logInfo);
    }

    @SneakyThrows
    public List<List<String>> analyzeUploadFolder(HttpServletRequest request) {
        File dir = new File("./src/main/resources/uploads/");

        FileFilter fileFilter = new WildcardFileFilter("*.JPG", IOCase.INSENSITIVE);
        FileFilter fileFilter1 = new WildcardFileFilter("*.PNG", IOCase.INSENSITIVE);
        FileFilter fileFilter2 = new WildcardFileFilter("*.GIF", IOCase.INSENSITIVE);
        FileFilter combinedFilter = f -> fileFilter.accept(f) || fileFilter1.accept(f) || fileFilter2.accept(f);
        File[] fileList = dir.listFiles(combinedFilter);
        if (fileList.length > 0)
            Arrays.sort(fileList, LastModifiedFileComparator.LASTMODIFIED_COMPARATOR);

        List<List<String>> generalList = new ArrayList<>();

        for (File f : fileList) {
            List<String> tmp = new ArrayList<>();
            tmp.add(f.getName());
            tmp.add(formatFileSize(f.length()));
            tmp.add(Files.probeContentType(f.toPath()));
            generalList.add(tmp);
        }
        return generalList;
    }

    public String formatFileSize(long size) {
        double k = size / 1024.0;
        double m = ((size / 1000.0) / 1024.0);

        DecimalFormat dec = new DecimalFormat("0");

        return (m > 1) ? dec.format(m).concat(" MB") :
                (k > 1) ? dec.format(k).concat(" KB")
                        : dec.format(size).concat(" Bytes");
    }

}
