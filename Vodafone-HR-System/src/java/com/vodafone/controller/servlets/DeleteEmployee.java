/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vodafone.controller.servlets;

import com.vodafone.db.model.dbutils.HR_DB_Helper;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ahmed_amer
 */
public class DeleteEmployee extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            HR_DB_Helper obB_Helper = new HR_DB_Helper();
            System.out.println("------------------------Deleteing Employee--------------------");
            /**
             * get Employee ID  From Query String  
             */
            String querySyring = request.getQueryString();
            String[] queryStringParts = querySyring.split("&");
            Integer employeeId = Integer.parseInt(queryStringParts[0].split("=")[1]);
            String imgName = "";
            try {
                imgName = queryStringParts[1].split("=")[1];

            } catch (Exception e) {
            }
            
            /**
             * If Employee Deleted Successfully  delete it's Image
             */
            if (obB_Helper.delete(employeeId)) {
                if (!imgName.isEmpty()) {
                    deleteImage(imgName, request);
                }
            }

            //request.getRequestDispatcher(request.getContextPath()+"/Employee/All_Employee_Template.jsp").forward(request, response);
            response.sendRedirect(request.getContextPath() + "/Employee/All_Employee_Template.jsp");
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
        processRequest(request, response);
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
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void deleteImage(String imgName, HttpServletRequest request) {
        String imagesPath = getDirctoryPath("images\\upload\\", request);
        File dirToMove = new File(imagesPath + "\\" + imgName);
        dirToMove.delete();
    }

    public String getDirctoryPath(String string, HttpServletRequest request) {
        String path = getServletContext().getRealPath("/");
        String newpath = "";
        newpath += path.substring(0, path.length() - 11);
        newpath += "\\web\\" + string;
        return newpath;
    }
}
