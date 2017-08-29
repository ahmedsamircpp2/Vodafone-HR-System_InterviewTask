/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vodafone.controller.servlets;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.vodafone.controller.constants.CommonFunctions;
import com.vodafone.controller.constants.constants;
import com.vodafone.db.model.dbutils.HR_DB_Helper;
import com.vodafone.db.model.dto.Employee;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileUploadException;

/**
 * Insert  New Employee Information  into Database 
 * @author ahmed_amer
 */
public class AddEmployee extends HttpServlet {

    /**
     * Contain Common Function
     */
    CommonFunctions commonFunctions;

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
            
            commonFunctions = new CommonFunctions();
            
            /**
             * Upload  Data into  Database if Not Successed return List Of Errors
             */
            List<String> errors = uploadData(request, response);
            
            /**
             * Concatenate Errors  as One String
             */
            if (errors != null && errors.size() > 0) {

                String errorString = "";
                for (String string : errors) {
                    errorString += "key";
                    errorString += "=";
                    errorString += string;
                    errorString += "&";
                }
                if (!errorString.isEmpty()) {
                    errorString += "key=-----------END----------------";
                }
                /**
                 * return to Same  Page to  Show Errors
                 */
                response.sendRedirect(request.getContextPath() + "/Employee/AddEmployee_Template.jsp?" + errorString);
            } else {
                /**
                 * return to  home Page
                 */
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            }
            int x = 1;
        } finally {
            out.close();
        }
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

    private List<String> upload(HttpServletRequest request, HttpServletResponse response, MultipartRequest multipartRequest) throws FileUploadException {
        /**
         * Get uploaded file
         */
        List<String> errors = new ArrayList<String>();
        String newFileName = "";
        try {
            
            File tmpFile = multipartRequest.getFile("uploaded");
            /**
             * get Image Extension
             */
            int posLastDot = tmpFile.getName().lastIndexOf(".");

            String extension = "";
            if (posLastDot > 0) {
                extension = tmpFile.getName().substring(posLastDot, tmpFile.getName().length());
            }

            /**
             * Rename New Image 
             */
            newFileName = new Date().getTime() + "ProfileImage" + extension;


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
          //  errors.add("Error Yor Uploaded Image is Null");
        }


        /**
         * get The New Employee Data, CHeck it for Consistance
         */
        Employee currentAddedEmployee;
        currentAddedEmployee = commonFunctions.getCurrentEmployee(multipartRequest);
        currentAddedEmployee.setStrImageUrl(newFileName);
        errors.addAll(commonFunctions.validateEmployeeData(request, currentAddedEmployee));
        if (errors.size() == 0) {
            insertEmployee(response, currentAddedEmployee);
        }
        /**
         * return Errors if Found 
         */
        return errors;
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
    private List<String> uploadData(HttpServletRequest request, HttpServletResponse response) {
        List<String> res = null;
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

    /**
     * Insert Employee 
     * @param response
     * @param currentEmployee
     * @return 
     */
    private String insertEmployee(HttpServletResponse response, Employee currentEmployee) {
        HR_DB_Helper obB_Helper = new HR_DB_Helper();
        return obB_Helper.insert(currentEmployee);
    }
}
