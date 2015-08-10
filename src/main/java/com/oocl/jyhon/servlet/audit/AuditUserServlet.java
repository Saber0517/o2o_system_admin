package com.oocl.jyhon.servlet.audit;

import com.oocl.jyhon.entiy.UserEntity;
import com.oocl.jyhon.service.UserEntityService;
import com.oocl.jyhon.serviceimpl.UserEntityServiceImpl;
import com.oocl.jyhon.util.FormDataUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by WhiteSaber on 15/8/9.
 */
@WebServlet(name = "AuditUserServlet", urlPatterns = {"/AuditUserServlet"})
public class AuditUserServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获得表格参数
        String pathTemp = InitFileFolder();
        List<FileItem> items = getFileItems(request, pathTemp);
        UserEntity userEntity = getUserEntity(items);

        UserEntityService userEntityService = new UserEntityServiceImpl();
        Integer result = userEntityService.changeEntityStatus(userEntity);
        request.getRequestDispatcher("main/auditUserApply.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }


    private String InitFileFolder() {
        String pathTemp = this.getServletContext().getRealPath("\\temp");
        System.out.println(pathTemp);
        String pathUpload = this.getServletContext().getRealPath("/upload");
        System.out.println(pathUpload);
        File path = new File(pathTemp);
        path.mkdir();
        path = new File(pathUpload);
        path.mkdir();
        return pathTemp;
    }

    private List<FileItem> getFileItems(HttpServletRequest request, String pathTemp) {
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        diskFileItemFactory.setRepository(new File(pathTemp));
        diskFileItemFactory.setSizeThreshold(10240);
        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
        List<FileItem> items = null;
        try {
            items = servletFileUpload.parseRequest(request);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        return items;
    }

    private UserEntity getUserEntity(List<FileItem> items) {
        UserEntity userEntity = new UserEntity();

        for (FileItem item : items) {
            if (item.isFormField()) {
                String fileName = item.getFieldName();
                if (fileName.equals("name")) {
                    userEntity.setUserName(item.getString());
                } else if (fileName.equals("psw")) {
                    userEntity.setPassword(item.getString());
                } else if (fileName.equals("userID")) {
                    userEntity.setUserID(Integer.valueOf(item.getString()));
                } else if (fileName.equals("tel")) {
                    userEntity.setTel(item.getString());
                } else if (fileName.equals("idCard")) {
                    userEntity.setIdCard(item.getString());
                } else if (fileName.equals("License")){
                    userEntity.setLicense(item.getString());
                }else if (fileName.equals("type")) {
                    userEntity.setStatusId(Integer.valueOf(item.getString()));
                }
                System.out.print(item.getFieldName() + ":");
                System.out.println(item.getString());
            } else {
                String pic = item.getFieldName();
                userEntity.setLicense(item.getName());
                System.out.println(item.getName());
            }
        }
        return userEntity;
    }
}
