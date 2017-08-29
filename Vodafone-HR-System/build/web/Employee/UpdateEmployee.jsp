<%-- 
    Document   : UpdateEmployee
    Created on : Jul 9, 2012, 10:47:59 AM
    Author     : ahmed_amer
--%>

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
        <script src="<%= request.getContextPath()%>/js/jquery.js" type="text/javascript" language="javascript"></script>
        <script type="text/javascript" language="javascript">

            $(function () {

                var offsetX = 20;
                var offSetY = 10;

                $("a img").hover(function (e) {

                    var myhref = $(this).attr("src");
                    $('<img id="largeImg" src ="' + myhref + '"/>')
                    .css('top', e.pageY + offSetY)
                    .css('left', e.pageX + offsetX).appendTo('body');
                }, function () {
                    $('#largeImg').remove();

                })


                $("a").mousemove(function (e) {

                    $('#largeImg').css('top', e.pageY + offSetY).css('left', e.pageX + offsetX);

                })

            });
    
        </script>
        <style type="text/css">
            a img
            {
                border:none;
                width:100px;
                height:80px;
            }
            #largeImg
            {
                position:absolute;
                width:200px;
                height:200px;
                border:4px solid Gray;
            }

        </style>
    </head>
    <body>


        <!--   ********************************* Content Start*******************************    -->

        <%
            String queryString = request.getQueryString();
            String[] parameters = queryString.split("&");
            String employeeName = parameters[0].split("=")[1].replace("%20", " ");
            String mobile = parameters[1].split("=")[1];
            Integer departementID = Integer.parseInt(parameters[2].split("=")[1]);
            Integer managerID = Integer.parseInt(parameters[3].split("=")[1]);
            String ImageURl = parameters[4].split("=")[1].replace("%20", " ");
            String gender = parameters[5].split("=")[1];
            Integer idEmloyee = Integer.parseInt(parameters[6].split("=")[1]);
            String imageName = parameters[7].split("=")[1];
            HR_DB_Helper helper = new HR_DB_Helper();
            Set<Department> dept = helper.All_Departments();
            Set<Employee> allEmployees = helper.All_Employees();
        %>
        <jsp:include page="../CommonTemplates/Banner.jsp"   />
        <table align="center" style="width: 60%">
            <tr>
                <jsp:include   page="../CommonTemplates/MENU.jsp" />
                <td align="center" valign="bottom">
                    <form action="../UpdateEmployee" method="post"  enctype="multipart/form-data" >
                        <input type="hidden" name="idEmployee" value="<%= idEmloyee %>" />
                        <input type="hidden" name="imageName" value="<%= imageName %>" />
                        <table id="table-2">
                    <caption><h1 align="center" >  Update Employee </h1> </caption>
                            <tr> <td> Employee Name: </td>  <td> <input  name="empName" type="text" value="<%= employeeName%>" /> </td></tr>
                            <tr> <td> Mobile : </td>   <td> <input type="text" name="mobile" value="<%= mobile%>" /> </td> </tr>
                            <tr> 
                                <td> Department : </td>
                                <td>
                                    <select name="departmentID" id="departmentID"  >
                                        <%
                                            for (Iterator it = dept.iterator(); it.hasNext();) {
                                                Department department = (Department) it.next();
                                        %>
                                        <option  <%= departementID == department.getIdDepartement() ? "selected" : ""%>  value="<%= department.getIdDepartement()%>" > <%= department.getStrDepartementName()%> </option>
                                        <%
                                            }
                                        %>
                                    </select>
                                </td>
                            </tr>

                            <tr>
                                <td> Manager : </td>
                                <td>
                                    <select name="managerID">
                                        <%
                                            for (Iterator it = allEmployees.iterator(); it.hasNext();) {
                                                Employee employee = (Employee) it.next();
                                        %>
                                        <option <%=  managerID == employee.getIdManager() ? "selected" : ""%>  value="<%= employee.getIdEmployee()%>"> <%= employee.getStrEmployeeFullname()%> </option>
                                        <%
                                            }
                                        %>
                                    </select>
                                </td>
                            </tr>
                            <tr> <td> Image : </td>   
                                <td> 
                                    <input type="file" name="uploaded"   /> 
                                    <a href="" > 
                                        <img src="<%= ImageURl%>" 
                                             alt="NO IMAGE"  height="20" width="40" /> 
                                    </a>
                                </td>
                            </tr>
                            <tr> <td> Gender : </td>
                                <td>
                                    <select name="gender">
                                        <option  <%= gender .equalsIgnoreCase("female") ? "selected" : ""%> value="female"> Female  </option>
                                        <option  <%= gender.equalsIgnoreCase("male") ? "selected" : ""%> value="male">   male    </option>
                                    </select>
                                </td>
                            </tr>
                            <tr> <td> <input type="submit" value="Update" name="save" /></td> 
                                <td> <input type="reset" value="Reset" /></td> </tr>
                        </table>
                        <!--   *********************************Content Start*******************************      -->
                    </form>

                </td>
            </tr>
        </table>
    </body>
</html>
