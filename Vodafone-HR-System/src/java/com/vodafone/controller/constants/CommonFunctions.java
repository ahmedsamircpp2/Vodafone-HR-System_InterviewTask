/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vodafone.controller.constants;

import com.oreilly.servlet.MultipartRequest;
import com.vodafone.db.model.dto.Employee;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileUploadException;

/**
 * Contain Function Used by many Classes like addEmployee, updateEmplyee
 * @author ahmed_amer
 */
public class CommonFunctions {

    /**
     * Validate Submitted Employee Data 
     * @param request
     * @param employee
     * @return list Of Error Found
     */
    public List<String> validateEmployeeData(HttpServletRequest request, Employee employee) {
        String errorCode = "-100";
        String successCode = "200";
        List<String> errors = new ArrayList<String>();
        Pattern pattern = Pattern.compile("\\d{10,14}");
        if (employee.getEnumGender().isEmpty()) {
            errors.add(errorCode + ": Gender is Empty");
        }

        if (employee.getStrEmployeeFullname().isEmpty()) {
            errors.add(errorCode + ": Your Full Name is Empty");
        }
        if (employee.getStrMobile().isEmpty()) {
            errors.add(errorCode + ": Your Mobile is Empty");
        } else if (!pattern.matcher(employee.getStrMobile()).find()) //   if(request.getParameter(errorCode))
        {
            errors.add(errorCode + ": Bad Mobile  Format");
        }
        return errors;
    }

    /**
     * setting Employee Information from MultipartRequest object  into Employee Object
     * @param request
     * @return Submitted Employee Object
     * @throws FileUploadException 
     */
    public Employee getCurrentEmployee(MultipartRequest request) throws FileUploadException {
        Employee employee = new Employee();

        try {
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
    
    
}
