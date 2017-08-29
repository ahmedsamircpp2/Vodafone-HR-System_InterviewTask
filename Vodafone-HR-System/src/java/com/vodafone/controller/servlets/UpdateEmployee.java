/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vodafone.controller.servlets;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.vodafone.db.model.dbutils.HR_DB_Helper;
import com.vodafone.db.model.dto.Employee;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileUploadException;

/**
 *
 * @author ahmed_amer
 */
public class UpdateEmployee extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, FileUploadException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            uploadData(request, response);
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } finally {
            out.close();
        }
    }

    /**
     * get UPdated Employee Data  [ set it into Employee Object] return it
     * @param request
     * @return Current Updated Employee
     * @throws FileUploadException 
     */
    public Employee getCurrentEmployee(MultipartRequest request) throws FileUploadException {

        Employee employee = new Employee();

        try {
            employee.setIdEmployee(Integer.parseInt(request.getParameter("idEmployee")));
            employee.setEnumGender(request.getParameter("gender"));
            employee.setIdDepartment(Integer.parseInt(request.getParameter("departmentID")));
            employee.setIdManager(Integer.parseInt(request.getParameter("managerID")));
            employee.setStrEmployeeFullname(request.getParameter("empName"));
            employee.setStrImageUrl(request.getParameter("uploaded"));
            employee.setStrMobile(request.getParameter("mobile"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employee;

    }
    /**
     * Validation  Not Complete [ validate Employee Data ]
     * @param request
     * @return 
     */
    public String validateEmployeeData(HttpServletRequest request) {
//        String errorCode = "-100";
//        String successCode = "200";
//        if (request.getParameter("gender").isEmpty()) {
//            return errorCode + ": Gender is Empty";
//        }
//        //   if(request.getParameter(errorCode))

        return null;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (FileUploadException ex) {
            Logger.getLogger(AddEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (FileUploadException ex) {
            Logger.getLogger(AddEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    /**
     *  Update  Employe Data Into Database
     * @param request
     * @param response
     * @param multipartRequest
     * @return
     * @throws FileUploadException 
     */
    private String upload(HttpServletRequest request, HttpServletResponse response, MultipartRequest multipartRequest) throws FileUploadException {
        /**
         * Get uploaded file
         */
        String newFileName = multipartRequest.getParameter("imageName");
        try {
            File tmpFile = multipartRequest.getFile("uploaded");
            int posLastDot = tmpFile.getName().lastIndexOf(".");

            String extension = "";
            if (posLastDot > 0) {
                extension = tmpFile.getName().substring(posLastDot, tmpFile.getName().length());
            }
            newFileName = new Date().getTime() + "_ProfileImage" + extension;

            /**
             * Do whatever adjustment you want to this temporary file
             */
            String imagesPath = getDirctoryPath("images\\upload\\", request);
            File dirToMove = new File(imagesPath);
            File fileToMove = new File(dirToMove, newFileName);
            tmpFile.renameTo(fileToMove);

            /**
             * Delete temporary file
             */
            tmpFile.delete();

        } catch (Exception e) {
            System.out.println("No  File To Upload");
        }

        Employee currentAddedEmployee;
        currentAddedEmployee = getCurrentEmployee(multipartRequest);
        currentAddedEmployee.setStrImageUrl(newFileName);
        insertEmployee(response, currentAddedEmployee);




        return newFileName;
    }
    /**
     * get Home Directory  for Images
     * @param string
     * @param request
     * @return Full Page Of Uploaded Images
     */
    public String getDirctoryPath(String string, HttpServletRequest request) {
        String path = getServletContext().getRealPath("/");
        String newpath = "";
        newpath += path.substring(0, path.length() - 11);
        newpath += "\\web\\" + string;
        return newpath;
    }

    /**
     *  Call upload Function to Insert Employee Data
     * @param request
     * @param response
     * @return 
     */
    private String uploadData(HttpServletRequest request, HttpServletResponse response) {
        String res = "";
        try {
            String tmpPath = getDirctoryPath("tmp\\", request);
            MultipartRequest multipartRequest = new MultipartRequest(request, tmpPath, /* 1MB */ 1024 * 1024, new DefaultFileRenamePolicy());
            if (multipartRequest.getParameter("save") != null) {
                res = upload(request, response, multipartRequest);
            } else {
                throw new IOException("Display Upload Dialogue");
            }
        } catch (Exception ex) {
            System.out.println("");
        }
        return res;
    }

    private String getImageType(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    private String insertEmployee(HttpServletResponse response, Employee currentEmployee) {
        HR_DB_Helper obB_Helper = new HR_DB_Helper();
        return obB_Helper.update(currentEmployee);
    }
}
