package edu.school21.cinema.servlets;

import edu.school21.cinema.services.UsersService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@WebServlet(urlPatterns = {"/FWA/images/*", "/images"})
public class ShowImageServlet extends HttpServlet {
    static final int fileMaxSize = 10000000;
    static final int memMaxSize = 10000000;
    private UsersService usersService;

    @Autowired
    String imgStorage;

    public void init(ServletConfig config) {
        ServletContext context  = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
        springContext.getAutowireCapableBeanFactory().autowireBean(this);
        this.usersService = springContext.getBean(UsersService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

//        Open image in new tab

        if (!request.getRequestURL().toString().contains("FWA"))
            return;
        response.setContentType("image/jpeg");
        String url = request.getRequestURL().toString();
        String imageName = url.substring(url.lastIndexOf("/")).replaceAll("%20"," ");

        ServletOutputStream out = response.getOutputStream();
        FileInputStream flinp;
        try {
            flinp = new FileInputStream(imgStorage + imageName);
        } catch (FileNotFoundException e) {
            flinp = new FileInputStream(imgStorage + "xxxxxxxxxxxxxx-default.png");
        }
        BufferedInputStream buffinp = new BufferedInputStream(flinp);
        BufferedOutputStream buffoup = new BufferedOutputStream(out);

        int ch = 0;
        while ((ch = buffinp.read()) != -1)
            buffoup.write(ch);

        buffinp.close();
        flinp.close();
        buffoup.close();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        Download file

        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        diskFileItemFactory.setRepository(new File(imgStorage));
        diskFileItemFactory.setSizeThreshold(memMaxSize);

        ServletFileUpload upload = new ServletFileUpload(diskFileItemFactory);
        upload.setSizeMax(fileMaxSize);

        try {
            List<FileItem> fileItems = upload.parseRequest(request);

            for (FileItem fileItem : fileItems) {
                System.out.println("ShowImageServlet, name: " + fileItem.getName());
                if (!fileItem.isFormField()) {
                    if (!FilenameUtils.getExtension(fileItem.getName()).toLowerCase(Locale.ROOT).equals("jpg")
                            && !FilenameUtils.getExtension(fileItem.getName()).toLowerCase(Locale.ROOT).equals("jpeg")
                            && !FilenameUtils.getExtension(fileItem.getName()).toLowerCase(Locale.ROOT).equals("png")
                            && !FilenameUtils.getExtension(fileItem.getName()).toLowerCase(Locale.ROOT).equals("gif"))
                        continue;
                    String fileName = dateFormat.format(new Date()) + "-" + fileItem.getName();
                    usersService.setPicName((String) request.getSession().getAttribute("email"), fileName);
                    File file = new File(imgStorage + fileName.substring(fileName.lastIndexOf("/") + 1));
                    fileItem.write(file);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/profile");
    }
}
