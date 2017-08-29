<%-- 
    Document   : UpdateEmployee
    Created on : Jul 9, 2012, 10:47:59 AM
    Author     : ahmed_amer
--%>

<%@page import="java.util.StringTokenizer"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.vodafone.controller.constants.constants"%>
<%@page import="java.util.List"%>
<%@page import="com.vodafone.db.model.dto.Employee"%>
<%@page import="com.vodafone.db.model.dbutils.HR_DB_Helper"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<%@page import="com.vodafone.db.model.dto.Department"%>
<%@page import="com.vodafone.controller.beans.DepartmentBean"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<%= request.getContextPath()%>/css/tableStyle.css" /> 
        <title>ADD Employee</title>
    </head>
    <body> 

        <!--   *********************************Content Start*******************************      -->

        <%
            HR_DB_Helper helper = new HR_DB_Helper();
            Set<Department> dept = helper.All_Departments();
            Set<Employee> allEmployees = helper.All_Employees();
            List<String> errors = new ArrayList<String>();
            String queryString = request.getQueryString();
            if (queryString != null) {
                String[] strings = queryString.split("&");
                for (int i = 0; i < strings.length; i++) {
                    errors.add(strings[i].split("=")[1].replace("%20", ""));
                }
                for (int i = 0; i < errors.size()-1; i++) {
        %>
        <h3 style="color: red;height: 2px;"><%= errors.get(i)%> </h3>

        <%            }
            }
        %>
        <form action="../AddEmployee" method="post"  enctype="multipart/form-data"  >
            <table id="table-2">
                <tr> <td> Employee Name: </td>  <td> <input  name="empName" type="text" /> </td></tr>
                <tr> <td> Mobile : </td>   <td> <input type="text" name="mobile" /> </td> </tr>
                <tr> <td> Department : </td>   
                    <td> 
                        <select name="departmentID" id="departmentID"  >
                            <%
                                for (Iterator it = dept.iterator(); it.hasNext();) {
                                    Department department = (Department) it.next();
                            %>
                            <option  value="<%= department.getIdDepartement()%>" > <%= department.getStrDepartementName()%> </option>
                            <%
                                }
                            %>
                        </select>
                    </td>
                </tr>

                <tr> <td> Manager : </td>   
                    <td>
                        <select name="managerID">
                            <%
                                for (Iterator it = allEmployees.iterator(); it.hasNext();) {
                                    Employee employee = (Employee) it.next();
                            %>
                            <option value="<%= employee.getIdEmployee()%>"> <%= employee.getStrEmployeeFullname()%> </option>
                            <%
                                }
                            %>
                        </select>
                    </td>
                </tr>
                <tr> <td> Image : </td>   
                    <td> 
                        <input type="file" name="uploaded"  /> 
                    </td>
                </tr>

                <tr> <td> Gender : </td>   
                    <td>
                        <select name="gender">
                            <option value="female"> Female  </option>
                            <option value="male">   Male    </option>
                        </select>
                    </td>
                </tr>
                <tr> <td> <input type="submit" value="Add" name="save" /></td> 
                    <td> <input type="reset" value="Reset" /></td> </tr>
            </table>
            <!--   *********************************Content Start*******************************      -->
        </form>
    </body>
</html>
